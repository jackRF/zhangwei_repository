<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" data-main="/app/js/main" src="/app/js/require.js"></script>


</head>
<body>
hello jsp33
<form action="">
用户登录<br/>
	<s:textfield name="username" key="username"></s:textfield>
	<s:textfield name="password" type="password" key="password"></s:textfield>
</form>
</body>
</html>