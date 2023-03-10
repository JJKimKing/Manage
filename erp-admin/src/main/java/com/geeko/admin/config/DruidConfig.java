package com.geeko.admin.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.geeko.admin.config.datasource.DynamicDataSource;
import com.geeko.admin.config.encrypt.DBPasswordCallback;
import com.geeko.admin.config.properties.DruidProperties;
import com.geeko.admin.enums.DataSourceType;
import com.geeko.admin.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 * 多数据源配置
 */
@Configuration
public class DruidConfig {


    private static final String MASTER = DataSourceType.MASTER.name();

    private static final String SLAVE = DataSourceType.SLAVE.name();

    @Autowired
    private DruidProperties druidProperties;


    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    @DependsOn({ "transactionManager" })
    public DataSource masterDataSource(Environment env) {
        String prefix = "spring.datasource.druid.master.";
        return getDataSource(env, prefix, MASTER);
    }


    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    @DependsOn({ "transactionManager" })
    public DataSource slaveDataSource(Environment env) {
        String prefix = "spring.datasource.druid.slave.";
        return getDataSource(env, prefix, SLAVE);
    }


    protected DataSource getDataSource(Environment env, String prefix, String dataSourceName)
    {
        Properties prop = build(env, prefix);
        prop.put("passwordCallback", new DBPasswordCallback());
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName(dataSourceName);
        ds.setXaProperties(prop);
        return ds;
    }



    protected Properties build(Environment env, String prefix)
    {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("connectionProperties", "pwd="+env.getProperty(prefix + "pwd"));
        prop.put("initialSize", druidProperties.getInitialSize());
        prop.put("minIdle", druidProperties.getMinIdle());
        prop.put("maxActive", druidProperties.getMaxActive());
        prop.put("maxWait", druidProperties.getMaxWait());
        prop.put("timeBetweenEvictionRunsMillis", druidProperties.getTimeBetweenEvictionRunsMillis());
        prop.put("minEvictableIdleTimeMillis", druidProperties.getMinEvictableIdleTimeMillis());
        prop.put("maxEvictableIdleTimeMillis", druidProperties.getMaxEvictableIdleTimeMillis());
        prop.put("validationQuery", druidProperties.getValidationQuery());
        prop.put("testWhileIdle", druidProperties.isTestWhileIdle());
        prop.put("testOnBorrow", druidProperties.isTestOnBorrow());
        prop.put("testOnReturn", druidProperties.isTestOnReturn());
        return prop;
    }


//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.master")
//    public DataSource masterDataSource(DruidProperties druidProperties) {
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        return druidProperties.dataSource(dataSource);
//    }
//
//    @Bean(name = "slaveDataSource")
//    @ConfigurationProperties("spring.datasource.druid.slave")
//    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
//    public DataSource salveDataSource(DruidProperties druidProperties) {
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        return druidProperties.dataSource(dataSource);
//    }
//
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }


    /**
     * 设置数据源
     *
     * @param targetDataSources 备选数据源集合
     * @param sourceName        数据源名称
     * @param beanName          bean名称
     */
    private void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName) {
        try {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        } catch (Exception e) {

        }
    }

}
