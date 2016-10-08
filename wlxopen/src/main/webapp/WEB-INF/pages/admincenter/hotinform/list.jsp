<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/wlx/myc" prefix="myc" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>通知列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap_simple.jsp"></jsp:include>

</head>
<body style="position: relative;">


<div class="panel panel-default">
  <div class="panel-heading">

  <myc:navigation  model="hotinform" editState="${navigationColumnEditState}" columnName="${navigationColumnName}" columnId="${navigationColumnId}"/>
  
  </div>
  <div class="panel-body">
	<form id="newsform" action="<c:url value='control/hotinform/list.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page" value="${formbean.page}" >
    <input type="hidden" name="editState"  value="${navigationColumnEditState}">
   
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
		    <c:if test="${formbean.editState }">
			 <td  width="5%">选项</td>
			</c:if>
			<td  width="30%">通知名称</td>
			<td  width="25%">通知链接</td>
			<td  width="30%">图片</td>
			<td  width="10%">显示状态</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity" varStatus="status">
				 
			<tr>
			  <c:if test="${formbean.editState }">
				 <td> 
				 	<input type="hidden" value="${entity.id }" name="ids">
				 	<input type="checkbox" value="${status.count-1}" name="checkeds">
				 </td>
			  </c:if>
			 <c:choose>
			 	<c:when test="${formbean.editState }">
				 <td> <input type="text" name="titles" class="form-control "  value="${entity.title }" required="required"> </td>
				 <td> <input type="text" name="links" class="form-control "  value="${entity.link }" required="required"> </td>
			 	</c:when>
			 	<c:otherwise>
			 	 <td>	${entity.title } </td>
				 <td>	${entity.link } </td>
			 	</c:otherwise>
			 </c:choose>
			 
			 <td>
				 <span >
			      <img style="width:50px;height: 30px;" class="img-rounded hitimg" alt="。。。" src="control/news/lookImage.action?savePath=${entity.picPath}">
				</span>
			 </td>
			<td> 
			
				 <c:choose>
				 	<c:when test="${formbean.editState }">
						<select class="form-control" name="states" >
						  <option value="YES" ${entity.state.toString().equals("YES")?'selected':'' }>显示</option>
						  <option value="NO"  ${entity.state.toString().equals("NO")?'selected':'' }>未显示</option>
						</select>
					</c:when>
				 	<c:otherwise>
					 	<span >
					    <font color="blue">${entity.state.toString().equals("YES")?'显示':'' }</font>
					    <font color="black">${entity.state.toString().equals("YES")?'':'未显示' }</font>
					   </span>
				 	</c:otherwise>
				 </c:choose>
			    
			 </td>
			
		  </c:forEach>
		  <c:if test="${formbean.editState }">
		 <tr>
		 
			 <td >全选 &nbsp; <input type="checkbox" onclick="selectAll(this)">  </td>
			 <td colspan="5" align="center">
			     <a href="control/hotinform/addUi.action" class="btn btn-success" >添加</a>
			   	 <input type="button" class="btn btn-info" onclick="javascript:_action('hotinform','update')"	value="确认修改">
			     <input type="button" class="btn btn-warning" onclick="javascript:_action('hotinform','delete')"	value="删除">
			 </td>
		</tr>
		</c:if>
		</tbody>
	</table>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
  
</div>
<div  id="windiv">
		<img id="winimg" class="img-rounded"  alt="" src="" >
</div>
</body>
<script type="text/javascript" src="js/control/base.js"></script>

</html>