package com.anji.appsp.sdk;

import com.anji.appsp.sdk.model.AppSpModel;
import com.anji.appsp.sdk.model.AppSpVersion;

/**
 * 版本更新回调接口
 *
 * @author chenhailong
 * Create Date:2020.9.3
 */
public interface IAppSpVersionUpdateCallback {
    /**
     * 背景：版本更新是此SDK的最重要的功能，公司项目多且散，如果其中项目出现
     * 更新问题，将可能是个灾难，因此需要一个公共更新策略。
     *
     * 更新方式：强制更新和非强制更新
     * 更新策略：全量更新和灰度发布
     * 对用户而言，灰度发布无感知
     * 交互方式：APP内部更新和跳转浏览器下载APK
     *
     * 如果返回数据为空或者版本号比当前低，认为无需更新
     * 否则要提示更新，根据系统版本或版本名来决定是否强制更新
     *
     * @param appSpModel update result
     */
    void update(AppSpModel<AppSpVersion> appSpModel);

    /**
     * 请求错误，未能获取后端数据
     * @param code 状态码
     * @param msg  错误信息
     */
    void error(String code,String msg);
}
