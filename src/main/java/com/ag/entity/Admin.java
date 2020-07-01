package com.ag.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
    后台管理
 */
@TableName("admin")
public class Admin implements Serializable {
@TableId(value = "id",type = IdType.UUID)
  private String id;
  @TableField("USERNAME")
  private String username;    //管理员名
  @TableField("PASSWORD")
  private String password;
  @TableField("OTHER")//密码
  private String other;       //其他

}
