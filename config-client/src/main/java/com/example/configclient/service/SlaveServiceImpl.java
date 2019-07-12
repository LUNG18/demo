package com.example.configclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public Page<UserPojo> list(Page<UserPojo> page,String name){
//        List<UserPojo> userPojos = userDao.selectList(page, name);
        return page.setRecords(userDao.selectList(page,name));
    }

}
