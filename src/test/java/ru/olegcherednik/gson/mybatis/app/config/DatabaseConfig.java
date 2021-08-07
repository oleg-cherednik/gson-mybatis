package ru.olegcherednik.gson.mybatis.app.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import ru.olegcherednik.gson.mybatis.app.mapper.MapperPackage;

import javax.sql.DataSource;

/**
 * @author Oleg Cherednik
 * @since 07.08.2021
 */
@Configuration
@MapperScan(basePackageClasses = MapperPackage.class)
public class DatabaseConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

}

