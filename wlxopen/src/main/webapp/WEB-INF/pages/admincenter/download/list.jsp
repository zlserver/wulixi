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
	
	function _action(method) {
		//如果未选中则不操作
		var columnIds=document.getElementsByName("columnIds");
		var flage = false;
		for( var i = 0;i <columnIds.length;i++)
			if(columnIds[i].checked ){
				flage = true;
				break;
			}
		if( flage){
			var form = document.forms[0];
			form.action="control/news/"+method+".action";
			form.submit();
	    }
	}
	
	function query() {
		var form = document.forms[0];
		form.submit();
	}
</script>
</head>
<body style="position: relative;">
<div class="panel panel-default">
  <div class="panel-heading">
  	${formbean.columnName}
  
  </div>
  <div class="panel-body">
	<form  action="<c:url value='control/download/list.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page" value="${formbean.page}" >
    <input type="hidden" name="editState"  value="${formbean.editState}">
    <input type="hidden" name="columnId" value="${formbean.columnId}">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<c:if test="${formbean.editState }">
			  <td  width="3%">选项</td>
			</c:if>
			<td  width="50%">名称</td>
			<td  width="9%">发布者</td>
			<td  width="9%">是否推荐</td>
			<td  width="10%">
				<select class="form-control" name="state" onchange="query()">
				  <option value=" " ${formbean.state.equals(" ")?'selected':'' }>状态</option>
				  <option value="VALIDATE" ${formbean.state.equals("VALIDATE")?'selected':'' }>已发表</option>
				  <option value="INVALIDATE"  ${formbean.state.equals("INVALIDATE")?'selected':'' }>待发表</option>
				  <option value="DELETE" ${formbean.state.equals("DELETE")?'selected':'' }>已屏蔽</option>
				</select>
			</td>
			<td  width="6%">下载量</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity">
				 
			<tr>
				<c:if test="${formbean.editState }">
					 <td> 
					 	<input type="checkbox" value="${entity.id }" name="columnIds">
					 </td>
				 </c:if>
			 <td>
			     <span >
			     <c:if test="${!formbean.editState }">
				     <a href="<c:url value='control/news/detail.action?id=${entity.id }'/>">
				       <font color="${entity.titleColor.toString()}">${entity.originName}</font> 
				      </a>
				      <font color="#CAC7F5"> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd hh:mm" /></font>
				  </c:if>
				   <c:if test="${formbean.editState }">
				   	 <a href="<c:url value='control/news/editUi.action?id=${entity.id }&columnId=${formbean.columnId}'/>">
				       <font color="${entity.titleColor.toString()}">${entity.originName}</font> 
				     </a>
				   </c:if>
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
			    <font color="blue">${entity.state.toString().equals("VALIDATE")?"已发表":"" }</font>
			    <font color="black">${entity.state.toString().equals("INVALIDATE")?"待发表":"" }</font>
			    <font color="red">${entity.state.toString().equals("DELETE")?"已屏蔽":"" }</font>
			   </span>
			  </c:if>


			   <c:if test="${formbean.editState }">
			    <select  class="form-control input-sm"  name="states" >
					  <option  value="VALIDATE"  ${entity.state.toString().equals("VALIDATE")?"selected":"" } >已发表</option>
					  <option  value="INVALIDATE" ${entity.state.toString().equals("INVALIDATE")?"selected":"" }>待发表</option>
					  <option  value="DELETE" ${entity.state.toString().equals("DELETE")?"selected":"" }>已屏蔽</option>
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
			   <span >${entity.downloadCount }</span>
			 </td>
		  </c:forEach>
		 <tr>
			 <td colspan="6" align="center">
			   <c:if test="${formbean.editState }">
			   	  <input type="button" class="btn btn-info" onclick="javascript:_action('update')"	value="确认修改">
			      <input type="button" class="btn btn-warning" onclick="javascript:_action('delete')"	value="删除">
			   </c:if>
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