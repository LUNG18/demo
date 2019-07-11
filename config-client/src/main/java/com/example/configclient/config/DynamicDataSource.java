package com.example.configclient.config;

import com.example.configclient.Enums.DataSourceEnum;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static DynamicDataSource instance;
    private static byte[] lock = new byte[0];
    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();
    }

    public static Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dbKey = DatabaseContextHolder.getDBKey();
        if(StringUtils.isEmpty(dbKey)){
            dbKey = DataSourceEnum.Master.getValue();
        }
        return dbKey;
    }

    private DynamicDataSource(){}

    public static synchronized DynamicDataSource getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new DynamicDataSource();
                }
            }
        }
        return instance;
    }
}
