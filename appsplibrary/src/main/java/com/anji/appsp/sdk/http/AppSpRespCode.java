package com.anji.appsp.sdk.http;

/**
 * 请求返回状态码，0000为正常
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpRespCode {
    public static final String SUCCESS = "0000";//正常
    public static final String REQUEST_EXCEPTION = "1001";//请求异常
    public static final String OS_VERSION_INVALID = "1207";//系统版本号不能为空
    public static final String APP_VERSION_INVALID = "1208";//应用版本号不能为空
    public static final String APPKEY_EMPTY_ERROR = "1202";//appKey为空
    public static final String APPKEY_VERIFT_ERROR = "1203";//appKey校验失败
}
