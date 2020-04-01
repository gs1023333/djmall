<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小页面</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
<script type="text/javascript">
	function update() {
		var index = layer.load(1); //换了种风格
		$.post(
				"<%=request.getContextPath()%>/auth/user/update",
				$("#fm").serialize(),
					function(data){
						if (data.code != "200") {
							layer.msg(data.msg, {icon: 5, time: 1000}, function(){
								layer.close(index); 
							});
							return;
						} 
						layer.msg(data.msg, {icon: 6, time: 1000}, function(){
							layer.close(index); 
							parent.window.location.href="<%=request.getContextPath()%>/auth/user/toShow";
						})
				}); 		
	}
</script>
</head>
<body>
		<form id="fm">
		<table  class="layui-table"  cellpadding="10px" >
			<tr>
				<th>编号</th>
				<th>角色名</th>
			</tr>

			<c:forEach var="r" items="${list}">
				<tr>
					<th><input type="radio" value="${r.roleId}" name="userLevel" <c:if test="${r.roleId == u.userLevel}">checked</c:if>/>${r.roleId}</th>
					<th>${r.roleName}</th>
				</tr>
			</c:forEach>
		</table>

			<input type="hidden" name="userId" value="${u.userId}"/>
			<input type="button" value="确定" onclick="update()"/>
		</form>
</body>
</html>