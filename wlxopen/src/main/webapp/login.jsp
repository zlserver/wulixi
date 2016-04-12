<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>后台登录界面</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   /*更新验证码*/
  function changeimage(img)
     {
     /*每点击一次地址都不一样，这样浏览器就不会取缓存中的页面而是重新访问  */
        img.src = img.src+"?"+ new Date().getTime();
     }
     
     /*防止表单重复提交*/
   var iscommitted = false;
   function doSubmit()
   {
     if( !iscommitted )
     {
       iscommitted = true;
       return true;
     }
     return false;
   }
  </script>
</head>
<body>
<table>
	<tr>
		<td  class="td2">
		<input class="userinput" type="text" name="checkCode" value="${form.checkCode }"/>
		<img alt="" src="<c:url value='/common/checkCode.html'/>"  onclick="changeimage(this)"  style="cursor:hand">
		  <span id="checkcode" >${form.errors.checkCode }</span> 
		</td>
	</tr>
</table>
</body>
</html>