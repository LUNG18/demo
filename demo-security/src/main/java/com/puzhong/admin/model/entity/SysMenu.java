package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.puzhong.admin.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_menu")
public class SysMenu extends BaseEntity {
    private Long parentId;
    private String name;
    private String enname;
    private Integer status;
    private Integer type;
    private String url;
}
