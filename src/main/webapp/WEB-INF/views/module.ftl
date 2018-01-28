<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/module.js"></script>
</head>

<body>
	<table id="dg" class="easyui-datagrid" pagination=true
		toolbar="#tb"  fit=true
		url="${ctx}/module/queryModulesByParams">
		<thead>
			<tr>
				<th field='id' checkbox=true></th>
				<!--<th field='id' >编号</th>-->
				<th field='moduleName'>模块名称</th>
				<th field='url' >模块url</th>
				<th field='moduleStyle' >模块样式</th>
				<th field="optValue">模块权限值</th>
				<th field='parentModuleName'>父级模块</th>
				<th field="grade" formatter="formatterGrade">层级</th>
				<th field='orders'>排序</th>
				<th field='createDate'>创建时间</th>
				<th field='updateDate'>更新时间</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<a href="javascript:openAddModuleDialog()" class="easyui-linkbutton"
			plain=true iconCls="icon-save">添加</a>
	    <a  href="javascript:openModifyModuleDialog()"  class="easyui-linkbutton" plain=true iconCls="icon-edit">修改</a>
	   <a  href="javascript:deleteModule()" class="easyui-linkbutton" plain=true iconCls="icon-remove">删除</a>
			  
	    
	    <br />
		<!-- 搜索条件配置 -->
		模块名称:<input type="text"  name="moduleName" id="moduleName" />
		权限值:<input type="text" name="optValue" id="optValue" /> 
		层级: <select
			id="grade" class="easyui-combobox" name="grade" style="width:200px;"
			panelHeight="auto">
			<option value="">全部</option>
			<option value="0">根级</option>
			<option value="1">一级菜单</option>
			<option value="2">二级菜单</option>
	          </select>
	        <a href="javascript:searchModuleByParams()" class="easyui-linkbutton"
			iconCls="icon-search">搜索</a>
	</div>
	
	
	
	
		<div id="dlg" class="easyui-dialog" style="width: 600px;height: 300px"
		closed=true buttons="#bt">
		<form id="fm" method="post">
			模块名称:<input name="moduleName" type="text" /> &nbsp;&nbsp;&nbsp;
			模块url:<input name="url" type="text" /><br /> 
			模块样式:<input name="module_style" type="text" /> &nbsp;&nbsp;&nbsp;
			模块权限值:<input name="optValue" type="text" /><br /> 
			排序:<input name="orders" type="text" />
			层级: <select
					id="grade02" class="easyui-combobox" name="grade" style="width:200px;"
					panelHeight="auto">
					<option value="0">根级</option>
					<option value="1">一级菜单</option>
					<option value="2">二级菜单</option>
	          </select>
	          <br/>
	         <div id="par"> 
	         上级菜单: <input id="parentId" class="easyui-combobox" name="parentId"   />  
			</div>
			<input name="id" id="id" type="hidden" />
		</form>
	</div>

	<div id="bt">
		<a   href="javascript:saveOrUpdateSaleChance()" class="easyui-linkbutton" iconCls="icon-save">保存</a> 
		<a href="javascript:closeModuleDlg()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	
	
	
	
	
</body>
</html>
