package com.puzhong.admin.security.service;

import com.puzhong.admin.base.enums.PermissionConfigEnum;
import com.puzhong.admin.model.entity.AuthUser;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.service.PermissionService;
import com.puzhong.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class AuthService extends AuthInitService {

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        if (!PermissionConfigEnum.Type.openPermission(permissionMap)) {
            return true;
        }

        String uri = request.getRequestURI();
        boolean ret = permitUriList.stream().anyMatch(url -> pathMatcher.match(url, uri));
        if (!ret) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof AuthUser) {
                Long userId = ((AuthUser) principal).getId();
                List<SysPermission> permissions = permissionService.getPermissionByUserId(userId);
                return permissions.stream().anyMatch(item -> pathMatcher.match(item.getUrl(), uri));
            }
        }
        return ret;
    }

    public UserDetails getAuthUserByUsername(String username) {
        return userService.getAuthUserByUsername(username);
    }

}
