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
	$("#dlg").dialog("open").dialog("setTitle","添加营销记录");
}

function closeDlg(){
	$("#dlg").dialog("close");
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
	$("#fm").form("submit",{
		url:submitUrl,
		onSubmit:function(param){
			param.createMan=$.cookie("trueName");
			return $(this).form("validate");
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				closeDlg();
				searchSaleChanceByParams();
				initFormData();
			}else{
				$.messager.alert("来自crm",data.msg,"error");
			}
		}
	})
}

function openModifySaleChanceDialog(){
	var rows= $("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择修改记录!","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行操作!","info");
		return;
	}
	$("#fm").form("load",rows[0]);
	$("#dlg").dialog("open").dialog("setTitle","更新机会记录");
}


function deleteSaleChance(){
	var rows= $("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择修改记录!","info");
		return;
	}
	
	var ids="ids=";
	for(var i=0;i<rows.length;i++){
		if(i<=rows.length-2){
			ids=ids+rows[i].id+"&ids="
		}else{
			ids=ids+rows[i].id
		}
	}
	
	$.messager.confirm("来自crm","确认删除选中的"+rows.length+"条记录?",function(r){
		if(r){
			$.ajax({
				type:"post",
				url:ctx+"/saleChance/deleteSaleChance",
				data:ids,
				dataType:"json",
				success:function(data){
					if(data.code==200){
						searchSaleChanceByParams();
					}else{
						$.messager.alert("来自crm",data.msg,"error");
					}
				}
			})
		}
	})

}

$(function(){
	$("#dlg").dialog({
		onClose:function(){
			initFormData();// 初始化表单数据
		}
	})
})