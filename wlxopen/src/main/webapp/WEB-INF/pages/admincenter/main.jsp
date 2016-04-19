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
<title>000系统管理后台</title>
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>

<link rel="stylesheet"  href="css/control/admin-all.css">
<link rel="stylesheet"  href="css/control/base.css">
<script type="text/javascript" src="js/control/main.js"></script>
<!-- <script type="text/javascript" src="js/control/chur.min.js"></script> 飘花-->
</head>
<script type="text/javascript">
function exit() {
	if( !confirm("确定退出"))
	{
		return false;
	}else{
		return true;
	}
}
</script>
<style type="text/css">
 #logindes{
 margin-top: 15px;
 padding-left: 5px;
 }
 #logindes p{
 font-size: 14px;
 }
 #logindes p font{
  color: red;
 }
</style>
<body>
	<div class="wrap">
		<!-- 头部开始 -->
		<%-- <div class="top_c">
		 <header class="main-header">
		  	 <div class="container">
		  	 	<div class="row page-header">
				     <h1 >000管理后台<small>一切都在变化</small></h1>
		  	 	</div>
		     </div>
		  </header>
			<div class="top-nav">
				<span class="btn btn-info">欢迎您，${admin.account}&nbsp;&nbsp;</span> <a href='<c:url value="/common/exit.uhtml"/>' class="btn btn-success" onclick="return exit()" ><font color="black">安全退出</font></a>
			</div>
		</div> --%>
		<!-- 头部结束 -->
		<!-- 左边菜单开始-->
		<div class="left_c left">
			<h1>
				<b>
					000面板管理<!-- <img alt="" src="images/admin.gif"> -->
				</b>
			</h1>
			<div class="acc">
			  <c:forEach items="${topColumns }" var="column">
			  	<div>
					<a class="one">${column.name }</a>
					 <ul class="kid">
						<c:forEach items="${column.childrens }" var="child">
						 <li><b class="tip"></b><a target="Conframe" href="<c:url value='${child.readUrl }'/>" >${child.name}</a></li>
						</c:forEach>                                                     
					</ul>
				</div>
			  </c:forEach>
			
				<div id="logindes" >
				 <p >登录用户:<font > ${admin.account } </font></p>
				 <p >登录时间:<font > ${admin.loginTime} </font></p>
				 <p >登录次数:<font > ${admin.loginCount } </font></p>
				 <p >发稿篇数:<font > ${admin.publishCount } </font></p>
				  <span>
				   <a href='<c:url value="/common/exit.uhtml"/>' class="btn btn-success" onclick="return exit()" ><font color="black">安全退出</font></a>
			      </span>
				 </div>
				<!-- <div id="datepicker"></div> -->
			</div>
		</div>
		<!-- 左边菜单结束 -->
		<!-- 右边框架开始 -->
		<div class="right_c"> <!-- glyphicon glyphicon-chevron-left aria-hidden="true"-->
			<div class="nav-tip"  onclick="javascript:void(0)">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> 
			</div>
		</div>
		<div class="Conframe">
			<iframe name="Conframe" id="Conframe"></iframe>
		</div>
		<!-- 右边框架结束 -->
		<div class="bottom_c">Copyright &copy;首都师范大学</div>

	</div>

</body>
</html>