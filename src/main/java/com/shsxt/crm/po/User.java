package com.shsxt.crm.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shsxt.base.BaseEntity;

public class User extends BaseEntity{
	
	private Integer id;
	private String userName;
	private String userPwd;
	private String trueName;
	private String email;
	private String phone;
	
	private List<Integer> roleIds=new ArrayList<Integer>();
	
	private String roleNames;
	
	private String roleIdsStr;
	
	
	
	
	
	public String getRoleIdsStr() {
		return roleIdsStr;
	}
	public void setRoleIdsStr(String roleIdsStr) {
		this.roleIdsStr = roleIdsStr;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	

}
