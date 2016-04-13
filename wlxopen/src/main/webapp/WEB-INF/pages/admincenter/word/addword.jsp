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
<title>添加单词</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
单词信息
<form action="<c:url value='admin/control/word/add.html'/>"  class="form-inline">
	 <div class="form-group col-md-2">
	   <!--  <label for="version" class="col-md-3 sr-only control-label">版本</label>
	     -->
	    <div class="input-group col-md-12">
	        <div class="input-group-addon label-info">版本</div>
			 <select class="form-control" name="version" >
			  <option value="0">-----------</option>
			  <option value="1">北师大版</option>
			  <option value="2">北京版</option>
			  <option value="3">外研社新标准</option>
			  <option value="4">外研社一年级起</option>
			  <option value="5">人教版</option>
			  <option value="6">朗文版</option>
			</select>
		</div>
	  </div>
	  <div class="form-group col-md-1">
		   <!--  <label  for="book" class="col-md-3 sr-only control-label">册数</label>
		   -->
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">册数</div>
			   <input id="book" name="book" class="form-control" >
			</div>
	  </div>
	   <div class="form-group col-md-1">
		    <label  for="unit" class="col-md-3 sr-only control-label">单元</label>
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">单元</div>
			   <input id="unit" name="unit" class="form-control" >
			</div>
	  </div>
	  <div class="form-group col-md-1">
		    <label  for="rank" class="col-md-3 sr-only control-label">序号</label>
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">序号</div>
			   <input id="rank" name="rank" class="form-control" >
			</div>
	  </div>
	  <div class="form-group col-md-2">
		    <label  for="content" class="col-md-3 sr-only control-label">单词</label>
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">单词</div>
			   <input id="content" name="content" class="form-control" >
			</div>
	  </div>
	   <button type="submit" class="btn btn-default">添加</button>
</form>
</body>
</html>