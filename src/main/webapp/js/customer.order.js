function formatterState(val){
	if(val==1){
		return "已支付";
	}
	if(val==0){
		return "未支付"
	}
}

function formatterOp(val,row){
	var href="javascript:openOrderDetailDlg("+row.id+")";
	return "<a  href="+href+">查看详情</a>";
}

function openOrderDetailDlg(orderId){
	
	//console.log("订单id:"+orderId);
	
	$.ajax({
		type:"post",
		url:ctx+"/customerOrder/queryOrderInfoByOrderId",
		data:{
			oid:orderId
		},
		dataType:"json",
		success:function(data){
			if(data.state==1){
				data.state="已付款";
			}else{
				data.state="待付款";
			}
			$("#fm").form("load",data);
		}
	})
	
	$("#dg2").datagrid("load",{
		orderId:orderId
	})
	
	
	$("#dlg").dialog("open");
}


function closeOrderDetailDialog(){
	$("#dlg").dialog("close");
}
