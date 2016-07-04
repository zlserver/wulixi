<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="/wlx/myc" prefix="myc" %>
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
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<style type="text/css">
 #logindes{
 margin-top: 15px;
 padding-left: 10px;
 }
 #logindes p{
 font-size: 14px;
 }
 #logindes p font{
  color: red;
 }
 #main{
 
 }
 .header{
background-color: #FBFBFB;
padding-left: 0px;
text-align:center;
margin: 0px;
font-size: 20px;
}
</style>
</head>

<body>
<div class="header">
  物理系学生工作网站
</div>
<div id="main">
	<div class="st_tree">
		<ul>
			<c:forEach items="${topColumns }" var="column">
			  <li><a href="javascript:void(0)" >${column.name }</a></li>
			  <ul show="false">
				  <c:forEach items="${column.childrens }" var="child">
				  	<!-- 无二级子类 -->
				  	<c:if test="${fn:length(child.childrens)<=0}">
				      <li>
				      <myc:choose>
						  	<myc:when test="${child.readUrl}">
						  	<a target="mainFrame" href="<c:url value='${child.readUrl }columnId=${child.id}&columnName=${child.name}&navigation=true'/>"> ${child.name}</a>
				       		</myc:when>
						  	<myc:otherwise>
						  	<a target="mainFrame" href="javascript:void()" >${child.name}</a>
				     		</myc:otherwise>
					  </myc:choose>
					    <c:if test="${!child.manageUrl.equals('')}">
					    &nbsp;[<a target="mainFrame" href="<c:url value='${child.manageUrl}columnId=${child.id}&columnName=${child.name}&editState=true&navigation=true'/>"><font color="red">管理</font></a>]
			 		   </c:if>
			 		  </li>
					</c:if>
					<!-- 有二级子类 -->
					 <c:if test="${fn:length(child.childrens)>0}">
					   <li><a href="javascript:void(0)" >${child.name }</a></li>
						<ul>
							<c:forEach  items="${child.childrens }" var="cchild">
							 <li>
							 
							  <myc:choose>
							  	<myc:when test="${cchild.readUrl}">
							  	 <a target="mainFrame" href="<c:url value='${cchild.readUrl }columnId=${cchild.id}&columnName=${cchild.name}&navigation=true'/>" >${cchild.name}</a>
								</myc:when>
							  	<myc:otherwise>
							  	 <a target="mainFrame" href="javascript:void()" >${cchild.name}</a>
								</myc:otherwise>
							  </myc:choose>
								  <c:if test="${!cchild.manageUrl.equals('')}">
								    &nbsp;[<a target="mainFrame" href="<c:url value='${cchild.manageUrl}columnId=${cchild.id}&columnName=${cchild.name}&editState=true&navigation=true'/>" ><font color="red">管理</font></a>]
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
	<div id="logindes" >
		 <p >登录用户:<font > ${admin.account } </font></p>
		 <p >登录时间:<font ><fmt:formatDate value="${admin.loginTime}" pattern="yy-MM-dd HH:mm"/> </font></p>
		 <p >登录次数:<font > ${admin.loginCount } </font></p>
		 <p >发稿篇数:<font > ${admin.publishCount } </font></p>
		  <span><%-- <c:url value="/common/exit.uhtml"/> --%>
		    <a href='javascript:void()' target="_self" class="btn btn-success" onclick="return exit()" ><font color="black">安全退出</font></a>
	      </span>
	 </div>
</div>
</body>
<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/SimpleTree.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".st_tree").SimpleTree({
		 click:function(a){
			 if(!$(a).attr("hasChild"))
				alert($(a).attr("ref")); 
		} 
	});
});
function exit() {
	if( !confirm("确定退出"))
	{
		return false;
	}else{
		window.parent.location.href="common/exit.uhtml";
		return true;
	}
}
</script>
</html>