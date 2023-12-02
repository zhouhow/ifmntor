package com.bjtu.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),

    FAIL(401, "失败"),

    SERVICE_ERROR(501, "服务异常"),

    DATA_ERROR(504, "数据异常"),

    LOGIN_AUTH(408, "未登陆"),
    LOGIN_MOBLE_ERROR(410, "登录失败"),
    ACCOUNT_STOP(411, "帐号暂不支持使用"),

    PERMISSION(409, "没有权限");
    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
