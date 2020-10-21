package com.anji.appsp.sdk;

import android.text.TextUtils;

import com.anji.appsp.sdk.model.AppSpModel;
import com.anji.appsp.sdk.model.AppSpNoticeModelItem;
import com.anji.appsp.sdk.model.AppSpVersion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理类
 * 版本更新成功/失败的处理；
 * 公告获取成功/失败的处理
 *
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpHandler {
    private IAppSpVersionUpdateCallback iAppSpVersionUpdateCallback;
    private IAppSpNoticeCallback iAppSpNoticeCallback;

    public void setIAppSpVersionUpdateCallback(IAppSpVersionUpdateCallback iAppSpVersionUpdateCallback) {
        this.iAppSpVersionUpdateCallback = iAppSpVersionUpdateCallback;
        this.iAppSpNoticeCallback = null;
    }

    public void setIAppSpNoticeCallback(IAppSpNoticeCallback iAppSpNoticeCallback) {
        this.iAppSpNoticeCallback = iAppSpNoticeCallback;
        this.iAppSpVersionUpdateCallback = null;
    }

    /**
     * 版本更新获取数据成功
     */
//    public void handleVersionSuccess(AppSpModel spVersionModel) {
//        synchronized (iAppSpVersionUpdateCallback) {
//            if (iAppSpVersionUpdateCallback != null) {
//                //Update
//                AppSpLog.d("版本更新返回客户端数据 " + spVersionModel);
//                iAppSpVersionUpdateCallback.update(spVersionModel);
//            }
//        }
//
//    }
    private String getStringElement(Object object) {
        if (object instanceof String) {
            return (String) object;
        }
        return "";
    }

    private Boolean getBooleanElement(Object object) {
        if (object instanceof Boolean) {
            return (Boolean) object;
        }
        return false;
    }

    /**
     * 版本更新获取数据成功
     */
    public void handleVersionSuccess(String data) {
        if (iAppSpVersionUpdateCallback != null) {
            synchronized (iAppSpVersionUpdateCallback) {
                //Update
                try {
                    AppSpModel<AppSpVersion> spVersionModel = new AppSpModel<>();
                    JSONObject jsonObject = new JSONObject(data);
                    spVersionModel.setRepCode(getStringElement(jsonObject.opt("repCode")));
                    spVersionModel.setRepMsg(getStringElement(jsonObject.opt("repMsg")));

                    Object repDtaObj = jsonObject.opt("repData");
                    if (repDtaObj != null && !TextUtils.isEmpty(repDtaObj.toString())
                            && !"null".equalsIgnoreCase(repDtaObj.toString())) {
                        JSONObject repData = new JSONObject(repDtaObj.toString());
                        if (repData != null) {
                            AppSpVersion appSpVersion = new AppSpVersion();
                            appSpVersion.setDownloadUrl(getStringElement(repData.opt("downloadUrl")));
                            appSpVersion.setUpdateLog(getStringElement(repData.opt("updateLog")));
                            appSpVersion.setShowUpdate(getBooleanElement(repData.opt("showUpdate")));
                            appSpVersion.setMustUpdate(getBooleanElement(repData.opt("mustUpdate")));
                            spVersionModel.setRepData(appSpVersion);
                        }
                    }
                    AppSpLog.d("版本更新返回客户端数据 " + spVersionModel);
                    iAppSpVersionUpdateCallback.update(spVersionModel);
                } catch (Exception e) {
                    AppSpLog.d("版本更新返回客户端数据 Exception e " + e.toString());
                }
            }
        }

    }

    /**
     * 公告获取数据成功
     */
    public void handleNoticeSuccess(String data) {
        if (iAppSpNoticeCallback != null) {
            synchronized (iAppSpNoticeCallback) {
                //Notice
                try {
                    AppSpModel<List<AppSpNoticeModelItem>> spVersionModel = new AppSpModel<>();
                    JSONObject jsonObject = new JSONObject(data);
                    spVersionModel.setRepCode(getStringElement(jsonObject.opt("repCode")));
                    spVersionModel.setRepMsg(getStringElement(jsonObject.opt("repMsg")));
                    Object repDtaObj = jsonObject.opt("repData");
                    if (repDtaObj != null && !TextUtils.isEmpty(repDtaObj.toString())
                            && !"null".equalsIgnoreCase(repDtaObj.toString())) {
                        JSONArray repData = new JSONArray(repDtaObj.toString());
                        List<AppSpNoticeModelItem> items = new ArrayList<>();
                        if (repData != null) {
                            for (int i = 0; i < repData.length(); i++) {
                                AppSpNoticeModelItem item = new AppSpNoticeModelItem();
                                JSONObject value = repData.getJSONObject(i);
                                item.setTitle(getStringElement(value.opt("title")));
                                item.setDetails(getStringElement(value.opt("details")));
                                item.setTemplateType(getStringElement(value.opt("templateType")));
                                item.setTemplateTypeName(getStringElement(value.opt("templateTypeName")));
                                items.add(item);
                            }
                            spVersionModel.setRepData(items);
                            iAppSpNoticeCallback.notice(spVersionModel);
                        }
                    }
                    AppSpLog.d("通知返回客户端数据 " + spVersionModel);
                } catch (Exception e) {
                    AppSpLog.d("通知返回客户端数据 Exception e " + e.toString());
                }

            }
        }
    }

    /**
     * 版本更新异常处理
     *
     * @param code
     * @param msg
     */
    public void handleUpdateException(String code, String msg) {
        if (iAppSpVersionUpdateCallback != null) {
            synchronized (iAppSpVersionUpdateCallback) {
                //Update error
                iAppSpVersionUpdateCallback.error(code, msg);
            }
        }
    }

    /**
     * 公告异常处理
     *
     * @param code
     * @param msg
     */
    public void handleNoticeExcption(String code, String msg) {
        if (iAppSpNoticeCallback != null) {
            synchronized (iAppSpNoticeCallback) {
                iAppSpNoticeCallback.error(code, msg);
            }
        }
    }

}
