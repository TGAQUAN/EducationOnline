<%@ page import="cn.wxj.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <title>新学生信息</title>
    <style>
        body {
            padding-top: 100px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        form {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">学生在线教学平台系统</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/adminIndex">主页</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/studentManage">学生管理</a></li>
                <li><a href="${pageContext.request.contextPath}/courseManage">课程管理</a></li>
                <li><a href="${pageContext.request.contextPath}/chooseManage">选课管理</a> </li>
                <li><a href="${pageContext.request.contextPath}/index">返回学生版主页</a></li>
            </ul>
        </div><!--/.nav-collapse -->

    </div>
</nav>

<div class="container">
    <form action="/addStudent" method="post">
        <h3>新学生信息</h3>
        <div class="form-group">
            <label for="id">学生学号</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="学号" required>
        </div>
        <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="姓名" required>
        </div>
        <div class="form-group">
            <label for="pwd">密码</label>
            <input type="text" class="form-control" id="pwd" name="pwd" placeholder="密码" required>
        </div>
        <div class="form-group">
            <label for="boy">性别</label>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="sex" value="男" id="boy" checked="true">男
            <input type="radio" name="sex" value="女" id="girl">女
        </div>
        <div class="form-group">
            <label for="major">学院专业</label>
            <input type="text" class="form-control" id="major" name="major" placeholder="学院专业" required>
        </div>

        <div class="form-group">
            <label for="year">学年</label>
            <input type="text" class="form-control" id="year" name="year" placeholder="学年" required>
        </div>
        <button type="submit" class="btn btn-success">提交</button>
        <a href="/studentManage" class="btn btn-default">返回</a>
    </form>
</div>
</body>
</html>
