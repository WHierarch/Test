package com.shsxt.crm.query;

import com.shsxt.base.BaseQuery;

public class ModuleQuery extends BaseQuery {
	private String moduleName;
	private String optValue;
	private Integer grade;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getOptValue() {
		return optValue;
	}
	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	

}
