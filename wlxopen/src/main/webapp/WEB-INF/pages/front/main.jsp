<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
</head>
<body>
${user.userName }登录成功
<div id="container">
 <form action='<c:url value="/search/sentence.html"/>' class="form-inline" style="margin-top: 200px;" >
	 <div class="form-group col-md-offset-4">
	    <div class="input-group">
	      <div class="input-group-addon">请输入一个问句：</div>
	      <input type="text" class="form-control" style="width: 400px;" id="text" name="text" >
	    </div>
	  </div>
      <input type="submit"  value="查询" class="btn btn-primary">                              
    </form>     
</div>
</body>
</html>