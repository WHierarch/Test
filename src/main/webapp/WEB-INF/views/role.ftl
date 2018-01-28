<html>
<head>
<#include "common.ftl">
<link rel="stylesheet" href="${ctx}/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/role.js"></script>
<script type="text/javascript" src="${ctx}/zTree_v3/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/zTree_v3/js/jquery.ztree.excheck.js"></script>
</head>

<body>
	<table id="dg" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/role/queryRolesByParams"  singleSelect="true">
		<thead>
			<tr>
				<th field='id' checkbox=true></th>
				<th field='roleName'>角色名称</th>
				<th field='roleRemark' >备注</th>
				<th field="createDate">创建时间</th>
				<th field='updateDate'>更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<a href="javascript:openAddRoleDialog()" class="easyui-linkbutton"
			plain=true iconCls="icon-save">添加</a> 
	    <a  href="javascript:openModifyRoleDialog()"  class="easyui-linkbutton" plain=true iconCls="icon-edit">修改</a>
	   <a  href="javascript:deleteRole()" class="easyui-linkbutton" plain=true iconCls="icon-remove">删除</a>
	    <a  href="javascript:openGrantDlg()" class="easyui-linkbutton" plain=true iconCls="icon-remove">关联权限</a>
			  <br />
		<!-- 搜索条件配置 -->
		角色名:<input type="text"  name="roleName" id="roleName" />
	        <a href="javascript:queryRolesByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>
	
	
	
	
	<div id="dlg" class="easyui-dialog" style="width: 600px;height: 300px"
		closed=true buttons="#bt">
		<form id="fm" method="post">
			角色名:<input name="roleName" type="text" /> &nbsp;&nbsp;&nbsp;
			角色备注:<input name="roleRemark" type="text" /><br /> 
		<input name="id" id="id" type="hidden" />
		</form>
	</div>

	<div id="bt">
		<a   href="javascript:saveOrUpdateRole()" class="easyui-linkbutton" iconCls="icon-save">保存</a> 
		<a href="javascript:closeRoleDlg()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<div id="tree" class="easyui-dialog" style="width: 600px;height: 300px"
		closed=true buttons="#bt02"  title="关联权限">
		  <div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<input name="roleId" id="roleId" type="hidden" />
		<input name="moduleIds" id="moduleIds" type="hidden" />
		
   </div>


    <div id="bt02">
		<a   href="javascript:addGrant()" class="easyui-linkbutton" iconCls="icon-save">授权</a> 
		<a href="javascript:closeAddGrantDlg()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
