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
height:100%;
/* border-right: 2px solid #DFF8DF;
border-left: 2px solid #DFF8DF;
margin-left: 15px; */
/* background:url(images/AritTGMid.jpg) repeat-y; */
}
#left{
text-align:left;
float:left;
margin:0px; 
padding:0px; 
padding-bottom:6px; 
margin-bottom:0px;
margin-left:3px;
width:220px;  background:url(images/AritTGBot.jpg) no-repeat left bottom;
border-right: 2px solid #A6C58A;
}
#right{
width: 730px;
float: right;
padding-left:6px;
padding-right:6px;
padding-bottom:6px;
margin-left:5px;
margin-top:3px;
margin-bottom:5px;
border: 2px solid #DFF8DF;
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
text-align: left;
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
/* 附件 */
#attach{
margin-top:5px;
text-align: left;

}
#clearfolat{
clear: both;
}
#newlist_title{
position: relative;
}
#title_text{
position: absolute;
top: 1px;
left: 10px;
color: #376934;
font: bold;
}
</style>
</head>
<body>
<div id="container">
	<div id="left">
		 <!--  <span style="width: 220px;height:20px;background-color: #D0E4BF;">
		  学工新闻
		  </span> -->
		  <div id="newlist_title">
	           <img id="AritTG1" src="images/newlist_top.png" alt="列表" usemap="#AritTGMap" /> 
	         <!--  <map id="AritTGMap" name="AritTGMap">
	              <area shape="rect" coords="260,2,318,23" href="#" alt="更多" title="更多">
	          </map> -->
	          <p id="title_text">
	          <myc:newstitle classCode="${classCode}"/>
	          </p>
          </div>
          <div class="left_list">
              <ul>
              <c:forEach items="${listnews }" var="entity">
                  <li style="color:#FF6600;">
                   <a  title="${entity.title}" href="front/news/siglenews.uhtml?classCode=${classCode}&newsId=${entity.id }" style="">
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
	 <c:if test="${news!=null}">
	 	<div id="title">
			<h3><font color="${news.titleColor.toString()}">${news.title}</font> </h3>
		</div>
		<div id="info">
		  <span>发表时间：<fmt:formatDate value="${news.createTime }" pattern="yyyy-MM-dd hh:mm" /> </span>  <span style="margin-left: 30px;">阅读量：${news.readCount }</span> 
		</div>
		<div id="article">
			${news.context }
		</div>
		<div id="attach">
		
		
		<c:forEach items="${newsFiles}" var="newsFile" varStatus="status">
		 <c:if test="${ status.count==1}">
		 <p style="color: red;">附件：</p>
		 </c:if>
	      <p><a href="front/news/download.uhtml?savePath=${newsFile.savePath}">${newsFile.originName }</a></p>
	    </c:forEach>
		</div>
	 </c:if>
  	</div>
  	<p id="clearfolat"></p>
</div>
</body>
</html>