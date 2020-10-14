<%@ page import="java.util.List" %>
<%@ page import="cn.wxj.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="yyf" uri="http://www.wxj.cn/common/" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <title>所有学生</title>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">学生在线教学平台系统</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/adminIndex">主页</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/studentManage">学生管理</a></li>
                <li><a href="${pageContext.request.contextPath}/courseManage">课程管理</a></li>
                <li><a href="${pageContext.request.contextPath}/chooseManage">选课管理</a></li>
                <li><a href="${pageContext.request.contextPath}/index">返回学生版主页</a></li>
            </ul>
        </div><!--/.nav-collapse -->

    </div>
</nav>
<%
    List<Student> students = (List<Student>) session.getAttribute("students");
    String msg = (String) session.getAttribute("msg");
    int num = 1;
%>
<div class="container theme-showcase" role="main">

    <ol class="breadcrumb" style="margin-top: 100px">
        <li><a href="${pageContext.request.contextPath}/adminIndex">首页</a></li>
        <li class="active">学生管理</li>
    </ol>
    <%
        if (msg != null) {
            if (msg.equals("添加成功") || msg.equals("更新成功") || msg.equals("删除成功")) {
    %>
    <div class="alert alert-success" style="margin-top: 50px" role="alert">
        <%
        } else {
        %>
        <div class="alert alert-danger" style="margin-top: 50px" role="alert">
            <%
                }
            %>
            <strong><%=msg%>
            </strong>
        </div>

        <%
                session.setAttribute("msg", null);
            }
        %>


        <table class="table table-striped">
            <thead>
            <tr>
                <th>编号</th>
                <th>学生姓名</th>
                <th>学号</th>
                <th>密码</th>
                <th>学院专业</th>
                <th>性别</th>
                <th>入学年份</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Student s : students) {
            %>
            <tr>
                <td><%=num++%>
                </td>
                <td><%=s.getName()%>
                </td>
                <td><%=s.getId()%>
                </td>
                <td><%=s.getPwd()%>
                </td>
                <td><%=s.getMajor()%>
                </td>
                <td><%=s.getSex()%>
                </td>
                <td><%=s.getYear()%>
                </td>
                <%--<td><a href="/changeStudent?id=<%=s.getId()%>" class="btn btn-primary" id="changeStu">修改</a>--%>
                <td><a href="${pageContext.request.contextPath}/changeStudent?id=<%=s.getId()%>" class="btn btn-primary" id="changeStu">修改</a>
                    <a href="${pageContext.request.contextPath}/delStudent?id=<%=s.getId()%>" class="btn btn-danger">删除</a>
                </td>
            </tr>
            <%
                }
            %>
            <tr>
                <td colspan="7">&nbsp</td>
                <td><a href="${pageContext.request.contextPath}/addStudent.jsp" class="btn btn-success">添加新学生</a></td>
            </tr>

            </tbody>
        </table>
        <div class="col-md-12 text-right">
		<yyf:page url="${pageContext.request.contextPath }/studentManage"></yyf:page>
		<%-- 分页标签 --%>
		</div>
    </div>

</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.0.0.min.js"></script>

</body>
</html>
