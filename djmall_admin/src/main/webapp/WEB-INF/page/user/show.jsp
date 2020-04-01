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
	
	$(function(){
		search();
	})
	function search(){
		var index = layer.load(1); //换了种风格
		$.post(
				"<%=request.getContextPath()%>/auth/user/show",
				$("#fm").serialize(),
				function(data){
					if (data.code != "200") {
						layer.msg(data.msg, {icon: 5, time: 1000}, function(){
							layer.close(index); 
						});
						return;
					}
					layer.close(index);
					var html = "";
					for (var i = 0; i < data.data.data.length; i++) {
						var user = data.data.data[i];
						html += "<tr>"
						html += "<th><input type='checkbox' name='ids' value='"+user.userId+"'/></th>"
						html += "<th>"+user.userId+"</th>"
						html += "<th>"+user.userName+"</th>"
						html += "<th>"+user.nickName+"</th>"
						html += user.userSex == 1 ? "<th>男</th>" : "<th>女</th>"
						html += "<th>"+user.userPhone+"</th>"
						html += "<th>"+user.userEmail+"</th>"
						if (user.userLevel == 1) {
							html += "<th>管理员</th>"
						} else if (user.userLevel == 2) {
							html += "<th>商户</th>"
						} else if (user.userLevel == 3){
							html += "<th>买家</th>"
						} else if (user.userLevel == 4){
							html += "<th>无</th>"
						}
						html += user.status == 1 ? "<th>正常</th>" : "<th>未激活</th>"
						html += "<th>"+user.createTime+"</th>"
						html += user.endTime == null ? "<th>还没有登录过</th>" : "<th>"+user.endTime+"</th>"
						html += "</tr>"
					}
					$("#tbd").html(html);
				})
	}
	function update() {
		var len = $(":checkbox[name='ids']:checked").length;
		var userId = $(":checkbox[name='ids']:checked").val();
		if (len > 1) {
			layer.alert('只能选择一项!');
			return;
		}
		if (len < 1) {
			layer.alert('至少选择一项!');
			return;
		}
		layer.open({
			type: 2,
			title: '修改信息',
			shadeClose: true,
			shade: 0.38,
			area: ['480px', '90%'],
			content: '<%=request.getContextPath()%>/auth/user/toUpdate?userId='+userId
		});
	}
	function activate() {
		var len = $(":checkbox[name='ids']:checked").length;
		var userId = $(":checkbox[name='ids']:checked").val();
		if (len > 1) {
			layer.alert('只能选择一项!');
			return;
		}
		if (len < 1) {
			layer.alert('至少选择一项!');
			return;
		}
		var index = layer.load(1); //换了种风格
		layer.confirm('是否激活该用户?', function(index){
			$.post(
					"<%=request.getContextPath()%>/auth/user/activate",
					{"userId":userId},
					function(data){
						if (data.code != "200") {
							layer.msg(data.msg, {icon: 5, time: 1000}, function(){
								layer.close(index);
							});
							return;
						}
						layer.msg(data.msg, {icon: 6, time: 1000}, function(){
							layer.close(index);
							search();
						})
					});
		});
		layer.close(index);
	}

	function del() {
		var len = $(":checkbox[name='ids']:checked").length;
		var userId = $(":checkbox[name='ids']:checked").val();
		if (len < 1) {
			layer.alert('至少选择一项!');
			return;
		}
		if (len > 1) {
			layer.alert('只能选择一项!');
			return;
		}
		var index = layer.load(1); //换了种风格
		layer.confirm('是否删除该用户?', function(index){
			$.post(
					"<%=request.getContextPath()%>/auth/user/del",
					{"userId":userId},
					function(data){
						if (data.code != "200") {
							layer.msg(data.msg, {icon: 5, time: 1000}, function(){
								layer.close(index);
							});
							return;
						}
						layer.msg(data.msg, {icon: 6, time: 1000}, function(){
							layer.close(index);
							search();
						})
					});
		});
		layer.close(index);
	}
	function authorization() {
		var len = $(":checkbox[name='ids']:checked").length;
		var userId = $(":checkbox[name='ids']:checked").val();
		if (len > 1) {
			layer.alert('只能选择一项!');
			return;
		}
		if (len < 1) {
			layer.alert('至少选择一项!');
			return;
		}
		layer.open({
			type: 2,
			title: '授权',
			shadeClose: true,
			shade: 0.38,
			area: ['480px', '90%'],
			content: '<%=request.getContextPath()%>/auth/user/toAuthorization?userId='+userId
		});
	}
	function resetPassWord() {
		var len = $(":checkbox[name='ids']:checked").length;
		var userId = $(":checkbox[name='ids']:checked").val();
		if (len > 1) {
			layer.alert('只能选择一项!');
			return;
		}
		if (len < 1) {
			layer.alert('至少选择一项!');
			return;
		}
		var index = layer.load(1); //换了种风格
		$.post(
				"<%=request.getContextPath()%>/auth/user/resetPassWord",
				{"userId":userId},
				function(data){
					if (data.code != "200") {
						layer.msg(data.msg, {icon: 5, time: 1000}, function(){
							layer.close(index);
						});
						return;
					}
					layer.msg(data.msg, {icon: 6, time: 1000}, function(){
						layer.close(index);
						search();
					})
				});
	}
	function find() {
		search();
	}
</script>
</head>
<body>
	<form id="fm" align = "center">
		<input type="hidden" value="1" id="pageNo" name="pageNo">

		<input type="text" name="userName" placeholder="用戶名/邮箱号/手机号" onblur="find()"/>模糊匹配<br><br>
		角色:<c:forEach var="r" items="${list}">
				<input type="radio" value="${r.roleId}" name="userLevel" onclick="find()"/>${r.roleName}
			</c:forEach><br><Br>
		性别:<input type="radio" value="1" name="userSex" onclick="find()"/>男
			 <input type="radio" value="2" name="userSex" onclick="find()"/>女<br><Br>
		状态:<select name="status" onclick="find()">
				<option value="-1" >激活状态</option>
				<option value="1" >正常</option>
				<option value="0" >未激活</option>
			</select><br><Br>
		<br><br>
		<input type="button" value="修改" class="layui-btn layui-btn-primary layui-btn-xs" onclick="update()"/>
		<input type="button" value="激活" class="layui-btn layui-btn-primary layui-btn-xs" onclick="activate()"/>
		<input type="button" value="重置密码" class="layui-btn layui-btn-primary layui-btn-xs" onclick="resetPassWord()"/>
		<input type="button" value="删除" class="layui-btn layui-btn-primary layui-btn-xs" onclick="del()"/>
		<input type="button" value="授权" class="layui-btn layui-btn-primary layui-btn-xs" onclick="authorization()"/>
	</form>
	<table  class="layui-table"  cellpadding="10px" >
		<tr>
			<th ></th>
			<th >ID</th>
			<th >用户名</th>
			<th >昵称</th>
			<th >性别</th>
			<th >手机号</th>
			<th >邮箱号</th>
			<th >级别</th>
			<th >状态</th>
			<th >注册时间</th>
			<th >最后登录时间</th>
		</tr>
		<tbody id = "tbd">
		
		</tbody>
	</table>

</body>
</html>