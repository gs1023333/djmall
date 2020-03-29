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
	<script src="<%=request.getContextPath()%>/static/slideVerify/js/jq-slideVerify.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css" media="all">
	<script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
	<script type="text/javascript">
	jQuery.validator.addMethod("isPhone", function (value, element) {
		var phone = $("input[name='userPhone']").val();
		var pattern = /^1[34578]\d{9}$/;
		return (pattern.test(phone));
	}, "请输入正确的联系人电话");

	$(function(){
		$("#fm").validate({
			rules:{
				userPhone:{
					required:true,
					isPhone:true,
					remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
						type: 'POST',
						url: "<%=request.getContextPath()%>/auth/user/findOneByPhone",
						data:{
							userPhone:function() {
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
				},
				userCode:{
					required:true,
					remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
						type: 'POST',
						url: "<%=request.getContextPath()%>/auth/user/findByPhoneAndCode",
						data:{
							userPhone:function() {
								return $("#userPhone").val();
							},
							userCode:function() {
								return $("#userCode").val();
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
				password:{
					required:true,
					maxlength:10,
					minlength:3
				},
				password1:{
					required:true,
					equalTo:"#password"
				}

			},
			messages:{
				userPhone:{
					required:"手机号不能为空！",
					isPhone:"请输入正确的联系人电话",
					remote:"该手机号未注册,请重新输入!"
				},
				userCode:{
					required:"验证码不能为空！",
					remote:"手机号或者验证码错误,请重新输入!"
				},
				password:{
					required:"请填写密码!",
					maxlength:"密码最多10位",
					minlength:"密码最少3位"
				},
				password1:{
					required:"请确认密码!",
					equalTo:"两次输入点密码不一致,请检查后重新输入"
				}
			}
		})
	})
	//验证码时间
	var countdown = 5;
	//图形状态
	var slideFinishState = "";

	//图形
	$(function(){
		console.log(parseFloat('1px'))

		var SlideVerifyPlug = window.slideVerifyPlug;
		var slideVerify = new SlideVerifyPlug('#verify-wrap',{
			wrapWidth:'250',//设置 容器的宽度 ,不设置的话，会设置成100%，需要自己在外层包层div,设置宽度，这是为了适应方便点；
			initText:'请按住滑块，123',  //设置  初始的 显示文字
			sucessText:'验证通过最右边',//设置 验证通过 显示的文字
			getSuccessState:function(res){
				//当验证完成的时候 会 返回 res 值 true，只留了这个应该够用了
				console.log(res);
				slideFinishState = slideVerify.slideFinishState;
			}
		});
		$("#resetBtn").on('click',function(){
			slideVerify.resetVerify();//可以重置 插件 回到初始状态
		})
	})


	function sendemail(){

		if ($("#userPhone").val() == null || $("#userPhone").val() == "") {
			layer.msg("请输入手机号再获取验证码", {icon: 5, time: 1000});
			return;
		}
		if(slideFinishState == false){
			layer.msg("请先完成图形验证码!", {icon: 5});
			return;
		}
		$.post(
				"<%=request.getContextPath()%>/auth/user/verify",
				$("#fm").serialize(),
				function(data){
					if (data.code != "200") {
						layer.msg(data.msg, {icon: 5, time: 1000});
						return;
					}
					layer.msg(data.msg +  "验证码有效期为五分钟,请在规定时限内进行处理!", {icon: 6, time: 1000})
				});
		var obj = $("#btn");
		settime(obj);
	}
	function settime(obj) {

		if (countdown == 0) {
			$("#validataCode").val("");
			obj.attr('disabled',false);
			//obj.removeattr("disabled");

			obj.val("免费获取验证码");
			countdown = 60;
			return;
		} else {
			obj.attr('disabled',true);
			obj.val("重新发送(" + countdown + ")");
			countdown--;
		}
		setTimeout(function() {
					settime(obj) }
				,1000)
	}

	$.validator.setDefaults({
	submitHandler: function() {
		var pwd = md5($("#password").val());
		var salt = $("#salt").val();
		$("#password").val(md5(pwd + salt));
		$("#password1").val(md5(pwd + salt));
	      var index = layer.load(1); //换了种风格
	      $.post("<%=request.getContextPath() %>/auth/user/findPwd",
			  $("#fm").serialize(),
	    		  function (data) {
						layer.close(index);
						layer.alert(data.msg, function(index){
							  icon: 1,
							  layer.close(index);
							  if (data.code != 200){
									return;
								}
								parent.window.location.href = "<%=request.getContextPath()%>/auth/user/toLogin";
							});  
		      })
		    }
		});

	function toLogin() {
		parent.window.location.href="<%=request.getContextPath()%>/auth/user/toLogin";
	}
</script>
<style type="text/css">
	.error{
		color:red
	}
</style>
</head>
<body>
	<form id="fm">
		<input type = "hidden" name = "salt" id="salt" value = "${salt}"/>
		手机号:<input type = "text" name = "userPhone" id="userPhone"/><br><br>
		图形验证码<br>
		<div class="verify-wrap" id="verify-wrap" style="float:left"></div><br><br><br><br>
		短信验证码：<input type="text" name="userCode" id="userCode"/>
		<input type="button" id="btn" value="获取验证码" class="layui-btn layui-btn-primary layui-btn-xs" onclick="sendemail()"><br><br>
		新密码:
		<input type="text" name="password"  id = "password"><br><Br>
		确认新密码:
		<input type="text" name="password1"  id = "password1"><br><Br>
		<input type = "submit" value = "确认修改" class="layui-btn layui-btn-primary layui-btn-xs"/>
		<input type = "button" value = "取消" class="layui-btn layui-btn-primary layui-btn-xs" onclick="toLogin()"/>
	</form>
</body>
</html>