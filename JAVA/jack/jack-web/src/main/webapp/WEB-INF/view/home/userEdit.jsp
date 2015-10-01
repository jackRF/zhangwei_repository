<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.10.2.min.js" ></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	console.log('sdfsfsf');
	$("#userForm").validate({
//		 onfocusout:false,
		 onkeyup:false,
		 
		rules:{
			name:{
				required:true
			},
			username:{
				required:true,
			    remote:{
			    	url:"common/validateField.json",
			    	type:"POST",
			    	dataType:"json",
			    	data:{field:"username",
			    		  value:function(){
			    		return $("#userForm input[name='username']").val();
			    	}}
			    }
			},
			password:{
				required:true
			}
		},
		message:{
			username:{
				remote:"sfsdfdsfsf"
			}
		}
	});
	$("#operate").on("click",function(){
		var flag=$("#userForm").valid();
		console.log(flag);
	});
})
</script>
</head>
<body>
<form id="userForm" action="userForm">
	<div>
		<span>姓名：</span>
		<div><input type="text" name="name" /></div>
	</div>
	<div>
		<span>系统用户名：</span>
		<div><input type="text" name="username" /></div>
	</div>
	<div>
		<span>密码：</span>
		<div><input type="password" name="password" /></div>
	</div>
	<div>
		  
		<input type="button" id="operate" value="保存" />
	</div>
</form>
</body>
</html>