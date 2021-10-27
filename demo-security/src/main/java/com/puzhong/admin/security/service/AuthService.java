package com.puzhong.admin.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.puzhong.admin.mapper.MenuMapper;
import com.puzhong.admin.mapper.PermissionMapper;
import com.puzhong.admin.mapper.UserMapper;
import com.puzhong.admin.model.entity.AuthUser;
import com.puzhong.admin.model.entity.SysMenu;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.model.entity.SysUser;
import com.puzhong.admin.model.vo.MenuVo;
import com.puzhong.admin.utils.BeanUtils;
import com.puzhong.admin.utils.TreeUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private PermissionMapper permissionMapper;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUser) {
            Long userId = ((AuthUser) principal).getId();
            List<String> permissions = permissionMapper.selectPermissionByUserId(userId);
            return permissions.stream().anyMatch(
                    url -> pathMatcher.match(url, request.getRequestURI())
            );
        }
        return false;
    }

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

    public String[] getAllPermitUrl() {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysPermission::getUrl);
        wrapper.eq(SysPermission::getStatus, 1);
        wrapper.eq(SysPermission::getType, 0);
        List<SysPermission> sysPermissionList = permissionMapper.selectList(wrapper);
        return sysPermissionList.stream().map(SysPermission::getUrl).toArray(String[]::new);
    }

    public List<MenuVo> getAdminMenuAllList() {
        List<SysMenu> menuList = menuMapper.selectAll();
        return this.buildMenuList(menuList);
    }

    public List<MenuVo> getAdminMenuList(Long userId) {
        List<SysMenu> menuList = menuMapper.selectByUserId(userId);
        return this.buildMenuList(menuList);
    }

    private List<MenuVo> buildMenuList(List<SysMenu> menuList) {
        List<MenuVo> voList = menuList.stream()
                .map(item -> BeanUtils.sourceToTarget(item, MenuVo.class))
                .collect(Collectors.toList());
        return TreeUtils.build(voList);
    }

}
