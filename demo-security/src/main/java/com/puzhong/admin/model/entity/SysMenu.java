package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("tb_menu")
public class SysMenu implements Serializable {
    private Long id;
    private Long parentId;
    private String name;
    private String enname;
    private Integer status;
    private Integer type;
    private String url;
}
