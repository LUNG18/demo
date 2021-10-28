package com.puzhong.admin.base.controller;

import com.puzhong.admin.base.param.BaseQuery;
import com.puzhong.admin.base.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class CommonControllerImpl<S extends CommonService<T, Q>, T, Q extends BaseQuery<T>> implements CommonController<T, Q> {

    @Autowired
    protected S baseService;

    @Override
    public CommonService<T, Q> getBaseService() {
        return baseService;
    }

}
