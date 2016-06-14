<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/wlx/myc" prefix="myc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<style type="text/css">
#container{
text-align: center;

}
#left{
text-align:left;
float:left;margin:0px; padding:0px; padding-bottom:6px; margin-bottom:0px;
margin-left:15px;
width:220px;  background:url(images/AritTGBot.jpg) no-repeat left bottom;
border-right: 2px solid #A6C58A;
}
#right{
width: 760;
float: left;
}

#title{
text-align: center;
border-bottom: 1px solid gray;
}
#info{
text-align: center;
}
#article{
border-top: 1px solid gray;
padding-top: 20px;
}

.left_list {margin-top:-3px; padding-top:6px; padding-bottom:4px; padding-bottom:0px;
	background:url(images/AritTGMid.jpg) repeat-y;
}

#left ul {margin:0px; padding:0; padding-left:5px; padding-bottom:6px; list-style:none; line-height:1.8em; }

#left ul li {
padding-left:10px;
	background:url(images/titlK.gif) no-repeat 0px 8px;
}
#left img{
 width:220px;
 height: 20px;
}
</style>
</head>
<body>
<div id="container">
	<div id="left">
          <img id="AritTG1" src="images/AritTG.jpg" alt="通知公告" usemap="#AritTGMap" />
          <map id="AritTGMap" name="AritTGMap">
              <area shape="rect" coords="260,2,318,23" href="#" alt="更多" title="更多">
          </map>
          <div class="left_list">
              <ul>
              <c:forEach items="${list }" var="entity">
                  <li style="color:#FF6600;">
                   <a  title="${entity.title}" href="" style="">
                        <font color="${entity.titleColor.toString()}">
                         <myc:strout value="${entity.title}" length="13" suffix="" /> 
                         </font>
                    </a>
                  </li>
              </c:forEach>
              </ul>
          </div>
	</div>
	<div id="right">
	 <c:if test="${news }">
	 	<div id="title">
			<h3><font color="${news.titleColor.toString()}">${news.title}</font> </h3>
		</div>
		<div id="info">
		  <span>发表时间：<fmt:formatDate value="${news.createTime }" pattern="yyyy-MM-dd hh:mm" /> </span>  <span style="margin-left: 30px;">阅读量：${news.readCount }</span> 
		</div>
		<div id="article">
			${news.context }
		</div>
	 </c:if>
  	</div>
</div>
</body>
</html>