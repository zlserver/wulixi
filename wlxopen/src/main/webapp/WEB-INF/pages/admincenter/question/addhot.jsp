<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/wlx/myc" prefix="myc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>添加热点问题</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap_simple.jsp"></jsp:include>
<style type="text/css">
.quesarea{
text-align: left;
border: 1px solid #BDC9D5;
margin:0px 0px 5px 5px;
padding: 5px 0px 3px 5px;
position: relative;

}
.quesarea p{
padding: 0px;
margin: 0px;
}
</style>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
  	
  <myc:navigation  model="question" editState="${navigationColumnEditState}" columnName="${navigationColumnName}" columnId="${navigationColumnId}"/>  
  
  </div>
  <div class="panel-body">
  
  <div class="quesarea">
	    <form action="control/question/addHot.action" method="post" onsubmit="return checkcontent()">
	    	
		    <input type="hidden" name="editState"  value="${navigationColumnEditState}">
		    <input type="hidden" name="columnId" value="${navigationColumnId}">
		    <p>问&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：
		         <input type="text"  name="question.title" id="q_title" style="width:587px " >  </p>
		    <p>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：
		    	<input type="radio" value="admin" checked="checked" name="question.identity">管理员
		    </p>
		    <p>问题描述：<textarea rows="3" cols="90" id="q_content" name="question.content"></textarea>  </p>
		    <p>回&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;答：<textarea rows="3" cols="90" id="q_answer" name="question.answer"></textarea>  </p>
		    <p style="margin-left: 69px;"><input class="btn btn-primary" type="submit" value="确定">  </p>
	    </form>
	   </div>
  
  </div>
</div>
<script type="text/javascript" src="js/control/addhot.js"></script>
</body>
</html>