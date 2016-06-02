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
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
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
 <script>
    $(function () {
        $("#publishTime").calendar({
            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
            complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
            readonly: true,                                        // 日期下限，默认：NaN(不限制)
            callback: function () {                               // 点击选择日期后的回调函数
               $("#publishTime").val();
            }
        });
    });
    
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
       编辑新闻
  </div>
  <div class="panel-body">
	<form id="newsform" method="post" action="control/news/edit.action" class="form-horizontal" enctype="multipart/form-data" >	
		<input type="hidden" name="columnId" value="${formbean.columnId }">
		<input type="hidden" name="news.id" value="${formbean.news.id }">
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
					 	 <input type="radio" name="titleColor" value="BLACK"  ${formbean.titleColor.equals("BLACK")?"checked":""}  >黑色
					     <input type="radio"  name="titleColor" value="RED"  ${formbean.titleColor.equals("RED")?"checked":""} >红色
					     <input type="radio"  name="titleColor" value="BLUE"  ${formbean.titleColor.equals("BLUE")?"checked":""} >蓝色
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
					<input type="submit"  value="确认修改" class="btn btn-info"  >
					<a href="javascript:history.go(-1);" class="btn btn-warning">返回</a>
				</td>
			</tr>
		</table>
	</form>
  </div>
  <div class="panel-footer">
     <c:forEach items="${formbean.news.newsFiles}" var="newsFile">
     <c:if test="${newsFile.type.toString().equals('NO_IMAGE')}">
      <a href="control/news/download.action?savePath=${newsFile.savePath}">${newsFile.originName }</a><br>
      </c:if>
     </c:forEach>
   </div>
</div>

<script src="js/jquery1.9.1/jquery.min.js"></script>
<script src="js/jquery.uploadfile.min.js"></script>
<script>
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