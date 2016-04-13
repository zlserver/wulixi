<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <base href="<%=basePath%>">
<base >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>闯关页面</title>
</head>
<body>
 <form action="<c:url value='/pass/control/passControll/listWordResource.html'/>"  class="form-inline">
    <%String wordId =(String)request.getAttribute("wordId");%><br>
            单词的wordId是:<%=wordId%><br><br>
    <%String ResourcePath =(String)request.getAttribute("ResourcePath");%><br>
            单词的ResourcePath是:<%=ResourcePath%><br><br>
    <p>boat对应的图片：<img src="<%=ResourcePath%>" width="128" height="128"> </p>
    <%String wordPicture1 =(String)request.getAttribute("wordPicture1");%><br>
            单词的附加图片1是: <img src="<%=wordPicture1%>" width="128" height="128">    
    <%String wordPicture2 =(String)request.getAttribute("wordPicture2");%><br>
            单词的附加图片2是:<img src="<%=wordPicture2%>" width="128" height="128">   
    
    
    
 </form>
</body>
</html>