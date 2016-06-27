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
width: 731px;
float: left;
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
tr{
border-bottom: 1px solid #BDC9D5;
}
td{
 height: 28px;
 
}
</style>
</head>
<body>
<div id="container">
	<div id="left">
		 <!--  <span style="width: 220px;height:20px;background-color: #D0E4BF;">
		  
		  </span> -->
		  <div id="newlist_title">
	           <img id="AritTG1" src="images/newlist_top.png" alt="通知公告" usemap="#AritTGMap" /> 
	          <map id="AritTGMap" name="AritTGMap">
	              <area shape="rect" coords="260,2,318,23" href="#" alt="更多" title="更多">
	          </map>
	          <p id="title_text">
	          <myc:newstitle classCode="${classCode}"/>
	          </p>
          </div>
          <div class="left_list">
              <ul>
              <c:forEach items="${listColumn }" var="entity">
                  <li style="color:#FF6600;">
                   <a  title="${entity.name}" href="front/download/single.uhtml?columnId=${entity.id}&page=1&classCode=down" style="">
                       ${entity.name}
                    </a>
                  </li>
              </c:forEach>
              </ul>
          </div>
	</div>
	<div id="right">
	 <table> 
		<thead>
		 <tr>
		   <td  width="50%">文件名 </td>
	       <td  width="14%">上传时间  </td>
	       <td  width="10%"> 大小</td>
	       <td  width="6%">下载量</td>
		 </tr>
		</thead>
		<tbody>
		<c:forEach  items="${pageView.records }" var="entity" >
		 <tr>
			 <td style="text-align: left;">
				 <a title="${entity.originName }" href="front/download/down.uhtml?savePath=${entity.savePath }&originName=${entity.originName}">
				<myc:strout value="${entity.originName }" length="35" suffix="..." /> 
				 </a> 
			 </td>
			 <td>
			 <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd" />
			 </td>
			 <td><myc:convert size="${entity.size}"/> </td>
			 <td><span >${entity.downloadCount }</span></td>
		 </tr>
		 </c:forEach>
		</tbody>
	</table>
  	</div>
  	<p id="clearfolat"></p>
</div>
</body>
</html>