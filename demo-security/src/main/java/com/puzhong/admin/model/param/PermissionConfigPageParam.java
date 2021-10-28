package com.puzhong.admin.model.param;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.puzhong.admin.base.enums.PermissionConfigEnum;
import com.puzhong.admin.base.param.BaseQuery;
import com.puzhong.admin.model.entity.SysPermissionConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionConfigPageParam extends BaseQuery<SysPermissionConfig> {

    private PermissionConfigEnum.Type type;
    private Integer status;

    @Override
    public void wrapQuery(LambdaQueryWrapper<SysPermissionConfig> wrapper) {
        wrapper.eq(this.type != null, SysPermissionConfig::getType, type);
        wrapper.eq(this.status != null, SysPermissionConfig::getStatus, status);
    }

}
