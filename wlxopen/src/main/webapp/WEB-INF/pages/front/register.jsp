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
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
<style type="text/css">
.registerform{
	position: relative;
	top: 50px;
	left: 42%;
	
}
</style>
</head>
<body>
<div id="container">
<form action='<c:url value="/user/register.html" />' method="post" class="registerform" >
	<div class="row">
	  <span   class="col-md-2">
	      <c:if test="${!empty error}">
		    <font color="red"><c:out value="${error}"/></font>
		</c:if>
		</span>
	</div>
	<div class="row">
	<div class="form-group col-md-2">
	    <label for="username">用户名</label>
	    <input type="text" name="userName"  id="userName" class="form-control"  placeholder="3-10">
  	</div>
  	</div>
  	<div class="row">
  	<div class="form-group col-md-2">
	    <label for="password">密码</label>
	    <input  type="password" name="password"   id="password" class="form-control"  placeholder="3-10">
  	</div>
  	</div>
  	<div class="row">
  		<div class="col-md-2" align="center">
  		   <input type="submit" value="注册" class="btn btn-warning">
  		</div>
	   
	</div>
</form>
</div>
</body>
</html>