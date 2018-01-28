package com.shsxt.crm.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
	private String idStr;
	private String userName;
	private String trueName;
	private String roleName;
	
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	private List<String> permissions=new ArrayList<String>();
	
	
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	@Override
	public String toString() {
		return "UserModel [idStr=" + idStr + ", userName=" + userName
				+ ", trueName=" + trueName + "]";
	}
	
	
	

}
