package com.example.configclient.service;

import com.example.configclient.Enums.DataSourceEnum;
import com.example.configclient.interfaces.DataSource;
import com.example.configclient.pojo.UserPojo;

import java.util.List;

@DataSource(DataSourceEnum.Slave1)
public interface SlaveService {

    List<UserPojo> list(String name);
}
