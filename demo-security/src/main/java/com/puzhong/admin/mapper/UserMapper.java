package com.puzhong.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puzhong.admin.model.entity.SysUser;

public interface UserMapper extends BaseMapper<SysUser> {

    SysUser selectByUsername(String username);

}

