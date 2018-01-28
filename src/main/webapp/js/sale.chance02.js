function formatterState(val){
	if(val==0){
		return "未分配";
	}
	if(val==1){
		return "已分配";
	}
	return "未定义";
}


$(function(){
	$("#dg").datagrid({
		rowStyler:function(index,row){
			if(row.state==0){
				return 'background-color:red';
			}
			if(row.state==1){
				return 'background-color:green';
			}
		}
	})
	
	$("#dlg").dialog({
		onClose:function(){
			initFormData();// 初始化表单数据
		}
	})
})

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
		state:$("#state").combobox("getValue")
	})
}

function openAddSaleChanceDialog(){
	openDlg("dlg", "添加机会数据");
}

function closeAddSaleChanceDlg(){
	closeDlg("dlg");
}

function initFormData(){
	$("#fm").form("clear");
}

function saveOrUpdateSaleChance(){
	var id=$("#id").val();
	var submitUrl=ctx+"/saleChance/saveSaleChance";
	if(!isEmpty(id)){
		submitUrl=ctx+"/saleChance/updateSaleChance";
	}
	saveOrUpdateData("fm", submitUrl, "dlg",searchSaleChanceByParams);
}

function openModifySaleChanceDialog(){
	openModifyDlg("dg", "fm", "dlg", "更新机会记录");
}


function deleteSaleChance(){
	deleteData("dg", ctx+"/saleChance/deleteSaleChance", searchSaleChanceByParams);
}

