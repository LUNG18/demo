package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.puzhong.admin.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_role")
public class SysRole extends BaseEntity {
    private Long parentId;
    private String name;
    private String enname;
}
