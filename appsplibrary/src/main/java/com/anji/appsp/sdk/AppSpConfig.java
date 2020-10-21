package com.anji.appsp.sdk;

import android.content.Context;
import android.os.Build;

import com.anji.appsp.sdk.http.AppSpCallBack;
import com.anji.appsp.sdk.http.AppSpHttpClient;
import com.anji.appsp.sdk.http.AppSpPostData;
import com.anji.appsp.sdk.http.AppSpRequestUrl;
import com.anji.appsp.sdk.model.AppSpRequestModel;
import com.anji.appsp.sdk.util.PhoneUtil;
import com.anji.appsp.sdk.util.RSAUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;

/**
 * 入口类，初始化，获取接口回调
 * 单例
 *
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpConfig {
    private static volatile AppSpConfig appSpConfig;
    private AppSpHandler spHandler;
    private Context mContext;
    private String appKey;
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDf2XdSpvtuC1xeRzP8VOhoF4CRrfOk0ZbxlYniauPWmQqNeDP1IMqoXyJ72FEmmFh6crNSKdGwqyy9ifRuiCPbQ0UhKMsOe7F/06FECoUH3NYipVIHgY4KYpHrIYo9uw8xTCWjUh9iz3Kv5yhulzHR+ORpNQ460xTki57yd6LA/wIDAQAB";

    private AppSpConfig() {
    }

    public static synchronized AppSpConfig getInstance() {
        if (appSpConfig == null) {
            appSpConfig = new AppSpConfig();
        }
        return appSpConfig;
    }

    public static void setDebuggable(boolean debug) {
        AppSpLog.setDebugged(debug);
    }

    /**
     * 初始化
     *
     * @param context 可以是 activity/fragment/view
     * @param appKey  用于标识哪个APP，唯一
     */
    public void init(Context context, String appKey) {
        mContext = context;
        this.appKey = appKey;
        if (spHandler == null) {
            spHandler = new AppSpHandler();
        }
        initDevice();
    }

    /**
     * 版本更新回调
     *
     * @param iAppSpVersionUpdateCallback
     */
    public void setVersionUpdateCallback(IAppSpVersionUpdateCallback iAppSpVersionUpdateCallback) {
        spHandler.setIAppSpVersionUpdateCallback(iAppSpVersionUpdateCallback);
        getVersion();
    }

    /**
     * 公告回调
     *
     * @param iAppSpNoticeCallback
     */
    public void setNoticeCallback(IAppSpNoticeCallback iAppSpNoticeCallback) {
        spHandler.setIAppSpNoticeCallback(iAppSpNoticeCallback);
        getNotice();
    }

    private JSONObject getRequestModelJsonObj() throws JSONException {
        String deviceId = PhoneUtil.getDeviceId(mContext);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appKey", appKey)
                .put("platform", "Android")
                .put("brand", Build.MODEL)
                .put("osVersion", Build.VERSION.RELEASE)
                .put("sdkVersion", Build.VERSION.SDK_INT)
                .put("deviceId", deviceId)
                .put("screenInfo", PhoneUtil.getScreenInfo(mContext))
                .put("versionCode", PhoneUtil.getVersionCode(mContext))
                .put("versionName", PhoneUtil.getVersionName(mContext))
                .put("netWorkStatus", PhoneUtil.getNetworkState(mContext));
        return jsonObject;
    }

    /**
     * 设备初始化
     */
    private void initDevice() {
        if (mContext == null
                || appKey == null
                || appKey.length() == 0) {
            return;
        }
        AppSpPostData appSpPostData = new AppSpPostData();
        JSONObject jsonObject = null;
        try {
            jsonObject = getRequestModelJsonObj();
        } catch (Exception e) {

        }
        if (jsonObject != null) {
            String jsonStr = jsonObject.toString().replaceAll("\"", "\"");
            AppSpLog.d("AAA jsonStr " + jsonStr);
            appSpPostData.put("data", jsonStr);
        }
        String signStr = "";
        PublicKey publicKey;
        try {
            publicKey = RSAUtil.getPublicKey(this.publicKey);
            signStr = RSAUtil.encrypt(jsonObject.toString(), publicKey);

        } catch (Exception e) {

        }
        AppSpLog.d("getVersion signStr " + signStr);
        appSpPostData.put("sign", signStr);
        AppSpHttpClient client = new AppSpHttpClient();
        client.request(AppSpRequestUrl.initDevice, appSpPostData, new AppSpCallBack() {
            @Override
            public void onSuccess(String data) {
                AppSpLog.d("initDevice success");
            }

            @Override
            public void onError(String code, String msg) {
            }
        });
    }

    /**
     * 版本更新
     */
    private void getVersion() {
        if (mContext == null
                || appKey == null
                || appKey.length() == 0) {
            return;
        }
        AppSpPostData appSpPostData = new AppSpPostData();
        JSONObject jsonObject = null;
        try {
            jsonObject = getRequestModelJsonObj();
        } catch (Exception e) {

        }
        if (jsonObject != null) {
            appSpPostData.put("data", jsonObject.toString());
        }
        String signStr = "";
        PublicKey publicKey;
        try {
            publicKey = RSAUtil.getPublicKey(this.publicKey);
            signStr = RSAUtil.encrypt(jsonObject.toString(), publicKey);
        } catch (Exception e) {

        }
        AppSpLog.d("getVersion signStr " + signStr);
        appSpPostData.put("sign", signStr);
        AppSpHttpClient client = new AppSpHttpClient();
        client.request(AppSpRequestUrl.getAppVersion, appSpPostData, new AppSpCallBack() {
            @Override
            public void onSuccess(String data) {
                spHandler.handleVersionSuccess(data);
            }

            @Override
            public void onError(String code, String msg) {
                spHandler.handleUpdateException(code, msg);
            }
        });
    }

    /**
     * 获取公告
     */
    private void getNotice() {
        if (mContext == null
                || appKey == null
                || appKey.length() == 0) {
            return;
        }
        AppSpPostData appSpPostData = new AppSpPostData();
        JSONObject jsonObject = null;
        try {
            jsonObject = getRequestModelJsonObj();
        } catch (Exception e) {

        }
        if (jsonObject != null) {
            appSpPostData.put("data", jsonObject.toString());
        }
        String signStr = "";
        PublicKey publicKey;
        try {
            publicKey = RSAUtil.getPublicKey(this.publicKey);
            signStr = RSAUtil.encrypt(jsonObject.toString(), publicKey);
        } catch (Exception e) {

        }
        AppSpLog.d("getNotice signStr " + signStr);
        appSpPostData.put("sign", signStr);

        AppSpHttpClient client = new AppSpHttpClient();
        client.request(AppSpRequestUrl.getAppNotice, appSpPostData, new AppSpCallBack() {
            @Override
            public void onSuccess(String data) {
                spHandler.handleNoticeSuccess(data);
            }

            @Override
            public void onError(String code, String msg) {
                spHandler.handleNoticeExcption(code, msg);
            }
        });
    }
}
