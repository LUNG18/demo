package com.puzhong.admin.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puzhong.admin.base.param.BaseQuery;
import com.puzhong.admin.base.vo.BaseVo;
import com.puzhong.admin.base.vo.PageData;

import java.io.Serializable;
import java.util.List;

public interface CommonService<T, E extends BaseQuery<T>> extends IService<T> {

    PageData<T> page(E page);

    PageData<T> page(E page, Wrapper<T> queryWrapper);

    List<T> list(E param);
}
