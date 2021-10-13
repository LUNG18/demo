package com.puzhong.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puzhong.admin.model.User;

public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

}

