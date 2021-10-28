package com.puzhong.admin.controller;

import com.puzhong.admin.base.controller.CommonControllerImpl;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.model.param.PermissionPageParam;
import com.puzhong.admin.service.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController extends CommonControllerImpl<PermissionService, SysPermission, PermissionPageParam> {

}
