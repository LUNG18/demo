package com.puzhong.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puzhong.admin.model.entity.SysPermission;

import java.util.List;

public interface PermissionService extends IService<SysPermission> {

    List<SysPermission> getPermissionByUserId(Long userId);

}
