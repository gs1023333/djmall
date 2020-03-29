<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function quit() {
		window.location.href = "<%=request.getContextPath()%>/user/quit";
	}
</script>
</head>
<body>
	<input type = "button" value = "退出" onclick="quit()"/><br>
	<h2 align="center">欢迎${user.userName}登陆北京点金教育平台,万元高薪,尽在点金!</h2>
	<div id="datetime" align="right" style="color:black">
		 <script>
	 			setInterval("document.getElementById('datetime').innerHTML=new Date().toLocaleString();", 1000);
	 	  </script>
	</div>
</body>
</html>