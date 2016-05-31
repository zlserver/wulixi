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
<title>新闻文件</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>

<link href="css/uploadfile.css" rel="stylesheet">
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
	<form id="newsform" action="<c:url value='control/news/listfile.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page" value="${formbean.page}" >
    <input type="hidden" name="newsId" value="${formbean.newsId}" >
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<td  width="3%">选项</td>
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
				   	 <a href="control/news/download.action?savePath=${entity.savePath}">
				       ${entity.originName}
				     </a>
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
			 <td colspan="6" align="center">
			   	  <input type="button" class="btn btn-info" onclick="javascript:_action('updateFile')"	value="确认修改">
			      <input type="button" class="btn btn-warning" onclick="javascript:_action('deleteFile')"	value="删除">
			   	  <a href="<c:url value='control/download/addUi.action?columnId=${formbean.columnId}'></c:url>"  class="btn btn-warning" >添加</a>
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

<script src="js/jquery1.9.1/jquery.min.js"></script>
<script src="js/jquery.uploadfile.min.js"></script>
<script>
$(document).ready(function() {
	
	$("#fileuploader").uploadFile({
		url:"control/news/upload.action", //后台处理方法
		fileName:"myfile",   //文件的名称，此处是变量名称，不是文件的原名称
		dragDrop:true,  //可以取消
		abortStr:"取消",
		sequential:true,  //按顺序上传
		sequentialCount:1,  //按顺序上传
		autoSubmit :"false",  //取消自动上传
		acceptFiles:"application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword" , //限制上传文件格式
		extErrorStr:"上传文件格式不对",
		maxFileCount:10,       //上传文件数量
		maxFileSize:1024*1024, //大小限制1M
		sizeErrorStr:"上传文件不能大于1M", 
		dragDropStr: "<span><b>附件拖放于此</b></span>",
		showFileCounter:false,
		returnType:"json",  //返回数据格式为json
		onSuccess:function(files,data,xhr,pd)  //上传成功事件，data为后台返回数据
		{
			//$("#eventsmessage").html($("#eventsmessage").html()+"<br/>Success for: "+JSON.stringify(data));
			var newsform = $("#newsform");
		   if( data.status==1){
				for( var i=0;i<data.data.length;i++){
					var inputNode='<input type="hidden" id="'+data.data[i].fileId+'" name="fileIds" value="'+data.data[i].fileId+'" >';
					newsform.append(inputNode);
				}
			}else{
				alert("上传失败");
			} 
		},
		showDelete: true,//删除按钮
		statusBarWidth:600,
		dragdropWidth:600,
		deleteCallback: function (data, pd) {
			 var fileId=data.data[0].fileId;
			 $.post("control/news/deleteFile.action", {fileId:fileId},
		            function (resp,textStatus, jqXHR) {
		                //alert("delete ok");
		                //alert(textSatus);
		      }); 
		    //删除input标签
		    $("#"+fileId).remove();
		    pd.statusbar.hide(); //You choice.
		}
	});
});
</script>
</body>
</html>