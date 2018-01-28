<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/sale.chance02.js"></script>
</head>

<body>
	<table id="dg" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/saleChance/querySaleChanceByParams">
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
				<th field='state' formatter="formatterState">分配状态</th>
				<th field='devResult' formatter="formatterDevResult">开发结果</th>
				<th field='createDate'>创建时间</th>
				<th field='updateDate'>更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
	   <#if user_permission?seq_contains("101001")>
		<a href="javascript:openAddSaleChanceDialog()" class="easyui-linkbutton"
			plain=true iconCls="icon-save">添加</a>
	   </#if>
	    <#if user_permission?seq_contains("101002")>
	    <a  href="javascript:openModifySaleChanceDialog()"  class="easyui-linkbutton" plain=true iconCls="icon-edit">修改</a>
	    </#if>
	    <#if user_permission?seq_contains("101003")>
	   <a  href="javascript:deleteSaleChance()" class="easyui-linkbutton" plain=true iconCls="icon-remove">删除</a>
			  
	    </#if>
	    
	    <br />
		<!-- 搜索条件配置 -->
		客户名称:<input type="text"  name="customerName" id="customerName" />
		创建人:<input type="text" name="createMan" id="createMan" /> 
		状态: <select
			id="state" class="easyui-combobox" name="state" style="width:200px;"
			panelHeight="auto">
			<option value="">全部</option>
			<option value="1">已分配</option>
			<option value="0">未分配</option>
	          </select>
	        <a href="javascript:searchSaleChanceByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>
	
	
	
	
	<div id="dlg" class="easyui-dialog" style="width: 600px;height: 300px"
		closed=true buttons="#bt">
		<form id="fm" method="post">
			机会来源:<input name="chanceSource" type="text" /> &nbsp;&nbsp;&nbsp;
			客户名称:<input name="customerName" type="text" /><br /> 
			联系人:<input name="linkMan" type="text" /> &nbsp;&nbsp;&nbsp;
			联系电话:<input name="linkPhone" type="text" /><br /> 
			概要:<input name="overview" type="text" />&nbsp;&nbsp;&nbsp;
			成功几率:<input name="cgjl" type="text" /><br /> 
			描述信息:<input name="description" type="text" /> &nbsp;&nbsp;&nbsp;
			分配人:
			<input  class="easyui-combobox" name="assignMan"   valueField='id'
			  textField='userName' url='${ctx}/user/queryCustomerManager' panelHeight="auto" />  
			<input name="id" id="id" type="hidden" />
			<input name="createMan" id="createMan"  type="hidden" />
		</form>
	</div>

	<div id="bt">
		<a   href="javascript:saveOrUpdateSaleChance()" class="easyui-linkbutton" iconCls="icon-save">保存</a> 
		<a href="javascript:closeDlg()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	


</body>
</html>
