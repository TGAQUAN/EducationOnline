<%@ page import="cn.wxj.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <title>首页-学生在线教学平台系统</title>
</head>
<%
    String id = null;
    String msg = null;
    if (session != null) {
        id = (String) session.getAttribute("user");
        msg = (String) session.getAttribute("msg");
    }
%>
<body role="document">
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}">学生在线教学平台系统</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/index">主页</a></li>
                <li><a href="${pageContext.request.contextPath}/showAllClasses">所有课程</a></li>
                <li><a href="${pageContext.request.contextPath}/showMyClasses">我的课程</a></li>
                <li><a href="${pageContext.request.contextPath}/adminLogin.jsp">管理员登录</a></li>
            </ul>
            <%
                if (id != null) {
            %>

            <ul class="nav navbar-nav navbar-right">
                <li><a><%=id%> 欢迎您!
                </a></li>
                <li><a id="change" href="#" data-toggle="modal"
                       data-target="#myModal">修改密码</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出登录</a></li>
            </ul>
            <%
            } else {
            %>
            <form class="navbar-form navbar-right" action="/login" method="post">
                <div class="form-group">
                    <input type="text" placeholder="学号" class="form-control" name="id" required>
                </div>
                <div class="form-group">
                    <input type="password" placeholder="密码" class="form-control" name="pwd" required>
                </div>
                <button type="submit" class="btn btn-success">登录</button>
            </form>
            <%
                }
            %>
        </div><!--/.nav-collapse -->

    </div>
</nav>

<div class="container theme-showcase" role="main">
    <%
        if (msg != null) {
            if (msg == "修改成功!") {
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
        <div class="jumbotron" style="height: 200px; color: #f0efee;">
            <h1>欢迎登陆学生在线教学管理系统!</h1>
            <p>welcome to students online education system!</p>
        </div>

        <div class="container">
            <ol class="breadcrumb" style="margin-top: 10px">
                <li class="active">热门课程</li>
            </ol>
            <!-- Example row of columns -->
            <div class="row">

                <%
                    int i=0;
                    List<Course> clzs = (List<Course>) session.getAttribute("clzs");
                    if (!(clzs == null || clzs.isEmpty())) {
                        for (Course clz : clzs) {
                            if(i==20) break;
                            i++;
                %>

                <div class="col-md-4">
                    <img src="${pageContext.request.contextPath}<%=clz.getImgurl()%>" width="360px" height="220px">
                    <h2><%=clz.getName()%>
                    </h2>
                    <p>授课老师:<%=clz.getBelong()%>
                    </p>
                    <p><%=clz.getDetail()%>
                    </p>
                    <p><a class="btn btn-default" href="${pageContext.request.contextPath}/showDetail?id=<%=clz.getId()%>" role="button">查看
                        详情 &raquo;</a></p>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        修改密码
                    </h4>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/changePwd" method="post">
                        <div class="form-group">
                            <label for="old">旧密码</label>
                            <input type="password" class="form-control" id="old" name="old" placeholder="旧密码" required>
                        </div>
                        <div class="form-group">
                            <label for="newpwd">新密码</label>
                            <input type="password" class="form-control" id="newpwd" name="newpwd" placeholder="新密码" required>
                        </div>
                        <div class="form-group">
                            <label for="newagain">确认新密码</label>
                            <input type="password" class="form-control" id="newagain" name="newagain" placeholder="再次输入新密码" required>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="提交">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                        </div>
                    </form>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</body>
</html>
