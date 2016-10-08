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
<title>添加重要通知</title>      

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
  
  <myc:navigation  model="hotinform" editState="${navigationColumnEditState}" columnName="${navigationColumnName}" columnId="${navigationColumnId}" />
 
  </div>
  <div class="panel-body">
	<form id="newsform" method="post" action="control/hotinform/save.action" class="form-horizontal" enctype="multipart/form-data" >	
		 <input type="hidden" name="editState"  value="${navigationColumnEditState}">
   
		<table class="table table-bordered">
			<tr>
				<td>
				 <div class="form-group">
				    <label for="title" class="col-md-1 control-label"> 题目</label>
				    <div class="col-md-11">
				      <input type="text" id="title" class="form-control col-md-6" name="title" value="${formbean.title }" required autofocus>
				    </div>
				  </div>
				</td>
			</tr>
			<tr>
				<td>
				 <div class="form-group">
				    <label for="link" class="col-md-1 control-label">链接</label>
				    <div class="col-md-11">
				      <input type="text" id="link" class="form-control col-md-6" name="link" value="${formbean.link }" required autofocus>
				    </div>
				  </div>
				</td>
			</tr>
			<%-- 
			<tr>
				<td>
				<textarea  rows="25" cols="80" id="context" name="news.context"  class="form-control"   required >${formbean.news.context }</textarea>
				 <script type="text/javascript">
                		CKEDITOR.replace('context');
            	</script>
				</td>
			</tr> --%>
			<tr>
			   <td>
				 <div class="form-group">
				    <label for="pic" class="col-md-1 control-label">图片</label>
				    <div class="col-md-11">
				      <input type="file" id="pic" class="form-control col-md-6" name="pic" required autofocus>
				    </div>
				  </div>
				</td>
			</tr>
			<tr>
				<td  align="center">
					<input type="submit"  value="保存" class="btn btn-success" onclick="return submit_action('save')" >
				    <span style="color: red">${formbean.result.error }</span>
				</td>
			</tr>
		</table>
	</form>
  </div>
</div>

<script src="js/jquery1.9.1/jquery.min.js"></script>
<script src="js/jquery.uploadfile.min.js"></script>
<script>

/*  */
function submit_action(choice) {
	
	var state = $("#state");
	
	if(choice=="save")
		state.val("WAITING");
	if(choice=="publish")
		state.val("PUBLISH");
	
	return true;
}
$(document).ready(function() {
	
	$("#fileuploader").uploadFile({
		url:"control/news/ajaxuploadFile.action", //后台处理方法
		fileName:"myfile",   //文件的名称，此处是变量名称，不是文件的原名称
		dragDrop:true,  //可以取消
		abortStr:"取消",
		sequential:true,  //按顺序上传
		sequentialCount:1,  //按顺序上传
		autoSubmit :"false",  //取消自动上传
		acceptFiles:"application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword" , //限制上传文件格式
		extErrorStr:"上传文件格式不对",
		maxFileCount:10,       //上传文件数量
		maxFileSize:5*1024*1024, //大小限制5M
		sizeErrorStr:"上传文件不能大于5M", 
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
			 $.post("control/news/ajaxdeleteFile.action", {fileId:fileId},
		            function (resp,textStatus, jqXHR) {
		                alert("delete ok");
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