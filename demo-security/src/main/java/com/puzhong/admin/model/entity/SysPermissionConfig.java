package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_permission_config")
public class SysPermissionConfig implements Serializable {
    private Long id;
    private String name;
    private Integer status;
    private String type;
}
