<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小页面</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript">
	layui.use('form', function(){
	    var form = layui.form;
		form.render();
	});

	$(function(){
		$("#fm").validate({
			rules:{
				roleName:{
					required:true,
					minlength:2,
					remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
	                    type: 'POST',
	                    url: "<%=request.getContextPath()%>/auth/role/findByName",
	                    data:{
							roleName:function() {
	                    		return $("#roleName").val();
	                	},
	                    dataType:"json",
	                    dataFilter:function(data,type){
	                    if (data == 'true'){
	                    	return true;	
	                    }else {
	                    	return false	;
	                    	}
	                   	 }
	                  
	                }
				}
				}
			},
			messages:{
				roleName:{
					required:"请填写角色名!",
					minlength:"用户名最小为两位",
					remote:"该用户名已存在,请重新输入!"
				}
			}
		})
	})
	$.validator.setDefaults({
		submitHandler: function() {
			var index = layer.load(1); //换了种风格
			$.post(
					"<%=request.getContextPath()%>/auth/role/add",
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
							parent.window.location.href="<%=request.getContextPath()%>/auth/role/toShow";
						})
					})
    	}
	});

</script>
<style type="text/css">
	.error{
		color:red;
	}
</style>
</head>
<body>
	<form id = "fm"  >
		<input type = "hidden" name = "isDel" value = "1"/>
		角色名:
			<input type="text" name="roleName" id = "roleName" /><br><Br>
		<input type = "submit" />
	</form>
</body>
</html>