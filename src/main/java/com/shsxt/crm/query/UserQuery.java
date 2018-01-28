package com.shsxt.crm.query;


import com.shsxt.base.BaseQuery;

/**
 * @author lp
 *
 */
public class UserQuery extends BaseQuery {
	private String userName;
	private String trueName;
	private String time;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
