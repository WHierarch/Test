package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.BaseQuery;
import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.db.dao.PermissionDao;
import com.shsxt.crm.db.dao.RoleDao;
import com.shsxt.crm.db.dao.UserDao;
import com.shsxt.crm.db.dao.UserRoleDao;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.po.User;
import com.shsxt.crm.po.UserRole;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;

@Service
public class UserService  extends BaseService<User>{
	@Resource
	private UserDao userDao;

	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	@Resource
	private RoleDao roleDao;

	public UserModel userLoginCheck(String userName,String userPwd){
		/**
		 * 1.参数合法性校验
		 * 2.执行记录查询 根据userName
		 * 3.判断查询结果
		 *    不存在  改用户不存在
		 *    存在
		 *       是否注销
		 *           isVlaid=0 注销
		 *           isValid=1  合法用户
		 *               校验密码
		 *                  密码比对  userPwd msd5加密 
		 *                    密码正确
		 *                       返回用户信息  构建返回的用户信息
		 *                    密码不正确  密码错误
		 */
		checkUserLoginParams(userName,userPwd);

		User temp= userDao.queryUserByUserName(userName);

		AssertUtil.isTure(null==temp, "用户记录不存在!");
		AssertUtil.isTure(temp.getIsValid()==0, "该用户已注销!");
		AssertUtil.isTure(!temp.getUserPwd().equals(Md5Util.encode(userPwd)), "密码不正确!");
		/**
		 * 构建用户信息
		 */
		return  buildUserInfo(temp);
	}

	private UserModel buildUserInfo(User temp) {
		UserModel userModel=new UserModel();
		userModel.setUserName(temp.getUserName());
		userModel.setTrueName(temp.getTrueName());
		List<Integer> roleIds= userRoleDao.queryRoleIdsByUserId(temp.getId());
		/*String roleName=roleDao.queryRoleNameByUserId(temp.getId());
		userModel.setRoleName(roleName);*/
		if(null!=roleIds&&roleIds.size()>0){
			/**
			 * 查询角色拥有的权限
			 */
			List<String> permissions= permissionDao.queryAllPermissionsByRoleIds(roleIds);
			userModel.setPermissions(permissions);
		}
		userModel.setIdStr(UserIDBase64.encoderUserID(temp.getId()));
		return userModel;
	}

	private void checkUserLoginParams(String userName, String userPwd) {
		AssertUtil.isTure(StringUtils.isBlank(userName), "用户名非空!");
		AssertUtil.isTure(StringUtils.isBlank(userPwd), "用户密码不能为空!");
	}



	public User queryUserById(Integer uid){
		User user=userDao.queryById(uid);
		user.setRoleNames(roleDao.queryRoleNameByUserId(uid));
		return user;
	}


	public void updateUserPassword(Integer userId,String oldPassword,String newPassword,String confirmPassword){
		/**
		 * 1.参数合法性校验
		 * 2.原始密码是否正确校验
		 * 3.执行密码更新（修改后的密码需要加密）
		 */
		checkUserPasswordParams(userId,oldPassword,newPassword,confirmPassword);
		User temp=userDao.queryById(userId);
		AssertUtil.isTure(!temp.getUserPwd().equals(Md5Util.encode(oldPassword)), "原始密码不正确!");
		newPassword= Md5Util.encode(newPassword);
		AssertUtil.isTure(userDao.updateUserPassword(userId, newPassword)<1, "密码更新失败!");
	}


	private void checkUserPasswordParams(Integer userId, String oldPassword,
			String newPassword, String confirmPassword) {
		AssertUtil.isTure(null==userId||null==userDao.queryById(userId), "用户未登录!");
		AssertUtil.isTure(StringUtils.isBlank(oldPassword), "原始密码非空!");
		AssertUtil.isTure(StringUtils.isBlank(newPassword), "新密码不能为空!");
		AssertUtil.isTure(StringUtils.isBlank(confirmPassword), "确认密码不能为空!");
		AssertUtil.isTure(!newPassword.equals(confirmPassword), "密码不一致!");
	}

	public static void main(String[] args) {
		Integer a=10;
		Integer b=10;

		System.out.println(a==b);
	}

	public void insertUser(User user){
		AssertUtil.isTure(insert(user)<1, CrmConstant.OP_FAILED_MSG);
	}


	public List<User> queryCUstomerManager(){
		return userDao.queryCustomerManager();
	}


	public void saveUser(User user){
		/**
		 * 1.参数合法性校验
		 *   用户名 
		 *   密码
		 *   trueName
		 * 2.用户名唯一校验 
		 * 3.设置额外字段值
		 *     isValid
		 *     createDate
		 *     updateDate
		 * 4.执行添加
		 * 5.关联角色
		 *     如果添加用户选择角色 执行用户角色记录添加 
		 *     
		 */
		checkAddUserParams(user.getUserName(),user.getTrueName());
		User temp= userDao.queryUserByUserName(user.getUserName());
		AssertUtil.isTure(null!=temp&&temp.getIsValid()==1, "该用户已存在!");
		user.setIsValid(1);
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		user.setUserPwd(Md5Util.encode("123456"));
		AssertUtil.isTure(userDao.insert(user)<1, CrmConstant.OP_FAILED_MSG);
		List<Integer> roleIds=user.getRoleIds();
		if(null!=roleIds&&roleIds.size()>0){
			/**
			 * 关联角色
			 */
			relationUserRoles(user.getId(),roleIds);
		}
	}

	/**
	 * 用户角色关联方法
	 * @param roleIds
	 */
	private void relationUserRoles(Integer userId,List<Integer> roleIds) {
		/**
		 * 批量添加用户角色记录到t_user_role表
		 */
		Integer total= userRoleDao.queryUserRolesByUserId(userId);
		if(total>0){
			/**
			 * 执行删除操作
			 *   根据userId 执行删除
			 *   
			 */
			AssertUtil.isTure(userRoleDao.deleteUserRolesByUserId(userId)<total, CrmConstant.OP_FAILED_MSG);
		}
		if(null!=roleIds&&roleIds.size()>0){
			List<UserRole> userRoles=new ArrayList<UserRole>();
			for(Integer rid:roleIds){
				UserRole userRole=new UserRole();
				userRole.setCreateDate(new Date());
				userRole.setIsValid(1);
				userRole.setUpdateDate(new Date());
				userRole.setRoleId(rid);
				userRole.setUserId(userId);
				userRoles.add(userRole);
			}
			AssertUtil.isTure(userRoleDao.insertBatch(userRoles)<userRoles.size(), CrmConstant.OP_FAILED_MSG);
		}



	}

	private void checkAddUserParams(String userName, 
			String trueName) {
		AssertUtil.isTure(StringUtils.isBlank(userName), "用户名非空!");
		//AssertUtil.isTure(StringUtils.isBlank(userPwd), "密码非空!");
		AssertUtil.isTure(StringUtils.isBlank(trueName), "真实名称非空!");
	}


	public void updateUser(User user){
		/**
		 * 1.参数合法性校验
		 *    userName
		 *    trueName
		 *  2.用户名唯一
		 *  3.额外字段值设置
		 *      updateDate
		 *  4.执行更新
		 *  5.关联用户角色
		 */
		AssertUtil.isTure(StringUtils.isBlank(user.getUserName()), "用户名非空!");
		AssertUtil.isTure(StringUtils.isBlank(user.getTrueName()), "真实名称非空!");
		User temp= userDao.queryUserByUserName(user.getUserName());
		AssertUtil.isTure(null!=temp&&temp.getIsValid()==1&&!temp.getId().equals(user.getId()), "该用户已存在!");
		user.setUpdateDate(new Date());
		AssertUtil.isTure(userDao.update(user)<1, CrmConstant.OP_FAILED_MSG);
		List<Integer> roleIds=user.getRoleIds();	// null==roleIds
		relationUserRoles(user.getId(), roleIds);   
	}

	public void deleteUser(Integer uid){
		AssertUtil.isTure(null==uid||null==userDao.queryById(uid), "待删除记录不存在!");
		int total=userRoleDao.queryUserRolesByUserId(uid);
		if(total>0){
			AssertUtil.isTure(userRoleDao.deleteUserRolesByUserId(uid)<total, CrmConstant.OP_FAILED_MSG);
		}
		AssertUtil.isTure(userDao.delete(uid)<1, CrmConstant.OP_SUCCESS_MSG);
	}

	@Override
	public Map<String, Object> queryForPage(BaseQuery baseQuery) {
		PageHelper.startPage(baseQuery.getPage(),baseQuery.getRows());
		List<User> users=userDao.queryForPage(baseQuery);
		if(null!=users&&users.size()>0){
			for(User user:users){
				String roleIdsStr=user.getRoleIdsStr();
				if(StringUtils.isNoneBlank(roleIdsStr)){

					String[] tempRoleIds= roleIdsStr.split(",");
					Integer[] ids= toIntegerArray(tempRoleIds);
					user.setRoleIds(CollectionUtils.arrayToList(ids));
				}

			}
		}
		PageInfo<User>  pageInfo=new PageInfo<User>(users);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}

	private Integer[] toIntegerArray(String[] tempRoleIds) {
		Integer[] ids=null;
		if(null!=tempRoleIds&&tempRoleIds.length>0){
			ids=new Integer[tempRoleIds.length];
			for(int i=0;i<tempRoleIds.length;i++){
				ids[i]=Integer.parseInt(tempRoleIds[i]);
			}
		}
		return ids;

	}

}
