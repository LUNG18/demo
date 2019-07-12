package com.example.configclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.configclient.basic.BaseController;
import com.example.configclient.pojo.UserPojo;
import com.example.configclient.service.MasterService;
import com.example.configclient.service.SlaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
@Slf4j
public class MyRestController extends BaseController {


    @Resource
    private MasterService masterService;
    @Resource
    private SlaveService slaveService;

    @RequestMapping("add")
    public String test(UserPojo pojo){
        masterService.addUser(pojo);
        return "over";
    }

    @RequestMapping("get")
    public Page<UserPojo> test2(String name){
        Page page = defaultPage();
        return slaveService.list(page,name);
    }


    /*@Value("${murl}")
    private String url;
    @Value("${musername}")
    private String username;
    @Value("${mpassword}")
    private String password;
    @Value("${mdriver}")
    private String driver;

    @RequestMapping("/database")
    public String database() {
        return url+" "+username+" "+password+" "+driver;
    }*/

}
