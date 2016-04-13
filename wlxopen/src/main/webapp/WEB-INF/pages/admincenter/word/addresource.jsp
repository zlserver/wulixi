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
<title>添加单词资源</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

<script type="text/javascript">
function dropLook() {
	var form = document.forms[0];
	form.action="admin/control/word/list.html";
	form.submit();
}
</script>
</head>
<body>
单词信息
<form action="<c:url value='admin/control/wordresource/add.html'/>"  method="post" enctype="multipart/form-data" class="form-inline">
	
	<!-- 返回到原来界面是所需的条件 -->
     <!-- <input type="hidden" name="page">
    <input type="hidden" name="content">
   <input type="hidden" name="version">
    <input type="hidden" name="book">
    <input type="hidden" name="unit">
    <input type="hidden" name="rank"> -->
    
	<input type="hidden"  id="uuuid" name="uuid"  value="${word.uuid }" >
	 <div class="form-group col-md-4">
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">单词</div>
			   <input type="text"  id="content" name="content" class="form-control " value="${word.content }" readonly>
			</div>
	  </div>
	  
	   <div class="form-group col-md-4">
	    <div class="input-group col-md-12">
	        <div class="input-group-addon label-info">资源类型</div>
			 <select class="form-control" name="type" >
			  <option value="0">-----------</option>
			  <option value="1">图片</option>
			  <option value="2">绘本</option>
			  <option value="3">声音</option>
			  <option value="4">视频</option>
			</select>
		</div>
	  </div>
	 
	   <div class="form-group col-md-3">
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">上传文件</div>
			   <input type="file" id="file" name="file" class="form-control" >
			</div>
	  </div>
	  <button type="submit" class="btn btn-warning">上传</button>
	   <input type="button" value="返回" class="btn btn-info " onclick="javascript:history.go(-1);"> 
</form>
</body>
</html>