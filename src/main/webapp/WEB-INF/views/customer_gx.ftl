<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/customer.gx.js"></script>
</head>

<body>
	<table id="dg" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/customerGx/queryCustomerGxDtosByParams">
		<thead>
			<tr>
				<th field='id' checkbox=true></th>
				<th field='name'>客户名称</th>
				<th field='sum' >贡献金额(￥)</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<!-- 搜索条件配置 -->
		状态: <select
			id="state" class="easyui-combobox" name="state" style="width:200px;"
			panelHeight="auto">
			<option value="">全部</option>
			<option value="1">流失</option>
			<option value="0">未流失</option>
	          </select>
	        <a href="javascript:searchCustomerGxByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>
	
	
	
	
</body>
</html>
