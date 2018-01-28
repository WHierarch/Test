function userLogin(){
	console.log("用户登录操作。。。");
	var userName=$("#userName").val();
	var userPwd=$("#userPwd").val();
	
	if(isEmpty(userName)){
		/**
		 * toastr
		 */
		alert("用户名不能为空!");
		return;
	}
	
	
	if(isEmpty(userPwd)){
		/**
		 * toastr
		 */
		alert("密码不能为空!");
		return;
	}
	
	var params={};
	params.userName=userName;
	params.userPwd=userPwd;
	
	$.ajax({
		type:"post",
		url:"user/userLogin",
		data:params,
		dataType:"json",
		success:function(data){
			if(data.code==200){
				/**
				 * 写入cookie
				 */
				var result=data.result;
				$.cookie("userIdStr",result.idStr);
				$.cookie("userName",result.userName);
				$.cookie("trueName",result.trueName);
				window.location.href="main";
			}else{
				alert(data.msg);
			}
		}
		
	})
	
	
	
}