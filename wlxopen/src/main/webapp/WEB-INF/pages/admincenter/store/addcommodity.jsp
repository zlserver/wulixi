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
<title>添加商品</title> 
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
<form action="<c:url value='admin/control/store/addCommodity.html'/>"  method="post" enctype="multipart/form-data" class="form-inline">
	
    
	<input type="hidden"  id="id" name="typeid"  value="${type.id }" >
	 <div class="form-group col-md-3">
			<div class="input-group col-md-12">
			   <div class="input-group-addon label-info">所属类型</div>
			   <input type="text"   id="typename" class="form-control " value="${type.name }" readonly>
			</div>
	  </div>
	     <div class="form-group col-md-3">
	     <div class="input-group col-md-12">
			   <div class="input-group-addon label-info">商品名称</div>
			   <input type="text"   id="name" name="name" class="form-control"  >
			</div>
	  </div>
	   <div class="form-group col-md-2">
	     <div class="input-group col-md-12">
			   <div class="input-group-addon label-info">金币数</div>
			   <input type="text"   id="coinCount" name="coinCount" class="form-control"  >
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