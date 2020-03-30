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
				"<%=request.getContextPath()%>/auth/role/show",
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
						var role = data.data.data[i];
						html += "<tr>"
						html += "<th>"+role.roleId+"</th>"
						html += "<th>"+role.roleName+"</th>"
						html += "<th>"
						html += "<a onclick='toShowResource("+role.roleId+")'>关联资源</a> | <a onclick='update("+role.roleId+")'>编辑</a> | <a onclick='del("+role.roleId+")'>删除</a> "
						html += "</th>"
						html += "</tr>"
					}
					$("#tbd").html(html);

				})
	}
	function update(roleId) {
		layer.open({
			type: 2,
			title: '修改信息',
			shadeClose: true,
			shade: 0.38,
			area: ['480px', '90%'],
			content: '<%=request.getContextPath()%>/auth/role/toUpdate?roleId='+roleId
		});
	}
	function toShowResource(roleId) {
		layer.open({
			type: 2,
			title: '关联资源',
			shadeClose: true,
			shade: 0.38,
			area: ['480px', '90%'],
			content: '<%=request.getContextPath()%>/auth/role/toShowResource?roleId='+roleId
		});
	}

	function del(roleId) {
		var index = layer.load(1); //换了种风格
		layer.confirm('是否删除该角色?', function(index){
			$.post(
					"<%=request.getContextPath()%>/auth/role/del",
					{"roleId":roleId},
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
	function toAdd() {
		layer.open({
			type: 2,
			title: '新增',
			shadeClose: true,
			shade: 0.38,
			area: ['480px', '90%'],
			content: '<%=request.getContextPath()%>/auth/role/toAdd'
		});
	}
</script>
</head>
<body>
	<form id="fm" align = "center">
		<br><br>
		<input type="button" value="新增" class="layui-btn layui-btn-primary layui-btn-xs" onclick="toAdd()"/>


	<table  class="layui-table"  cellpadding="10px" >
		<tr>
			<th >编号</th>
			<th >角色名</th>
			<th >操作</th>
		</tr>
		<tbody id = "tbd">
		
		</tbody>
	</table>
	</form>
</body>
</html>