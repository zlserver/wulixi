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
<base href="<%=basePath%>" target="bodyframe">   
<title>主页头部</title> 
   <link href="css/default.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        <!--
        @import url(css/default.css);
        @import url(css/homepage.css);
        -->
    </style>

    <script type="text/javascript" src="js/prototype.js"></script>
    <script type="text/javascript" src="js/scriptaculous.js?load=effects"></script>
    <script type="text/javascript" src="js/lightbox.js"></script>
    <script type="text/javascript" src="js/ajax.js"></script>
<script  type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
     <style type="text/css">
        #header{padding:0px 0 10px 0;}
        #menu { font:12px verdana, arial, sans-serif; }
        #menu, #menu li {list-style:none;padding:0;margin:0;}
        #menu li { float:left; display: block;width: 107px;}
        #menu li a {display:block; padding:8px 29px;background:#DFF8DF;color:#31802F;text-decoration:none;border-right:1px solid #000;}
        #menu li a:hover {background:#FEFEFE;color:#31802F;text-decoration:none;border-right:1px solid #000;}
        #menu li a.last {border-right:0; }
        #header, #container, #footer, #copyright{
            position:relative;
            margin:0 auto 0;
            display:block;
            width:963px;
        }
       #mainbody{
       height: auto;
       }
       
       #menu .chosen{
       	background-color: white;
       }
    </style>
    <script type="text/javascript">
    function setChosen(anode) {
    	$("a").removeClass("chosen");
		$(anode).addClass("chosen");
	}
    </script>
</head>
<body>
<div id="header">
    <div id="headBg" style="margin-bottom:0px; margin-bottom:0px; *margin-bottom:0px; _margin-bottom:-3px; position:relative; ">
        <img src="images/top.jpg" width="963" height="210" border="0" id="banar1"  />
        <div style="width: 963px;height: 33px;">
            <ul id="menu">
                <li><a class="chosen" onclick="setChosen(this)" style="border-left:1px solid #000;" href="front/home.uhtml"><b>首&nbsp;&nbsp;&nbsp;页</b></a></li>
                <li><a  onclick="setChosen(this)" href="#"><b>学生组织</b></a></li>
                <li><a  onclick="setChosen(this)" href="#"><b>规章制度</b></a></li>
                <li><a  onclick="setChosen(this)"  href="#"><b>思想教育</b></a></li>
                <li><a  onclick="setChosen(this)" href="#"><b>资助管理</b></a></li>
                <li><a  onclick="setChosen(this)"  href="#"><b>心理咨询</b></a></li>
                <li><a  onclick="setChosen(this)"  href="#"><b>就业工作</b></a></li>
                <li><a  onclick="setChosen(this)"  href="#"><b>国防教育</b></a></li>
                <li><a  onclick="setChosen(this)"  href="http://grad.cnu.edu.cn/index.htm"><b>研究生院</b></a></li>
            </ul>
        </div>
    </div>

    <div style="color:#FFFFFF; font-weight:bold; position:absolute; right:20px; top:3px;">
        <span style="CURSOR:pointer;" onclick="window.external.AddFavorite('http://test1.esl.net.cn','首师大学生处')" title="首师大学生处">加入收藏</span>&nbsp;|&nbsp;<span onclick="var strHref=window.location.href;this.style.behavior='url(#default#homepage)';this.setHomePage('http://student.cnu.edu.cn');" style="CURSOR:pointer;">设为首页</span>
    </div>
</div>
</body>

</html>