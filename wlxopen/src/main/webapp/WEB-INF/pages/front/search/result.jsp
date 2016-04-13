<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
      <%String info=(String)request.getAttribute("text");%><br>
      <%String tempAnswer=(String)request.getAttribute("tempAnswer");%><br>
      <%String tempVersion=(String)request.getAttribute("tempVersion");%><br>
      <%String tempBook=(String)request.getAttribute("tempBook");%><br>
      <%String tempScene=(String)request.getAttribute("tempScene");%><br>
      <%String tempPattern=(String)request.getAttribute("tempPattern");%><br>
              
      库中相似度最高的句子是:<input type="text" style="height:30px;width:300px"name="text" value="<%=info%>"><br><br><br>
      关于这条句子的属性：<br><br>
       回答:            <%=tempAnswer%><br><br>
       教材版本:         <%=tempVersion%><br><br>
       册数:            <%=tempBook%><br><br>
       情境对话:         <%=tempScene%><br><br>
      重要句型:          <%=tempPattern%><br><br>
         

  </body>
</html>
