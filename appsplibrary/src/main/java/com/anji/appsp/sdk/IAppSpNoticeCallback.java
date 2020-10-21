package com.anji.appsp.sdk;

import com.anji.appsp.sdk.model.AppSpModel;
import com.anji.appsp.sdk.model.AppSpNoticeModelItem;

import java.util.List;

/**
 * 公告回调接口
 *
 * @author chenhailong
 * Create Date:2020.9.3
 */
public interface IAppSpNoticeCallback {
    /**
     * @param appSpModel 公告详情，支持多个公告，公告类型暂且支持对话框和跑马灯
     */
    void notice(AppSpModel<List<AppSpNoticeModelItem>> appSpModel);

    /**
     * 请求错误，未能获取后端数据
     * @param code 状态码
     * @param msg  错误信息
     */
    void error(String code,String msg);
}
