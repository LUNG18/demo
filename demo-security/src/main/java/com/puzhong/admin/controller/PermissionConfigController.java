package com.puzhong.admin.controller;

import com.puzhong.admin.base.controller.CommonControllerImpl;
import com.puzhong.admin.model.entity.SysPermissionConfig;
import com.puzhong.admin.model.param.PermissionConfigPageParam;
import com.puzhong.admin.service.PermissionConfigService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission/config")
public class PermissionConfigController extends CommonControllerImpl<PermissionConfigService, SysPermissionConfig, PermissionConfigPageParam> {

}
