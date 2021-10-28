package com.puzhong.admin.service.impl;

import com.puzhong.admin.base.service.CommonServiceImpl;
import com.puzhong.admin.mapper.PermissionMapper;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.model.param.PermissionPageParam;
import com.puzhong.admin.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl extends CommonServiceImpl<PermissionMapper, SysPermission, PermissionPageParam> implements PermissionService {

    @Override
    public List<SysPermission> getPermissionByUserId(Long userId) {
        return baseDao.selectPermissionByUserId(userId);
    }
}
