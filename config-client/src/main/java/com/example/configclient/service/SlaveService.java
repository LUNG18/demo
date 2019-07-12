package com.example.configclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.configclient.Enums.DataSourceEnum;
import com.example.configclient.interfaces.DataSource;
import com.example.configclient.pojo.UserPojo;

import java.util.List;

@DataSource(DataSourceEnum.Slave1)
public interface SlaveService {

    Page<UserPojo> list(Page<UserPojo> page,String name);
}
