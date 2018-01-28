function saveCustomerService(){
	$("#fm").form("submit",{
		url:ctx+"/customerServe/saveCustomerServce",
		onSubmit:function(param){
			param.createPeople=$.cookie("trueName");
			return $("#fm").form("validate");
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				initFormData();
			}else{
				$.messager.alert("来自crm",data.msg,"error");
			}
		}
	})
}

function initFormData(){
	$("#serveType").combobox("clear");
	$("#customer").val("");
	$("#overview").val("");
	$("#serviceRequest").val("");
	
	
}