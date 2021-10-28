package com.puzhong.admin.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puzhong.admin.base.param.BaseQuery;
import com.puzhong.admin.base.vo.PageData;
import com.puzhong.admin.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommonServiceImpl<M extends BaseMapper<T>, T, E extends BaseQuery<T>> extends ServiceImpl<BaseMapper<T>, T> implements CommonService<T, E> {

    @Autowired
    protected M baseDao;

    @Autowired(required = false)
    protected RedisUtils redisUtils;

    @Override
    public PageData<T> page(E page) {
        return this.page(page, page.getWrapper());
    }

    @Override
    public PageData<T> page(E page, Wrapper<T> queryWrapper) {
        IPage<T> param = page.getPagination();
        IPage<T> data = this.getBaseMapper().selectPage(param, queryWrapper);
        return PageData.build(data);
    }

    @Override
    public List<T> list(E query) {
        return this.getBaseMapper().selectList(query.getWrapper());
    }


}
