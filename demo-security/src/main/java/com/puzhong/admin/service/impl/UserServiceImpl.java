package com.puzhong.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puzhong.admin.mapper.MenuMapper;
import com.puzhong.admin.mapper.UserMapper;
import com.puzhong.admin.model.entity.AuthUser;
import com.puzhong.admin.model.entity.SysMenu;
import com.puzhong.admin.model.entity.SysUser;
import com.puzhong.admin.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails getAuthUserByUsername(String username) {
        SysUser sysUser = userMapper.selectByUsername(username);
        if (sysUser != null) {
            List<SysMenu> menus = menuMapper.selectByUserId(sysUser.getId());
            List<GrantedAuthority> authorities = menus.stream()
                    .filter(item -> item != null && StringUtils.hasText(item.getEnname()))
                    .map(item -> new SimpleGrantedAuthority(item.getEnname()))
                    .collect(Collectors.toList());
            return new AuthUser(sysUser, authorities);
        }
        return null;
    }
}
