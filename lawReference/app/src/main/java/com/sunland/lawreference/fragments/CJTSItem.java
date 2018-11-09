package com.sunland.lawreference.fragments;

import java.util.ArrayList;
import java.util.List;

public class CJTSItem {
    public String fileNameEN;
    public String fileNameCN;
    public CJTSItem superItem;
    public List<CJTSItem> subItems;

    public String getFileNameEN() {
        return fileNameEN;
    }

    public void setFileNameEN(String fileNameEN) {
        this.fileNameEN = fileNameEN;
    }

    public String getFileNameCN() {
        return fileNameCN;
    }

    public void setFileNameCN(String fileNameCN) {
        this.fileNameCN = fileNameCN;
    }

    public CJTSItem getSuperItem() {
        return superItem;
    }

    public void setSuperItem(CJTSItem superItem) {
        this.superItem = superItem;
    }

    public List<CJTSItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<CJTSItem> subItems) {
        this.subItems = subItems;
    }
}
