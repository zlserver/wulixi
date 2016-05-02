<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>新闻列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<style type="text/css">

</style>
<script type="text/javascript">
	//查询
	function topage(page)
	{
		var form = document.forms[0];
		form.page.value= page;
		form.submit();
	}
</script>
</head>
<body style="position: relative;">
<div class="panel panel-default">

  <div class="panel-body">
	<form  action="<c:url value='control/news/list.html'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page" ">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<td  width="70%">新闻名称</td>
			<td  width="20%">发布时间</td>
			<td  width="10%">阅读量</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity">
			<tr>
			 <td>
			     <span ><a href="<c:url value='control/news/detail.html?news.id=${entity.id }'/>">${entity.title}</a></span>
			 </td>
			  <td>
			   <span > <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd hh:mm" /> </span>
			 </td>
			
			 <td> 
			   <span >${entity.readCount }</span>
			 </td>
		  </c:forEach>
		 <tr>
			 <td colspan="3" align="center">
			 <a class="btn btn-primary" href="control/news/addUi?classCode=${formbean.classCode}">发布新闻</a>
			 </td>
		</tr>
		</tbody>
	</table>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
  
</div>

</body>
</html>