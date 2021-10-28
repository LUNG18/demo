package com.puzhong.admin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 公共字段，自动填充值
 */
public class FieldMetaObjectHandler implements MetaObjectHandler {

    public static String CREATED_TIME = "createdAt";
    public static String UPDATED_TIME = "updatedAt";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, CREATED_TIME, LocalDateTime.class, now);
        this.strictInsertFill(metaObject, UPDATED_TIME, LocalDateTime.class, now);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictUpdateFill(metaObject, UPDATED_TIME, LocalDateTime.class, now);
    }
}
