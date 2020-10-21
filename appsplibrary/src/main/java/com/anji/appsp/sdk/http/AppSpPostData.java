package com.anji.appsp.sdk.http;

import org.json.JSONObject;

import java.util.LinkedHashMap;

/**
 * 携带请求参数的类
 *
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpPostData extends LinkedHashMap {
    private static final long serialVersionUID = -3918611306392239969L;


    @Override
    public String toString() {
        String regLeft = "\"" + "\\{";
        String regRight = "\\}" + "\"";
        return new JSONObject(this).toString().replaceAll("\\\\", "")
                .replaceAll(regLeft, "{").replaceAll(regRight, "}");
    }
}
