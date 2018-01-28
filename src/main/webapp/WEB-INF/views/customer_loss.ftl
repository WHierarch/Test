<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/customer.loss.js"></script>
</head>

<body>
	<table id="dg" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/customerLoss/queryCustomerLossesByParams">
		<thead>
			<tr>
				<th field='id' checkbox=true></th>
				<th field='cusName'>客户名称</th>
				<th field='cusNo' >客户编号</th>
				<th field="cusManager">客户经理</th>
				<th field='lastOrderTime'>最后下单日期</th>
				<th field="confirmLossTime">确认流失时间</th>
				<th field='state' formatter="formatterState">流失状态</th>
				<th field='lossReason'>流失原因</th>
				<th field='createDate'>创建时间</th>
				<th field='updateDate'>更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<!-- 搜索条件配置 -->
		客户名称:<input type="text"  name="cusName" id="cusName" />
		客户编号:<input type="text" name="cusNo" id="cusNo" /> 
	        <a href="javascript:searchCustomerLossByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>
	
	
	
	
	

</body>
</html>
