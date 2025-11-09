package com.example.controller;

import com.example.db.mysql.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private final UserDao mysqlMapper;
    private final com.example.db.oracle.dao.UserDao oracleMapper;
    private final com.example.db.gauss.dao.UserDao gaussMapper;

    @Value("${spring.datasource.use-db}")
    private String currentDb;

    public TestController(UserDao mysqlMapper, com.example.db.oracle.dao.UserDao oracleMapper, com.example.db.gauss.dao.UserDao gaussMapper) {
        this.mysqlMapper = mysqlMapper;
        this.oracleMapper = oracleMapper;
        this.gaussMapper = gaussMapper;
    }

    @GetMapping("/users")
    public Object getUsers() {
        if ("oracle".equalsIgnoreCase(currentDb)) return oracleMapper.selectList(null);
        if ("gauss".equalsIgnoreCase(currentDb)) return gaussMapper.selectList(null);
        return mysqlMapper.selectList(null);
    }
}