<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wordresult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"> 
  </head>
  
   <body>
         单词:<input type="text" style="height:30px;width:300px"name="text" value="${word.content}"><br><br><br>
         课文原句：${word.propertyText}<br><br>   
         情景段落：${word.propertyScene}<br><br>    
          延伸例句：${word.propertyExtend}<br><br>   
          联想：${word.propertyAssociate}<br><br> 
          同义词 ：${word.propertySynonyms}<br><br> 
         反义词：${word.propertyAntonym}<br><br> 
         拓展：${word.propertyExpand}<br><br> 
         常用：${word.propertyCommonUse}<br><br>     
         百科：${word.propertyNcyclopedia}<br><br>       
          用法：${word.propertyUse}<br><br> 
          
    <input type="button" name="Log" value="单词属性" onclick="window.location.href='<%=basePath%>pass/control/passControl/list.html'">&nbsp;&nbsp; </td>
                     
          
    <%-- <input type="button" name="button" value="查看" onclick=window.location.href="<%=basePath%>/pages/pass/pass.jsp";/>           
           <span class="btn btn-warning ">中文含义:</span><input type="text" style="height:30px;width:300px"name="text" value="${word.propertyChinese }"><br><br><br>
        <span class="btn btn-primary">主题-功能意念：</span>${word.propertyFunction }<br><br>    
         <span class="btn btn-success ">反义词：</span>${word.propertyAntonym }<br><br>   
         <span class="btn btn-info">同义词：</span>同义词：${word.propertySynonyms }<br><br>       --%>  
  </body>
</html>
