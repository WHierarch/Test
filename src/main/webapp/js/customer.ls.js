function searchCustomerLossByParams(){
	$("#dg").datagrid("load",{
		cusName:$("#cusName").val(),
		cusNo:$("#cusNo").val()
	})
}
function formatterState(val){
	if(val==0){
		return "暂缓流失";
	}
	if(val==1){
		return "确认流失";
	}
}

