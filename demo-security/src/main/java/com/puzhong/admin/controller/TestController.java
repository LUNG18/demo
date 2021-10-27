package com.puzhong.admin.controller;

import com.puzhong.admin.utils.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private RedisUtils redisUtils;

    @RequestMapping("/permit/uri")
    public List<String> permitUri(){
        return redisUtils.getList("123");
    }
}
