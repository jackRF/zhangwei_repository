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
	
})
</script>
</head>
<body>
<form action="/userForm">
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
</form>
</body>
</html>