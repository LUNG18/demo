package com.puzhong.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puzhong.admin.model.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService extends IService<SysUser> {

    UserDetails getAuthUserByUsername(String username);

}
