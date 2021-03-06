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
<title>图片列表</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/pages/share/bootstrap_simple.jsp"></jsp:include>
<link href="css/front/picture_list.css">
<style type="text/css">
#container{
text-align: center;
height:100%;
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
	           <img id="AritTG1" src="images/newlist_top.png" alt="列表" usemap="#AritTGMap" /> 
	         
	          <p id="title_text">
	          <myc:newstitle classCode="${preClassCode}"/>
	          </p>
          </div>
          <div class="left_list">
              <ul>
              <c:forEach items="${listColumn }" var="entity">
                  <li style="color:#FF6600;">
                   <a  title="${entity.name}" href="front/picture/pictureList.uhtml?preClassCode=${ preClassCode}&columnId=${entity.id }" style="">
                       ${entity.name}
                    </a>
                  </li>
              </c:forEach>
              </ul>
          </div>
	</div>
	<div id="right">
		<form  action="front/picture/pictureList.uhtml" method="get">
		 <input type="hidden" name="preClassCode" value="${preClassCode}">
	    <input type="hidden" name="columnId" value="${formbean.columnId}">
	    <input type="hidden" name="page" >
	  	
		 <table> 
			
			<tbody>
			 <c:forEach  items="${pageView.records }" var="entity" varStatus="status" >
			 <c:if test="${status.index%2==0 }">
			 <tr>
			 </c:if>
				 <td style="text-align: left;padding-left: 20px;">
					<img style="width: 300px;height: 280px;margin-left: 20px;" alt="" src="front/download/lookImage.uhtml?savePath=${entity.savePath }&originName=${entity.originName}">
				 	<p align="center">校园风光</p>
				 </td>
		    <c:if test="${status.index%2==1 }">
			 </tr>
			 </c:if>
			 </c:forEach> 
			</tbody>
		</table>
	   <div> 
	    <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
		</div>
	</form>
  	</div>
  	<p id="clearfolat"></p>
</div>
<script type="text/javascript">
//查询
function topage(page)
{
	var form = document.forms[0];
	form.page.value= page;
	form.submit();
}
</script>
</body>
</html>