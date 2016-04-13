<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主题下所属单词</title>
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/admin-all.css">
<link rel="stylesheet" type="text/css" href="css/base.css">

<style type="text/css">
.editClass{
cursor:pointer;
}
.xeditClass{
cursor:pointer;
display: none;
}
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
<body>

<div class="panel panel-default">
   <!-- <div class="panel-heading">
    <h3 class="panel-title">当前位置 &gt;热点话题管理 &gt;<a href='control/group/list'><font color="blue">热点话题信息</font></a>&gt;<font style="font-family:'楷体';font-weight: bold; "></font></h3>
  </div>  -->
  <div class="panel-body">
  
	<form  action="<c:url value='admin/control/wordtheme/wordlist.html'/>" method="post">
	
   <!-- 查询参数 -->
    <input type="hidden" name="page">
    <input type="hidden" name="id" value="${theme.id}">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
				<td align="center" width="20%">所属主题名称</td>
				<td align="center" width="20%">单词ID</td>
				<td align="center" width="20%">单词</td>
				<td align="center" width="40%">版本信息</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageView.records }" var="entity">
			<tr>
				<td align="center"> ${theme.name }</td> 
				<td align="center">${entity.id}</td>
			
				<td align="center">${entity.content} </td>
				<td align="center">
				 ${entity.version==1?"北师大版":"" }${entity.version==2?"北京版":"" }
				${entity.version==3?"外研社新标准":"" }${entity.version==4?"外研社一年级起":"" }
				${entity.version==5?"人教版":"" }${entity.version==6?"朗文版":"" }
				${entity.book}册${entity.unit}单元第${entity.rank}个单词 
				</td>
			</tr>
		
		 </c:forEach>
		</tbody>
	</table>
		<div align="center">
		 <a href="<c:url value='admin/control/wordtheme/addUI.html'/>"  class="btn btn-info" >添加单词</a>
		</div>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
</div>
</body>
</html>