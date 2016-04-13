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
<title>添加单词主题</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

</head>
<body>

<div class="panel panel-default">
	<ol class="breadcrumb">
	  <li><a href="admin/control/wordtheme/list.html">单词主题</a></li>
	  <c:forEach items="${urlParams}" var="item" >
	    <li><a href="admin/control/wordtheme/list.html?id=${item.key}">${item.value }</a></li>
	  </c:forEach>
	</ol>
  <!-- <div class="panel-body">
         添加单词主题
  </div> -->
</div>
 <form action="<c:url value='admin/control/wordtheme/add.html'/>"  method="post"  enctype="multipart/form-data" class="form-horizontal" >
  <input type="hidden"  id="parentId" name="parentId"  value="${parentId }" >
	<p style="color: red;text-align:left;margin-left: 150px;">${formbean.errors.error }</p>
	<%-- <div class="form-group">
		 <label for="parentContent" class="col-md-1 control-label">父类</label>
	    <div class="col-md-3">
	     <input type="text"   id="parentContent" class="form-control " value="${parent.content }" readonly>
	    </div>
	 </div> --%>
	 <div class="form-group">
		 <label for="number" class="col-md-1 control-label">序号</label>
	    <div class="col-md-3">
	     <input type="text"   id="number" name="number" class="form-control" value="${formbean.number }" required>
	    </div>
	  </div>
	  <div class="form-group">
		 <label for="content" class="col-md-1 control-label">主题内容</label>
	    <div class="col-md-3">
	      <input type="text"   id="content" name="content" class="form-control" value="${formbean.content }"  required >
	    </div>
	  </div>
	  
	   <div class="form-group">
		 <label for="english" class="col-md-1 control-label">英文意思</label>
	    <div class="col-md-3">
	      <input type="text"   id="english" name="english" class="form-control"  value="${formbean.english }" required >
	    </div>
	  </div>
	
	   <div class="form-group">
		 <label for="logo" class="col-md-1 control-label">主题图片</label>
	    <div class="col-md-3">
	      <input type="file"   id="logo" name="logo" class="form-control"  >
	      <p class="help-block">图片不要大于500k.</p>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-md-offset-1 col-md-4">
	      <button type="submit" class="btn btn-default">添加</button>
	      <input type="button" value="返回" class="btn btn-info " onclick="javascript:history.go(-1);">
	    </div>
	  </div>
	  
</form>
</body>
</html>