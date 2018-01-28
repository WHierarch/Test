function searchModuleByParams(){
	$("#dg").datagrid("load",{
		moduleName:$("#moduleName").val(),
		grade:$("#grade").combobox("getValue"),
		optValue:$("#optValue").val()
	})
}

function formatterGrade(val){
	if(val==0){
		return "根级";
	}
	if(val==1){
		return "一级菜单";
	}
	if(val==2){
		return "二级菜单";
	}
}

function openAddModuleDialog(){
	openDlg("dlg", "添加模块");
}

function closeModuleDlg(){
	closeDlg("dlg");
}

$(function(){
	$("#par").hide();
	$("#grade02").combobox({
		onChange:function(newValue){
			console.log(newValue);
			if(newValue!=0){
				$("#par").show();
				loadParentModulesByGrade(newValue-1);
			}else{
				/**
				 * 隐藏下拉框
				 */
				
				$("#par").hide();
				$("#parentId").combobox("clear");
			}
		}
	})
	
})





function loadParentModulesByGrade(grade){
     $("#parentId").combobox({
    	 url:ctx+"/module/queryModulesByGrade?grade="+grade,
    	 valueField:"id",    
    	 textField:"moduleName",
    	 panelHeight:"auto"
     })
}

function saveOrUpdateSaleChance(){
	var id=$("#id").val();
	var submitUrl=ctx+"/module/saveModule";
	if(!isEmpty(id)){
		submitUrl=ctx+"/module/updateModule";
	}
	saveOrUpdateData("fm", submitUrl, "dlg", searchModuleByParams);
}


function openModifyModuleDialog(){
	openModifyDlg("dg", "fm", "dlg", "修改模块");
	
}

function deleteModule(){
	deleteData("dg", ctx+"/module/deleteModule", searchModuleByParams);
}