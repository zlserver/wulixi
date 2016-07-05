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
<title>新闻列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<link href="css/uploadfile.css" rel="stylesheet">
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
			form.action="control/download/"+method+".action";
			          
			form.submit();
	    }
	}
	/**
	 将上传的附件保存
	*/
	function saveFile(method){
		var form = document.forms[0];
		form.action="control/download/"+method+".action";
		          
		form.submit();
	}
	function query() {
		var form = document.forms[0];
		form.page.value=1;
		form.submit();
	}
	
	/* 全选 */
	function selectAll(checkNode){
		var checkeds=document.getElementsByName("checkeds");
		var state=checkNode.checked;
		for( var i = 0;i <checkeds.length;i++)
		  checkeds[i].checked=state;
	}
</script>
</head>
<body style="position: relative;">


<div class="panel panel-default">
  <div class="panel-heading">
  <a href="control/download/list.action?columnId=${navigationColumnId}&editState=${navigationColumnEditState}&columnName=${navigationColumnName}&type=${navigationType}">
  	${navigationColumnName}
  </a>
  </div>
  <div class="panel-body">
	<form id="downloadform" action="<c:url value='control/download/list.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" name="page" value="${formbean.page}" >
    <input type="hidden" name="editState"  value="${navigationColumnEditState}">
    <input type="hidden" name="columnId" value="${navigationColumnId}">
    <input type="hidden" name="type" value="${navigationType}">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<c:if test="${formbean.editState }">
			  <td  width="6%">选项</td>
			</c:if>
			<td  width="50%">名称</td>
			<td  width="10%">作者</td>
			<td width="10%">大小</td>
			<td width="7%">下载量</td>
			<td  width="10%">
			<select class="form-control" name="suggest" onchange="query()">
				  <option value="0" ${formbean.suggest==0?'selected':'' }>推荐状态</option>
				  <option value="1" ${formbean.suggest==1?'selected':'' }>推荐</option>
				  <option value="2"  ${formbean.suggest==2?'selected':'' }>不推荐</option>
				</select>
			</td>
			<td  width="10%">
				<select class="form-control" name="state" onchange="query()">
				  <option value=" " ${formbean.state.equals(" ")?'selected':'' }>状态</option>
				  <option value="VALIDATE" ${formbean.state.equals("VALIDATE")?'selected':'' }>已发表</option>
				  <option value="INVALIDATE"  ${formbean.state.equals("INVALIDATE")?'selected':'' }>待发表</option>
				  <option value="DELETE" ${formbean.state.equals("DELETE")?'selected':'' }>已屏蔽</option>
				</select>
			</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity" varStatus="status">
			<tr>
			<c:if test="${formbean.editState }">
				 <td> 
				 	<input type="hidden" value="${entity.id }" name="fileIds">
				 	<input type="checkbox" value="${status.count-1}" name="checkeds">
				 </td>
			 </c:if>
			 <td>
			     <span >
				     <a href="<c:url value='control/download/down.action?savePath=${entity.savePath }'/>">
				       <font color="${entity.titleColor.toString()}">${entity.originName}</font> 
				      </a>
				   &nbsp;&nbsp;&nbsp;&nbsp; <font color="#CAC7F5"> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd hh:mm" /></font>
				</span>
			 </td>
			  <td> 
				${entity.author }
			   </td>
			 <td>
			 <myc:convert size="${entity.size}"/> 
			 </td>
			 <td> 
			   <span >${entity.downloadCount }</span>
			 </td>
			  <td>
			  <c:if test="${!formbean.editState }">
			   <span >
			    <font color="blue">${entity.suggest==1?"推荐":"" }</font>
			    <font color="black">${entity.suggest==2?"不推荐":"" }</font>
			   </span>
			  </c:if>

			   <c:if test="${formbean.editState }">
			    <select  class="form-control input-sm"  name="suggests" >
					  <option value="1"  ${entity.suggest==1?"selected":"" } >推荐</option>
					  <option value="2" ${entity.suggest==2?"selected":"" }>不推荐</option>
				 </select> 
			   </c:if>
			 </td>
			<td> 
			   <c:if test="${!formbean.editState }">
			   <span >
			    <font color="blue">${entity.state.toString().equals("VALIDATE")?"已发表":"" }</font>
			    <font color="black">${entity.state.toString().equals("INVALIDATE")?"待发表":"" }</font>
			    <font color="red">${entity.state.toString().equals("DELETE")?"已屏蔽":"" }</font>
			   </span>
			  </c:if>


			   <c:if test="${formbean.editState }">
			    <select  class="form-control input-sm"  name="states" >
					  <option  value="VALIDATE"  ${entity.state.toString().equals("VALIDATE")?"selected":"" } >已发表</option>
					  <option  value="INVALIDATE" ${entity.state.toString().equals("INVALIDATE")?"selected":"" }>待发表</option>
					  <option  value="DELETE" ${entity.state.toString().equals("DELETE")?"selected":"" }>已屏蔽</option>
			     </select> 
			   </c:if>
			 </td>
			
		  </c:forEach>
		  
		  <c:if test="${formbean.editState }">
		   <tr>
			   <td colspan="7">
			   <div id="fileuploader">上传附件</div>
			   </td>
			</tr> 
		  </c:if>
		 <tr>
		 
		  <c:if test="${formbean.editState }">
		    <td >全选  <input type="checkbox" onclick="selectAll(this)">  </td>
			 <td colspan="6" align="center">
			   	  <input id="addBtn" type="button" class="btn btn-success" onclick="javascript:saveFile('add')"  disabled="disabled"	value="添加">
			     <input type="button" class="btn btn-info" onclick="javascript:_action('update')"	value="确认修改">
			      <input type="button" class="btn btn-warning" onclick="javascript:_action('delete')"	value="删除">
			    
			 </td>
			</c:if>
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
</script>
</body>
</html>