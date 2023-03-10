package com.geeko.admin.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource masterDataSource, Map<Object, Object> targetDataSources) {
        this.setDefaultTargetDataSource(masterDataSource);
        this.setTargetDataSources(targetDataSources);
        this.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getSourceType();
    }

}
