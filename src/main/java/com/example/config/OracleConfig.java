package com.example.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "use-db", havingValue = "oracle", matchIfMissing = true)
@MapperScan(basePackages = "com.example.db.oracle.dao", sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleConfig {
    private final DruidProp druidProp;
    public OracleConfig(DruidProp druidProp) { this.druidProp = druidProp; }

    @Bean("oracleDataSource")
    @ConfigurationProperties("spring.datasource.oracle")
    public DataSource oracleDataSource() { return druidProp.getOracle(); }

    @Bean("oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(oracleDataSource());
        factory.setMapperLocations(new org.springframework.core.io.support.PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/oracle/*.xml"));
        return factory.getObject();
    }

    @Bean("oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager() {
        return new DataSourceTransactionManager(oracleDataSource());
    }
}