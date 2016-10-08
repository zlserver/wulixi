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
    
   
	$(document).ready(function() {
		
		$("#fileuploader").uploadFile({
			url:"control/download/ajaxuploadFile.action", //后台处理方法
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
				var downloadform = $("#downloadform");
			   if( data.status==1){
					for( var i=0;i<data.data.length;i++){
						var inputNode='<input type="hidden" id="'+data.data[i].fileId+'" name="fileIds" value="'+data.data[i].fileId+'" >';
						downloadform.append(inputNode);
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
				 $.post("control/download/deleteFile.action", {fileId:fileId},
			            function (resp,textStatus, jqXHR) {
			                //alert(textSatus);
			      }); 
			    //删除input标签
			    $("#"+fileId).remove();
			    pd.statusbar.hide(); //You choice.
			}
		});
	});