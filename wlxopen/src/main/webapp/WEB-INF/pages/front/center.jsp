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
<title>物理系学生工作网站</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script  type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<style type="text/css">
body{
text-align: center;
}
#bodyframe{
height: 500px;
margin-left: 13px;
border-right: 2px solid rgb(223, 248, 223);
border-left: 2px solid rgb(223, 248, 223);
}
</style>   
<script type="text/javascript">
function setHigh(win) {
	var subWeb= win.contentDocument;
	var heigh=subWeb.body.scrollHeight;
	if(heigh<500)
		$(win).height(500);
	else
	   $(win).height(heigh);
}


</script>
</head>

<body>

<iframe name="topframe" width="980" id="topframe" frameborder="0"  name="win" scrolling="no" onload="this.height=this.contentWindow.document.documentElement.scrollHeight" src="<c:url value='front/center/top.uhtml'/>"></iframe>

<iframe name="bodyframe" width="962" id="bodyframe" frameborder="0"  name="win" scrolling="no" onload="setHigh(this)" src="<c:url value='front/home.uhtml'/>"></iframe>

<iframe name="bottomframe" width="980" id="bottomframe" frameborder="0"  name="bottom" scrolling="no" onload="this.height=this.contentWindow.document.documentElement.scrollHeight" src="<c:url value='front/center/bottom.uhtml'/>"></iframe>
 
</body>
</html>