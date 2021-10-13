package com.puzhong.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puzhong.admin.model.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectByUserId(Long userId);

}
