package com.puzhong.admin.base.param;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * base类的易封装性
 */
@Data
public class BaseQuery<E> {

    /**
     * 分页
     */
    private Integer page = 1;

    /**
     * 每页个数
     */
    private Integer size = 10;

    /**
     * 排序
     */
    private String order = "ASC";

    /**
     * 分类（多个用）
     */
    private String sort;

    @JsonIgnore
    public Page<E> getPagination() {
        Page<E> resultPage = new Page<>();
        resultPage.setCurrent(page);
        resultPage.setSize(size);
        if (org.springframework.util.StringUtils.hasText(sort)) {
            //进入排序
            String[] orders = order.split("&");
            String[] sorts = sort.split("&");
            for (int i = 0; i < sorts.length; i++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(StringUtils.camelToUnderline(sorts[i]));
                orderItem.setAsc("ASC".equalsIgnoreCase(i < orders.length ? orders[i] : orders[0]));
                resultPage.addOrder(orderItem);
            }
        }
        return resultPage;
    }

    /**
     * 对wrapper做排序
     */
    @JsonIgnore
    protected void wrapOrder(QueryWrapper<E> queryWrapper) {
        if (StringUtils.isNotBlank(sort)) {
            //进入排序
            String[] orders = order.split("&");
            String[] sorts = sort.split("&");
            for (int i = 0; i < sorts.length; i++) {
                String sortField = StringUtils.camelToUnderline(sorts[i]);
                queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(i < orders.length ? orders[i] : orders[0]), sortField);
            }
        }
    }

    @JsonIgnore
    protected void wrapQuery(LambdaQueryWrapper<E> queryWrapper) {
    }

    @JsonIgnore
    public LambdaQueryWrapper<E> getWrapper() {
        LambdaQueryWrapper<E> queryWrapper = new LambdaQueryWrapper<>();

        wrapQuery(queryWrapper);
        return queryWrapper;
    }

}
