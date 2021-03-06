<%@ page import="cn.wxj.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.wxj.entity.StudyInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="yyf" uri="http://www.wxj.cn/common/" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <title>选课记录</title>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath }/">学生在线教学平台系统</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath }/adminIndex">主页</a></li>
                <li><a href="${pageContext.request.contextPath }/studentManage">学生管理</a></li>
                <li><a href="${pageContext.request.contextPath }/courseManage">课程管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath }/chooseManage">选课管理</a></li>
                <li><a href="${pageContext.request.contextPath }/index">返回学生版主页</a></li>
            </ul>
        </div><!--/.nav-collapse -->

    </div>
</nav>
<%
    List<StudyInfo> records = (List<StudyInfo>) session.getAttribute("records");
    String msg = (String) session.getAttribute("msg");
    int num = 1;
%>
<div class="container theme-showcase" role="main">

    <ol class="breadcrumb" style="margin-top: 100px">
        <li><a href="${pageContext.request.contextPath }/adminIndex">首页</a></li>
        <li class="active">选课管理</li>
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
                <th>学院专业</th>
                <th>课程编号</th>
                <th>课程名</th>
                <th>授课老师</th>
                <th>学分</th>
                <th>开课学期</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (StudyInfo s : records) {
            %>
            <tr>
                <td><%=num++%>
                </td>
                <td><%=s.getS_name()%>
                </td>
                <td><%=s.getS_id()%>
                </td>
                <td><%=s.getS_major()%>
                </td>
                <td><%=s.getC_id()%>
                </td>
                <td><%=s.getC_name()%>
                </td>
                <td><%=s.getC_belong()%>
                </td>
                <td><%=s.getC_credit()%>
                </td>
                <td><%=s.getC_time()%>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath }/delStudyInfo?id=<%=s.getId()%>" class="btn btn-danger">删除</a>
                </td>
            </tr>
            <%
                }
            %>
            <tr>
                <td colspan="9">&nbsp</td>
                <td>
                    <button class="btn btn-success" data-toggle="modal"
                            data-target="#myModal">添加选课记录
                    </button>
                </td>
            </tr>

            </tbody>
        </table>
        
        <div class="col-md-12 text-right">
			<yyf:page url="${pageContext.request.contextPath }/chooseManage"></yyf:page>
			<%-- 分页标签 --%>
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
                        添加选课记录
                    </h4>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath }/addChoose" method="post">
                        <div class="form-group">
                            <label for="stuId">学生学号</label>
                            <input type="text" class="form-control" id="stuId" name="stuId" placeholder="学号" required>
                        </div>
                        <div class="form-group">
                            <label for="clzId">课程编号</label>
                            <input type="text" class="form-control" id="clzId" name="clzId" placeholder="课程编号" required>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</body>
</html>
