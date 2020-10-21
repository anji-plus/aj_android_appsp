package com.anji.appsp.sdk.model;

import java.io.Serializable;
/**
 * 请求model
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpRequestModel implements Serializable {
    private String appKey;//应用和后端对接的key
    private String deviceId;//设备唯一标识
    private String platform;//平台
    private String screenInfo;//屏幕宽高
    private String versionCode;//版本号
    private String versionName;//版本名
    private String brand;//品牌
    private String osVersion;//系统版本，如果7.1.2
    private String sdkVersion;//系统SDK版本，比如android 28
    private String netWorkStatus;//联网方式

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getScreenInfo() {
        return screenInfo;
    }

    public void setScreenInfo(String screenInfo) {
        this.screenInfo = screenInfo;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getNetWorkStatus() {
        return netWorkStatus;
    }

    public void setNetWorkStatus(String netWorkStatus) {
        this.netWorkStatus = netWorkStatus;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    @Override
    public String toString() {
        return "AppSpRequestModel{" +
                "appKey='" + appKey + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", platform='" + platform + '\'' +
                ", screenInfo='" + screenInfo + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                ", brand='" + brand + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", netWorkStatus='" + netWorkStatus + '\'' +
                '}';
    }
}
