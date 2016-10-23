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
padding-left:10%;
padding-top:5%;
padding-right:6px;
padding-bottom:6px;
margin-left:5px;
margin-top:3px;
margin-bottom:5px;
border: 2px solid #DFF8DF;
text-align: center;
}

#title{
text-align: center;
border-bottom: 1px solid gray;
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

  #xgbox{
        	width: 540px;
        	height: 330px;
        	padding:0px;
        	position: relative;
          	overflow: hidden;
          	text-align: center;
        }
        #xgbox ul{
            position: absolute;
        	width: 600%;
        	top:0px;
        	left:0px;
        	margin: 0px;
        	padding: 0px;
            list-style: none;
        }
        #xgbox ul li{
        	height: 330px;
        	width: 540px;
        	float: left;
        	margin:0px;
        	padding:0px;
            list-style:none;
        }
        #xgbox ul li img{
        	width: 540px;
        	height: 330px;
        }
        
         #arr {
            display:none;
        }

        #arr span {
            width: 50px;
            height: 100px;
            position: absolute;
         
            top: 40%;
            margin-top: -20px;
            background: #000;
            cursor: pointer;
            line-height: 40px;
            text-align: center;
            font-weight: bold;
            font-family: '����';
            font-size: 40px;
            color: #fff;
            opacity: 0.3;
            border: 1px solid #fff;
            padding:23px 0px 0px 0px;
        }
	   #left_span{
	      left: 5px;
	   }
       #right_span {
            right: 5px;
            left: auto;
        }
        #pic_des{
        	padding-top: 5px;
        	font-size: 14px;
        	text-align: center;
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
		<div id="xgbox">
	        <ul id="xgtarget">
	        <c:forEach  items="${pageView.records }" var="entity" varStatus="status" >
			   <li>
			   <img alt="${entity.des }" src="front/download/lookImage.uhtml?savePath=${entity.savePath }&originName=${entity.originName}">
			   </li>
			</c:forEach> 
	        <li>
			   <img alt="${pageView.records.get(0).des }" src="front/download/lookImage.uhtml?savePath=${pageView.records.get(0).savePath }&originName=${pageView.records.get(0).originName}">
		    </li>
	       </ul>
	        <div id="arr"><span id="left_span">&lt;</span><span id="right_span">&gt;</span></div>
  	
      </div>
      <div>
	     <p id="pic_des">${pageView.records.get(0).des }</p>
	  </div>
	  <form  action="front/picture/pictureList.uhtml" method="get">
		 <input type="hidden" name="preClassCode" value="${preClassCode}">
	    <input type="hidden" name="columnId" value="${formbean.columnId}">
	    <input type="hidden" name="page" >
	    <div> 
	    <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
		</div>
	</form>
	 </div>
  	<p id="clearfolat"></p>
</div>

<script type="text/javascript" src="js/jquery1.9.1/jquery.min.js"></script>
<script type="text/javascript">
//学工新闻
var xg_current =0;
var xg_imageWidth =540;
var xg_setId;
var xg_target = $("#xgtarget");
var xg_box = $("#xgbox");
//间隔时间
var midle = 4000;
var piccount='${piccount}';
piccount++;

var xuewidthpow=piccount*100;
$("#xgtarget").width(xuewidthpow+"%");//设置记账图片长度
	
 xg_box.bind('mouseover',function(){
	
	$("#arr").show();
});
xg_box.bind('mouseout',function(){
	
	$("#arr").hide();
}); 
$("#left_span").bind('click',function(){
	clearInterval(xg_setId);
	xg_current--;
	if(xg_current<0)
		xg_current=piccount-1;
	var leftmargin=-((xg_current)*xg_imageWidth);
	xg_target.css("left",leftmargin+"px");

	var des=$("#xgtarget li img:eq("+xg_current+")").attr("alt");

	$("#pic_des").html(des);
	xg_setId=setInterval(xgcyc,midle);
});

$("#right_span").bind('click',function(){
	clearInterval(xg_setId);
	
	xg_current++;
	if(xg_current>=(piccount-1))
			xg_current=0;
	var leftmargin=-((xg_current)*xg_imageWidth);
	xg_target.css("left",leftmargin+"px");
	var des=$("#xgtarget li img:eq("+xg_current+")").attr("alt");

	$("#pic_des").html(des);
	xg_setId=setInterval(xgcyc,midle);
});
//学工新闻窗口移动
function xgcyc() {
	
	var des=$("#xgtarget li img:eq("+xg_current+")").attr("alt");
	
	if(xg_current==(piccount-1))
		var des=$("#xgtarget li img:eq(0)").attr("alt");
	if( xg_current==piccount){
		xg_target.css("left",0+"px");
		xg_current=1;
		var des=$("#xgtarget li img:eq(1)").attr("alt");
	}
	var leftmargin=-(xg_current*xg_imageWidth);
	$(xg_target).animate({left:leftmargin},{duration:500});
	xg_current++;
	$("#pic_des").html(des);
}

xg_setId=setInterval(xgcyc,midle);
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