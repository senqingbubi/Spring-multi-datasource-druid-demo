package com.example.db.gauss.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.db.gauss.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {}