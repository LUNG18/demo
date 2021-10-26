package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_role")
public class SysRole implements Serializable {
    private Long id;
    private Long privatge9;
    private String name;
    private String enname;
}
