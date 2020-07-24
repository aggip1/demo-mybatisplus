package com.ag.entity;

import lombok.ToString;

@ToString
public class Test1 {
    private boolean isLast;
    public void setLast(boolean isLast){this.isLast=isLast;}
    public boolean isLast(){return this.isLast;}


}
