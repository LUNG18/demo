package com.puzhong.admin.service.impl;

import com.puzhong.admin.base.service.CommonServiceImpl;
import com.puzhong.admin.mapper.PermissionConfigMapper;
import com.puzhong.admin.model.entity.SysPermissionConfig;
import com.puzhong.admin.model.param.PermissionConfigPageParam;
import com.puzhong.admin.service.PermissionConfigService;
import org.springframework.stereotype.Service;

@Service
public class PermissionConfigServiceImpl extends CommonServiceImpl<PermissionConfigMapper, SysPermissionConfig, PermissionConfigPageParam> implements PermissionConfigService {

}
