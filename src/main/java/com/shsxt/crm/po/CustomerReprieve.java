package com.shsxt.crm.po;

import java.util.Date;

import com.shsxt.base.BaseEntity;

public class CustomerReprieve extends BaseEntity{
    private Integer id;

    private Integer lossId;

    private String measure;

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
    }

    
}