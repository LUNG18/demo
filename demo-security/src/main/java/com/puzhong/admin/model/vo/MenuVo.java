package com.puzhong.admin.model.vo;

import com.puzhong.admin.utils.TreeUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class MenuVo extends TreeUtils.TreeNode<MenuVo> {
    private String name;
    private String enname;
    private String url;
}
