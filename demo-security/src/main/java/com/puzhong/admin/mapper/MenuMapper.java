package com.puzhong.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puzhong.admin.model.entity.SysMenu;

import java.util.List;

public interface MenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectAll();

    List<SysMenu> selectByUserId(Long userId);

}
