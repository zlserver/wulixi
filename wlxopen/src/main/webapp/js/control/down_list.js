
	/**
	 将上传的附件保存
	*/
	function saveFile(method){
		
		var form = document.forms[0];
		form.action="control/download/"+method+".action";
		          
		form.submit();
	}
	
	$(document).ready(function() {
	
	$("#fileuploader").uploadFile({
		url:"control/download/ajaxuploadFile.action", //后台处理方法
		fileName:"myfile",   //文件的名称，此处是变量名称，不是文件的原名称
		dragDrop:true,  //可以取消
		abortStr:"取消",
		sequential:true,  //按顺序上传
		sequentialCount:1,  //按顺序上传
		autoSubmit :"false",  //取消自动上传
		//acceptFiles:"application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/msword,application/zip,application/x-rar" , //限制上传文件格式
		extErrorStr:"上传文件格式不对",
		maxFileCount:10,       //上传文件数量
		maxFileSize:20*1024*1024, //大小限制20M
		sizeErrorStr:"上传文件不能大于20M", 
		dragDropStr: "<span><b>附件拖放于此</b></span>",
		showFileCounter:false,
		returnType:"json",  //返回数据格式为json
		onSuccess:function(files,data,xhr,pd)  //上传成功事件，data为后台返回数据
		{
			//$("#eventsmessage").html($("#eventsmessage").html()+"<br/>Success for: "+JSON.stringify(data));
			var downloadform = $("#downloadform");
		   if( data.status==1){
				for( var i=0;i<data.data.length;i++){
					var inputNode='<input type="hidden" id="'+data.data[i].fileId+'" name="nfileIds" value="'+data.data[i].fileId+'" >';
					downloadform.append(inputNode);
					$("#addBtn").removeAttr("disabled");
				}
			}else{
				alert("上传失败");
				alert(data.message);
			} 
		},
		showDelete: true,//删除按钮
		statusBarWidth:600,
		dragdropWidth:600,
		deleteCallback: function (data, pd) {
			 var fileId=data.data[0].fileId;
			 $.post("control/download/ajaxdeleteFile.action", {fileId:fileId},
		            function (resp,textStatus, jqXHR) {
		                //alert(textSatus);
		      }); 
		    //删除input标签
		    $("#"+fileId).remove();
		    pd.statusbar.hide(); //You choice.
		}
	});
});