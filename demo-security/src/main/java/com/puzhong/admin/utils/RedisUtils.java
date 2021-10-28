package com.puzhong.admin.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisUtils {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    /**
     * 判断缓存是否存在
     */
    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 删除对象
     */
    public void delete(String key) {
        boolean hasKey = stringRedisTemplate.hasKey(key);
        if (hasKey) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     */
    public <T> void setObject(String key, T value) {
        ValueOperations<String, String> operation = stringRedisTemplate.opsForValue();
        operation.set(key, toJson(value));
    }

    /**
     * 获得缓存的基本对象。
     */
    public <T> T getObject(String key) {
        ValueOperations<String, String> operation = stringRedisTemplate.opsForValue();
        return toObj(operation.get(key));
    }

    /**
     * 缓存List数据
     */
    public <T> void resetList(String key, List<T> dataList) {
        if (this.hasKey(key)) {
            this.delete(key);
        }
        setList(key, dataList);
    }

    /**
     * 缓存List数据
     */
    public <T> void setList(String key, List<T> dataList) {
        ListOperations listOperation = stringRedisTemplate.opsForList();
        if (null != dataList) {
            for (T t : dataList) {
                listOperation.rightPush(key, t);
            }
        }
    }

    /**
     * 获得缓存的list对象
     */
    public <T> List<T> getList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, String> listOperation = stringRedisTemplate.opsForList();
        long size = listOperation.size(key);
        for (int i = 0; i < size; i++) {
            dataList.add(toObj(listOperation.leftPop(key)));
        }
        return dataList;
    }

    /**
     * 缓存Set
     */
    public <T> void setSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, String> setOperation = stringRedisTemplate.boundSetOps(key);
        for (T t : dataSet) {
            setOperation.add(toJson(t));
        }
    }

    /**
     * 获得缓存的set
     */
    public <T> Set<T> getSet(String key) {
        Set<T> dataSet = new HashSet<>();
        BoundSetOperations<String, String> operation = stringRedisTemplate.boundSetOps(key);
        Long size = operation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(toObj(operation.pop()));
        }
        return dataSet;
    }

    /**
     * 缓存Map
     */
    public <T> void resetMap(String key, Map<String, T> dataMap) {
        if (hasKey(key)) {
            delete(key);
        }
        setMap(key, dataMap);
    }

    /**
     * 缓存Map
     */
    public <T> void setMap(String key, Map<String, T> dataMap) {
        if (null != dataMap) {
            HashOperations hashOperations = stringRedisTemplate.opsForHash();
            for (String mapKey : dataMap.keySet()) {
                hashOperations.put(key, mapKey, toJson(dataMap.get(mapKey)));
            }
        }
    }

    /**
     * 缓存Map
     */
    public <T> void setMap(String key, String mapKey, String mapValue) {
        HashOperations hashOperations = stringRedisTemplate.opsForHash();
        if (StringUtils.isNotBlank(mapKey)) {
            hashOperations.put(key, mapKey, mapValue);
        }
    }

    public Map<Object, Object> getObjectMap(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 获得缓存的Map
     */
    public <T> Map<String, T> getMap(String key) {
        Map<String, T> ret = new HashMap<>();
        Map<Object, Object> map = getObjectMap(key);
        map.forEach((k, v) -> {
            if (k instanceof String) {
                ret.put((String) k, toObj(toJson(v)));
            }
        });
        return ret;
    }

    /**
     * 获得缓存的Map
     */
    public <T> T getMapValue(String key, String mapKey) {
        return (T) stringRedisTemplate.opsForHash().entries(key).get(mapKey);
    }

    /**
     * 删除对象
     */
    public void deleteMap(String key, String mapKey) {
        HashOperations hashOperations = stringRedisTemplate.opsForHash();
        if (hashOperations.hasKey(key, mapKey)) {
            hashOperations.delete(key, mapKey);
        }
    }

    /**
     * 缓存Map
     */
    public <T> HashOperations<String, Integer, T> setIntegerMap(String key, Map<Integer, T> dataMap) {
        HashOperations hashOperations = stringRedisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     */
    public <T> Map<Integer, T> getIntegerMap(String key) {
        Map<Integer, T> ret = new HashMap<>();
        Map<Object, Object> map = getObjectMap(key);
        map.forEach((k, v) -> {
            if (k instanceof Integer) {
                ret.put((Integer) k, toObj(toJson(v)));
            }
        });
        return ret;
    }

//    /**
//     * 从Redis中获取对象
//     */
//    public Object getObject(String key) throws Exception {
//        Object object = null;
//        try {
//            String serializeObj = get(key);
//            if (null == serializeObj || serializeObj.length() == 0) {
//                object = null;
//            } else {
//                object = SerializeUtil.deserialize(serializeObj);
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        return object;
//    }
//
//    /**
//     * 保存对象到Redis 对象不过期
//     */
//    public boolean setObject(String key, Object object) throws Exception {
//        return setObject(key, object, -1);
//    }
//
//    /**
//     * 保存对象到Redis 并设置超时时间
//     */
//    public boolean setObject(String key, Object object, int timeout) throws Exception {
//        String value = SerializeUtil.serialize(object);
//        boolean result = false;
//        try {
//            //为-1时不设置超时时间
//            if (timeout != -1) {
//                set(key, value, timeout);
//            } else {
//                set(key, value);
//            }
//            result = false;
//        } catch (Exception e) {
//            throw e;
//        }
//        return result;
//    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = (String) stringRedisTemplate.opsForValue().get(key);
        if (expire != NOT_EXPIRE) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public void set(String key, Object value, long expire) {
        stringRedisTemplate.opsForValue().set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object obj) {
        if (obj instanceof Integer || obj instanceof Long || obj instanceof Float ||
                obj instanceof Double || obj instanceof Boolean || obj instanceof String) {
            return String.valueOf(obj);
        }
        return JSON.toJSONString(obj);
    }

    private <T> T toObj(String value) {
        return (T) JSON.parse(value);
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

}
