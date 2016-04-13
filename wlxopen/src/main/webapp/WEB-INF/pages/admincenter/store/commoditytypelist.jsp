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
<title>商品类型列表界面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript">

	//查询
	function topage(page)
	{
		var form = document.forms[0];
		form.page.value= page;
		form.submit();
	}
	//删除、恢复商品类型
	function makeVisible(method,id) {
		
		var form = document.forms[0];
		form.action='admin/control/store/'+method+".html";
		form.id.value=id;
		form.submit();
	}
</script>

</head>
<body>


<div class="panel panel-default">
  <%-- <div class="panel-heading">
    <h3 class="panel-title">当前位置 &gt;热点话题管理 &gt;<a href='control/group/list'><font color="blue">热点话题信息</font></a>&gt;<font style="font-family:'楷体';font-weight: bold; ">${pageView.records[0].groupChat.name}</font></h3>
  </div> --%>
  <div class="panel-body">
  
	<form  action="<c:url value='admin/control/store/listtype.html'/>" method="post">
	
   <!-- 查询参数 -->
    <input type="hidden" name="page">
    <!-- 商品类型id，商品商品类型时需要 -->
    <input type="hidden" name="id">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
				<td align="center" width="25%">商品类型ID</td>
				<td align="center" width="20%">商品类型名称</td>
				<td align="center" width="20%">类型等级</td>
				<td align="center" width="10%">包含商品数</td>
				<td align="center" width="15%">创建时间</td>
				<td align="center" width="10%">操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageView.records }" var="entity">
			<tr>
				<td align="center"><a href="<c:url value='admin/control/store/getCommoditysOfType.html?id=${entity.id }&page=1'/>">${entity.id} </a></td> 
				<td align="center">
				${entity.name}
				 </td>
				<td align="center">
				 ${entity.grade}
				 </td>
				<td align="center">
				${entity.count}
				</td>
				<td align="center">${entity.createTime} </td>
				<td align="center">
				　<c:if test="${entity.visible==true}">
					<input type="button" value="删除" class="btn btn-info btn-xs" onclick="javascript:makeVisible('disableType','${entity.id }')">
				 </c:if>
				 <c:if test="${entity.visible==false}">
					<input type="button" value="恢复" class="btn btn-success btn-xs" onclick="javascript:makeVisible('enableType','${entity.id }')">
				 </c:if> </td>
			</tr>
			
		 </c:forEach>
		</tbody>
	</table>
		<div align="center">
		 <a href="<c:url value='admin/control/store/addTypeUI.html'/>"  class="btn btn-info" >添加商品类型</a>
		</div>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
</div>
</body>
</html>