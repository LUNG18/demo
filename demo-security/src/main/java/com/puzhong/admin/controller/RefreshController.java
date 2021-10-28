package com.puzhong.admin.controller;

import com.puzhong.admin.security.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/refresh")
public class RefreshController {

    @Resource
    private AuthService authService;

    @RequestMapping("/auth")
    public void auth(){
        authService.refresh();
    }

}
