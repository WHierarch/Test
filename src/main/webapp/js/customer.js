function searchCustomer(){
	$("#dg").datagrid("load",{
		khno:$("#s_khno").val(),
		khName:$("#s_name").val()
	})
}


function openCustomerAddDialog(){
	openDlg("dlg", "添加客户信息");
}

function closeCustomerDialog(){
	closeDlg("dlg");
}


function saveOrUpdateCustomer(){
	var submitUrl=ctx+"/customer/saveCustomer";
	if(!isEmpty($("#id").val())){
		submitUrl=ctx+"/customer/updateCustomer";
	}
	saveOrUpdateData("fm", submitUrl, "dlg", searchCustomer);
}



function openCustomerModifyDialog(){
	openModifyDlg("dg", "fm", "dlg", "修改客户信息");
}

function deleteCustomer(){
	deleteData("dg", ctx+"/customer/deleteCustomer", searchCustomer);
}


function openCustomerOtherInfo(title,state){
	var rows=$("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择客户记录!");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条客户记录!");
		return;
	}
	window.parent.openTab(title,ctx+"/customer/toCustomerInfoOtherPage?state="+state+"&cid="+rows[0].id);
}



