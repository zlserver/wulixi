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
<title>智能问答系统登录</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
<%-- <jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include> --%>
<style type="text/css">
.loginform{
	position: relative;
	top: 50px;
	left: 42%;
	
}
</style>


</head>
<body>


<div id="container ">

<form action='<c:url value="/user/login.html" />' method="post" class="loginform" >
	<div class="row">
	  <span   class="col-md-2">
	      <c:if test="${!empty error}">
		    <font color="red"><c:out value="${error}"/></font>
		</c:if>
		</span>
	</div>

	<div class="row">
	<div class="form-group col-md-2">
	    <label for="userName">用户名</label>
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
  	<div class="col-md-1" align="center">
	  	<input type="submit" value="登录" class="btn btn-warning ">
		<a href=<c:url value="/user/registerUI.html"/> class="btn btn-info ">注册</a>
  	</div>
	</div>
</form>
</div>
<!-- 
<script type="text/javascript"  src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script> -->

</body>
</html>