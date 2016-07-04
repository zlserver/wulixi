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
<base href="<%=basePath%>" target="infoFrame">   
<title>标题</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<style type="text/css">
#box ul {
 list-style-type: none;
 margin:0px;
 padding:0px;
}
li {
 margin:0px;
 margin-top:5px;
 margin-bottom:5px;
 float:left;
 width:70px;
 height:30px;
 text-align: center;
}
ul li a{
display:block;
height: 25px;
}
ul a:HOVER {
	background-color: blue;
	color: white;
}
ul a:ACTIVE {
	background-color: blue;
	color: white;
}
ul a:FOCUS{
	color: white;
}
.selected{
    background-color: blue;
	color: white;
}
.unselected{
background-color: white;
color: black;
}
</style>
<script type="text/javascript">
$(function () {
    $("ul a:first").addClass("selected");
});

 function selected(anode){
	 $(".ano").removeClass("selected");
	 $(anode).addClass("selected");     
 }
 function navigation(columnId,columnName,editState) {
	 window.parent.location.href="control/news/list.action?columnId="+columnId+"&columnName="+columnName+"&editState="+editState;
}
</script>

</head>
<body>
<div class="panel-heading">
  <a onclick="navigation('${navigationColumnId}','${navigationColumnName}','${navigationColumnEditState}')" href="javascript:void(0)">
  	${navigationColumnName}
  </a>
  </div>
<div id="box">
	<ul>
	 <li ><a class="ano" href="<c:url value='control/news/editUi.action?id=${newsId}&columnId=${columnId}'/>" onclick="selected(this)">新闻详情</a> </li>
	 <li ><a class="ano"  href="<c:url value='control/news/listfile.action?newsId=${newsId }&type=NO_IMAGE'/>" onclick="selected(this)" >新闻附件</a> </li>
	 <li ><a  class="ano" href="<c:url value='control/news/listfile.action?newsId=${newsId }&type=IMAGE'/>"  onclick="selected(this)">展示图片</a> </li>
	</ul>
</div>

</body>
</html>