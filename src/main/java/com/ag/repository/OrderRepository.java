package com.ag.repository;

import com.ag.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ag
 * @since 2020-07-07
 */
@Repository
public interface OrderRepository extends BaseMapper<Order> {
    List<Order> findAll();

}
