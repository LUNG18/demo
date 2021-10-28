package com.puzhong.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puzhong.admin.model.entity.SysMenu;
import com.puzhong.admin.model.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<SysMenu> {

    List<MenuVo> getAdminMenuAllList();

    List<MenuVo> getAdminMenuList(Long userId);

}
