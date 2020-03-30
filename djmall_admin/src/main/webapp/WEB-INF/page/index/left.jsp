<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/js/jquery.ztree.exedit.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
	<script type="text/javascript">
		var setting = {
			data: {
				simpleData: {
					enable: true,
					idKey:"resourceId",
					pIdKey: "parentId"
				},
				key: {
					name: "resourceName",
					url : "xUrl"
				},
				keep: {
					parent:true,
					leaf:true,
				},
			},
			callback: {
				onClick: function (event, treeId, treeNode) {
					if (!treeNode.isParent) {
						parent.right.location.href = "<%=request.getContextPath()%>" + treeNode.url;
					}
				}
			}
		};

		$(document).ready(function(){
			show();
			openAllTreenode();
		});
		$(function(){
			show();
		});
		/*function openAllTreenode(){

			// 获取树对象
			var treeObj = $.fn.zTree.getZTreeObj("ztree");
			/!* 获取所有树节点 *!/
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			// 展开除第一级之外的其他节点
			for (var i = 0, length_1 = nodes.length; i < length_1; i++) {
				if(nodes[i].level == 0){
					continue;
				}
				nodes[i].open = true;
			}
			//展开第一级节点
			treeObj.expandNode(nodes[0], true);

		}*/
		function show(){
			$.post(
					"<%=request.getContextPath()%>/auth/index/show",
					{},
					function (data){
						$.fn.zTree.init($("#ztree"), setting, data.data);
					})
		}
	</script>
</head>
<body>
	<div id="ztree" class="ztree" align="center">

	</div>
</body>
</html>