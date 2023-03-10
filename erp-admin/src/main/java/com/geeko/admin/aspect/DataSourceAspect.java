package com.geeko.admin.aspect;

import com.geeko.admin.annotation.DataSource;
import com.geeko.admin.config.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author JaneKim
 * @date 2023/3/8
 * @descript
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {


    @Pointcut("@annotation(com.geeko.admin.annotation.DataSource)||@within(com.geeko.admin.annotation.DataSource)")
    public void dsPointCut() {

    }


    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        DataSource dataSource = getDataSource(proceedingJoinPoint);
        if (Objects.nonNull(dataSource)) {
            DynamicDataSourceContextHolder.setDataSource(dataSource.value().name());

        }
        try {
            return proceedingJoinPoint.proceed();
        }finally {
            //销毁数据源
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    private DataSource getDataSource(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        DataSource dataSource = AnnotationUtils.findAnnotation(methodSignature.getMethod(), DataSource.class);
        if (Objects.nonNull(dataSource)) {
            return dataSource;
        }

        return AnnotationUtils.findAnnotation(methodSignature.getDeclaringType(), DataSource.class);
    }
}
