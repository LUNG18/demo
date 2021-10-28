package com.puzhong.admin.constant.enums;

import java.util.HashMap;
import java.util.Map;

public class PermissionConfigEnum {

    public enum Type {

        OPEN_PERMISSION("权限控制")
        ;

        private String display;
        private static Map<String, Type> all = new HashMap<>();

        Type(String display) {
            this.display = display;
        }

        static {
            for (Type type : Type.values()) {
                all.put(type.name(), type);
            }
        }

        public static boolean openPermission(Map<String, Integer> permissionMap) {
            Integer status = permissionMap.get(OPEN_PERMISSION.name());
            return status != null && status == 1;
        }
    }
}
