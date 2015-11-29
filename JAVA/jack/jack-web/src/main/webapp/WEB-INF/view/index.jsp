<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
$(function(){
	console.log($("#tsfg").text(),'7897');
	//[{"userName":"sfs","pwd":"123434","name":"zhangwei"}]
	$.ajax({
		type:"POST",
		url:"common/queryUserList.json",
		data:'[34,89]',
		contentType:'application/json',
		dataType:'json',
		success:function(data){
			
		}
		
	});

	});
	localStorage.pagecount=localStorage.pagecount?Number(localStorage.pagecount)+1:1;
	$(window).on("storage",function(key){
		console.log(localStorage.pagecount,key);
		window.close();
	});
</script>

</head>
<body>
index;java
<div id="tsfg">gdfd</div>
</body>
</html>