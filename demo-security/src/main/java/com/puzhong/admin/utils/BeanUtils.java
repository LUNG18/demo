package com.puzhong.admin.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class BeanUtils {

    public static <T> T sourceToTarget(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.getDeclaredConstructor().newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            log.error("convert error ", e);
        }

        return targetObject;
    }

    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target) {
        if (sourceList == null) {
            return null;
        }

        List<T> targetList = new ArrayList<>(sourceList.size());

        try {
            for (Object source : sourceList) {
                T targetObject = target.getDeclaredConstructor().newInstance();
                org.springframework.beans.BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        } catch (Exception e) {
            log.error("convert error ", e);
        }

        return targetList;
    }

}
