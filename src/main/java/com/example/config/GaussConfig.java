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
@ConditionalOnProperty(prefix = "spring.datasource", name = "use-db", havingValue = "gauss")
@MapperScan(basePackages = "com.example.db.gauss.dao", sqlSessionFactoryRef = "gaussSqlSessionFactory")
public class GaussConfig {
    private final DruidProp druidProp;
    public GaussConfig(DruidProp druidProp) { this.druidProp = druidProp; }

    @Bean("gaussDataSource")
    @ConfigurationProperties("spring.datasource.gauss")
    public DataSource gaussDataSource() { return druidProp.getGauss(); }

    @Bean("gaussSqlSessionFactory")
    public SqlSessionFactory gaussSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(gaussDataSource());
        factory.setMapperLocations(new org.springframework.core.io.support.PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/gauss/*.xml"));
        return factory.getObject();
    }

    @Bean("gaussTransactionManager")
    public DataSourceTransactionManager gaussTransactionManager() {
        return new DataSourceTransactionManager(gaussDataSource());
    }
}