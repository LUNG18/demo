package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_permission")
public class SysPermission implements Serializable {
    private Long id;
    private Long menuId;
    private String name;
    private Integer status;
    private Integer type;
    private String url;
}
