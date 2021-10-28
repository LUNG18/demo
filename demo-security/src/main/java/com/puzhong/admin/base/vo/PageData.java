package com.puzhong.admin.base.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 */
@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int total;

    private List<T> records;

    /**
     * 分页
     *
     * @param records 列表数据
     * @param total   总记录数
     */
    public PageData(List<T> records, long total) {
        this.records = records;
        this.total = (int) total;
    }

    /**
     * 分页
     *
     * @param records 列表数据
     * @param total   总记录数
     */
    public static <T> PageData<T> build(List<T> records, long total) {
        return new PageData<>(records, total);
    }


    /**
     * 分页
     *
     * @param page 分页对象
     */
    public static <T> PageData<T> build(IPage<T> page) {
        return build(page.getRecords(), page.getTotal());
    }

}
