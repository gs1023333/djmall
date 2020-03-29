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
            data: {
                simpleData: {
                    enable: true,
                    idKey:"resourceId",
                    pIdKey: "parentId"
                },
                key: {
                    name: "resourceName"
                },
                keep: {
                    parent:true,
                    leaf:true
                },
            },
            view: {
                selectedMulti: false
            },
            edit: {
                enable: true,
                showRemoveBtn: false,
                showRenameBtn: false
            },
            callback: {
                beforeDrag: beforeDrag
            }
        };

        $(document).ready(function(){
            show();
            $("#add").bind("click", add);
            $("#edit").bind("click", edit);
            $("#remove").bind("click", remove);
        });

        $(function(){
            show();
        });
        function show(){
            $.post(
                "<%=request.getContextPath()%>/auth/resource/show",
                {},
                function (data){
                    $.fn.zTree.init($("#resourceTree"), setting, data.data);
                })
        }
        function beforeDrag(treeId, treeNodes) {
            return false;
        }
        function add(e) {
            var zTree = $.fn.zTree.getZTreeObj("resourceTree"),
                nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0],
                parentId = treeNode == null ? 0: treeNode.resourceId;
            layer.open({
                type: 2,
                title: '修改',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath()%>/auth/resource/toAdd?parentId='+parentId
            });

        };
        function edit() {
            var zTree = $.fn.zTree.getZTreeObj("resourceTree"),
                nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0];

            if (nodes.length == 0) {
                layer.alert("请先选择一个节点");
                return;
            }
            layer.open({
                type: 2,
                title: '修改',
                shadeClose: true,
                maxmin: true, //开启最大化最小化按钮
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath()%>/auth/resource/toUpdate?resourceId='+treeNode.resourceId
            });
        };
        function remove(e) {
            var zTree = $.fn.zTree.getZTreeObj("resourceTree"),
                nodes = zTree.getSelectedNodes(),
                treeNode = nodes[0];
            if (nodes.length == 0) {
                layer.alert("请先选择一个节点");
                return;
            }
            $.post("<%=request.getContextPath()%>/auth/resource/del",
                {"resourceId":treeNode.resourceId},
                function (data){
                    if (data.code != "200") {
                        return;
                    }
                    layer.msg(data.msg,{time:500},function () {
                        show();
                    })

                })
        };


    </SCRIPT>
</head>
<body>
<form id="fm">
        <input type="button" id="add" class="layui-btn layui-btn-warm" value="新增资源">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="edit" class="layui-btn layui-btn-warm" value="编辑">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" id="remove" class="layui-btn layui-btn-warm" value="删除"><br><br>
    <div id="resourceTree" class="ztree"></div>
</form>
</body>
</html>
