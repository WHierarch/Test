function queryRolesByParams(){
	$("#dg").datagrid("load",{
		roleName:$("#roleName").val(),
	})
}


function openAddRoleDialog(){
	openDlg("dlg", "添加角色记录");
}

function closeRoleDlg(){
	closeDlg("dlg");
}


function saveOrUpdateRole(){
	var id=$("#id").val();
	var submitUrl=ctx+"/role/saveRole";
	if(!isEmpty(id)){
		submitUrl=ctx+"/role/updateRole";
	}
	saveOrUpdateData("fm",submitUrl, "dlg", queryRolesByParams);
}


function openModifyRoleDialog(){

	openModifyDlg("dg", "fm", "dlg", "修改角色信息");
}

function deleteRole(){
	deleteData("dg", ctx+"/role/deleteRole", queryRolesByParams)
}


function initFormData(){
	$("#fm").form("clear");
}

$(function(){
	$("#dlg").dialog({
		onClose:function(){
			initFormData();
		}
	})

	//loadTreeData();//加载树形数据

 $("#tree").dialog({
		onClose:function(){
			$("#roleId").val("");
			$("#moduleIds").val("");
		}
	})
})

var zTreeObj;
function loadTreeData(rid){
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: zTreeOnCheck
			}
	};
	var zNodes;
	$.ajax({
		type:"post",
		url:ctx+"/module/queryAllModules",
		data:{
			roleId:rid
		},
		dataType:"json",
		success:function(data){
			zNodes=data;
			zTreeObj=$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	})
}

function openGrantDlg(){
	var rows= $("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择角色!","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条角色记录进行操作!","info");
		return;
	}
	loadTreeData(rows[0].id);
	$("#roleId").val(rows[0].id);
	$("#tree").dialog("open");
}


function zTreeOnCheck(){
	var nodes=  zTreeObj.getCheckedNodes(true);
	/**
	 * moduleIds=1&moduleIds=2&moduleIds=3
	 */
	console.log(nodes);
	var moduleIds="moduleIds=";
	if(nodes.length>0){
		for(var i=0;i<nodes.length;i++){
			if(i<=nodes.length-2){
				moduleIds=moduleIds+nodes[i].id+"&moduleIds=";
			}else{
				moduleIds=moduleIds+nodes[i].id;
			}
		}
		console.log(moduleIds);
		console.log($("#moduleIds").val());
	}
	$("#moduleIds").val(moduleIds);
}


function addGrant(){
	$.ajax({
		type:"post",
		url:ctx+"/role/addGrant",
		data:"rid="+$("#roleId").val()+"&"+$("#moduleIds").val(),
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.code==200){
				/*$("#roleId").val("");
				$("#moduleIds").val("");*/
				$("#tree").dialog("close");
			}
		}
	})
}



function closeAddGrantDlg(){
	$("#tree").dialog("close");
}