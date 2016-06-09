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
<frameset rows="600,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src='<c:url value='front/center/top.uhtml'/>' name="front_topFrame" scrolling="no"  id="front_topFrame" title="front_topFrame" />
	<frame src=""  frameborder="1" name="front_bodyFrame" scrolling="yes" id="front_bodyFrame" title="front_bodyFrame" />		
</frameset>
</html>