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
<title>标题</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
 <frameset rows="35,*" cols="*" frameborder="yes" border="1" framespacing="2">
	<frame frameborder="1" bordercolor="red"  src='<c:url value='control/news/menu.action?newsId=${id }&columnId=${columnId}'/>' name="menuFrame" scrolling="no"  id="menuFrame" title="menuFrame" />
	<frame src="<c:url value='control/news/editUi.action?id=${id}&columnId=${columnId}'/>" name="infoFrame" scrolling="yes" id="infoFrame" title="infoFrame" />
	
</frameset> 
</html> 
 
 