package com.anji.appsp.sdk.model;

import java.io.Serializable;

/**
 * 请求出现异常时，Message将code和msg交给Handler处理
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpMessageObj implements Serializable {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
