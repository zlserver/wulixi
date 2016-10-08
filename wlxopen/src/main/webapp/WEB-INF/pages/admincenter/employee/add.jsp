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
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<script src="js/lyz.calendar.min.js" type="text/javascript"></script>
<link href="css/lyz.calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="ckeditor/ckeditor.js">
</script><link href="css/uploadfile.css" rel="stylesheet">
<title>添加普通员工</title>      

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
  	<myc:navigation  model="employee" editState="${navigationColumnEditState}" columnName="${navigationColumnName}" columnId="${navigationColumnId}" />
 
  </div>
  <div class="panel-body">
	<form id="newsform" method="post" action="control/employee/save.action" class="form-horizontal" enctype="multipart/form-data" >	
		 <input type="hidden" name="editState"  value="${navigationColumnEditState}">
   
		<table class="table table-bordered">
			<tr>
				<td>
				 <div class="form-group">
				    <label for="account" class="col-md-1 control-label"> 账号</label>
				    <div class="col-md-11">
				      <input type="text" id="title" class="form-control col-md-6" name="account" value="${formbean.account }" required autofocus>
				    </div>
				  </div>
				</td>
			</tr>
			<tr>
				<td>
				 <div class="form-group">
				    <label for="password" class="col-md-1 control-label">密码</label>
				    <div class="col-md-11">
				      <input type="text" id="link" class="form-control col-md-6" name="password" value="${formbean.password }" required autofocus>
				    </div>
				  </div>
				</td>
			</tr>
			
			<tr>
			   <td>
				 <div class="form-group">
				    <label for="role" class="col-md-1 control-label">类型</label>
				    <div class="col-md-11">
				       <select class="form-control" name="role" >
						<option value="2"  >普通管理员</option>
						<option value="1" >超级管理员</option>
						 </select>
				      </div>
				  </div>
				</td>
			</tr>
			<tr>
				<td  align="center">
					<input type="submit"  value="保存" class="btn btn-success" >
				    <span style="color: red">${formbean.result.error }</span>
				</td>
			</tr>
		</table>
	</form>
  </div>
</div>

</body>
</html>