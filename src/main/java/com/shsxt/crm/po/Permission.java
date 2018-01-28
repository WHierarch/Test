package com.shsxt.crm.po;

import com.shsxt.base.BaseEntity;

public class Permission extends BaseEntity{
	private Integer roleId;
	private Integer moduleId;
	private String aclValue;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getAclValue() {
		return aclValue;
	}
	public void setAclValue(String aclValue) {
		this.aclValue = aclValue;
	}
	
	

}
