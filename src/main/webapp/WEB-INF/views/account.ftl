<html>
<head>
<title>账户管理</title>

<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/account.js"></script>

</head>

<body>
	<table id="account" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/account/queryAccountsByParams?userId=46">
		<thead>
			<tr>
				<th field='id' checkbox=true></th>
				<th field='aname'>账户名称</th>
				<th field='type' formatter="formatterType">类型</th>
				<th field="money">金额</th>
				<th field='createTime'>创建时间</th>
				<th field="updateTime">更新时间</th>
				<th field='remark'>备注</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<a href="javascript:openAddAccountDialog()" class="easyui-linkbutton"
			plain=true iconCls="icon-save">添加</a> 
	    <a  href="javascript:openModifyAccountDialog()"  class="easyui-linkbutton" plain=true iconCls="icon-edit">修改</a>
	   <a  href="javascript:deleteAccount()" class="easyui-linkbutton" plain=true iconCls="icon-remove">删除</a>
			  <br />
		<!-- 搜索条件配置 -->
		账户名称:<input type="text" name="aname" id="aname" /> 类型: <select
			id="type" class="easyui-combobox" name="type" style="width:200px;"
			panelHeight="auto">
			<option value="">全部</option>
			<option value="0">工商</option>
			<option value="1">建设</option>
		</select> 创建时间:<input id="time" type="text" class="easyui-datebox"></input> <a
			href="javascript:searchAccountByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>


	<div id="dlg" class="easyui-dialog" style="width: 400px;height: 300px"
		closed=true buttons="#bt">
		<form id="fm" method="post">
			账户名称:<input name="aname" type="text" /><br /> 账户金额:<input
				name="money" type="text" /><br /> 账户类型: <select id="type"
				class="easyui-combobox" name="type" style="width:200px;"
				panelHeight="auto">
				<option value="">请选择</option>
				<option value="0">工商</option>
				<option value="1">建设</option>
			</select><br /> 账户备注:<input name="remark" type="text" /><br />
			<input name="id" id="id" type="hidden" />
		</form>
	</div>

	<div id="bt">
		<a   href="javascript:saveOrUpdateAccount()" class="easyui-linkbutton" iconCls="icon-save">保存</a> 
		<a href="javascript:closeDlg()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
