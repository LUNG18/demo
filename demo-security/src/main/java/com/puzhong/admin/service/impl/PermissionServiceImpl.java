package com.puzhong.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puzhong.admin.mapper.PermissionMapper;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, SysPermission> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<SysPermission> getPermissionByUserId(Long userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }
}
