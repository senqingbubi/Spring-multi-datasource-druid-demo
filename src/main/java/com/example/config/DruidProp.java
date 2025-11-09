package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidProp {

    private DruidDataSource oracle;
    private DruidDataSource gauss;



    public DruidDataSource getOracle() { return oracle; }
    public void setOracle(DruidDataSource oracle) { this.oracle = oracle; }

    public DruidDataSource getGauss() { return gauss; }
    public void setGauss(DruidDataSource gauss) { this.gauss = gauss; }
}