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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品类型界面</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="<c:url value='admin/control/store/addType.html'/>"  method="post" >

	   <div class="form-group col-md-3">
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">商品类型名称</div>
			   <input type="text" id="name" name="name" class="form-control" required>
			</div>
	  </div>
	  <div class="form-group col-md-3">
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">类型等级</div>
			   <input type="number" id="grade" name="grade" class="form-control" required>
			</div>
	  </div>
	  <button type="submit" class="btn btn-warning">添加</button>
	   <input type="button" value="返回" class="btn btn-info " onclick="javascript:history.go(-1);"> 
</form>
	
</body>
</html>