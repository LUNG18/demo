package com.example.configclient.controller;

import com.example.configclient.service.MasterService;
import com.example.configclient.service.SlaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
@Slf4j
public class MyRestController {


    @Resource
    private MasterService masterService;
    @Resource
    private SlaveService slaveService;

    @RequestMapping("test")
    public String test(String name){
        return masterService.findByName(name).toString();
    }

    @RequestMapping("test2")
    public String test2(String name){
        return slaveService.findByName(name).toString();
    }


    @Value("${murl}")
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
    }

}
