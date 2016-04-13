<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自适应学习系统管理后台登陆</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
<script src="js/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/control/login.css">

<script type="text/javascript">
	function check() {
		var account = document.getElementById("account").value;
		var password = document.getElementById("password").value;
		if (account == "") {
			document.getElementById("error").innerHTML = "请输入账号";
			return false;
		} else
			document.getElementById("error").innerHTML = "";
		if (password == "") {
			document.getElementById("error").innerHTML = "请输入密码";
			return false;
		} else
			document.getElementById("error").innerHTML = "";
		return true;
	}
</script>

</head>
<body>
	<div id="clouds" class="stage"></div>
	<div class="loginmain"></div>

	<div class="row-fluid">
		<h1>学生</h1>
		<form method="post" action='<c:url value="/admin/login.html" />' class="form-horizontal"
			onsubmit="return check();" style="width: 500px;">
			<div align="center" class="form-group">
				<label for="inputAccount" class="col-sm-2 control-label">账号</label>
				<div class="col-sm-8">
					<input type="text" value="liang${formbean.account }"   required="required" class="form-control" id="account" name="account">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-8">
					<input type="password" value="123${formbean.password }"  required="required" class="form-control" id="password" name="password">
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">登录</button>
				<span id="error" style="color: red;">${formbean.errors.result }</span>
			</div>
		</form>
	</div>

</body>
</html>