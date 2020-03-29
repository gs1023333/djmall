<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小页面</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/md5-min.js"></script>
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

		//手机号码验证
		jQuery.validator.addMethod("phone", function(value, element) {
			var length = value.length;
			var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
			return this.optional(element) || (length == 11 && mobile.test(value));
		}, "请正确填写手机号码");

		$(function(){
			$("#fm").validate({
				rules:{
					userName:{
						required:true,
						minlength:2,
						remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
							type: 'POST',
							url: "<%=request.getContextPath()%>/auth/user/findByName",
							data:{
								userName:function() {
									return $("#userName").val();
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
					},
					nickName:{
						required:true,
						remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
							type: 'POST',
							url: "<%=request.getContextPath()%>/auth/user/difference",
							data:{
								userName:function() {
									return $("#userName").val();
								},
								nickName:function() {
									return $("#nickName").val();
								},
								dataType:"json",
								dataFilter:function(data,type){
									if (data == 'true'){
										return true;
									}else {
										return false;
									}
								}

							}
						}
					},
					password:{
						required:true,
						minlength:3
					},
					password1:{
						required:true,
						equalTo:"#password"
					},
					userEmail:{
						required:true,
						email:true,
						remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
							type: 'POST',
							url: "<%=request.getContextPath()%>/auth/user/findByEmail",
							data:{
								userName:function() {
									return $("#userEmail").val();
							},
							dataType:"json",
							dataFilter:function(data,type){
							if (data == 'true'){
								return true;
							}else {
								return false;
								}
							 }

						}
					}
					},
					userPhone:{
						required:true,
						phone:true,
						remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
							type: 'POST',
							url: "<%=request.getContextPath()%>/auth/user/findByPhone",
							data:{
								userName:function() {
									return $("#userPhone").val();
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
					userName:{
						required:"请填写用户名!",
						minlength:"用户名最小为两位",
						remote:"该用户名已存在,请重新输入!"
					},
					nickName:{
						required:"请填写昵称!",
						remote:"该昵称与用户名重复,请重新输入!"
					},
					password:{
						required:"请填写密码!",
						minlength:"密码最少3位"
					},
					password1:{
						required:"请确认密码!",
						equalTo:"两次输入点密码不一致,请检查后重新输入"
					},
					userEmail:{
						required:"请填写邮箱号",
						email:"邮箱格式不正确,请检查!",
						remote:"该邮箱已存在,请重新输入!"
					},
					userPhone:{
						required:"请填写手机号",
						remote:"该手机号已存在,请重新输入!"
					}
				}
			})
		})
		$.validator.setDefaults({
			submitHandler: function() {
				var index = layer.load(1); //换了种风格
				var pwd = md5($("#password").val());
				var salt = $("#salt").val();
				$("#password").val(md5(pwd + salt));
				$.post(
						"<%=request.getContextPath()%>/auth/user/add",
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
								parent.window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
							})
						})
			}
		});
		function toLogin() {
			parent.window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
		}
	</script>
	<style type="text/css">
		.error{
			color:red;
	}
</style>
</head>
<body>
	<form id = "fm"  >
		<input type = "hidden" name = "status" value = "1"/>
		<input type = "hidden" name = "isDel" value = "1"/>
		<input type = "hidden" name = "salt" id="salt" value = "${salt}"/>
		用户名:
			<input type="text" name="userName" id = "userName" /><br><Br>
		昵称:
			<input type="text" name="nickName" id = "nickName" /><br><Br>
	   	密码框:
	    	<input type="text" name="password"  id = "password"><br><Br>
		确认密码:
			<input type="text" name="password1"  id = "password1"><br><Br>
	   	手机号:
	    	<input type="text" name="userPhone" id = "userPhone"><br><Br>
	   	邮箱号:
	    	<input type="text" name="userEmail" id = "userEmail"><br><Br>
		角色:
		<c:forEach var="r" items="${list}">
				<input type="radio" value="${r.roleId}" name="userLevel" <c:if test="${r.roleId == 3}">checked</c:if>/>${r.roleName}
		</c:forEach><br><Br>
		性别:
			<input type="radio" value="1" name="userSex" checked/>男
			<input type="radio" value="2" name="userSex" />女<br><Br>
		<input type="button" value="以有账号?前去登录" class="layui-btn layui-btn-primary layui-btn-xs" onclick="toLogin()"/><br><Br>
		<input type = "submit" class="layui-btn layui-btn-primary layui-btn-xs" />
	</form>
</body>
</html>