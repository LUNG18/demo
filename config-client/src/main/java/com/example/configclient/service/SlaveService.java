package com.example.configclient.service;

import com.example.configclient.Enums.DataSourceEnum;
import com.example.configclient.interfaces.DataSource;
import com.example.configclient.pojo.TestPojo;

@DataSource(DataSourceEnum.Slave1)
public interface SlaveService {

    public TestPojo findByName(String name);
}
