package com.geeko.admin.config.jpa;

import com.geeko.admin.config.jta.AtomikosJtaPlatform;
import com.geeko.admin.config.properties.MasterJpaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author JaneKim
 * @date 2023/3/9
 * @descript
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager",
        basePackages = {"com.geeko.admin.**.master"})
public class MasterJpaConfig {


    @Resource(name="masterDataSource")
    private DataSource masterDataSource;

    @Autowired
    private MasterJpaProperties masterJpaProperties;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory (EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = masterJpaProperties.getJpaProperties();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        return builder
                .dataSource(masterDataSource)
                .packages("com.geeko.admin.**.master.domain")
                .persistenceUnit("masterPersistenceUnit")
                .properties(properties)
                .build();
    }


    @Bean(name = "masterTransactionManager")
    public PlatformTransactionManager masterTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(masterEntityManagerFactory(builder).getObject());
        return transactionManager;
    }
}
