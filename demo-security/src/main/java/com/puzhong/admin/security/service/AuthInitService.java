package com.puzhong.admin.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.model.entity.SysPermissionConfig;
import com.puzhong.admin.service.PermissionConfigService;
import com.puzhong.admin.service.PermissionService;
import com.puzhong.admin.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.puzhong.admin.base.constant.ConstantKey.AUTH_ADMIN_PERMISSION_CONFIG;
import static com.puzhong.admin.base.constant.ConstantKey.AUTH_ADMIN_PERMISSION_URL;

@Slf4j
public class AuthInitService {

    @Resource
    protected RedisUtils redisUtils;
    @Resource
    private PermissionService permissionService;
    @Resource
    private PermissionConfigService permissionConfigService;

    protected final AntPathMatcher pathMatcher = new AntPathMatcher();

    protected List<String> permitUriList = new ArrayList<>();

    protected Map<String, Integer> permissionMap = new HashMap<>();

    @PostConstruct
    private void init() {
        this.initPermissionConfig();
        this.initPermitUriList();
    }

    public void refresh() {
        init();
    }

    private void initPermitUriList() {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysPermission::getUrl);
        wrapper
                .eq(SysPermission::getStatus, 1)
                .eq(SysPermission::getType, 0);

        permitUriList = permissionService.list(wrapper).stream().map(SysPermission::getUrl).collect(Collectors.toList());
        redisUtils.resetList(AUTH_ADMIN_PERMISSION_URL, permitUriList);
        log.info("放行路径 {}", permitUriList);
    }

    private void initPermissionConfig() {
        LambdaQueryWrapper<SysPermissionConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysPermissionConfig::getType, SysPermissionConfig::getStatus);
        List<SysPermissionConfig> list = permissionConfigService.list(wrapper);
        permissionMap = list.stream().collect(Collectors.toMap(SysPermissionConfig::getType, SysPermissionConfig::getStatus));
        redisUtils.resetMap(AUTH_ADMIN_PERMISSION_CONFIG, permissionMap);
        log.info("权限配置 {}", permissionMap);
    }
}
