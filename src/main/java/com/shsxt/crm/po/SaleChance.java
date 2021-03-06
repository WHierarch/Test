package com.shsxt.crm.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shsxt.base.BaseEntity;

public class SaleChance extends BaseEntity{
	private Integer id;
	private String chanceSource;
	private String customerName;
	private Integer cgjl;
	private String overview;
	private String linkMan;
	private String linkPhone;
	private String description;
	private String createMan;
	private String assignMan;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date assignTime;
	private Integer state;
	private Integer devResult;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChanceSource() {
		return chanceSource;
	}
	public void setChanceSource(String chanceSource) {
		this.chanceSource = chanceSource;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCgjl() {
		return cgjl;
	}
	public void setCgjl(Integer cgjl) {
		this.cgjl = cgjl;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public String getAssignMan() {
		return assignMan;
	}
	public void setAssignMan(String assignMan) {
		this.assignMan = assignMan;
	}
	public Date getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getDevResult() {
		return devResult;
	}
	public void setDevResult(Integer devResult) {
		this.devResult = devResult;
	}
	@Override
	public String toString() {
		return "SaleChance [id=" + id + ", chanceSource=" + chanceSource
				+ ", customerName=" + customerName + ", cgjl=" + cgjl
				+ ", overview=" + overview + ", linkMan=" + linkMan
				+ ", linkPhone=" + linkPhone + ", description=" + description
				+ ", createMan=" + createMan + ", assignMan=" + assignMan
				+ ", assignTime=" + assignTime + ", state=" + state
				+ ", devResult=" + devResult + "]";
	}
	
	

}
