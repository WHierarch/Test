<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/customer.serve.assign.js"></script>
</head>

<body>
	<table id="dg" title="服务分配" class="easyui-datagrid" fitColumns="true"
	pagination="true" rownumbers="true"
	url="${ctx}/customerServe/queryCustomerServesByParams?state=1"
	fit="true" toolbar="#tb">
	<thead>
		<tr>
			<th field="cb" checkbox="true" align="center"></th>
			<th field="id" width="50" align="center">编号</th>
			<th field="customer" width="50" align="center">客户名称</th>
			<th field="overview" width="50" align="center" hidden="true">概要</th>
			<th field="serveType" width="50" align="center">服务类型</th>
			<th field="serviceRequest" width="50" align="center">请求内容</th>
			<th field="createPeople" width="50" align="center">服务创建人</th>
			<th field="createDate" width="50" align="center">创建时间</th>
			<th field="updateDate" width="50" align="center">创建时间</th>
		</tr>
	</thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openAssignDlg()" class="easyui-linkbutton" iconCls="icon-add" plain="true">分配</a>
	</div>
</div>

<!--分配信息 对话框-->
<div id="dlg" class="easyui-dialog" title="服务分配" closed="true"
	style="width:800px;height:450px;padding: 10px 20px"
	buttons="#dlg-buttons">
	<form id="fm" method="post">
		<input type="hidden" id="id" name="id" />
		<table cellspacing="15px">
			<tr>
				<td>服务类型：</td>
				<td><input type="text" id="serveType" name="serveType" readonly="readonly" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>客户：</td>
				<td><input type="text" id="customer" name="customer" readonly="readonly" />
			</tr>
			<tr>
				<td>概要：</td>
				<td colspan="4"><input type="text" id="overview"
					name="overview" style="width: 419px" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>服务请求：</td>
				<td colspan="4"><textarea id="serviceRequest"
						name="serviceRequest" rows="5" cols="49" readonly="readonly"></textarea>&nbsp;<font
					color="red">*</font></td>
			</tr>
			<tr>
				<td>创建人：</td>
				<td colspan="4"><input id="createPeople" name="createPeople" readonly="readonly"></input>
				</td>
				<td>创建时间：</td>
				<td colspan="4"><input id="createDate" name="createDate" readonly="readonly"></input>
				</td>
			</tr>
			<tr>
				<td>分配给：</td>
				<td><select name="assigner" class="easyui-combobox"
					url="${ctx}/user/queryCustomerManager" valueField="userName"
					textField="userName" style="width:200px;" editable="false"
					panelHeight="auto">
				</select></td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:addCustomerServeAssign()" class="easyui-linkbutton"
		iconCls="icon-ok">保存</a> <a
		href="javascript:closeCustomerServeAssignDialog()" class="easyui-linkbutton"
		iconCls="icon-cancel">关闭</a>
</div>


</body>
</html>
