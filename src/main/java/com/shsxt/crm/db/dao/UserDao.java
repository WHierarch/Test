package com.shsxt.crm.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.shsxt.base.BaseDao;
import com.shsxt.crm.po.User;

public interface UserDao extends BaseDao<User>{
	
	/**
	 * insert
	 * insertBatch
	 * queryById
	 * queryByParams
	 * update
	 * updateBatch
	 * delete
	 * deleteBatch
	 */
	
	
	/**
	 * 
	 * @param userName
	 * @return
	 * 
	 */
	public User queryUserByUserName(@Param("userName") String userName);

	//public User queryUserById(@Param("uid")Integer uid);
	
	
	@Update("update t_user set user_pwd=#{newPassword} where id=#{userId} and is_valid=1")
	public Integer updateUserPassword(@Param("userId")Integer userId,@Param("newPassword")String newPassword);

	
	
	public List<User> queryCustomerManager();
	
	
	
 }
