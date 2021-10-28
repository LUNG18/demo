package com.puzhong.admin.base.controller;

import com.puzhong.admin.base.param.BaseQuery;
import com.puzhong.admin.base.service.CommonService;
import com.puzhong.admin.base.vo.PageData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface CommonController<T, Q extends BaseQuery<T>> {

    CommonService<T, Q> getBaseService();

    @RequestMapping(value = "/page")
    default PageData<T> page(Q query) {
        return this.getBaseService().page(query);
    }

    @RequestMapping(value = "/list")
    default List<T> list(Q query) {
        return this.getBaseService().list(query);
    }

    @RequestMapping(value = "/show/{id}")
    default T show(Serializable id) {
        return this.getBaseService().getById(id);
    }

    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    default boolean insert(T entity) {
        return this.getBaseService().save(entity);
    }

    @RequestMapping(value = "/updateById", consumes = MediaType.APPLICATION_JSON_VALUE)
    default boolean updateById(T entity) {
        return this.getBaseService().updateById(entity);
    }

    @RequestMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    default boolean delete(Collection<? extends Serializable> idList) {
        return this.getBaseService().removeByIds(idList);
    }

}
