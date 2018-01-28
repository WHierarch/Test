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


function formatterOp(val,row){
	/**
	 * state==0  暂缓处理
	 * state==1  确认流失
	 */
	if(row.state==0){
		var href="javascript:openAddCustomerReprieveTab("+row.id+")";
		return "<a href="+href+">添加暂缓措施</a>";
	}
	if(row.state==1){
		return "确认流失";
	}
}

function openAddCustomerReprieveTab(lossId){
	window.parent.openTab("暂缓措施管理",ctx+"/customerLoss/queryCustomerLossByLossId?lossId="+lossId);
}
