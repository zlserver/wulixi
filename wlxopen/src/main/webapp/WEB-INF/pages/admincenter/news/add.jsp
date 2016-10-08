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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <base href="<%=basePath%>">   
<jsp:include page="/WEB-INF/pages/share/bootstrap_simple.jsp"></jsp:include>
<script src="js/lyz.calendar.min.js" type="text/javascript"></script>
<link href="css/lyz.calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="ckeditor/ckeditor.js">
</script><link href="css/uploadfile.css" rel="stylesheet">
<title>添加新闻</title>      

<style type="text/css">
#divDate{
position: absolute;
top: 110px;
left: 265px;
}
</style>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
  
   <myc:navigation  model="news" editState="${navigationColumnEditState}" columnName="${navigationColumnName}" columnId="${navigationColumnId}"/>
 
  </div>
  <div class="panel-body">
	<form id="newsform" method="post" action="control/news/add.action" class="form-horizontal" enctype="multipart/form-data" >	
		<input type="hidden" name="columnId" value="${columnId }">
		<input type="hidden" name="state" id="state" >
		<table class="table table-bordered">
			<tr>
				<td>
				 <div class="form-group">
				    <label for="title" class="col-md-1 control-label"> 题目</label>
				    <div class="col-md-11">
				      <input type="text" id="title" class="form-control col-md-6" name="news.title" value="${formbean.news.title }" required autofocus>
				    </div>
				  </div>
				</td>
			</tr>
			<tr>
				<td>
					<span>
					    标题颜色：
					 <c:choose>
					 	<c:when test="${formbean.titleColor!=null}">
					 	 <input type="radio" name="titleColor" value="BLACK"  ${formbean.titleColor.equals("BLACK")?"checked":""}  >黑色
					     <input type="radio"  name="titleColor" value="RED"  ${formbean.titleColor.equals("RED")?"checked":""} >红色
					     <input type="radio"  name="titleColor" value="BLUE"  ${formbean.titleColor.equals("BLUE")?"checked":""} >蓝色
					    </c:when>
					 	<c:otherwise>
					 	  <input type="radio" name="titleColor" value="BLACK"  checked="checked" >黑色
					      <input type="radio"  name="titleColor" value="RED"   >红色
					      <input type="radio"  name="titleColor" value="BLUE"   >蓝色
					   </c:otherwise>
					 </c:choose>
					</span>
					<!-- <span style="position: relative;margin-left: 50px;">
					    日期：
					  <input required id="publishTime" type="text" name="createTime"  >
					 </span> -->
				</td>
			</tr>
			
			<tr>
				<td>
				<textarea  rows="25" cols="80" id="context" name="news.context"  class="form-control"   required >${formbean.news.context }</textarea>
				 <script type="text/javascript">
                		CKEDITOR.replace('context');
            	</script>
				</td>
			</tr>
			<tr>
			   <td>
			   <div id="fileuploader">上传附件</div>
			   </td>
			</tr>
			<tr>
				<td  align="center">
					<input type="submit"  value="发表" class="btn btn-info" onclick=" return submit_action('publish')" >
					<input type="submit"  value="保存" class="btn btn-success" onclick="return submit_action('save')" >
					<a href="javascript:history.go(-1);" class="btn btn-warning">返回</a>
				</td>
			</tr>
		</table>
	</form>
  </div>
</div>

<script src="js/jquery1.9.1/jquery.min.js"></script>
<script src="js/jquery.uploadfile.min.js"></script>
<script type="text/javascript" src="js/control/base.js"></script>
<script type="text/javascript" src="js/control/newsuploadfile.js"></script>

</body>
</html>