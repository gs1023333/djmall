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
				"<%=request.getContextPath()%>/role/update",
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
							parent.window.location.href="<%=request.getContextPath()%>/role/toShow";
						})
				}); 		
	}
</script>
</head>
<body>
	<form class="layui-form"  style="margin-top: 20px" id = "fm">
		<!-- 角色名 -->
		<div class="layui-form-item" >
	    <label class="layui-form-label">角色名</label>
	    <div class="layui-input-inline">
			<input type="text" name="roleName"  lay-verify="required"  value = "${r.roleName}" class="layui-input">
	    </div>
	    </div>
	    
		<input type = "hidden" name = "id" value = "${r.id}"/>
		 
		<div class="layui-form-label" >
		<input type = "button" class="layui-btn layui-btn-radius layui-btn-normal"  value = "修改" onclick="update()"/>
		</div>
	</form>
</body>
</html>