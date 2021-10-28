package com.puzhong.admin.model.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class PermissionSaveParam implements Serializable {
    private Long menuId;
    @NotEmpty(message = "权限名称不能为空")
    private String name;
    private String description;
    @Pattern(regexp = "//.*/", message = "权限路径不能为空 切 必须以'/'开头")
    private String url;
    private Integer status;
    private Integer type;
}
