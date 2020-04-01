<%--
  User: GSSS
  Date: 2020-3-27 下午 09:16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/md5-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layer-v3.1.1/layer/layer.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui-v2.5.5/layui/css/layui.css"  media="all">
    <script src="https://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#fm").validate({
                rules: {
                    userName: {
                        required: true
                    },
                    password: {
                        required: true
                    }
                },
                messages: {
                    userName: {
                        required: "请填写账号/邮箱号/手机号!"
                    },
                    password: {
                        required: "请填写密码!"
                    }
                }
            })
        })
        function getSalt() {
            $.post(
                "<%=request.getContextPath()%>/auth/user/getSalt",
                {"userName": $("#userName").val()},
                function (data) {
                    $("#salt").val(data.data);
                });
        }
        $.validator.setDefaults({
            submitHandler: function() {
                var index = layer.load(1); //换了种风格
                var pwd = md5($("#password").val());
                var salt = $("#salt").val();
                $("#password").val(md5(pwd + salt));
                $.post(
                    "<%=request.getContextPath()%>/auth/user/login",
                    $("#fm").serialize(),
                    function (data) {
                        if (data.code != "200") {
                            if (data.msg == "300") {
                                var userName = $("#userName").val();
                                layer.confirm('该账户已被重置密码,须修改密码后进行登录,将跳转登录页面', function(index){
                                    layer.open({
                                        type: 2,
                                        title: '修改密码',
                                        shadeClose: true,
                                        shade: 0.38,
                                        area: ['480px', '90%'],
                                        content: '<%=request.getContextPath()%>/auth/user/toUpdatePwd?userName='+userName
                                    });
                                });
                            }
                            return;
                            layer.msg(data.msg, {icon: 5, time: 1000}, function () {
                                layer.close(index);
                            });
                            return;
                        }
                        layer.msg(data.msg, {icon: 6, time: 1000}, function () {
                            layer.close(index);
                            window.location.href = "<%=request.getContextPath()%>/index/index";
                        })
                    });
            }
        });
        if (window.top.document.URL != document.URL) {
            window.top.location = document.URL
        }
        function add() {
            layer.open({
                type: 2,
                title: '人员注册',
                shadeClose: true,
                shade: 0.3,
                area: ['380px', '70%'],
                content: '<%=request.getContextPath()%>/auth/user/toAdd' //iframe的url
            });
        }


        function toFindPwd(){
            layer.open({
                type: 2,
                title: '找回密码页面',
                shadeClose: false,
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath()%>/auth/user/toFindPwd' //iframe的url
            });
        }
    </script>
    <style type="text/css">
        .error{
            color:red;
        }
    </style>
</head>
<body>
<form class="layui-form"  id= "fm" style="margin-top: 20px;">
    <input type="hidden" id="salt" name="salt" />
    <div class="layui-form-item" >
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="userName" id="userName"  lay-verify="required" placeholder="请输入用戶名/邮箱号/手机号" onblur="getSalt()" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="text" name="password" id="password"  lay-verify="required" placeholder="请输入密码"  class="layui-input">
        </div>
    </div>
    <div class="layui-form-label" style="margin-left: 20px"><br><br>
        <input type = "submit" class="layui-btn layui-btn-warm"  value = "登录" /><br><br>
        <input type="button" value="忘记密码" class="layui-btn layui-btn-warm" onclick="toFindPwd()"><br><br>
        <input type = "button"  class="layui-btn layui-btn-warm" value = "还没有账户吗,快去注册一个吧!"  onclick="add()"/><br><br>
    </div>
</form>
</body>
</html>
