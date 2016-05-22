<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base href="<%=basePath%>" target="mainFrame">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>内容</title>
<link rel="stylesheet" type="text/css" href="js/tree_themes/SimpleTree.css"/>
<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/SimpleTree.js"></script>
<script type="text/javascript">
$(function(){
	$(".st_tree").SimpleTree({
		 click:function(a){
			 if(!$(a).attr("hasChild"))
				alert($(a).attr("ref")); 
				
		} 
	});
});
</script>
</head>

<body>
	<div class="st_tree">
		<ul>
			<c:forEach items="${topColumns }" var="column">
			  <li><a href="javascript:void(0)" >${column.name }</a></li>
			  <ul show="true">
				  <c:forEach items="${column.childrens }" var="child">
				  	<!-- 无二级子类 -->
				  	<c:if test="${fn:length(child.childrens)<=0}">
				      <li>
				       	<a target="mainFrame" href="<c:url value='${child.readUrl }?columnId=${child.id}&columnName=${child.name}'/>" >${child.name}</a>
					    <c:if test="${!child.manageUrl.equals('')}">
					    &nbsp;&nbsp;&nbsp;[<a target="mainFrame" href="<c:url value='${child.manageUrl}?columnId=${child.id}&columnName=${child.name}&editState=true'/>" ><font color="red">管理</font></a>]
			 		   </c:if>
			 		  </li>
					</c:if>
					<!-- 有二级子类 -->
					 <c:if test="${fn:length(child.childrens)>0}">
					   <li><a href="javascript:void(0)" >${child.name }</a></li>
						<ul>
							<c:forEach  items="${child.childrens }" var="cchild">
							 <li>
								  <a target="mainFrame" href="<c:url value='${cchild.readUrl }?columnId=${cchild.id}&columnName=${cchild.name}'/>" >${cchild.name}</a>
								  <c:if test="${!cchild.manageUrl.equals('')}">
								    &nbsp;&nbsp;&nbsp;[<a target="mainFrame" href="<c:url value='${cchild.manageUrl}?columnId=${cchild.id}&columnName=${cchild.name}&editState=true'/>" ><font color="red">管理</font></a>]
								  </c:if>
							  </li>    
							 </c:forEach>
				        </ul> 
					</c:if> 
				  </c:forEach>
			  </ul>
			  
			</c:forEach>
			
			
		</ul>
	</div>
</body>
</html>