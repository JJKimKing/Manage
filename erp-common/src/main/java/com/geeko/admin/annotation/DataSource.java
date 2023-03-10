package com.geeko.admin.annotation;

import com.geeko.admin.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {

    DataSourceType value() default DataSourceType.MASTER;
}
