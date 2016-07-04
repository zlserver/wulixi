<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>物理系学生工作网站管理后台</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<frameset rows="0,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src='<c:url value='control/center/top.action'/>' name="topFrame" scrolling="no"  id="topFrame" title="topFrame" />
	<frameset cols="210,*" frameborder="yes" border="1"  bordercolor="#F5F5F5" framespacing="1">
		<frame src="<c:url value='control/center/left.action'/>"  frameborder="1" name="leftFrame" scrolling="yes" id="leftFrame" title="leftFrame" />
		<frame src="<c:url value='control/center/main.action'/>" name="mainFrame" id="mainFrame" title="mainFrame"  scrolling="yes"/>
	</frameset>
</frameset>
</html>