package com.ag.eumn;

public enum AdminEnum {
    USERNAME("AAA", "111"),
    PASSWORD("BBB", "222");
    private String code;
    private String index;

    AdminEnum(String code, String index) {
        this.code = code;
        this.index = index;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public String getIndex() {
        return index;
    }

}
