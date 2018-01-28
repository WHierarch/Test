package com.shsxt.crm.po;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shsxt.base.BaseEntity;

public class CusDevPlan extends BaseEntity{
	private Integer id;
	private String planItem;
	private String exeAffect;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date planDate;
	private Integer saleChanceId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlanItem() {
		return planItem;
	}
	public void setPlanItem(String planItem) {
		this.planItem = planItem;
	}
	public String getExeAffect() {
		return exeAffect;
	}
	public void setExeAffect(String exeAffect) {
		this.exeAffect = exeAffect;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public Integer getSaleChanceId() {
		return saleChanceId;
	}
	public void setSaleChanceId(Integer saleChanceId) {
		this.saleChanceId = saleChanceId;
	}
	

}
