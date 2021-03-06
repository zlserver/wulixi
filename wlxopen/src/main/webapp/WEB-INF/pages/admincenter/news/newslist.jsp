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
<title>新闻列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap_simple.jsp"></jsp:include>
<style type="text/css">
</style>
</head>
<body style="position: relative;">
<div class="panel panel-default">
  <div class="panel-heading">
 
  <myc:navigation  model="news" editState="${navigationColumnEditState}" columnName="${navigationColumnName}" columnId="${navigationColumnId}"/>  
  </div>
  <div class="panel-body">
	<form  action="<c:url value='control/news/list.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="columnName" value="${navigationColumnName}" >
    <input type="hidden" name="page" value="${formbean.page}" >
    <input type="hidden" name="editState"  value="${navigationColumnEditState}">
    <input type="hidden" name="columnId" value="${navigationColumnId}">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<c:if test="${formbean.editState }">
			  <td  width="6%">选项</td>
			</c:if>
			<td  width="50%">新闻名称</td>
			<td  width="8%">作者</td>
			<td  width="10%">
			<select class="form-control" name="suggest" onchange="query()">
				  <option value="0" ${formbean.suggest==0?'selected':'' }>推荐状态</option>
				  <option value="1" ${formbean.suggest==1?'selected':'' }>推荐</option>
				  <option value="2"  ${formbean.suggest==2?'selected':'' }>不推荐</option>
				</select>
			</td>
			<td  width="10%">
				<select class="form-control" name="state" onchange="query()">
				  <option value=" " ${formbean.state.equals(" ")?'selected':'' }>状态</option>
				  <option value="PUBLISH" ${formbean.state.equals("PUBLISH")?'selected':'' }>发表</option>
				  <option value="WAITING"  ${formbean.state.equals("WAITING")?'selected':'' }>待发表</option>
				  <option value="CLOSE" ${formbean.state.equals("CLOSE")?'selected':'' }>已屏蔽</option>
				</select>
			</td>
			<td  width="6%">顺序</td>
			<td  width="6%">阅读量</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity" varStatus="status">
				 
			<tr>
				<c:if test="${formbean.editState }">
					 <td> 
					 	<input type="hidden" value="${entity.id }" name="columnIds">
					 	<input type="checkbox" value="${status.count-1}" name="checkeds">
					 </td>
				 </c:if>
			 <td>
			     <span >
			     <c:if test="${!formbean.editState }">
				     <a href="<c:url value='control/news/detail.action?id=${entity.id }'/>">
				       <font color="${entity.titleColor.toString()}">${entity.title}</font> 
				      </a>
				      </c:if>
				   <c:if test="${formbean.editState }">
				   
				    <a href="<c:url value='control/news/detailmain.action?id=${entity.id }&columnId=${formbean.columnId}'/>">
				       <font color="${entity.titleColor.toString()}">${entity.title}</font> 
				     </a>
				    <%-- <a href="<c:url value='control/news/listfile.action?newsId=${entity.id }'/>">
				       <font color="${entity.titleColor.toString()}">${entity.title}</font> 
				     </a> --%>
				   	<%--  <a href="<c:url value='control/news/editUi.action?id=${entity.id }&columnId=${formbean.columnId}'/>">
				       <font color="${entity.titleColor.toString()}">${entity.title}</font>
				     </a> --%>
				   </c:if>
				   <font color="#CAC7F5"> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd hh:mm" /></font>
				  
			     </span>
			 </td>
			  <td> 
				${entity.author }
			   </td>
			  <td>
			  <c:if test="${!formbean.editState }">
			   <span >
			    <font color="blue">${entity.suggest==1?"推荐":"" }</font>
			    <font color="black">${entity.suggest==2?"不推荐":"" }</font>
			   </span>
			  </c:if>

			   <c:if test="${formbean.editState }">
			    <select  class="form-control input-sm"  name="suggests" >
					  <option value="1"  ${entity.suggest==1?"selected":"" } >推荐</option>
					  <option value="2" ${entity.suggest==2?"selected":"" }>不推荐</option>
				 </select> 
			   </c:if>
			 </td>
			<td> 
			   <c:if test="${!formbean.editState }">
			   <span >
			    <font color="blue">${entity.state.toString().equals("PUBLISH")?"发表":"" }</font>
			    <font color="black">${entity.state.toString().equals("WAITING")?"待发表":"" }</font>
			    <font color="red">${entity.state.toString().equals("CLOSE")?"已屏蔽":"" }</font>
			   </span>
			  </c:if>


			   <c:if test="${formbean.editState }">
			    <select  class="form-control input-sm"  name="states" >
					  <option  value="PUBLISH"  ${entity.state.toString().equals("PUBLISH")?"selected":"" } >发表</option>
					  <option  value="WAITING" ${entity.state.toString().equals("WAITING")?"selected":"" }>待发表</option>
					  <option  value="CLOSE" ${entity.state.toString().equals("CLOSE")?"selected":"" }>已屏蔽</option>
			     </select> 
			   </c:if>
			 </td>
			 <td> 
			  <c:if test="${!formbean.editState }">
			   <span >${entity.sequence }</span>
			  </c:if>
			   <c:if test="${formbean.editState }">
			   	 <input  class="form-control  input-sm"  type="text" name="sequences" value="${entity.sequence }"> 
			   </c:if>
			 </td>
			 <td>
			   <span >${entity.readCount }</span>
			 </td>
		  </c:forEach>
		 <tr>
			 <%--  <c:if test="${!formbean.editState }">
			  <td colspan="6" align="center">
			    <a class="btn btn-primary" href="control/news/addUi.action?columnId=${formbean.columnId}">发布新闻</a>
			  </td>
			  </c:if> --%>
			   <c:if test="${formbean.editState }">
			   
			    <td >全选  <input type="checkbox" onclick="selectAll(this)">  </td>
			    <td colspan="6" align="center">
			   	  <a class="btn btn-primary" href="control/news/addUi.action?columnId=${formbean.columnId}">发布新闻</a>
			 
			   	  <input type="button" class="btn btn-info" onclick="javascript:_action('news','update')"	value="确认修改">
			      <input type="button" class="btn btn-warning" onclick="javascript:_action('news','delete')"	value="删除">
			     </td>
			   </c:if>
			 
		</tr>
		</tbody>
	</table>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
  
</div>
<script type="text/javascript" src="js/control/base.js"></script>

</body>
</html>