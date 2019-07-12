package com.example.configclient.service;

import com.example.configclient.dao.UserDao;
import com.example.configclient.pojo.UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j

public class MasterServiceImpl implements MasterService{

    @Resource
    private UserDao userDao;


    @Override
    public void addUser(UserPojo pojo){
         userDao.insert(pojo);
    }

}
