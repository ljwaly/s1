package com.ljw.spring.source.s1.transcation;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
//@EnableTransactionManagement
// <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
@MapperScan(basePackages = {"com.ljw.spring.source.s1.dao"})
public class EnableTransactionManagementBean {

    /**
     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    /**
     * 这样也可以
     *
     * 这个是一种事务管理器（数据库的失误管理器，实现了TransactionManager接口）
     *
     */
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dtm = new DataSourceTransactionManager();
        dtm.setDataSource(dataSource);
        return dtm;
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(platformTransactionManager);
        return transactionTemplate;
    }
}
