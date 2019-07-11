package com.example.configclient.interfaces;

import com.example.configclient.Enums.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    DataSourceEnum value() default DataSourceEnum.Master;
}
