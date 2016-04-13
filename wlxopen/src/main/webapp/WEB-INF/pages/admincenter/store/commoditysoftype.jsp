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
</script>
</head>
<body>

<div class="panel panel-default">
 
  <div class="panel-body">
  
	<form  action="<c:url value='admin/control/store/addCommodity.html'/>" method="post">
	
   <!-- 查询参数 -->
    <input type="hidden" name="page">
    <input type="hidden" name="id" value="${type.id}">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
				<td align="center" width="20%">所属类型</td>
				<td align="center" width="20%">商品ID</td>
				<td align="center" width="20%">名称</td>
				<td align="center" width="10%">商品金币数</td>
				<td align="center" width="20%">创建时间</td>
				<td align="center" width="10%">操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageView.records }" var="entity">
			<tr>
				<td align="center"> ${type.name }</td> 
				<td align="center">${entity.id}</td>
				<td align="center">
				   ${entity.name}
				   <img src="${entity.savePath }" class="img-circle" style="width:30px;height: 30px;" alt="...">
				 </td>
				<td align="center">${entity.coinCount} </td>
				<td align="center">${entity.createTime} </td>
				<td align="center">
				 <input type="button" value="删除" class="btn btn-success btn-xs" onclick="javascript:makeVisible('enableType','${entity.id }')">
				</td>
				<td align="center">
				
				</td>
			</tr>
		
		 </c:forEach>
		</tbody>
	</table>
		<div align="center">
		 <a href="<c:url value='admin/control/store/addCommodityUI.html?typeid=${type.id}'/>"  class="btn btn-info" >添加商品</a>
		</div>
	</form>
  </div>
  <%--  <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div> --%>
</div>
</body>
</html>