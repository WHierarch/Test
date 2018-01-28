$(function(){
	$("#dg").edatagrid({
		url:ctx+"/customerReprieve/queryCustomerReprievesByParams?lossId="+$("#lossId").val(),
		saveUrl:ctx+"/customerReprieve/saveCustomerReprieve?lossId="+$("#lossId").val(),
		updateUrl:ctx+"/customerReprieve/updateCustomerReprieve?lossId="+$("#lossId").val()
	})
	
	var state=$("#state").val();
	if(state==1){
		$("#toolbar").remove();
		$("#dg").edatagrid("disableEditing");
	}
})


function searchCustomerReprieve(){
	$("#dg").edatagrid("load");
}
function delCustomerReprieve(){
	deleteData("dg", ctx+"/customerReprieve/deleteCustomerReprieve", searchCustomerReprieve);
}


function saveCustomerReprieve(){
	$("#dg").edatagrid("saveRow");
	searchCustomerReprieve();
}

function confirmCustomerLossState(){
	$.messager.confirm("来自crm","确认流失?",function(r){
		if(r){
			$.messager.prompt("来自crm","请输入流失原因",function(msg){
				if(msg){
					$.ajax({
						type:"post",
						url:ctx+"/customerLoss/confirmCustomerLossState",
						data:{
							lossId:$("#lossId").val(),
							msg:msg
						},
						dataType:"json",
						success:function(data){
							if(data.code==200){
								$("#toolbar").remove();
								$("#dg").edatagrid("disableEditing");
							}else{
								$.messager.alert("来自crm",data.msg,"error");
							}
						}
					
					})
				}
			})
		}
		
		
	})
}