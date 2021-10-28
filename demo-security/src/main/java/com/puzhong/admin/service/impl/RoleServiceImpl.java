package com.puzhong.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puzhong.admin.mapper.RoleMapper;
import com.puzhong.admin.model.entity.SysRole;
import com.puzhong.admin.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {

}
