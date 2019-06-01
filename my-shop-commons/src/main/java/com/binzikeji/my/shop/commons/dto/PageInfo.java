package com.binzikeji.my.shop.commons.dto;

import com.binzikeji.my.shop.commons.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Chundekepa
 * @create: 2019-03-08 23:47
 **/
public class PageInfo<T extends BaseEntity> implements Serializable {

    private int draw;
    //总记录数
    private int recordsTotal;
    //过滤
    private int recordsFiltered;
    //数据
    private List<T> data;
    //错误
    private String error;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
