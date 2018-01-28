<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/user.js"></script>
</head>

<body>
	<table id="dg" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/user/queryUsersByParams">
		<thead>
			<tr>
				<th field='id' checkbox=true></th>
				<th field='userName'>用户名</th>
				<th field='trueName' >真实名称</th>
				<th field="phone">手机号</th>
				<th field='email'>邮箱</th>
				<th field='roleNames'>角色</th>
				<th field="createDate">创建时间</th>
				<th field='updateDate'>更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<a href="javascript:openAddUserDialog()" class="easyui-linkbutton"
			plain=true iconCls="icon-save">添加</a> 
	    <a  href="javascript:openModifyUserDialog()"  class="easyui-linkbutton" plain=true iconCls="icon-edit">修改</a>
	   <a  href="javascript:deleteUser()" class="easyui-linkbutton" plain=true iconCls="icon-remove">删除</a>
			  <br />
		<!-- 搜索条件配置 -->
		用户名:<input type="text"  name="userName" id="userName" />
		邮箱:<input type="text" name="email" id="email" /> 
	        <a href="javascript:queryUsersByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>
	
	
	
	
	<div id="dlg" class="easyui-dialog" style="width: 600px;height: 300px"
		closed=true buttons="#bt">
		<form id="fm" method="post">
			用户名:<input name="userName" type="text" /> &nbsp;&nbsp;&nbsp;
			真实名称:<input name="trueName" type="text" /><br /> 
			联系电话:<input name="phone" type="text" /><br /> 
			邮箱:<input name="email" type="text" />&nbsp;&nbsp;&nbsp;
			角色:
			<input class="easyui-combobox" id="role" name="roleIds"
					multiple="true" data-options="panelHeight:'auto',editable:false,valueField:'id',textField:'roleName',url:'${ctx}/role/queryAllRoles'" />&nbsp;<font
					color="red">
		<input name="id" id="id" type="hidden" />
		</form>
	</div>

	<div id="bt">
		<a   href="javascript:saveOrUpdateUser()" class="easyui-linkbutton" iconCls="icon-save">保存</a> 
		<a href="javascript:closeUserDlg()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	


</body>
</html>
