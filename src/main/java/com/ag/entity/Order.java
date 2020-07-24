package com.ag.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ag
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "order1")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
//    @TableId("id")
    private String oid;
//    @TableField("order_id")
    private String orderId;
//    @TableField("order_name")
    private String orderName;
//    @TableField("admin_id")
    private String adminId;


}
