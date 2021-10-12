package com.puzhong.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/t")
    public String t(){
        return "我是说";
    }

    @RequestMapping("/t2")
    public String t2(){
        return "222222";
    }
}
