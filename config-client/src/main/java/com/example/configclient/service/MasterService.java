package com.example.configclient.service;

import com.example.configclient.Enums.DataSourceEnum;
import com.example.configclient.interfaces.DataSource;
import com.example.configclient.pojo.UserPojo;

@DataSource(DataSourceEnum.Master)
public interface MasterService {

    void addUser(UserPojo pojo);
}
