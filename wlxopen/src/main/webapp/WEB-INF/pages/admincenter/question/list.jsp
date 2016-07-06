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
<title>留言列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<style type="text/css">

</style>
<script type="text/javascript">
	//查询
	function topage(page){
   		var form = document.forms[0];
		form.page.value= page;
		form.submit();
	}
	
	function _action(method) {
		//如果未选中则不操作
		var checkeds=document.getElementsByName("checkeds");
		var flage = false;
		for( var i = 0;i <checkeds.length;i++)
			if(checkeds[i].checked ){
				flage = true;
				break;
			}
		if( flage){
			if( method=="delete")
			{
				if( !confirm("确定删除"))
				{
					return false;
				}
			}
			var form = document.forms[0];
			form.action="control/question/"+method+".action";
			          
			form.submit();
	    }
	}
	
	function query() {
		var form = document.forms[0];
		form.submit();
	}
	/* 全选 */
	function selectAll(checkNode){
		var checkeds=document.getElementsByName("checkeds");
		var state=checkNode.checked;
		for( var i = 0;i <checkeds.length;i++)
		  checkeds[i].checked=state;
	}
</script>
</head>
<body style="position: relative;">


<div class="panel panel-default">
  <div class="panel-heading">
  	<a href="control/question/list.action?columnId=${navigationColumnId}&editState=${navigationColumnEditState}&columnName=${navigationColumnName}">
  	${navigationColumnName}
  </a>
  </div>
  <div class="panel-body">
	<form  action="<c:url value='control/question/list.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page" value="${formbean.page}" >
    <input type="hidden" name="editState"  value="${navigationColumnEditState}">
    <input type="hidden" name="columnId" value="${navigationColumnId}">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<c:if test="${formbean.editState }">
			  <td  width="6%">选项</td>
			</c:if>
			<td  width="40%">问题</td>
			<td  width="9%">提问者</td>
			<td  width="8%">
				回答者
			</td>
			<td  width="9%">
				<select class="form-control" name="hot" onchange="query()">
				  <option value=" " ${formbean.hot.equals(" ")?'selected':'' }>热点状态</option>
				  <option value="YES" ${formbean.hot.equals("YES")?'selected':'' }>热点</option>
				  <option value="NO"  ${formbean.hot.equals("NO")?'selected':'' }>非热点</option>
				</select>
			</td>
			<td  width="9%">
			  <select class="form-control" name="handle" onchange="query()">
				  <option value=" " ${formbean.handle.equals(" ")?'selected':'' }>回答状态</option>
				  <option value="YES" ${formbean.handle.equals("YES")?'selected':'' }>已回答</option>
				  <option value="NO"  ${formbean.handle.equals("NO")?'selected':'' }>未回答</option>
				</select>
			</td>
			<td  width="9%">
			<select class="form-control" name="visible" onchange="query()">
				  <option value=" " ${formbean.visible.equals(" ")?'selected':'' }>显示状态</option>
				  <option value="YES" ${formbean.visible.equals("YES")?'selected':'' }>显示</option>
				  <option value="NO"  ${formbean.visible.equals("NO")?'selected':'' }>不显示</option>
				</select>
			</td>
			
			
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
			 <td>
			    <span class="badge">${status.count}</span>
			      ${entity.title }
			     <font color="#CAC7F5"> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd hh:mm" /></font>
			 </td>
			  <td> 
				${entity.identity }
			   </td>
			   <td>${entity.account }</td>
			  <td>
			  <c:if test="${!formbean.editState }">
			   <span >
			    <font color="blue">${entity.hot.toString().equals("YES")?"热点":"" }</font>
			    <font color="red">${entity.hot.toString().equals("NO")?"非热点":"" }</font>
			   </span>
			  </c:if>

			   <c:if test="${formbean.editState }">
			    <select  class="form-control input-sm"  name="hots" >
					  <option value="YES"   ${entity.hot.toString().equals("YES")?"selected":"" }>热点</option>
					  <option value="NO" ${entity.hot.toString().equals("NO")?"selected":"" }>非热点</option>
				 </select> 
			   </c:if>
			 </td>
			  <td> 
			   <span >
			    <font color="blue">${entity.handle.toString().equals("YES")?"已回答":"" }</font>
			    <font color="red">${entity.handle.toString().equals("NO")?"未回答":"" }</font>
			   </span>
			 </td>
			 <td> 
			 <c:if test="${!formbean.editState }">
			   <span >
			    <font color="blue">${entity.visible.toString().equals("YES")?"显示":"" }</font>
			    <font color="red">${entity.visible.toString().equals("NO")?"不显示":"" }</font>
			   </span>
			  </c:if>

			   <c:if test="${formbean.editState }">
			    <select  class="form-control input-sm"  name="visibles" >
					  <option value="YES"   ${entity.visible.toString().equals("YES")?"selected":"" }>显示</option>
					  <option value="NO" ${entity.visible.toString().equals("NO")?"selected":"" }>不显示</option>
				 </select> 
			   </c:if>
			 </td>
			</tr>
			<tr>
			 <c:if test="${!formbean.editState }">
			<!--  <div class="alert alert-danger" role="alert">
				  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  <span class="sr-only">Error:</span>
				  Enter a valid email address
			  </div> -->
				  <td colspan="6" style="padding-left: 30px;">
				  <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
				  ${entity.content}
				  </td>
			  </c:if>
			 <c:if test="${formbean.editState }">
				 <td colspan="7">
				 <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
				 ${entity.content}</td>
			  </c:if>
			</tr>
			<tr>
				<c:if test="${!formbean.editState }">
				<td colspan="6" style="padding-left: 30px;">
					<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
					${entity.answer}
				</td>
			    </c:if>
			   <c:if test="${formbean.editState }">
			    <td colspan="7">
			    <textarea  rows="2" cols="80" id="answers" name="answers"  class="form-control" >${entity.answer}</textarea>
			    </td>
			    </c:if>
			</tr>
		  </c:forEach>
		 
		   <c:if test="${formbean.editState }">
		   <tr>
		    <td >全选  <input type="checkbox" onclick="selectAll(this)">  </td>
		   <td colspan="6" align="center">
		   	  <input type="button" class="btn btn-info" onclick="javascript:_action('update')"	value="确认修改">
		      <input type="button" class="btn btn-warning" onclick="javascript:_action('delete')"	value="删除">
		       <a class="btn btn-primary" href="control/question/addHotUi.action">设置热点问题</a>
			 
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

</body>
</html>