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
<title>单词列表界面</title>
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/admin-all.css">
<link rel="stylesheet" type="text/css" href="css/base.css">
<script type="text/javascript">
	function del() {
		/* if (!confirm("确定禁言?"))
			return false;
		else */
			return true;
	}
	function topage(page)
	{
		var form = document.forms[0];
		form.page.value= page;
		form.submit();
	}
	 function dropLook(qpage) {
		var form = document.forms[0];
		form.page.value=qpage;
		form.action="control/group/list";
		form.submit();
	}
	function _action(methodName,uuid){
		var form = document.forms[0];
		form.action="admin/control/word/"+ methodName+".html?uuid="+uuid;
		form.submit();
	}
</script>
</head>
<body>


<div class="panel panel-default">
  <%-- <div class="panel-heading">
    <h3 class="panel-title">当前位置 &gt;热点话题管理 &gt;<a href='control/group/list'><font color="blue">热点话题信息</font></a>&gt;<font style="font-family:'楷体';font-weight: bold; ">${pageView.records[0].groupChat.name}</font></h3>
  </div> --%>
  <div class="panel-body">
  
	<form  action="<c:url value='admin/control/word/list.html'/>" method="post">
	
	<table >
       <tbody>
         <tr>
           <td>查询条件：</td>
           
           <td>&nbsp;&nbsp;单词内容：</td>
           <td>
           <input type="text"   id="content" name="content" value="${formbean.content }" style="width: 120px"  >
           </td>
            <td>&nbsp;&nbsp;版本信息:</td>
           <td>
	            <select  name="version" >
				  <option value="0" ${formbean.version==0?"selected":"" }>---------------------</option>
				  <option value="1" ${formbean.version==1?"selected":"" }>北师大版</option>
				  <option value="2" ${formbean.version==2?"selected":"" }>北京版</option>
				  <option value="3" ${formbean.version==3?"selected":"" }>外研社新标准</option>
				  <option value="4" ${formbean.version==4?"selected":"" }>外研社一年级起</option>
				  <option value="5" ${formbean.version==5?"selected":"" }>人教版</option>
				  <option value="6" ${formbean.version==6?"selected":"" }>朗文版</option>
				</select>
				版
           </td>
           <td>
	            <input type="text" id="book" name="book"  value="${formbean.book }" style="width: 40px">册
           </td>
           <td>
	            <input  type="text" id="unit" name="unit"   value="${formbean.unit }" style="width: 40px">单元
           </td>
           <td>
	            <input type="text"  id="rank" name="rank"  value="${formbean.rank }" style="width: 40px">序号
           </td> 
           <td>
           <input  type="button" onclick="topage('1')" value="查询 " name="sButton2">
           </td>
        </tr>
     </tbody>
   </table>
   <!-- 查询参数 -->
    <input type="hidden" name="page">
    <input type="hidden" name="uuid">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
				<td align="center" width="7%">单词ID</td>
				<td align="center" width="10%">单词</td>
				<td align="center" width="15%">版本信息</td>
				<td align="center" width="28%">单词资源</td>
				<td align="center" width="10%">单词属性资源</td>
				<td align="center" width="10%">创建时间</td>
				<td align="center" width="5%">操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageView.records }" var="entity">
			<tr>
				<td align="center">${entity.id} </td> 
				<%-- <td align="center"><img alt="无" src="${entity.picturepath}" class="img-rounded" style="width: 35px;height: 30px;"/>   </td>
				<td align="center"><a href='control/user/list?qaccount=${entity.account}'>${entity.account }</a></td> --%>
				<td align="center">${entity.content} </td>
				<td align="center">
				${entity.version==1?"北师大版":"" }${entity.version==2?"北京版":"" }
				${entity.version==3?"外研社新标准":"" }${entity.version==4?"外研社一年级起":"" }
				${entity.version==5?"人教版":"" }${entity.version==6?"朗文版":"" }
				${entity.book}册${entity.unit}单元第${entity.rank}个单词
				 </td>
				<td align="center">
				<a class="btn btn-info btn-xs" href="<c:url value='admin/control/wordresource/addUI.html?uuid=${entity.uuid }'/>">添加资源</a>
				 <a class="btn btn-primary btn-xs" href="<c:url value='admin/control/wordresource/resourceDetailUI.html?uuid=${entity.uuid }'/>">查看详情</a>
				  </td>
				
				<td align="center">
				<a class="btn btn-info btn-xs" href="<c:url value='admin/control/wordattributeresource/addUI.html?uuid=${entity.uuid }'/>">添加属性资源</a>
				<a class="btn btn-primary btn-xs" href="<c:url value='admin/control/wordattributeresource/resourceDetailUI.html?uuid=${entity.uuid }'/>">查看详情</a>
				</td>
	
				
				<td align="center">${entity.createtime} </td>
				<td align="center">
				 <input type="button" value="编辑" class="btn btn-info btn-xs" onclick="javascript:_action('delete','${entity.uuid }')">
				 </td>
			</tr>
		 </c:forEach>
		</tbody>
	</table>
		<div align="center">
		 <a href="<c:url value='admin/control/word/addUI.html'/>"  class="btn btn-info" >添加单词</a>
		 <a href="<c:url value='admin/control/word/importUI.html'/>"  class="btn btn-primary" >导入单词</a>
		</div>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
</div>
</body>
</html>