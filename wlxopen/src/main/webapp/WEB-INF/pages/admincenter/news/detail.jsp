<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/wlx/myc" prefix="myc" %>
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
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<style type="text/css">

#title{
text-align: center;
border-bottom: 1px solid gray;
}
#info{
text-align: center;
}
#article{
border-top: 1px solid gray;
padding-top: 20px;
}
</style> 
</head>
<body>
<div  class="panel panel-default">
  <div class="panel-heading">
    <myc:navigation  model="news" editState="${navigationColumnEditState}" columnName="${navigationColumnName}" columnId="${navigationColumnId}"/>  
  
  </div>
  <div class="panel-body">
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
   <div class="panel-footer">
     <c:forEach items="${news.newsFiles}" var="newsFile">
      <c:if test="${newsFile.type.toString().equals('NO_IMAGE') }">
       <a href="control/news/download.action?savePath=${newsFile.savePath}">${newsFile.originName }</a><br>
      </c:if>
     </c:forEach>
   </div>
</div>
</body>
</html>