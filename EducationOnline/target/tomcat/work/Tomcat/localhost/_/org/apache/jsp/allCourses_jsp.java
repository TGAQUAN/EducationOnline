/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-09-01 08:52:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import cn.goodym.entity.Student;
import java.util.List;
import cn.goodym.entity.Course;

public final class allCourses_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->\n");
      out.write("    <!-- Bootstrap core CSS -->\n");
      out.write("    <link href=\"static/bootstrap-3.3.5-dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    <title>所有课程</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<nav class=\"navbar navbar-inverse navbar-fixed-top\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"navbar-header\">\n");
      out.write("            <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\"\n");
      out.write("                    aria-expanded=\"false\" aria-controls=\"navbar\">\n");
      out.write("                <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("                <span class=\"icon-bar\"></span>\n");
      out.write("            </button>\n");
      out.write("            <a class=\"navbar-brand\" href=\"#\">学生在线选课系统</a>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"navbar\" class=\"collapse navbar-collapse\">\n");
      out.write("            <ul class=\"nav navbar-nav\">\n");
      out.write("                <li><a href=\"/adminIndex\">主页</a></li>\n");
      out.write("                <li><a href=\"/studentManage\">学生管理</a></li>\n");
      out.write("                <li class=\"active\"><a href=\"/courseManage\">课程管理</a></li>\n");
      out.write("                <li><a href=\"/chooseManage\">选课管理</a></li>\n");
      out.write("                <li><a href=\"/index\">返回学生版主页</a></li>\n");
      out.write("            </ul>\n");
      out.write("        </div><!--/.nav-collapse -->\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</nav>\n");

    List<Course> course = (List<Course>) session.getAttribute("courses");
    String msg = (String) session.getAttribute("msg");
    int num = 1;

      out.write("\n");
      out.write("<div class=\"container theme-showcase\" role=\"main\">\n");
      out.write("\n");
      out.write("    <ol class=\"breadcrumb\" style=\"margin-top: 100px\">\n");
      out.write("        <li><a href=\"/adminIndex\">首页</a></li>\n");
      out.write("        <li class=\"active\">课程管理</li>\n");
      out.write("    </ol>\n");
      out.write("    ");

        if (msg != null) {
            if (msg.equals("添加成功") || msg.equals("更新成功") || msg.equals("删除成功")) {
    
      out.write("\n");
      out.write("    <div class=\"alert alert-success\" style=\"margin-top: 50px\" role=\"alert\">\n");
      out.write("        ");

        } else {
        
      out.write("\n");
      out.write("        <div class=\"alert alert-danger\" style=\"margin-top: 50px\" role=\"alert\">\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("            <strong>");
      out.print(msg);
      out.write("\n");
      out.write("            </strong>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        ");

                session.setAttribute("msg", null);
            }
        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <table class=\"table table-striped\">\n");
      out.write("            <thead>\n");
      out.write("            <tr>\n");
      out.write("                <th>编号</th>\n");
      out.write("                <th>课程编号</th>\n");
      out.write("                <th>课程名</th>\n");
      out.write("                <th>开课学期</th>\n");
      out.write("                <th>课程学分</th>\n");
      out.write("                <th>授课老师</th>\n");
      out.write("                <th>开课地点</th>\n");
      out.write("                <th>已选人数/课程容量</th>\n");
      out.write("                ");
      out.write("\n");
      out.write("                <th>操作</th>\n");
      out.write("            </tr>\n");
      out.write("            </thead>\n");
      out.write("            <tbody>\n");
      out.write("            ");

                for (Course c : course) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(num++);
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(c.getId());
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(c.getName());
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(c.getTime());
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(c.getCredit());
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(c.getBelong());
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(c.getPlace());
      out.write("\n");
      out.write("                </td>\n");
      out.write("                <td>");
      out.print(c.getSelected());
      out.write(' ');
      out.write('/');
      out.write(' ');
      out.print(c.getAmount());
      out.write("\n");
      out.write("                </td>\n");
      out.write("                ");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("                ");
      out.write("\n");
      out.write("                <td><a href=\"/changeCourse?id=");
      out.print(c.getId());
      out.write("\" class=\"btn btn-primary\">修改</a>\n");
      out.write("                    <a href=\"/adminDelCourse?id=");
      out.print(c.getId());
      out.write("\" class=\"btn btn-danger\">删除</a>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td colspan=\"8\">&nbsp</td>\n");
      out.write("                <td><a href=\"addCourse.jsp\" class=\"btn btn-success\">添加课程</a></td>\n");
      out.write("            </tr>\n");
      out.write("\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/jquery-3.0.0.min.js\"></script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
