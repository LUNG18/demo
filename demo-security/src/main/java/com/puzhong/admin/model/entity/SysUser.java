package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_user")
public class SysUser implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Integer status;

}
