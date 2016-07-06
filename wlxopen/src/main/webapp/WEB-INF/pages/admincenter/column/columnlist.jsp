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
<base href="<%=basePath%>">   
<title>栏目列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<style type="text/css">
.td{
text-align: center;
}
.editClass{
cursor:pointer;
}
.xeditClass{
cursor:pointer;
display: none;
}

</style>

</head>
<body style="position: relative;">
<div class="panel panel-default">
	<ol class="breadcrumb">
	
	   <!-- 当前列表的父类id -->
	   <c:set var="parentId" value="" scope="page"></c:set>
	
	  <c:forEach items="${columnNavigation}" var="item"  varStatus="status">
	    <li><a href="control/column/list.action?parentId=${item.key}&parentName=${item.value}">${item.value }</a></li>
	    
	     <!-- 当且列表父类的父类，也就是导航列表的倒数第二个，用于返回上一层使用。-->
	    <c:set var="backParentId" value="${parentId }" scope="page"></c:set>
	    
	    <!-- 当且列表的父类id -->
	    <c:set var="parentId" value="${item.key}" scope="page"></c:set>
	    <!-- 当且列表的父类名称-->
	    <c:set var="parentName" value="${item.value }" scope="page"></c:set>
	   
	  </c:forEach>
	</ol>

  <div class="panel-body">
	<form  action="<c:url value='control/column/list.action'/>" method="post">
   <!-- 查询参数 -->
    <input type="hidden" id="parentId" name="parentId" value="${parentId}">
    <input type="hidden" name="page" value="${page }">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
			<!-- <td  width="10%">ID号</td> -->
			<td  width="15%">栏目名称</td>
			<td  width="13%">分类码</td>
			<td  width="12%">分类说明</td>
			
			<myc:choose>
				<myc:when test="${parentId}">
				
					<td  width="15%">浏览程序</td>
					<td  width="15%">管理程序</td>
				</myc:when>
				<myc:otherwise>
				
					<!-- <td  width="8%">组别</td> -->
					<td  width="7%">顺序</td>
				</myc:otherwise>
			</myc:choose>
			<td  width="8%">编辑</td>
			<td  width="10%">删除</td>
			</tr>
		</thead>
		<tbody>
			 <c:forEach items="${pageView.records }" var="entity">
			<tr>
			 <%-- <td>
			  <span>${entity.id }</span>
			 </td>  --%> 
			 <td>
			     <span id="name${entity.id}"><a href="<c:url value='control/column/list.action?parentId=${entity.id }&parentName=${entity.name}'/>">${entity.name}</a></span>
				 <input class="form-control" type="hidden" id="iname${entity.id}"  name="name" value="${entity.name}"> 
			</td>
			  <td>
			   <span id="classCode${entity.id}">${entity.classCode }</span>
			   <input  class="form-control"  type="hidden" id="iclassCode${entity.id}" name="classCode" value="${entity.classCode }"> 
			  </td>
			 <td> 
			   <span id="typeDes${entity.id}">
				   ${entity.typeDes.toString().equals("LIST_TYPE")?"列表类":"" }
				   ${entity.typeDes.toString().equals("DES_TYPE")?"介绍类":"" }
				   ${entity.typeDes.toString().equals("SYSTEM_TYPE")?"系统类":"" }
				   ${entity.typeDes.toString().equals("OTHER_TYPE")?"其它类":"" }
			   </span>
			   <select class="form-control"  style="display:none;" id="itypeDes${entity.id}" name="typeDes" >
					  <option value="0" ${entity.typeDes.toString().equals("LIST_TYPE")?"selected":"" }>列表类</option>
					  <option value="1" ${entity.typeDes.toString().equals("DES_TYPE")?"selected":"" }>介绍类</option>
					  <option value="2" ${entity.typeDes.toString().equals("SYSTEM_TYPE")?"selected":"" }>系统类</option>
					  <option value="2" ${entity.typeDes.toString().equals("OTHER_TYPE")?"selected":"" }>其它类</option>
				 </select>  
			 </td>
			 
			 <myc:choose>
				<myc:when test="${parentId}">
				 <td> 
				   <span id="readUrl${entity.id}">${entity.readUrl }</span>
				   <input  class="form-control"  type="hidden" id="ireadUrl${entity.id}" name="readUrl" value="${entity.readUrl }"> 
				 </td>
				 <td>
				   <span id="manageUrl${entity.id}">${entity.manageUrl }</span>
				   <input  class="form-control"  type="hidden" id="imanageUrl${entity.id}" name="manageUrl" value="${entity.manageUrl }"> 
				 </td>
				</myc:when>
				<myc:otherwise>
				<%-- <td>
				   <span id="groupType${entity.id}">${entity.groupType }</span>
				   <input  class="form-control"  type="hidden" id="igroupType${entity.id}" name="groupType" value="${entity.groupType }"> 
				</td> --%>
				 <td> 
				   <span id="sequence${entity.id}">${entity.sequence }</span>
				   <input  class="form-control"  type="hidden" id="isequence${entity.id}" name="sequence" value="${entity.sequence }"> 
				</td>
				</myc:otherwise>
			  </myc:choose>
			 <td>
			  <a id="edit${entity.id}" href="javaScript:void(0)" onclick="displayEditButton('${entity.id}')" class="btn btn-info btn-xs" >编辑</a>
			   <!--  glyphicon-ok  glyphicon-pencil-->
				 <span class="glyphicon glyphicon-ok xeditClass" id="ok${entity.id}" aria-hidden="true" onclick="edit('${entity.id}')" ></span>
				 &nbsp;
				 <span class="glyphicon glyphicon-remove xeditClass" id="cancel${entity.id}" aria-hidden="true" onclick="dropEdit('${entity.id}')" ></span>
				  
			 </td>
			 <td> 
			   <input type="button"  value="删除" class="btn btn-info btn-xs" onclick="javascript:deleteColumn('${entity.id}')">&nbsp;&nbsp;
			</td>
			</tr>
			<tr>
			   <td colspan="10">
				<c:forEach  items="${entity.childrens }" var="child" varStatus="status" >
			      &nbsp; &nbsp;[<a id="child${entity.id }${status.count}" href="<c:url value='control/column/list.action?parentId=${child.id }&parentName=${child.name}&doubleParentId=${entity.id}&doubleParentName=${entity.name}'/>"><font color="#992222">${child.name}</font></a>]  
				</c:forEach>
			   </td>
			</tr>
		  </c:forEach>
		</tbody>
	</table>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
  
  
   
</div>

<div style="position:relative; bottom: 5px;right: 0px;left: 0px;">
	
  <form  action="<c:url value='control/column/add.action'/>" method="post"  onsubmit="return checkAdd()">
     <input type="hidden" name="parentId" value="${parentId }">
	<table class="table table-bordered table-striped" width="100%">
	 
	  <tbody>
	  	<tr>
	  	 <td colspan="1">
	  	  父类:
	  	 <font color="red">${parentName}</font>
	  	 </td>
	  	 <td>类名称</td> <td><input type="text" id="name" name="column.name"    required="required" class="form-control"> </td>
	  	 <td>分类码</td><td><input type="text" id="classCode" name="column.classCode"    required="required" class="form-control"> </td>
	  	 <td>分类说明</td>
	  	 <td>
	  	    <select class="form-control" name="typeDes" >
					  <option value="0" }>列表类</option>
					  <option value="1" }>介绍类</option>
					  <option value="2" }>系统类</option>
					  <option value="2" }>其它类</option>
			 </select>
	  	 </td>
	  	 <td>管理程序</td>
	  	 <td><input type="text" name="column.manageUrl" class="form-control"> </td>
	  	</tr>
	  	
	  	<tr>
	  	 <td colspan="1">
	  	    <a href="<c:url value='columnhelp.jsp'/>" target="blank">栏目管理帮助</a>
		 </td>
	  	 <td colspan="8" align="center"> 
	  	 	<input type="submit"  class="btn btn-info "  value="添加"> 
		     <a href="<c:url value='control/column/list.action?parentId=${backParentId}'/>"  class="btn btn-info " >返回上一层</a>
		</td> 
	  	</tr>
	  </tbody>
	</table>
  </form>
</div>
</body>
<script type="text/javascript">
	//查询
	function topage(page)
	{
		var form = document.forms[0];
		 /* var len =form.typeDes.length;
		var i=0;
		for( i=0;i<len ;i++){
			form.typeDes[i].value=null;
		}  */
		form.page.value= page;
		form.submit();
	}
	/* 删除确认 */
	function deleteColumn(id) {
		//检测是否有子类
		var child = $("#child"+id+"1").attr("href");
		
		if( child ){
			alert("请先删除子类!");
		}else{
			if( confirm("确定要删除吗？")){
				var form = document.forms[0];
				form.action="control/column/delete.action?column.id="+id;
				/* var len =form.typeDes.length;
				var i=0;
				for( i=0;i<len ;i++){
					form.typeDes[i].value=null;
				}  */
				form.submit();
			}
		}
		
	}
	
	
	/*
	确认编辑，和后台交互
	*/
	function edit(id){
		//编辑按钮
		var edita = $("#edit"+id);
		//确定按钮
		var okspan = $("#ok"+id);
		//取消按钮
		var cancelspan = $("#cancel"+id);
		//获取所有编辑框和显示span
		//栏目名称
		var namespan = $("#name"+id);
		var nameinput=$("#iname"+id);
		//分类码
		var classCodespan = $("#classCode"+id);
		var classCodeinput=$("#iclassCode"+id);
		//分类说明
		var typeDesspan = $("#typeDes"+id);
		var typeDesselect=$("#itypeDes"+id);
		//浏览程序
		var readUrlspan = $("#readUrl"+id);
		var readUrlinput=$("#ireadUrl"+id);
		//管理程序
		var manageUrlspan = $("#manageUrl"+id);
		var manageUrlinput=$("#imanageUrl"+id);
		//组别
		var groupTypespan = $("#groupType"+id);
		var groupTypeinput=$("#igroupType"+id);
		//顺序
		var sequencespan = $("#sequence"+id);
		var sequenceinput=$("#isequence"+id);
		
		var name = nameinput.val();
		var classCode = classCodeinput.val();
		var typeDes = typeDesselect.val();
		var readUrl =readUrlinput.val();
		var manageUrl =manageUrlinput.val();
		var groupType=groupTypeinput.val();
		var sequence=sequenceinput.val();
		
		var parentId = $("#parentId").val();
		$.post("control/column/ajaxEdit.action",{"parentId":parentId,"column.id":id,"column.name":name,"column.classCode":classCode,"typeDes":typeDes,"column.readUrl":readUrl,"column.manageUrl":manageUrl,"column.groupType":groupType,"column.sequence":sequence},
		  function(data){
			var status = data.status;
			var message = data.message;
			if( status == 1){
				/*修改成功，将显示内容改为已修改的*/
				conveterValue(id,true);
			}else{
				//修改失败，将编辑框的名称改回原样
				alert(message);
				conveterValue(id,false);
			}
			//显示内容，隐藏编辑框			
			editState(false, id);
			
		},"json");
	}
	
	/**
	state:true   显示内容替换为编辑内容
	state:false  将编辑内容替换为显示内容
	*/
	function conveterValue(id, state){
		//获取所有编辑框和显示span
		//栏目名称
		var namespan = $("#name"+id+" a:only-child");
		var nameinput=$("#iname"+id);
		//分类码
		var classCodespan = $("#classCode"+id);
		var classCodeinput=$("#iclassCode"+id);
		//分类说明
		var typeDesspan = $("#typeDes"+id);
		var typeDesselect=$("#itypeDes"+id);
		//浏览程序
		var readUrlspan = $("#readUrl"+id);
		var readUrlinput=$("#ireadUrl"+id);
		//管理程序
		var manageUrlspan = $("#manageUrl"+id);
		var manageUrlinput=$("#imanageUrl"+id);
		//组别
		var groupTypespan = $("#groupType"+id);
		var groupTypeinput=$("#igroupType"+id);
		//顺序
		var sequencespan = $("#sequence"+id);
		var sequenceinput=$("#isequence"+id);

		var yname = namespan.text();
		var yclassCode = classCodespan.text();
		var ytypeDes = typeDesspan.text();
		var yreadUrl =readUrlspan.text();
		var ymanageUrl =manageUrlspan.text();
		var ygroupType=groupTypespan.text();
		var ysequence=sequencespan.text();
		
		var name = nameinput.val();
		var classCode = classCodeinput.val();
		var typeDes = typeDesselect.val();
		var readUrl =readUrlinput.val();
		var manageUrl =manageUrlinput.val();
		var groupType=groupTypeinput.val();
		var sequence=sequenceinput.val();
		//显示内容替换为编辑内容
		if( state){
			namespan.text(name);
			classCodespan.text(classCode);
			
			switch(parseInt(typeDes)){//列表类\介绍类\系统类\其它类
			case 0:
				typeDesspan.text("列表类");
				break;
			case 1:
				typeDesspan.text("介绍类");
				break;
			case 2:
				typeDesspan.text("系统类");
				break;
			case 3:
				typeDesspan.text("其它类");
				break;
			}
			readUrlspan.text(readUrl);
			manageUrlspan.text(manageUrl);
			groupTypespan.text(groupType);
			sequencespan.text(sequence);
		}else{//将编辑内容替换为显示内容
			nameinput.val(yname);
			classCodeinput.val(yclassCode);
			var selectedValue;
			var typeDes =ytypeDes.trim();
			if( typeDes=="列表类")
				selectedValue=0;
			if( typeDes=="介绍类")
				selectedValue=1;
			if( typeDes=="系统类")
				selectedValue=2;
			if( typeDes=="其它类")
				selectedValue=3;
			
			typeDesselect.val(selectedValue);
			readUrlinput.val(yreadUrl);
			manageUrlinput.val(ymanageUrl);
			groupTypeinput.val(ygroupType);
			sequenceinput.val(ysequence);
		}
		
	}
	/*
	放弃编辑
	*/
	function dropEdit(id) {
		conveterValue(id,false);
		//隐藏编辑框
		editState(false,id);
	}
	
	/*
	显示编辑框
	 点击编辑按钮时显示编辑框同时显示“确认”、“取消按钮”
	*/
	function displayEditButton(id) {
		//显示编辑框
		editState(true,id);
	}
	/*
	 编辑框状态
	 state为true显示编辑框隐藏内容
	 state为false隐藏编辑框显示内容
	*/
	function editState(state,id) {
		//编辑按钮
		var edita = $("#edit"+id);
		//确定按钮
		var okspan = $("#ok"+id);
		//取消按钮
		var cancelspan = $("#cancel"+id);
		//获取所有编辑框和显示span
		//栏目名称
		var namespan = $("#name"+id);
		var nameinput=$("#iname"+id);
		//分类码
		var classCodespan = $("#classCode"+id);
		var classCodeinput=$("#iclassCode"+id);
		//分类说明
		var typeDesspan = $("#typeDes"+id);
		var typeDesselect=$("#itypeDes"+id);
		//浏览程序
		var readUrlspan = $("#readUrl"+id);
		var readUrlinput=$("#ireadUrl"+id);
		//管理程序
		var manageUrlspan = $("#manageUrl"+id);
		var manageUrlinput=$("#imanageUrl"+id);
		//组别
		var groupTypespan = $("#groupType"+id);
		var groupTypeinput=$("#igroupType"+id);
		//顺序
		var sequencespan = $("#sequence"+id);
		var sequenceinput=$("#isequence"+id);
		//显示编辑框
		if(state){
			edita.hide();
			okspan.show();
			cancelspan.show();
			namespan.hide();
			nameinput.attr("type","text");
			classCodespan.hide();
			classCodeinput.attr("type","text");
			typeDesspan.hide();
			typeDesselect.show();
			readUrlspan.hide();
			readUrlinput.attr("type","text");
			manageUrlspan.hide();
			manageUrlinput.attr("type","text");
			groupTypespan.hide();
			groupTypeinput.attr("type","text");
			sequencespan.hide();
			sequenceinput.attr("type","text");
		}else{//隐藏编辑框
			edita.show();
			okspan.hide();
			cancelspan.hide();
			namespan.show();
			nameinput.attr("type","hidden");
			classCodespan.show();
			classCodeinput.attr("type","hidden");
			typeDesspan.show();
			typeDesselect.hide();
			readUrlspan.show();
			readUrlinput.attr("type","hidden");
			manageUrlspan.show();
			manageUrlinput.attr("type","hidden");
			groupTypespan.show();
			groupTypeinput.attr("type","hidden");
			sequencespan.show();
			sequenceinput.attr("type","hidden");
		}
	}
	/**
	添加子栏目进行校验
	**/
	function checkAdd(){
		var name = $("#name").val();
		var classCode = $("#classCode").val;
		if( name.trim()==""||classCode.trim()==""){
			alert("请填写栏目名称和分类码");
			return false;
		}
		alert(classCode.trim().length);
		if( classCode.trim().length>15){
			alert("分类码长度不能大于15");
			return false;
		}
		return true;
	}
</script>
</html>