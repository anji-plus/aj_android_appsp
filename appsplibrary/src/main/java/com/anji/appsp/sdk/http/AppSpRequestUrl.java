package com.anji.appsp.sdk.http;

/**
 * 接口地址
 *
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpRequestUrl {
    //开发
    //    private static final String Host = "http://10.108.26.127:8081/sp/";
    //测试
    private static final String Host = "https://appsp.anji-plus.com/sp/";
    public static final String initDevice = Host + "phone/deviceInit";
    public static final String getAppVersion = Host + "phone/appVersion";
    public static final String getAppNotice = Host + "phone/appNotice";
}
