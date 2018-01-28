function formatterDevResult(val){
	if(val==0){
		return "未开发";
	}
	if(val==1){
		return "开发中";
	}
	
	if(val==2){
		return "开发成功";
	}
	if(val==3){
		return "开发失败";
	}
}


function searchSaleChanceByParams(){
	$("#dg").datagrid("load",{
		customerName:$("#customerName").val(),
		createMan:$("#createMan").val(),
	})
}

function formatterOp(val,row){
	var devResult=row.devResult;
	if(devResult==2||devResult==3){
		//return "查看详情";
		var href="javascript:openCusDevPlanDetailTab('客户开发详情查看_"+row.id+"',"+row.id+")";
		return "<a href="+href+">查看详情</a>";
	}
	if(devResult==1||devResult==0){
		//return  "开发";
		var href="javascript:openCusDevPlanDetailTab('开发计划项管理_"+row.id+"',"+row.id+")";
		return "<a href="+href+">开发</a>"
	}
}


$(function(){
	$("#dg").datagrid({
		rowStyler:function(index,row){
			if(row.devResult==0||row.devResult==1){
				return 'background-color:yellow';
			}
			if(row.devResult==2){
				return 'background-color:green';
			}
			if(row.devResult==3){
				return 'background-color:red';
			}
		}
	})
	
})


function openCusDevPlanDetailTab(title,sid){
	window.parent.openTab(title,ctx+"/saleChance/querySaleChanceBySid?sid="+sid);
}
