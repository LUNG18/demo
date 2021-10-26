package com.puzhong.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puzhong.admin.model.entity.SysPermission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<SysPermission> {

    List<String> selectPermissionByUserId(Long userId);

}
