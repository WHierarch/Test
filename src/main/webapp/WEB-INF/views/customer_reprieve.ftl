<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.edatagrid.js" ></script>
     <script type="text/javascript" src="${ctx}/js/base.js" ></script>
    <script type="text/javascript" src="${ctx}/js/customer.reprieve.js" ></script>
</head>
<body style="margin: 15px">
<div id="p" class="easyui-panel" title="流失记录信息" style="width: 700px;height: 200px;padding: 10px">
    <table cellspacing="8px">
        <input type="hidden" id="lossId" name="saleChanceId" value="${customerLoss.id}"/>
        <input type="hidden" id="state" name="state" value="${customerLoss.state}"/>
        <tr>
            <td>客户名称：</td>
            <td><input type="text" id="cusName" name="cusName" readonly="readonly" value="${(customerLoss.cusName)!""}" /></td>
            <td> </td>
            <td>客户编号</td>
            <td><input type="text" id="cusNo" name="cusNo" readonly="readonly" value="${(customerLoss.cusNo)!""}"/></td>
        </tr>
    </table>
</div>
<br/>


<table id="dg" title="暂缓措施列表" style="width:700px;height:250px"
       toolbar="#toolbar" idField="id" pagination="true" rownumbers="true" fitColumns="true" >
    <thead>
    <tr>
        <th field="cb" checkbox=true>编号</th>
        <th field="id" width="50">编号</th>
        <th field="measure" width="100" editor="{type:'validatebox',options:{required:true}}">计划内容</th>
       <th field="createDate" width="100" >创建时间</th>
        <th field="updateDate" width="100" >更新时间</th>
    </tr>
    </thead>
</table>
<div id="toolbar">
        <a href="javascript:$('#dg').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加暂缓措施</a>
        <a href="javascript:delCustomerReprieve()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除暂缓措施</a>
        <a href="javascript:saveCustomerReprieve()" class="easyui-linkbutton" iconCls="icon-save" plain="true" >保存暂缓措施</a>
        <a href="javascript:$('#dg').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true" >撤销行</a>
        <a href="javascript:confirmCustomerLossState()" class="easyui-linkbutton" iconCls="icon-undo" plain="true" >确认流失</a>
</div>
</body>
</html>