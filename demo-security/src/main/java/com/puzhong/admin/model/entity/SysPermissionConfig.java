package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.puzhong.admin.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_permission_config")
public class SysPermissionConfig  extends BaseEntity {
    private String name;
    private Integer status;
    private String type;
}
