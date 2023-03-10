package com.geeko.admin.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JaneKim
 * @date 2023/3/9
 * @descript
 */
@Configuration
public class MasterJpaProperties {

    @Value("${spring.hibernate.master.hbm2ddl.ddl-auto}")
    private String auto;

    @Value("${spring.hibernate.show-sql}")
    private boolean showSql;


    @Value("${spring.hibernate.master.dialect}")
    private String dialect;


    public Map<String, Object> getJpaProperties() {
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", auto);
        jpaProperties.put("hibernate.show_sql", showSql);
        jpaProperties.put("hibernate.dialect", dialect);
        return jpaProperties;
    }
}
