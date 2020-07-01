package com.ag.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
  用户
 */
@TableName(value = "t_user")
public class User implements Serializable {

  private String id;
  private String phoneNumber;    //手机号
  private String password;       //密码
  private String name;           //姓名
  private String dharma;        //法号
  private String headImg;       //头像
  private String sex;          //性别
  private String address;      //地址  省市
  private String sign;         //签名
  private String guruId;       //上师id
  private java.util.Date lastDate;     //最后一次登录时间
  private java.util.Date createDate;   //第一次注册时间
  private String status;        //状态
  private String salt;          //盐值
  private String other;         //其他


}
