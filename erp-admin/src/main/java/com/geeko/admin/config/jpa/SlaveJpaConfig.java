package com.geeko.admin.config.jpa;

import cn.hutool.core.map.MapUtil;
import com.geeko.admin.config.jta.AtomikosJtaPlatform;
import com.geeko.admin.config.properties.SlaveJpaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JaneKim
 * @date 2023/3/9
 * @descript
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "slaveEntityManagerFactory",
        transactionManagerRef = "slaveTransactionManager",
        basePackages = {"com.geeko.admin.**.slave"})
public class SlaveJpaConfig {


    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;


    @Autowired
    private SlaveJpaProperties slaveJpaProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = slaveJpaProperties.getJpaProperties();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        return builder
                .dataSource(slaveDataSource)
                .packages("com.geeko.admin.**.slave.domain")
                .persistenceUnit("slavePersistenceUnit")
                .properties(properties)
                .build();
    }


    @Bean(name = "slaveTransactionManager")
    public PlatformTransactionManager slaveTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(slaveEntityManagerFactory(builder).getObject());
        return transactionManager;
    }
}
