<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>物理系学生工作网站</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   /*更新验证码*/
  function changeimage(img)
     {
     /*每点击一次地址都不一样，这样浏览器就不会取缓存中的页面而是重新访问  */
        img.src = img.src+"?"+ new Date().getTime();
     }
     
     /*防止表单重复提交*/
   var iscommitted = false;
   function doSubmit()
   {
     if( !iscommitted )
     {
       iscommitted = true;
       return true;
     }
     return false;
   }
  </script>
  
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
<script src="js/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/control/login.css">
</head>     
<body>
	<div class="row-fluid">
		<h1>物理系学生工作网站</h1>
		<span id="error" style="color: red;" >${formbean.result.error }</span> 
		       
		<form method="post" action='<c:url value="/common/login.uhtml" />' class="form-horizontal" style="width: 500px;">
			<div align="center" class="form-group">
				<label for="inputAccount" class="col-sm-2 control-label">账号</label>
				<div class="col-sm-8">
					<input type="text" value="${formbean.account }"   required="required" class="form-control" id="account" name="account">
				    </div>
			</div>
			<div class="form-group">
				<label for="inputPassword" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-8">
					<input type="password" value="${formbean.password}"   required="required" class="form-control" id="password" name="password">
					</div>
			</div>
			<div class="form-group">
				<label for="checkCode" class="col-sm-2 control-label">验证码</label>
				<div class="col-sm-8">
				    <div class="col-sm-5" style="padding-left: 0px;padding-right: 0px;">
				    	<input type="text"   required="required" class="form-control" value="${formbean.checkCode }" id="checkCode" name="checkCode">
					</div>
				    <div class="col-sm-3">
				       <img alt="" src="<c:url value='/common/checkCode.uhtml'/>"  onclick="changeimage(this)"  style="cursor:hand">
		            </div>
					 </div>
				
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary">登录</button>
			</div>
		</form>
	</div>
</body>
</html>