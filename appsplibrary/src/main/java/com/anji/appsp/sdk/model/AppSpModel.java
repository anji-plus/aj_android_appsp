package com.anji.appsp.sdk.model;
/**
 * 后端返回的结果
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpModel<T> {
    private String repCode;
    private String  repMsg;
    private T repData;

    public String getRepCode() {
        return repCode;
    }

    public void setRepCode(String repCode) {
        this.repCode = repCode;
    }

    public String getRepMsg() {
        return repMsg;
    }

    public void setRepMsg(String repMsg) {
        this.repMsg = repMsg;
    }

    public T getRepData() {
        return repData;
    }

    public void setRepData(T repData) {
        this.repData = repData;
    }

    @Override
    public String toString() {
        return "AppSpModel{" +
                "repCode='" + repCode + '\'' +
                ", repMsg='" + repMsg + '\'' +
                ", repData=" + repData +
                '}';
    }
}
