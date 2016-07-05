<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%><!-- isErrorPage 标识为异常页面，则可以访问异常对象exception-->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>物理系学生工作网站</title>
  </head>
  <body>
  亲-_-,出现未知错误！我们会尽快维护<br/>
  <%out.println(exception.getMessage()); %>
  </body>
</html>
