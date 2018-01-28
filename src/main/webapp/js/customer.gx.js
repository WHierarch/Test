function searchCustomerGxByParams(){
	$("#dg").datagrid("load",{
		state:$("#state").combobox("getValue")
	})
}