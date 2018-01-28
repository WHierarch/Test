$(function(){
	$("#dg").edatagrid({
		url:ctx+"/cusDevPlan/queryCusDevPlansByParams?sid="+$("#saleChanceId").val(),   
	    saveUrl:ctx+"/cusDevPlan/saveCusDevPlan?saleChanceId="+$("#saleChanceId").val(),    
	    updateUrl: ctx+"/cusDevPlan/updateCusDevPlan?saleChanceId="+$("#saleChanceId").val(),
	    destroyUrl:ctx+"/cusDevPlan/deleteCusDevPlan"
	})
	
	var devResult=$("#devResult").val();
	if(devResult==2||devResult==3){
		$("#toolbar").remove();
		$("#dg").edatagrid("disableEditing");//禁用编辑状态
	}
	
})


function saveCusDevPlan(){
	$("#dg").edatagrid("saveRow");
	searchCusDevPlan();
	//$("#dg").edatagrid("load");
	
}


function searchCusDevPlan(){
	$("#dg").edatagrid("load");
}

function delCusDevPlan(){
	deleteData("dg", ctx+"/cusDevPlan/deleteCusDevPlan", searchCusDevPlan);
}


function updateSaleChanceDevResult(state){
	
	$.messager.confirm("来自crm","确认执行该操作?",function(r){
		if(r){
			$.ajax({
				type:"post",
				url:ctx+"/saleChance/updateSaleChanceDevResult",
				data:{
					saleChanceId:$("#saleChanceId").val(),
					state:state
				},
				dataType:"json",
				success:function(data){
					if(data.code==200){
						$("#toolbar").remove();
						$("#dg").edatagrid("disableEditing");//禁用编辑状态
					}else{
						$.messager.alert("来自crm",data.msg,"error");
					}
				}
			})
		}
	})
	
	
}