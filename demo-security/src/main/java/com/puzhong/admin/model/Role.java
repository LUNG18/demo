package com.puzhong.admin.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Long id;
    private Long privatge9;
    private String name;
    private String enname;
}
