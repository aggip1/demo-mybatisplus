package com.ag.http.response;


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
public class AdminResp implements Serializable {
  private String id;
  private String username;    //管理员名
  private String password;
  private String other;       //其他

}
