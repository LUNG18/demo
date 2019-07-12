package com.example.configclient.service;

import com.example.configclient.dao.UserDao;
import com.example.configclient.pojo.UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j

public class SlaveServiceImpl implements SlaveService{

    @Resource
    private UserDao userDao;


    @Override
    public List<UserPojo> list(String name){
        return userDao.selectList(name);
    }

}
