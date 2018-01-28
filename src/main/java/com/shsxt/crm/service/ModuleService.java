package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shsxt.base.BaseService;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.db.dao.ModuleDao;
import com.shsxt.crm.db.dao.PermissionDao;
import com.shsxt.crm.dto.TreeDto;
import com.shsxt.crm.po.Module;
import com.shsxt.crm.utils.AssertUtil;

@Service
public class ModuleService extends BaseService<Module> {
	
	@Resource
	private ModuleDao moduleDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	public List<TreeDto> queryAllModules(){
		return moduleDao.queryAllModules();
	}
	
	/**
	 * 查询所有树形节点数据 
	 *  设置指定节点选中 id
	 * @return
	 */
	public List<TreeDto> queryAllModules(Integer rid){
		/**
		 * 1.根据角色查询角色已授权的模块id List<Integer>
		 * 2.查询所有模块数据  List<TreeDto>
		 * 3.循环遍历List<TreeDto>
		 */
		List<Integer> moduleIds=permissionDao.queryModuleIdsByRoleId(rid);
		List<TreeDto> treeDtos=moduleDao.queryAllModules();
		if(null!=treeDtos&&treeDtos.size()>0){
			for(TreeDto treeDto:treeDtos){
				if(null!=moduleIds&&moduleIds.size()>0){
					if(moduleIds.contains(treeDto.getId())){
						treeDto.setChecked(true);
					}
				}
			}
		}
		return treeDtos;
	}
	
	
	public void saveModule(Module module){
		/**
		 * 1.参数校验
		 *    模块名
		 *    权限值
		 *    层级
		 * 2.记录唯一性校验
		 *    根级菜单  名称唯一 权限值 唯一
		 *    一级菜单  名称唯一  权限值唯一
		 * 3.额外字段值设置
		 *     isValid
		 *     createDate
		 *     updateDate
		 * 4.执行添加
		 */
		checkModuleParams(module.getModuleName(),module.getOptValue(),module.getGrade());
		Module temp=null;
		if(module.getGrade()!=2){
			temp=moduleDao.queryModuleByModuleName(module.getModuleName());
			AssertUtil.isTure(null!=temp, "该模块已存在!");
			temp=moduleDao.queryModuleByOptValue(module.getOptValue());
			AssertUtil.isTure(null!=temp, "权限值已存在!");
		}
		module.setCreateDate(new Date());
		module.setUpdateDate(new Date());
		module.setIsValid(1);
		AssertUtil.isTure(moduleDao.insert(module)<1, CrmConstant.OP_FAILED_MSG);
	}

	private void checkModuleParams(String moduleName, String optValue,
			Integer grade) {
		AssertUtil.isTure(StringUtils.isBlank(moduleName), "模块名称非空!");
		AssertUtil.isTure(StringUtils.isBlank(optValue), "权限值非空!");
		AssertUtil.isTure(null==grade||(grade!=0&&grade!=1&&grade!=2), "层级值非法!");
	}
	
	
	private void checkModuleParams(String moduleName, String optValue,
			Integer grade,Integer id) {
		checkModuleParams(moduleName,optValue,grade);
		AssertUtil.isTure(null==id||null==moduleDao.queryById(id), "待更新记录不存在!");
	}
	
	public void updateModule(Module module){
		/**
		 * 1.参数校验
		 *    模块名
		 *    权限值
		 *    层级
		 *    id
		 * 2.记录唯一性校验
		 *    根级菜单  名称唯一 权限值 唯一
		 *    一级菜单  名称唯一  权限值唯一
		 * 3.额外字段值设置
		 *     updateDate
		 * 4.执行更新
		 */
		checkModuleParams(module.getModuleName(), module.getOptValue(), module.getGrade(), module.getId());
		Module temp=null;
		if(module.getGrade()!=2){
			temp=moduleDao.queryModuleByModuleName(module.getModuleName());
			AssertUtil.isTure(null!=temp&&!temp.getId().equals(module.getId()), "该模块已存在!");
			temp=moduleDao.queryModuleByOptValue(module.getOptValue());
			AssertUtil.isTure(null!=temp&&!temp.getId().equals(module.getId()), "权限值已存在!");
		}
		module.setUpdateDate(new Date());
		AssertUtil.isTure(moduleDao.update(module)<1, CrmConstant.OP_FAILED_MSG);
	}
	
	public void deleteModule(Integer mid){
		/**
		 * 思路1:
		 *    查询记录 判断层级
		 *       根级  
		 *          删除根级  一级  二级菜单级联删除
		 *       一级
		 *          删除一级菜单  二级菜单
		 *        二级菜单
		 *           删除二级菜单
		 *       ...
		 *  思路2:根据mid
		 *      根据根记录查询根记录下所有的子记录
		 *        根据pid 查询子记录
		 *        递归查询子记录
		 */
		List<Integer> results=new ArrayList<Integer>();
		results= getSubModuleIds(mid,results);
		if(null!=results&&results.size()>0){
			/*for(Integer id:results){
				System.err.println("模块id:"+id);
			}*/
			AssertUtil.isTure(moduleDao.deleteModulesBatch(results)<results.size(), CrmConstant.OP_FAILED_MSG);
		}
		
	}

	private List<Integer> getSubModuleIds(Integer mid, List<Integer> results) {
		Module module= moduleDao.queryById(mid);
		if(null!=module){
			results.add(module.getId());
			List<Module> modules=moduleDao.querySubModulesByPid(module.getId());
			if(null!=modules&&modules.size()>0){
				for(Module temp:modules){
					results= getSubModuleIds(temp.getId(), results);
				}
			}
		}
		return results;
	}

	public List<Module> queryModulesByGrade(Integer grade) {
		return moduleDao.queryModulesByGrade(grade);
	}
	
	
}
