package com.example.db.mysql.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.db.mysql.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {}