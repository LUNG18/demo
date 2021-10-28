package com.puzhong.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puzhong.admin.mapper.MenuMapper;
import com.puzhong.admin.model.entity.SysMenu;
import com.puzhong.admin.model.vo.MenuVo;
import com.puzhong.admin.service.MenuService;
import com.puzhong.admin.utils.BeanUtils;
import com.puzhong.admin.utils.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuVo> getAdminMenuAllList() {
        List<SysMenu> menuList = menuMapper.selectAll();
        return this.buildMenuList(menuList);
    }

    @Override
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
