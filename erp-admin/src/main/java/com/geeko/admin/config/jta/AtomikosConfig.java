package com.geeko.admin.config.jta;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.geeko.admin.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @author JaneKim
 * @date 2023/3/9
 * @descript
 */
@Configuration
@EnableTransactionManagement
public class AtomikosConfig implements TransactionManagementConfigurer {


    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        AtomikosJtaPlatform.transaction = userTransactionImp;
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager",initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable
    {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        AtomikosJtaPlatform.transactionManager = userTransactionManager;
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }


    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    @Primary
    public PlatformTransactionManager transactionManager() throws Throwable
    {
        UserTransaction userTransaction = userTransaction();
        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }


    /**
     * 设置默认事务管理
     * @return
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return SpringUtils.getBean("masterTransactionManager");
    }
}
