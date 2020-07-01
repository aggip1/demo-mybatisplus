package com.ag.repository;

import com.ag.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository extends BaseMapper<Admin> {
}
