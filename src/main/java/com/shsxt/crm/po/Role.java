package com.shsxt.crm.po;

import java.util.Date;

import com.shsxt.base.BaseEntity;

public class Role extends BaseEntity{
    private Integer id;

    private String roleName;

    private String roleRemark;

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }

  
}