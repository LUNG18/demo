package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.puzhong.admin.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_permission")
public class SysPermission extends BaseEntity {
    private Long menuId;
    private String name;
    private String description;
    private Integer status;
    private Integer type;
    private String url;
}
