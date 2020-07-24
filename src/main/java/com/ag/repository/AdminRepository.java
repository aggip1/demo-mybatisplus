package com.ag.repository;

import com.ag.entity.Admin;
import com.ag.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminRepository extends BaseMapper<Admin> {
    Optional<Admin> findOne(@Param("name") String name, @Param("password") String password);
    List<Admin> testResultMap(String id);
}
