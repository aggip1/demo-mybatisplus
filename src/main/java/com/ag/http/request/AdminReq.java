package com.ag.http.request;


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
public class AdminReq implements Serializable {
  private String id;
  private String username;    //管理员名
  private String password;
  private String other;       //其他

}
