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
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
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
    
    /*  */
	function _action(choice) {
		
    	var state = $("#state");
    	
		if(choice=="save")
			state.val("WAITING");
		if(choice=="publish")
			state.val("PUBLISH");
		
		return true;
	}
</script>
</head>
<body>
	<form method="post" action="control/news/add.action" enctype="multipart/form-data" >	
		<input type="hidden" name="columnId" value="${columnId }">
		<input type="hidden" name="state" id="state" >
		<table class="table table-bordered">
			<tr>
				<td>题目<input type="text" class="form-control" name="news.title" value="${formbean.news.title }" required autofocus></td>
			</tr>
			<tr>
				<td>
					<span>
					    标题颜色：<!-- checked="checked" -->
					 <c:choose>
					 	<c:when test="${formbean.titleColor!=null}">
					 	 <input type="radio" name="titleColor" value="BLACK"  ${formbean.titleColor.equals("BLACK")?"checked":""}  >黑色
					     <input type="radio"  name="titleColor" value="RED"  ${formbean.titleColor.equals("RED")?"checked":""} >红色
					     <input type="radio"  name="titleColor" value="BLUE"  ${formbean.titleColor.equals("BLUE")?"checked":""} >蓝色
					    </c:when>
					 	<c:otherwise>
					 	  <input type="radio" name="titleColor" value="BLACK"  checked="checked" >黑色
					      <input type="radio"  name="titleColor" value="RED"   >红色
					      <input type="radio"  name="titleColor" value="BLUE"   >蓝色
					   </c:otherwise>
					 </c:choose>
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
				<td  align="center">
					<input type="submit"  value="保存" class="btn btn-info" onclick="return _action('save')" >
					<input type="submit"  value="发表" class="btn btn-info" onclick=" return _action('publish')" >
					<a href="javascript:history.go(-1);" class="btn btn-warning">返回</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>