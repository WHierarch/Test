function queryUsersByParams(){
	$("#dg").datagrid("load",{
		userName:$("#userName").val(),
		email:$("#email").val()
	})
}


function openAddUserDialog(){
	openDlg("dlg", "添加用户记录");
}

function closeUserDlg(){
	closeDlg("dlg");
}


function saveOrUpdateUser(){
	var id=$("#id").val();
	var submitUrl=ctx+"/user/saveUser";
	if(!isEmpty(id)){
		submitUrl=ctx+"/user/updateUser";
	}
	saveOrUpdateData("fm",submitUrl, "dlg", queryUsersByParams);
}


function openModifyUserDialog(){
	
	openModifyDlg("dg", "fm", "dlg", "修改用户信息");
}

function deleteUser(){
	deleteData("dg", ctx+"/user/deleteUser", queryUsersByParams)
}
