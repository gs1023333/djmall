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
				"<%=request.getContextPath()%>/auth/resource/update",
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
							parent.window.location.href="<%=request.getContextPath()%>/auth/resource/toShow";
						})
				}); 		
	}
</script>
</head>
<body>
	<form class="layui-form"  style="margin-top: 20px" id = "fm">
		<!-- 资源名称 -->
		<div class="layui-form-item" >
	    <label class="layui-form-label">资源名称</label>
	    <div class="layui-input-inline">
			<input type="text" name="resourceName"  lay-verify="required"  value = "${resource.resourceName}" class="layui-input">
	    </div>
	    </div>
		<!-- url -->
		<div class="layui-form-item" >
	    <label class="layui-form-label">url</label>
	    <div class="layui-input-inline">
			<input type="text" name="url"  lay-verify="required"  value = "${resource.url}" class="layui-input">
	    </div>
	    </div>
		<!-- 资源编码 -->
		<div class="layui-form-item" >
			<label class="layui-form-label">资源编码</label>
			<div class="layui-input-inline">
				<input type="text" name="resourceCode"  lay-verify="required"  class="layui-input" value = "${resource.resourceCode}">
			</div>
		</div>
		<!-- 类型 -->
		<div class="layui-form-item">
			<label class="layui-form-label">类型</label>
			<div class="layui-input-block">
				<select name = "type" lay-filter="类型">
						<option value = "菜单" <c:if test="${resource.type == '菜单'}">selected</c:if>>菜单</option>
						<option value = "按钮" <c:if test="${resource.type == '按钮'}">selected</c:if>>按钮</option>
				</select><br><br>
			</div>
		</div>
	    
		<input type = "hidden" name = "resourceId" value = "${resource.resourceId}"/>
		 
		<div class="layui-form-label" >
		<input type = "button" class="layui-btn layui-btn-radius layui-btn-normal"  value = "修改" onclick="update()"/>
		</div>
	</form>
</body>
</html>