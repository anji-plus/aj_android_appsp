package com.anji.appsp.sdk.model;

import java.io.Serializable;

/**
 * @author chenhailong
 * Create Date:2020.9.3
 */
public class AppSpNoticeModelItem implements Serializable {
    public String title;//公告标题
    public String details;//公告内容
    public String templateType;//模板风格
    public String templateTypeName;//模板名称

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateTypeName() {
        return templateTypeName;
    }

    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }
}
