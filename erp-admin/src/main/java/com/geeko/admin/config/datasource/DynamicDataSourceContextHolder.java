package com.geeko.admin.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
public class DynamicDataSourceContextHolder {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder .class);


    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();


    /**
     * 设置数据源
     * @param sourceType
     */
    public static void setDataSource(String sourceType) {
        logger.info("切换到{}数据源",sourceType);
        CONTEXT_HOLDER.set(sourceType);
    }


    /**
     * 获取数据源变量
     * @return
     */
    public static String getSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }

}
