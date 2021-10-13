package com.puzhong.admin.security.service;

import com.puzhong.admin.mapper.PermissionMapper;
import com.puzhong.admin.mapper.UserMapper;
import com.puzhong.admin.model.Permission;
import com.puzhong.admin.model.User;
import com.puzhong.admin.model.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private PermissionMapper permissionMapper;

    UserDetails getAuthUserByUsername(String username) {
        try {
            User user = userMapper.selectByUsername(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (user != null) {
                List<Permission> permissions = permissionMapper.selectByUserId(user.getId());
                authorities = permissions.stream()
                        .filter(item -> item != null && StringUtils.hasText(item.getEnname()))
                        .map(item -> new SimpleGrantedAuthority(item.getEnname()))
                        .collect(Collectors.toList());
            }

            return new AuthUser(user.getUsername(), user.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户不存在");
        }
    }


}
