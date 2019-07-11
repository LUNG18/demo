package com.example.configclient.service;

import com.example.configclient.dao.TestDao;
import com.example.configclient.pojo.TestPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j

public class SlaveServiceImpl implements SlaveService{

    @Resource
    private TestDao testDao;


    @Override
    public TestPojo findByName(String name){
        return testDao.findByName(name);
    }

}
