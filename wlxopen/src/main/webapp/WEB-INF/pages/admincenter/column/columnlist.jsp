<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/wlx/myc" prefix="myc" %>

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

 <jsp:include page="/WEB-INF/pages/share/bootstrap_simple.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="css/control/columnlist.css">
</head>
<body style="position: relative;">
<div class="panel panel-default">
	<ol class="breadcrumb">
	
	   <!-- 当前列表的父类id -->
	   <c:set var="parentId" value="" scope="page"></c:set>
	
	  <c:forEach items="${columnNavigation}" var="item"  varStatus="status">
	    <li><a href="control/column/list.action?parentId=${item.key}&parentName=${item.value}">${item.value }</a></li>
	    
	     <!-- 当且列表父类的父类，也就是导航列表的倒数第二个，用于返回上一层使用。-->
	    <c:set var="backParentId" value="${parentId }" scope="page"></c:set>
	    
	    <!-- 当前列表的父类id -->
	    <c:set var="parentId" value="${item.key}" scope="page"></c:set>
	    <!-- 当前列表的父类名称-->
	    <c:set var="parentName" value="${item.value }" scope="page"></c:set>
	   
	  </c:forEach>
	</ol>

  <div class="panel-body">
	<form  action="<c:url value='control/column/list.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" id="parentId" name="parentId" value="${parentId}">
    <input type="hidden" name="page" value="${page }">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<!-- <td  width="10%">ID号</td> -->
			<td  width="15%">栏目名称</td>
			<td  width="13%">分类码</td>
			<td  width="12%">分类说明</td>
			
			<myc:choose>
				<myc:when test="${parentId}">
				
					<td  width="15%">浏览程序</td>
					<td  width="15%">管理程序</td>
				</myc:when>
				<myc:otherwise>
				
					<!-- <td  width="8%">组别</td> -->
					<td  width="7%">顺序</td>
				</myc:otherwise>
			</myc:choose>
			 <c:if test="${parentName.equals('导航管理')}">
			     <td  width="7%">顺序</td>
			 </c:if>
			<td  width="8%">编辑</td>
			<td  width="10%">删除</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity">
			<tr>
			 <%-- <td>
			  <span>${entity.id }</span>
			 </td>  --%> 
			 <td>
			     <span id="name${entity.id}">
			     <a onclick="nagivation('${entity.id }','${entity.name}','','')" href="javascript:void(0)">${entity.name}</a></span>
				 <input class="form-control" type="hidden" id="iname${entity.id}"  name="name" value="${entity.name}"> 
			</td>
			  <td>
			   <span id="classCode${entity.id}">${entity.classCode }</span>
			   <input  class="form-control"  type="hidden" id="iclassCode${entity.id}" name="classCode" value="${entity.classCode }"> 
			  </td>
			 <td> 
			   <span id="typeDes${entity.id}">
				   ${entity.typeDes.toString().equals("LIST_TYPE")?"列表类":"" }
				   ${entity.typeDes.toString().equals("DES_TYPE")?"介绍类":"" }
				   ${entity.typeDes.toString().equals("SYSTEM_TYPE")?"系统类":"" }
				   ${entity.typeDes.toString().equals("OTHER_TYPE")?"其它类":"" }
			   </span>
			   <select class="form-control"  style="display:none;" id="itypeDes${entity.id}" name="typeDes" >
					  <option value="0" ${entity.typeDes.toString().equals("LIST_TYPE")?"selected":"" }>列表类</option>
					  <option value="1" ${entity.typeDes.toString().equals("DES_TYPE")?"selected":"" }>介绍类</option>
					  <option value="2" ${entity.typeDes.toString().equals("SYSTEM_TYPE")?"selected":"" }>系统类</option>
					  <option value="2" ${entity.typeDes.toString().equals("OTHER_TYPE")?"selected":"" }>其它类</option>
				 </select>  
			 </td>
			 
			 <myc:choose>
				<myc:when test="${parentId}">
				 <td> 
				   <span id="readUrl${entity.id}">${entity.readUrl }</span>
				   <input  class="form-control"  type="hidden" id="ireadUrl${entity.id}" name="readUrl" value="${entity.readUrl }"> 
				 </td>
				 <td>
				   <span id="manageUrl${entity.id}">${entity.manageUrl }</span>
				   <input  class="form-control"  type="hidden" id="imanageUrl${entity.id}" name="manageUrl" value="${entity.manageUrl }"> 
				 </td>
				</myc:when>
				<myc:otherwise>
				 <td> 
				   <span id="sequence${entity.id}">${entity.sequence }</span>
				   <input  class="form-control"  type="hidden" id="isequence${entity.id}" name="sequence" value="${entity.sequence }"> 
				</td>
				</myc:otherwise>
			  </myc:choose>
			    <c:if test="${parentName.equals('导航管理')}">
			    <td> 
				   <span id="sequence${entity.id}">${entity.sequence }</span>
				   <input  class="form-control"  type="hidden" id="isequence${entity.id}" name="sequence" value="${entity.sequence }"> 
				</td>
				</c:if>
			 <td>
			  <a id="edit${entity.id}" href="javaScript:void(0)" onclick="displayEditButton('${entity.id}')" class="btn btn-info btn-xs" >编辑</a>
			   <!--  glyphicon-ok  glyphicon-pencil-->
				 <span class="glyphicon glyphicon-ok xeditClass" id="ok${entity.id}" aria-hidden="true" onclick="edit('${entity.id}')" ></span>
				 &nbsp;
				 <span class="glyphicon glyphicon-remove xeditClass" id="cancel${entity.id}" aria-hidden="true" onclick="dropEdit('${entity.id}')" ></span>
				  
			 </td>
			 <td> 
			   <input type="button"  value="删除" class="btn btn-info btn-xs" onclick="javascript:deleteColumn('${entity.id}')">&nbsp;&nbsp;
			   <c:if test="${parentName.equals('导航管理')}">
			   <myc:choose>
			   	<myc:when test="${entity.visible==true }">
			   	 <input type="button"  value="下线" class="btn btn-success btn-xs" onclick="javascript:turnColumnState('${entity.id}',false)">&nbsp;&nbsp;
			 	</myc:when>
			 	<myc:otherwise>
			 	 <input type="button"  value="上线" class="btn btn-warning btn-xs" onclick="javascript:turnColumnState('${entity.id}',true)">&nbsp;&nbsp;
			   </myc:otherwise>
			   </myc:choose>
			   </c:if>
			  </td>
			</tr>
			<tr>
			   <td colspan="10">
				<c:forEach  items="${entity.childrens }" var="child" varStatus="status" >
			      &nbsp; &nbsp;[<a id="child${entity.id }${status.count}" onclick="nagivation('${child.id }','${child.name}','${entity.id }','${entity.name}')" href="javascript:void(0)"><font color="#992222">${child.name}</font></a>]  
				</c:forEach>
			   </td>
			</tr>
		  </c:forEach>
		</tbody>
	</table>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
</div>

<div style="position:relative; bottom: 5px;right: 0px;left: 0px;">
	
  <form id="addform" action="<c:url value='control/column/add.action'/>" method="post"  onsubmit="return checkAdd()">
     <input type="hidden" name="parentId" value="${parentId }">
	<table class="table table-bordered table-striped" width="100%">
	 
	  <tbody>
	  	<tr>
	  	 <td colspan="1">
	  	  父类:
	  	 <font color="red">${parentName}</font>
	  	 </td>
	  	 <td>类名称</td> <td><input type="text" id="name" name="column.name"    required="required" class="form-control"> </td>
	  	 <td>分类码</td><td><input  type="text" id="classCode" name="column.classCode"    required="required" class="form-control"> </td>
	  	 <td>分类说明</td>
	  	 <td>
	  	    <select class="form-control" name="typeDes" >
					  <option value="0" }>列表类</option>
					  <option value="1" }>介绍类</option>
					  <option value="2" }>系统类</option>
					  <option value="2" }>其它类</option>
			 </select>
	  	 </td>
	  	 <td>管理程序</td>
	  	 <td><input type="text" name="column.manageUrl" class="form-control"> </td>
	  	</tr>
	  	
	  	<tr>
	  	 <td colspan="1">
	  	    <a href="<c:url value='columnhelp.jsp'/>" target="blank">栏目管理帮助</a>
		 </td>
	  	 <td colspan="8" align="center"> 
	  	 	<input type="submit"  class="btn btn-info "  value="添加"> 
		     <a href="<c:url value='control/column/list.action?parentId=${backParentId}'/>"  class="btn btn-info " >返回上一层</a>
		</td> 
	  	</tr>
	  </tbody>
	</table>
  </form>
</div>

<form id="nagform"  action="<c:url value='control/column/list.action'/>" method="post">

    <input type="hidden" id="parentId" name="parentId" >
    <input type="hidden" id="parentName" name="parentName" >
    <input type="hidden" id="doubleParentId" name="doubleParentId" >
    <input type="hidden" id="doubleParentName" name="doubleParentName" >
</form>
</body>
<script type="text/javascript" src="js/control/columnlist.js"></script>
</html>