package com.sunland.hangzhounews.bean;

import com.sunlandgroup.def.bean.result.ResultBase;

import java.util.List;

public class NewsListResponseBean extends ResultBase {
    private int totalCount;
    private int totalPage;
    private List<GeneralNewsInfo> generalNewsInfo;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GeneralNewsInfo> getGeneralNewsInfo() {
        return generalNewsInfo;
    }

    public void setGeneralNewsInfo(List<GeneralNewsInfo> generalNewsInfo) {
        this.generalNewsInfo = generalNewsInfo;
    }
}
