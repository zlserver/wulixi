<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单词主题</title>
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/admin-all.css">
<link rel="stylesheet" type="text/css" href="css/base.css">
<script type="text/javascript" src="js/admin/word/wordthemelist.js"></script> 

<style type="text/css">
.editClass{
cursor:pointer;
}
.xeditClass{
cursor:pointer;
display: none;
}
</style>

</head>
<body>

<div class="panel panel-default">
<ol class="breadcrumb">
  <li><a href="admin/control/wordtheme/list.html">单词主题</a></li>
  <c:forEach items="${urlParams}" var="item"  varStatus="status">
    <li><a href="admin/control/wordtheme/list.html?id=${item.key}">${item.value }</a></li>
    <!-- 设置父类id -->
    <c:set var="parentId" value="${item.key}"></c:set>
    <!-- 主题等级 ,如果是二级主题则不可以添加主题,如果当前页是一级主题下的子主题themeGrade=1，则不应该有添加主题按钮-->
    <c:set var="themeGrade" value="${status.count}"></c:set>
  </c:forEach>
</ol>

  <div class="panel-body">
	<form  action="<c:url value='admin/control/wordtheme/list.html'/>" method="post">
	<!-- 只有一级目录才可以查询 -->
	<c:if test="${themeGrade!=1 }">
		<table >
	       <tbody>
	         <tr>
	           <td>查询条件：</td>
	           <td>&nbsp;&nbsp;主题状态：</td>
	           <td>
		           <select id="qvisible"  name="qvisible">
	               <option value="0"  ${formbean.qvisible==0?"selected":"" } >状态</option> 
	               <option  value="1" ${formbean.qvisible==1?"selected":"" } >已开启</option>
	               <option value="2" ${formbean.qvisible==2?"selected":"" } >已屏蔽</option> 
	              </select>
	            </td>
	           <td>
	           <input  type="button" onclick="topage(1)" value="查询 " name="sButton2">
	           </td>
	        </tr>
	     </tbody>
	   </table>
	</c:if>
	
   <!-- 查询参数 -->
    <input type="hidden" name="page">
    <!-- 父类id -->
    <input type="hidden" name="id" value="${parentId}">
    <!-- 查询是否开启-->
    <input type="hidden" name="qvisible" value="${formbean.qvisible}">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
				<td align="center" width="20%">主题ID</td>
				<td align="center" width="10%">主题序号</td>
				<td align="center" width="15%">主题内容</td>
				<td align="center" width="20%">英文意思</td>
				<td align="center" width="8%">logo</td>
				<td align="center" width="12%">创建时间</td>
				<!-- 只有一级目录才可以屏蔽 -->
		        <c:if test="${themeGrade!=1 }">
				 <td align="center" width="5%">状态</td>
				</c:if>
				<td align="center" width="10%">操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageView.records }" var="entity">
			<tr id="theme${entity.id}">
				<!-- 只有一级目录可以点击 -->
				<c:choose>
				  <c:when test="${ themeGrade==1}">
				  <td align="center">${entity.id}</td> 
				 </c:when>
				  <c:otherwise>
				  <td align="center"><a href="<c:url value='admin/control/wordtheme/list.html?id=${entity.id }&page=1'/>">${entity.id} </a></td> 
				 </c:otherwise>
				</c:choose>
				
				<td align="center">${entity.number}</td>
				<td align="center">
				 <span id="s${entity.id}">${entity.content}</span>
				 <input type="hidden" id="i${entity.id}"  value="${entity.content}"> 
				  <!--  glyphicon-ok  glyphicon-pencil-->
				 <span class="glyphicon glyphicon-pencil editClass" id="e${entity.id}" aria-hidden="true" onclick="editName('${entity.id}')" ></span>
				 &nbsp;
				 <span class="glyphicon glyphicon-remove xeditClass" id="de${entity.id}" aria-hidden="true" onclick="dropEditName('${entity.id}')" ></span>
				 
				 </td>
				<td align="center">${entity.english}</td>
				<td align="center" >
					<img src="${entity.picturePath }" class="img-circle" style="width:30px;height: 30px;" alt="...">
				</td>
				<td align="center">${entity.createTime} </td>
				<!-- 只有一级目录才可以屏蔽 -->
		       <c:if test="${themeGrade!=1 }">
					<td align="center">
						  <c:if test="${entity.visible }">
						   <input type="button"     value="已开启" id="envisible${entity.id }" class="btn btn-success btn-xs" onclick="javascript:ajaxDisable('${entity.id }')">
						   <input type="button" style="display: none;" value="已屏蔽" id="disvisible${entity.id }" class="btn btn-danger btn-xs" onclick="javascript:ajaxEnable('${entity.id }')">
						  </c:if>
						  <c:if test="${!entity.visible }">
						    <input type="button"  style="display: none;" value="已开启" id="envisible${entity.id }" class="btn btn-success btn-xs" onclick="javascript:ajaxDisable('${entity.id }')">
						    <input type="button"  value="已屏蔽" id="disvisible${entity.id }" class="btn btn-danger btn-xs" onclick="javascript:ajaxEnable('${entity.id }')">
						   </c:if>
					</td>
				</c:if>
				<td align="center">
				  <input type="button" value="删除" class="btn btn-primary btn-xs" onclick="javascript:ajaxDelete('${entity.id }')">
				  <a href="<c:url value='admin/control/wordtheme/editUI.html?id=${entity.id}'/>" class="btn btn-info btn-xs" >编辑</a>
				</td>
			</tr>
			
		 </c:forEach>
		</tbody>
	</table>
		<div align="center">
		 <a href="<c:url value='admin/control/wordtheme/addUI.html?parentId=${parentId}'/>"  class="btn btn-info" >添加主题</a>
		 	<!-- 只有一级目录才可以导入主题 -->
	     <c:if test="${themeGrade!=1 }">
		  <a href="<c:url value='admin/control/wordtheme/importUI.html'/>"  class="btn btn-warning " >导入主题</a>
		 </c:if>
		</div>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
</div>
</body>
</html>