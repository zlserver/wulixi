<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<title>添加新闻</title>      

</head>
<body>
	<form method="post" action="control/news/add.action" enctype="multipart/form-data" >	
		<input type="hidden" name="columnId" value="${columnId }">
		<table class="table table-bordered">
			<tr>
				<td>题目<input type="text" class="form-control" name="news.title"  required autofocus></td>
			</tr>
			<tr>
				<td>
				标题颜色：
				<span>
				  <input type="radio" name="titleColor" value="BLACK"  checked="checked">黑色
				  <input type="radio"  name="titleColor" value="RED"  >红色
				  <input type="radio"  name="titleColor" value="BLUE"  >蓝色
				</span>
				</td>
			</tr>
			
			<tr>
				<td  >
				<textarea  rows="25" cols="80" id="context" name="news.context" required ></textarea>
				 <script type="text/javascript">
                		CKEDITOR.replace( 'context' );
            	</script>
				</td>
			</tr>
			<tr>
				<td  align="center">
					<input type="submit" class="btn btn-primary" value="&nbsp;&nbsp;确定&nbsp;&nbsp;" >
					<a href="control/news/list.action" class="btn btn-warning">返回</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>