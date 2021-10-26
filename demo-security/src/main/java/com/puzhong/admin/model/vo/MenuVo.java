package com.puzhong.admin.model.vo;

import com.puzhong.admin.utils.TreeUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class MenuVo extends TreeUtils.TreeNode<MenuVo> {
    private Long id;
    private Long parentId;
    private String name;
    private String enname;
    private String url;

    @Override
    protected Long getMenuId() {
        return this.id;
    }

    @Override
    protected Long getMenuPid() {
        return this.parentId;
    }
}
