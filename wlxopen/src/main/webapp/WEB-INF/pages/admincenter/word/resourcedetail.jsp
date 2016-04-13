<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <base href="<%=basePath%>">   
	<title>${word.content}单词资源详细界面</title> 
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    
  </head>
  <body>

	<div class="container-fluid" style="text-align:left; width: 700px;border:solid red 1px;">
	 	<div style="text-align: left;"> 
	 		<ol class="breadcrumb">
			  <li><h3>${word.content }</h3></li>
			  <li><a href="#">
			    ${word.version==1?"北师大版":"" }${word.version==2?"北京版":"" }
				${word.version==3?"外研社新标准版":"" }${word.version==4?"外研社一年级起版":"" }
				${word.version==5?"人教版":"" }${word.version==6?"朗文版":"" }
				</a></li>
			  <li ><a href="#">${word.book}册</a></li>
			   <li ><a href="#">${word.unit}单元</a></li>
			   <li><a href="javascript:void()">第${word.rank}个单词</a></li>
			  
			</ol> 
	 	</div>
	 	<span id="voicedata">
		 	<!-- <span style="margin-right: 30px;" id="ying">
		 		<small>英</small>
		 		<a href="javascript:playmusic();" ><img alt="" src="images/admin/word/voice.png"> </a>
		 		
		 	</span>
		 	<span>
		 		<small>美</small>
		 		<a href=""> <img alt="" src="images/admin/word/voice.png"> </a>
		 	</span>
		 	  -->
	 	</span>
	 	<div> 
	 	<span class="btn btn-primary">图片</span>
	 		<div class="row" id="picturedata">
	 		<%-- <c:forEach  items="${list }" var="resource">
		 		<div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="${resource.savepath }" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="<c:url value='admin/control/wordresource/delete.html?id=${resource.id}'/>" class="btn btn-warning" role="button">删除</a>
				      </div>
				    </div>
				  </div>
	 		</c:forEach> --%>
			  
			  <div class="col-sm-6 col-md-3">
			    <div class="thumbnail" style="border:0px;">
			    <a  href="<c:url value='admin/control/wordresource/addUI.html?uuid=${word.uuid }'/>">
			      <img src="images/add.jpg" class="img-circle btn" alt="...">
			    </a>
			    </div>
			  </div>
			</div>
	 	</div>
	 	<div>  
	 	<span class="btn btn-primary">绘本</span>
		 	<div class="row" id="bookpicturedata">
				 
				  <div class="col-sm-6 col-md-3">
				    <div class="thumbnail" style="border:0px;">
				    <a  href="<c:url value='admin/control/wordresource/addUI.html?uuid=${word.uuid }'/>">
				      <img src="images/add.jpg"  class="img-circle btn" alt="...">
				    </a>
				    </div>
				  </div>
			</div>
	 	
	 	</div>
	 	<div> 
	 	<span class="btn btn-primary">视频</span>
	 		<div class="row" id="videodata">
			 	
			 	 <div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="images/hbmonkey.jpeg" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="#" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				      </div>
				  </div>
				  <div class="col-sm-6 col-md-3">
				    <div class="thumbnail" style="border:0px;">
				      <img src="images/add.jpg" class="img-circle btn" alt="...">
				    </div>
				  </div>
			</div>
	 	</div>
	 	
	 	<div>
	 	<input type="button" value="返回" class="btn btn-info " onclick="javascript:history.go(-1);">
	   </div>
	 	
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-2.1.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
<script  type="text/javascript">
//单词id
 var wordid ="${word.uuid}"; 
//资源删除连接
var deleteUrl = "admin/control/wordresource/ajaxDelete.html";

//播放声音，每次点击都会产生一个声音，需要修复
function playmusic(id,savepath){
	var nodeid= $("#"+id);
	var str='<embed id="emv" src="'+savepath+'" autostart="true" loop="false"  hidden="true" volume="3">';
	$("#"+id).append(str);

} 
/**
 * 删除资源，id为资源的id
 */
function deleteResource(id){
	//确认是否删除
	if( confirm("确定删除"))
	{
		//获取删除按钮
		var deleteNode = $("#a"+id);
		//现将删除按钮设为不可用,同时文字改为正在删除
		deleteNode.addClass("disabled");
		deleteNode.text("正在删除...");
		//确认删除则调用ajax删除
		$.get(deleteUrl,{id:id}, function(content) {
			var status = content.status;
			var message = content.message;
			if( status ==1){//删除成功
				$("#"+id).remove();
			}else{
				alert(message);
				//删除失败恢复按钮
				deleteNode.removeClass("disabled");
				deleteNode.text("删除");
			}
			
		},"json"); 
	}
}
//进入界面就开始运行
$(function(){
	//获取单词资源
	getResource(wordid,1);//图片
	getResource(wordid,2);//绘本
	getResource(wordid,3);//声音
	
});

/**通过ajax获取单词的图片、绘本、声音、视频
 * uuid:单词id
   type:资源类型，1：图片，2：绘本,3:声音,4:视频
 */
function getResource(uuid,type){
	$.get("admin/control/wordresource/ajaxFetchResources.html",{uuid:uuid,type:type}, function(content) {
		var data = content.result.data;
		var count = content.result.count;
		if( count > 0){
			//将数据显示出来
			switch(type){
			case 1:	//图片
				showPictureData(data);
				break;
			case 2: //绘本
				showBookPictureData(data);
				break;
			case 3: //声音
				showVioceData(data);
				break;
			case 4:  //视频
				showVideoData(data);
				break;
			}
		}
	},"json"); 
} 
//通过ajax获取后台图片
function showPictureData(data){
	var str="";
	for(var i=0;i<data.length;i++)
	{
		  str+='<div id="'+data[i].id+'" class="col-sm-6 col-md-3"><div class="thumbnail">';
		  str+='<img src="'+data[i].savepath+'" alt="...">';
		  str+='<div class="caption" style="text-align: center;">';
		  str+='<a href="javascript:deleteResource(\''+data[i].id+'\');" id="a'+data[i].id+'" class="btn btn-warning"  role="button">删除</a>';
		  str+='</div>';
		  str+='</div></div>';
	}
	//追加到图片的中内容的前面
	$("#picturedata").prepend(str);
}
//通过ajax获取后台显示绘本
function showBookPictureData(data){
	var str="";
	for(var i=0;i<data.length;i++)
	{
		  str+='<div id="'+data[i].id+'"  class="col-sm-6 col-md-3"><div class="thumbnail">';
		  str+='<img src="'+data[i].savepath+'" alt="...">';
		  str+='<div class="caption" style="text-align: center;">';
		  str+='<a href="javascript:deleteResource(\''+data[i].id+'\');" class="btn btn-warning" role="button">删除</a>';
		  str+='</div>';
		  str+='</div></div>';
	}
	//追加到图片的中内容的前面
	$("#bookpicturedata").prepend(str);
}

//通过ajax获取后台显示声音
function showVioceData(data){
	 var str="";
		for(var i=0;i<data.length;i++)
		{
			//英式发音
			if( i ==0){
				 str+='<span style="margin-right: 30px;" id="ying">';
				  str+='<small>英</small>';
				  str+='<a href="javascript:playmusic(\'ying\',\''+data[i].savepath+'\');" >';
				  str+='<img alt="" src="images/admin/word/voice.png"> </a>';
				  str+='</span>';
			}else{//美式发音
				str+='<span style="margin-right: 30px;" id="mei">';
				  str+='<small>美</small>';
				  str+='<a href="javascript:playmusic(\'mei\',\''+data[i].savepath+'\');" >';
				  str+='<img alt="" src="images/admin/word/voice.png"> </a>';
				  str+='</span>';
			}
		}
		//追加到图片的中内容的前面
		$("#voicedata").append(str);
}

//通过ajax获取后台显示视频
function showVideoData(data){
	
	
}
</script>
  </body>
</html>