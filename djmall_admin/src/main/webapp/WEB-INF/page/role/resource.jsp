<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/zTree-zTree_v3-master/zTree_v3/js/jquery.ztree.exedit.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
    <SCRIPT type="text/javascript">

        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey:"resourceId",
                    pIdKey: "parentId"
                },
                key: {
                    name: "resourceName"
                }
            },
            view: {
                expandSpeed: "slow",
                showLine: false
            }

        };

        $(function(){
            show();
        });
        function show(){
            $.post(
                "<%=request.getContextPath()%>/auth/role/showResource",
                {"roleId":"${roleId}"},
                function (data){
                    $.fn.zTree.init($("#resourceTree"), setting, data.data);
                })
        }

        function relatedResource() {
            var treeObj = $.fn.zTree.getZTreeObj("resourceTree");
            var nodes = treeObj.getCheckedNodes(true);
            var ids = new Array();
            if (nodes != null) {
                for (var i = 0; i < nodes.length; i++) {
                    ids.push(nodes[i].resourceId);
                }
            }
        var index = layer.load(1);
        $.post(
            "<%=request.getContextPath()%>/auth/role/relatedResource",
            nodes != null ? {roleId:"${roleId}", resourceIds:ids} : {roleId:"${roleId}"},
            function (data) {
                layer.msg(data.msg, {time:1000}, function () {
                    layer.close(index);
                    window.location.href = '<%=request.getContextPath()%>/auth/role/toShowResource?roleId='+${roleId};
                })
            }
        );
        }
    </SCRIPT>
</head>
<body>
<form id="fm">
    <input type="hidden" value="${roleId}" name="roleId">
    <input type="button" onclick="relatedResource()" value="保存">
    <div id="resourceTree" class="ztree"></div>
</form>
</body>
</html>
