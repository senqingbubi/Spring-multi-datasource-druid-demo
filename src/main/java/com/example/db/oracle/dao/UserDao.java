package com.example.db.oracle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.db.oracle.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {}