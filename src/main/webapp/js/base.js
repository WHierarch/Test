/**
 *  1.变化的  参数
 *  2.不变的  执行流程
 */


/**
 * dlgId :对话框id
 * title:对话框标题
 */
function openDlg(dlgId,title){
  $("#"+dlgId).dialog("open").dialog("setTitle",title);
}

function closeDlg(dlgId){
	$("#"+dlgId).dialog("close");
}

function initFormData(formId){
	$("#"+formId).form("clear");
}

function saveOrUpdateData(formId,submitUrl,dlgId,searchParams){
	//param=param
	$("#"+formId).form("submit",{
		url:submitUrl,
		onSubmit:function(){
			//param=otherFormParams;
			//param.createMan="admin";
			return $(this).form("validate");
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				closeDlg(dlgId);
				searchParams();
				initFormData(formId);
			}else{
				$.messager.alert("来自crm",data.msg,"error");
			}
		}
	})
}


function openModifyDlg(dataGridId,formId,dlgId,title){
	var rows= $("#"+dataGridId).datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择修改记录!","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行操作!","info");
		return;
	}
	$("#"+formId).form("load",rows[0]);
	openDlg(dlgId,title);
}


function deleteData(dataGridId,delUrl,searchParams){
	var rows= $("#"+dataGridId).datagrid("getSelections");
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
				url:delUrl,
				data:ids,
				dataType:"json",
				success:function(data){
					if(data.code==200){
						searchParams();
					}else{
						$.messager.alert("来自crm",data.msg,"error");
					}
				}
			})
		}
	})

	
}
