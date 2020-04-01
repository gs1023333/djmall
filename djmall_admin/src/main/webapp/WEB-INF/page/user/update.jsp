<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	layui.use('form', function(){
	    var form = layui.form;
	    form.render();
	});

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
	<form class="layui-form"  style="margin-top: 20px" id = "fm">
		<!-- 用戶名 -->
		<div class="layui-form-item" >
	    <label class="layui-form-label">用户名</label>
	    <div class="layui-input-inline">
			<input type="text" name="userName"  lay-verify="required"  value = "${u.userName}" class="layui-input">
	    </div>
	    </div>
	    
	    <!-- 手机号 -->
	    <div class="layui-form-item">
	   	<label class="layui-form-label">手机号</label>
	    <div class="layui-input-inline">
	    	<input type="text" name="userPhone" lay-verify="required"  class="layui-input" value = "${u.userPhone}">
	    </div>
	    </div>

		<!-- 邮箱号 -->
	    <div class="layui-form-item">
	   	<label class="layui-form-label">邮箱号</label>
	    <div class="layui-input-inline">
	    	<input type="text" name="userEmail" lay-verify="required"   class="layui-input" value = "${u.userEmail}">
	    </div>
	    </div>

	    <!-- 性别 -->
	    <div class="layui-form-item">
	    <label class="layui-form-label">性别</label>
	    <div class="layui-input-block">
			<input  type = "radio" title="男" name = "userSex" value = "1" <c:if test="${u.userSex == 1}">checked</c:if>/>
			<input  type = "radio" title="女" name = "userSex" value = "2" <c:if test="${u.userSex == 2}">checked</c:if>/>
	   	</div>
	 	</div>
		  
		<input type = "hidden" name = "userId" value = "${u.userId}"/>
		 
		<div class="layui-form-label" >
		<input type = "button" class="layui-btn layui-btn-radius layui-btn-normal"  value = "修改" onclick="update()"/>
		</div>
	</form>
</body>
</html>