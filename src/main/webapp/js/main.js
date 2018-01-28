function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}


function logout(){
	$.messager.confirm("来自crm","确定退出当前系统?",function(r){
		if(r){
			$.messager.alert("来自crm","系统将在5秒后自动退出...");
			toLogOut(5000);
		}
	})
}


function toLogOut(time){
	setTimeout(function(){
		$.removeCookie("userIdStr");
		$.removeCookie("userName");
		$.removeCookie("trueName");
		/*$.cookie("userIdStr","",{
			expires:0
		});
		$.cookie("userName","",{
			expires:0
		});
		$.cookie("trueName","",{
			expires:0
		})*/
		window.location.href="index";
	}, time);
}


function openPasswordModifyDialog(){
	$("#dlg").dialog("open").dialog("setTitle","修改密码");
}


function modifyPassword(){
	$("#fm").form("submit",{
		url:ctx+"/user02/updateUserPassword",
		onSubmit:function(){
			return $(this).form("validate");
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				$.messager.alert("来自crm","密码更新成功,系统将在3秒后自动退出!","info");
				toLogOut(3000);
			}else{
				$.messager.alert("来自crm",data.msg,"error");
			}
		}
	})
}
