<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>标题</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/uploadfile.css" rel="stylesheet">
<script src="js/jquery1.9.1/jquery.min.js"></script>
<script src="js/jquery.uploadfile.min.js"></script>
</head>
<body>
 <div id="fileuploader">Upload</div>
 <input type="text" name="testName">
 <div id="eventsmessage"></div>
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
		maxFileSize:300*1024, //大小限制100kb
		sizeErrorStr:"上传文件不能大于100kb", 
		dragDropStr: "<span><b>文件拖放于此</b></span>",
		formData: {"name":"周亮dd"}, //发送表单数据
		showFileCounter:false,
		returnType:"json",  //返回数据格式为json
		onSuccess:function(files,data,xhr,pd)  //上传成功事件，data为后台返回数据
		{

			$("#eventsmessage").html($("#eventsmessage").html()+"<br/>Success for: "+JSON.stringify(data));
			$("#eventsmessage").html($("#eventsmessage").html()+"<br/>Success for: "+data.status);
			
		},
		showDelete: true,//删除按钮
		showDownload:true,//下载按钮
		statusBarWidth:600,
		dragdropWidth:600,
		deleteCallback: function (data, pd) {
		   /*  for (var i = 0; i < data.length; i++) {
		        $.post("delete.php", {op: "delete",name: data[i]},
		            function (resp,textStatus, jqXHR) {
		                //Show Message	
		                alert("File Deleted");
		            });
		    } */
		    pd.statusbar.hide(); //You choice.

		},
		downloadCallback:function(filename,pd)
		{
			location.href="download.php?filename="+filename;
		}
	});
	
});
</script>

<!-- <form id= "uploadForm">  
      <p >指定文件名： <input type="text" name="filename" value= ""/></p >  
      <p >上传文件： <input type="file" name="file"/></ p>  
      <input type="button" value="上传" onclick="doUpload()" />  
</form> 
<script>
    function doUpload() {  
         var formData = new FormData($( "#uploadForm" )[0]);  
         $.ajax({  
              url: 'control/file/upload.action' ,  
              type: 'POST',  
              data: formData,  
              async: false,  
              cache: false,  
              contentType: false,  
              processData: false,  
              success: function (returndata) {  
                  alert(returndata);  
              },  
              error: function (returndata) {  
                  alert(returndata);  
              }  
         });  
    }  
 </script> -->
</body>
</html>