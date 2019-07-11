package com.example.configclient.Enums;

import lombok.Getter;

public enum DataSourceEnum {
    Master("master"),

    Slave1("slave_1");

    DataSourceEnum(String value) {
        this.value = value;
    }

    @Getter
    private String value;
}
