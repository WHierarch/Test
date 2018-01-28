<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/cus.dev.plan.js"></script>
</head>

<body>
	<table id="dg" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/saleChance/querySaleChanceByParams?state=1" singleSelect=true>
		<thead>
			<tr>
				<th field='id' checkbox=true></th>
				<th field='customerName'>客户名称</th>
				<th field='chanceSource' >机会来源</th>
				<th field="linkMan">联系人</th>
				<th field='linkPhone'>联系电话</th>
				<th field="cgjl">成功几率(%)</th>
				<th field='overview'>概要</th>
				<th field='description'>描述信息</th>
				<th field='createMan'>创建人</th>
				<th field='assignMan'>分配人</th>
				<th field='assignTime'>分配时间</th>
				<th field='devResult' formatter="formatterDevResult">开发结果</th>
				<th field='createDate'>创建时间</th>
				<th field='updateDate'>更新时间</th>
				<th field='op' formatter="formatterOp">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<!-- 搜索条件配置 -->
		客户名称:<input type="text"  name="customerName" id="customerName" />
		创建人:<input type="text" name="createMan" id="createMan" /> 
	        <a href="javascript:searchSaleChanceByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>
</body>



</html>