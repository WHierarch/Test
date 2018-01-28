function openAssignDlg(){
	//openDlg("dlg", "服务分配");
	var rows=$("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择一条记录进行分配!","info");
		return;
	}
	
	if(rows.length>1){
		$.messager.alert("来自crm","一次只能选择一条记录进行分配!","info");
		return;
	}
	
	$("#fm").form("load",rows[0]);
	$("#dlg").dialog("open").dialog("setTitle","服务分配");
	
	
}

function closeCustomerServeAssignDialog(){
	closeDlg("dlg");
}x


function addCustomerServeAssign(){
	$("#fm").form("submit",{
		url:ctx+"/customerServe/updateCustomerServe",
		onSubmit:function(param){
			param.state="2";
			return $("#fm").form("validate");
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				$("#dlg").dialog("close");
				$("#dg").datagrid("reload");
			}else{
				$.messager.alert("来自crm",data.msg,"error");
			}
		}
	})
}