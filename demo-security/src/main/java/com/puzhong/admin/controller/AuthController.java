package com.puzhong.admin.controller;

import com.puzhong.admin.model.vo.MenuVo;
import com.puzhong.admin.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    MenuService menuService;

    @RequestMapping("/admin/menu/all/list")
    public List<MenuVo> adminMenuAllList() {
        return menuService.getAdminMenuAllList();
    }

    @RequestMapping("/admin/menu/list")
    public List<MenuVo> adminMenuList(Long userId) {
        return menuService.getAdminMenuList(userId);
    }
}
