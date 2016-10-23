<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">   
<title>物理系学生工作网站</title> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script  type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script> 
<style type="text/css">
body{
text-align: center;
}
#bodyframe{
height: 500px;
margin-left: 13px;
border-right: 2px solid rgb(223, 248, 223);
border-left: 2px solid rgb(223, 248, 223);
}
#hitwin{
	width: 340px;
	height: 220px;
	position: fixed;
	border: 2px solid red;
	border-radius:5px;
	background-color:#FFFFFF;
	text-align: center;
	padding: 2px;
}
#hitwin  img{
width: 330px;
height: 188px;

}

.hita{
	margin-left: 2px;
	height:25px;
	width:340px;
	display: block;
	font-size: 16px;
	text-decoration: none;
	color: red;
}
.hitoff{
position: absolute;
top: 0px;
right: 0px;
height: 25px;
width: 25px;
border-radius:0px;
font-size:24px;
color:white;
background-color:red;
cursor: pointer;
}
</style>   
</head>
<body>
<iframe name="topframe" width="980" id="topframe" frameborder="0"   scrolling="no" onload="this.height=this.contentWindow.document.documentElement.scrollHeight" src="<c:url value='front/top.uhtml?classCode=DHGL01'/>"></iframe>

<iframe name="bodyframe" width="962" id="bodyframe" frameborder="0"   scrolling="no" onload="setHigh(this)" src="<c:url value='front/home.uhtml'/>"></iframe>

<iframe name="bottomframe" width="980" id="bottomframe" frameborder="0"  name="bottom" scrolling="no" onload="this.height=this.contentWindow.document.documentElement.scrollHeight" src="<c:url value='front/center/bottom.uhtml'/>"></iframe>
 
 <div id="hitwin">
 <a href="${inform.link }" class="hita" target="bodyframe">
<marquee>${inform.title }</marquee>
 </a>
 <a target="bodyframe" href="${inform.link }" >
 <img alt="" src="front/news/lookImage.uhtml?savePath=${inform.picPath}" >
 </a>
  <span class="glyphicon glyphicon-remove hitoff" id="ok${entity.id}" title="关闭" aria-hidden="true" ></span>
 
 </div>
</body>
<script type="text/javascript">
var bottom=0;
var right=0;
var link ='${inform.link }';
var show  = '${show}';
$(document).ready(function(){
	var divWin=$("#hitwin");
	divWin.hide();
	//alert(show);
	if( show=="true"){
		//根据链接判断是站内新闻还是站外新闻，站内新闻在当前页显示，站外新闻在新页面显示
		
		var index =link.indexOf("wlxopen");
		if( index<0){//属于站外新闻
			$("a").attr("target","_blank");
		}
			
		$(".hitoff").bind("click",function(){
			divWin.hide();
		});
		setTimeout(function() {
			divWin.css("bottom",bottom);
			divWin.css("right",right);
			divWin.show();
		}, 1000);
	}
	
});

function setHigh(win) {
	var height =win.contentWindow.document.documentElement.scrollHeight;
	
	/* var subWeb= win.contentDocument;
	var heigh=subWeb.body.scrollHeight; */
	//alert(height+":"+heigh);
	if(height<500)
		$(win).height(500);
	else
	   $(win).height(height);
}
</script>
</html>