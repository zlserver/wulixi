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
<title>回应壁列表</title> 
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
.comm{
text-align: left;
border: 1px solid #BDC9D5;
margin:0px 0px 5px 5px;
padding-bottom:3px;
position: relative;
}
.q_title{
background-color:#DFF8DF;
margin: 0px 0px 5px 0px;
padding-left: 3px;
}
.desc{
padding:0px 0px 0px 13px;
}
.question{
margin-bottom: 10px;
}
.quesarea{
text-align: left;
border: 1px solid #BDC9D5;
margin:0px 0px 5px 5px;
padding: 5px 0px 3px 5px;
position: relative;

}
.quesarea p{
padding: 0px;
margin: 0px;
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
	          <myc:newstitle classCode="${classCode}"/>
	          </p>
          </div>
          <div class="left_list">
              <ul>
              <c:forEach items="${hotQuestions }" var="entity">
                  <li style="color:#FF6600;">
                   <a  title="${entity.title}" href="front/question/hot.uhtml?questionId=${entity.id}&classCode=${classCode}" style="">
                       ${entity.title}
                    </a>
                  </li>
              </c:forEach>
              </ul>
          </div>
	</div>
	<div id="right">
	   <div class="quesarea">
	    <form action="front/question/add.uhtml" method="post" onsubmit="return checkcontent()">
	    	<input type="hidden" name="classCode" value="${classCode}">
		    <p>问&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：
		         <input type="text"  name="question.title" id="q_title" style="width:587px " >  </p>
		    <p>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：
		    	<input type="radio" value="student" checked="checked" name="question.identity">学生 
		    	<input type="radio" value="teacher" name="question.identity">老师
		    </p>
		    <p>问题描述：<textarea rows="3" cols="90" id="q_content" name="question.content"></textarea>  </p>
		    <p style="margin-left: 69px;"><input type="submit" value="提问">  </p>
	    </form>
	   </div>
	   
	<form  action="front/question/list.uhtml" method="get">
	 <input type="hidden" name="classCode" value="${classCode}">
     
    <input type="hidden" name="page" >
  	 <c:forEach  items="${pageView.records }" var="entity" >
		 <div class="comm">
		   <div class="question">
			   <p class="q_title">
			       <myc:strout value="${entity.title }" length="35" suffix="..." /> 
			          <span style="position:absolute; right:10px;">
			             <font color="#337AB7"> <fmt:formatDate value="${entity.createTime }" pattern="yyyy-MM-dd HH:mm" /></font>
				      </span>
				</p>
			    <div class="desc" >
				    <span class="label label-success">问题描述</span>
				    <span>${entity.content}</span>
				</div>
		   </div>
		   <div class="desc" >
			    <span class="label label-info">回答</span>
			    <span>${entity.answer }</span>
		   </div>
		 </div>
		 </c:forEach>
		 <div> 
		 <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
		 </div>
	  </form>
  	</div>
  	<p id="clearfolat"></p>
</div>
</body>

<script type="text/javascript">
function checkcontent() {
	var title =$("#q_title").val();
	var content =$("#q_content").val();
	if( !title || title.trim()==""){

		alert("请输入问题");
		return false;
	}
	if( !content || content.trim()==""){

		alert("请输入问题描述");
		return false;
	}
	
	return true;
}
//查询
function topage(page)
{
	var form = document.forms[1];
	form.page.value= page;
	form.submit();
}
</script>
</html>