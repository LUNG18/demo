package com.puzhong.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.puzhong.admin.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("tb_user")
public class SysUser extends BaseEntity {
    private String username;
    private String password;
    private Integer status;

}
