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
<title>栏目列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<style type="text/css">
.td{
text-align: center;
}
</style>
</head>
<body>
<div class="panel panel-default">
	<ol class="breadcrumb">
	  <c:forEach items="${urlParams}" var="item"  varStatus="status">
	    <li><a href="control/column/list.html?parentId=${item.key}">${item.value }</a></li>
	    <!-- 设置父类id -->
	    <c:set var="parentId" value="${item.key}"></c:set>
	    <!-- 主题等级 ,如果是二级主题则不可以添加主题,如果当前页是一级主题下的子主题themeGrade=1，则不应该有添加主题按钮-->
	    <c:set var="themeGrade" value="${status.count}"></c:set>
	  </c:forEach>
	</ol>

  <div class="panel-body">
	<form  action="<c:url value='admin/control/word/list.html'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page">
    <input type="hidden" name="uuid">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<td  width="7%"></td>
			<td  width="7%">ID号</td>
			<td  width="7%">分类名称</td>
			<td  width="7%">分类码</td>
			<td  width="7%">分类说明</td>
			<td  width="7%">父ID</td>
			<td  width="7%">浏览程序</td>
			<td  width="7%">管理程序</td>
			<td  width="7%">组别</td>
			<td  width="7%">顺序</td>
			<td  width="7%">子类管理</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity">
			<tr style="border: 1px;border-style: solid;">
			 <td> <input type="radio" value="1"> </td>
			 <td> <input type="text" value="${entity.id }"> </td>
			 <td> <input name="name" type="text" value="${entity.name }"> </td>
			 <td> <input  name="classCode" type="text" value="${entity.classCode }"> </td>
			 <td> 
			 	 <select  name="typeDes" >
					  <option value="0" ${entity.typeDes.toString().equals("LIST_TYPE")?"selected":"" }>列表类</option>
					  <option value="1" ${entity.typeDes.toString().equals("DES_TYPE")?"selected":"" }>介绍类</option>
					  <option value="2" ${entity.typeDes.toString().equals("SYSTEM_TYPE")?"selected":"" }>系统类</option>
					  <option value="2" ${entity.typeDes.toString().equals("OTHER_TYPE")?"selected":"" }>其它类</option>
				 </select>  
			 </td>
			 <td> <input type="text" value="${entity.parent.id }"> </td>
			 <td> <input type="text" value="${entity.readUrl}"> </td>
			 <td> <input type="text" value="${entity.manageUrl}"> </td>
			 <td> <input type="text" value="${entity.groupType}"> </td>
			 <td> <input type="text" value="${entity.sequence}"> </td>
			 <td> 管理子类</td>
			</tr>
		  </c:forEach>
		</tbody>
	</table>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
  
  
   <div style="position:absolute; bottom: 5px;right: 0px;left: 0px;">
	
  <form  action="<c:url value='control/column/add.html'/>" method="post">
	<table class="table table-bordered table-striped" width="100%">
	 
	  <tbody>
	  	<tr>
	  	 <td colspan="1">大类</td>
	  	 <td>类名称</td> <td><input type="text" name="name" value="${entity.sequence}"> </td>
	  	 <td>分类码</td><td><input type="text" name="classCode"  value="${entity.sequence}"> </td>
	  	 <td>分类说明</td>
	  	 <td>
	  	    <select  name="typeDes" >
					  <option value="0" }>列表类</option>
					  <option value="1" }>介绍类</option>
					  <option value="2" }>系统类</option>
					  <option value="2" }>其它类</option>
			 </select>
	  	 </td>
	  	 <td>管理程序</td><td><input type="text" name="manageUrl"  value="${entity.sequence}"> </td>
	  	</tr>
	  	
	  	<tr>
	  	 <td colspan="1">
	  	    <a href="<c:url value='admin/control/wordtheme/importUI.html'/>" >栏目管理帮助</a>
		 </td>
	  	 <td colspan="8" align="center">
	  	 	 <a href="<c:url value='admin/control/wordtheme/importUI.html'/>"  class="btn btn-info " >添加</a>
		     <a href="<c:url value='admin/control/wordtheme/importUI.html'/>"  class="btn btn-info " >返回上一层</a>
		</td> 
	  	</tr>
	  </tbody>
	</table>
  </form>
</div>
</div>


</body>
</html>