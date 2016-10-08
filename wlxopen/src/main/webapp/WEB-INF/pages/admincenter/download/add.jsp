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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <base href="<%=basePath%>">   
<jsp:include page="/WEB-INF/pages/share/bootstrap_simple.jsp"></jsp:include>
<script src="js/lyz.calendar.min.js" type="text/javascript"></script>
<link href="css/lyz.calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<link href="css/uploadfile.css" rel="stylesheet">
<script  src="js/control/down_add.js"></script>
<title>添加下载</title>      

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
  	${formbean.columnName}
  
  </div>
  <div class="panel-body">
	<form id="downloadform" method="post" action="control/download/add.action" class="form-horizontal" enctype="multipart/form-data" >	
		<input type="hidden" name="columnId" value="${columnId }">
		<input type="hidden" name="state" id="state" >
		<table class="table table-bordered">
			<tr>
			   <td>
			   <div id="fileuploader">上传附件</div>
			   </td>
			</tr>
			<tr>
				<td  align="center">
					<input type="submit"  value="上传" class="btn btn-info" onclick="return submit_action('save')" >
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
<script src="js/control/down_add.js"></script>
</body>
</html>