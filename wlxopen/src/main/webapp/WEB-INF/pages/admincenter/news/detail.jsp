<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <base href="<%=basePath%>">   
<title>新闻详细信息</title>       
<style type="text/css">
#container{
 margin: 10px 20px 10px 20px;
}
#title{
text-align: center;
border-bottom: 1px solid gray;
}
#info{
text-align: center;
}
#article{
border-top: 1px solid gray;
}
</style> 
</head>
<body>

<div id="container">
	<div id="title">
		<h3><font color="${news.titleColor.toString()}">${news.title}</font> </h3>
	</div>
	<div id="info">
	  <span>发表时间：<fmt:formatDate value="${news.createTime }" pattern="yyyy-MM-dd hh:mm" /> </span>  <span style="margin-left: 30px;">阅读量：${news.readCount }</span> 
	</div>
	<div id="article">
		${news.context }
	</div>
</div>
</body>
</html>