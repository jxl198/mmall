package com.mmall.common;

/**
 * @author jiangxl
 * @description
 * @date 2018-05-25 10:51
 **/
public enum ResponseCode {


    SUCCESS(0, "SUCCESS"),

    ERROR(1, "ERROR"),

    NEED_LOGIN(10, "NEED_LOGIN"),

    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");

    private int code;
    private String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }


}
