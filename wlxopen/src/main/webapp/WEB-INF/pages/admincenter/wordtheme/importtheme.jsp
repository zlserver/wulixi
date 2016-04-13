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
<title>导入单词界面</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
  <div class="panel-body">
         导入单词主题
  </div> 
<form action="<c:url value='admin/control/wordtheme/importtheme.html'/>" method="post" enctype="multipart/form-data">
	<p style="color: red;text-align:left;margin-left: 150px;">${error }</p>
	
	  <div class="form-group ">
		  	<label for="wordfile">导入execl文件</label>
		    <input type="file" id="themefile" name="themefile">
		    <p class="help-block">Example block-level help text here.</p>
	  </div>
	   <button type="submit" class="btn btn-default">添加</button>
</form>
</body>
</html>