package com.puzhong.admin.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class Permission implements Serializable {
    private Long id;
    private Long parentId;
    private String name;
    private String enname;
}
