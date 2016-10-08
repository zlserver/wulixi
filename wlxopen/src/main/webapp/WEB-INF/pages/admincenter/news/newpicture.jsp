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
<title>新闻预览图片</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>

<link href="css/uploadfile.css" rel="stylesheet">

</head>
<body style="position: relative;">


<div class="panel panel-default">
  <div class="panel-heading">
  	${formbean.columnName}
  </div>
  <div class="panel-body">
	<form id="newsform" action="<c:url value='control/news/listfile.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page" value="${formbean.page}" >
    <input type="hidden" name="newsId" value="${formbean.newsId}" >
    <input type="hidden" name="type" value="${formbean.type}" >
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<td  width="5%">选项</td>
			<td  width="50%">名称</td>
			<td  width="10%">大小</td>
			<td  width="10%">显示状态</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity" varStatus="status">
				 
			<tr>
				 <td> 
				 	<input type="hidden" value="${entity.id }" name="fileIds">
				 	<input type="checkbox" value="${status.count-1}" name="checkeds">
				 </td>
			 <td>
			     <span >
			      <img style="width:50px;height: 30px;" class="img-rounded hitimg" alt="。。。" src="control/news/lookImage.action?savePath=${entity.savePath}">
				   	<%--  <a href="control/news/download.action?savePath=${entity.savePath}">
				       ${entity.originName}
				     </a> --%>
			     </span>
			 
			 </td>
			 <td>
			 	<myc:convert size="${entity.size}"/> 
			 </td>
			<td> 
			    <select class="form-control" name="states" >
				  <option value="VALIDATE" ${entity.state.toString().equals("VALIDATE")?'selected':'' }>显示</option>
				  <option value="INVALIDATE"  ${entity.state.toString().equals("INVALIDATE")?'selected':'' }>未显示</option>
				</select>
			 </td>
			
		  </c:forEach>
		  <tr>
			   <td colspan="4">
			   <div id="fileuploader">上传附件</div>
			   </td>
			</tr> 
		 <tr>
		 <td >全选 &nbsp; <input type="checkbox" onclick="selectAll(this)">  </td>
			 <td colspan="5" align="center">
			   	 <input id="addBtn" type="button" class="btn btn-success" onclick="javascript:saveFile('saveNewsFile')"  disabled="disabled"	value="添加">
			     <input type="button" class="btn btn-info" onclick="javascript:_action('news','updateFile')"	value="确认修改">
			     <input type="button" class="btn btn-warning" onclick="javascript:_action('news','deleteFile')"	value="删除">
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
<div  id="windiv">
		<img id="winimg" class="img-rounded"  alt="" src="" >
</div>
</body>

<script src="js/jquery1.9.1/jquery.min.js"></script>
<script src="js/jquery.uploadfile.min.js"></script>
<script type="text/javascript" src="js/control/base.js"></script>
<script type="text/javascript" src="js/control/showpic.js"></script>
<script type="text/javascript" src="js/control/uploadpic.js"></script>

</html>