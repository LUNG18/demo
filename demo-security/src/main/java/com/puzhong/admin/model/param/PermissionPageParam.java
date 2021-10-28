package com.puzhong.admin.model.param;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.puzhong.admin.base.param.BaseQuery;
import com.puzhong.admin.model.entity.SysPermission;
import com.puzhong.admin.model.entity.SysPermissionConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionPageParam extends BaseQuery<SysPermission> {

    private Integer type;
    private Integer status;

    @Override
    public void wrapQuery(LambdaQueryWrapper<SysPermission> wrapper) {
        wrapper.eq(this.type != null, SysPermission::getType, type);
        wrapper.eq(this.status != null, SysPermission::getStatus, status);
    }

}
