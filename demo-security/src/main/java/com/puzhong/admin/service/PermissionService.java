package com.puzhong.admin.service;

import com.puzhong.admin.base.service.CommonService;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.model.param.PermissionPageParam;

import java.util.List;

public interface PermissionService extends CommonService<SysPermission, PermissionPageParam> {

    List<SysPermission> getPermissionByUserId(Long userId);

}
