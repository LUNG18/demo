package com.puzhong.admin.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionVo implements Serializable {
    private Long id;
    private Long menuId;
    private String name;
    private String url;
}
