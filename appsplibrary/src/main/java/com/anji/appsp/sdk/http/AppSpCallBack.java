package com.anji.appsp.sdk.http;

/**
 * 请求处理类，用基本的HttpURLConnection
 * 避免依赖其他library
 * 我们已经处理了https的握手场景
 *
 * @param <T> 数据模型
 * @author chenhailong
 * @Date 2020/9/1
 */
public interface AppSpCallBack<T> {

    /**
     * 请求成功且业务代码正常
     * @param data 返回处理后数据模型
     */
    void onSuccess(String data);

    /**
     * 在请求失败或者业务代码异常时调用
     * @param code  状态码，分为业务码和网络状态码
     *              业务码分为
     * @param msg   错误日志
     */
    void onError(String code, String msg);
}
