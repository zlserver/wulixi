<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/wlx/myc" prefix="myc" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">   
<title>首页body</title> 
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
            margin-left: -8px;
        }
    </style>
</head>
<body>

<div id="container">
    <div id="main">

        <div id="area1">
            <!--学工新闻-->
            <div id="artiNews">
                    <img id="artiNews1" src="images/ArtiNews.jpg" alt="学工新闻" usemap="#artiNewsMap" />
                    <map id="artiNewsMap" name="artiNewsMap">
                        <area shape="rect" coords="616,2,695,23" href="front/news/siglenews.uhtml?classCode=xue" alt="更多" title="更多">
                    </map>

                    <div class="NewsMain">
                        <table cellpadding="0" cellspacing="0" border="0" width="695">
                            <tr>
                                <td width="247" align="center">
                                    <div id="fgImg">
                                     <a  title="${hotNews.title}" href="front/news/siglenews.uhtml?classCode=xue&newsId=${hotNews.id }">
                                       <img src="front/news/lookImage.uhtml?savePath=${xuePic.savePath}" alt="" width="240" height="210" />
                                     </a>
                                   </div>
                                    <!--新闻链接-->
                                    <%-- <a  title="${hotNews.title}" href="front/news/siglenews.uhtml?classCode=xue&newsId=${hotNews.id }">
                                          <font color="${hotNews.titleColor.toString()}">
                                           <myc:strout value="${hotNews.title}" length="30" suffix="..." /> 
                                           </font>
                                     </a> --%>
                                </td>
                                <td valign="top">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    
                                     <c:forEach items="${xueNews}" var="xueNew">
                                        <tr height="22">
                                            <td width="21" align="center"><img src="images/titlK.gif" /></td>
                                            <td>
                                            <a  title="${xueNew.title}" href="front/news/siglenews.uhtml?classCode=xue&newsId=${xueNew.id }">
                                            <font color="${xueNew.titleColor.toString()}">
                                             <myc:strout value="${xueNew.title}" length="30" suffix="..." /> 
                                             </font>
                                            </a>
                                            </td>
                                            <td width="30">
                                            <fmt:formatDate value="${xueNew.createTime }" pattern="yy-MM-dd" />
                                            </td>
                                        </tr>
                                       </c:forEach>
                                        
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            <div id="Box1">
                <!--通知公告-->
                <div id="AritTG">
                    <img id="AritTG1" src="images/AritTG.jpg" alt="通知公告" usemap="#AritTGMap" />
                    <map id="AritTGMap" name="AritTGMap">
                        <area shape="rect" coords="260,2,318,23" href="front/news/siglenews.uhtml?classCode=tong" alt="更多" title="更多">
                    </map>
                    <div class="TGCont">
                        <ul>
                        <c:forEach items="${tongNews }" var="tongNew">
                            <li style="color:#FF6600;">
                             <a  title="${tongNew.title}" href="front/news/siglenews.uhtml?classCode=tong&newsId=${tongNew.id }" style="">
                                  <font color="${tongNew.titleColor.toString()}">
                                   <myc:strout value="${tongNew.title}" length="20" suffix="..." /> 
                                   </font>
                              </a>
                              <img alt="" src="images/new.gif">
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                </div>
                <!--就业信息-->
                <div id="AritYx">
                    <img id="AritYx1" src="images/ArtiYX.jpg" alt="就业信息" usemap="#AritYXMap" />
                    <map id="AritYXMap" name="AritYXMap">
                        <area shape="rect" coords="260,2,325,23" href="front/news/siglenews.uhtml?classCode=job" alt="更多" title="更多">
                    </map>
                    
                    <ul>
                       <c:forEach items="${jobNews }" var="jobNew">
                            <li style="color:#FF6600;">
                             <a  title="${jobNew.title}" href="front/news/siglenews.uhtml?classCode=job&newsId=${jobNew.id }" style="">
                                  <font color="${jobNew.titleColor.toString()}">
                                   <myc:strout value="${jobNew.title}" length="25" suffix="..." /> 
                                   </font>
                              </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div id="Box2">
                <!--专题橱窗-->
                <div id="artiZhuanti" style="float: left">
                    <img id="ArtiZT" src="images/ArtiZhuanti.jpg" alt="专题橱窗" usemap="#ArtiZTMap" />
                    <map id="ArtiZTMap" name="ArtiZTMap">
                        <area shape="rect" coords="140,2,225,23" href="" alt="更多" title="更多">
                    </map>

                    <table cellpadding="0" cellspacing="0" border="0" style="padding-left: 2px">
                        <tr>
                            <td style="width: 178px">
                                <div class="zhuanTi">
                                    <a href="http://202.204.208.108/jyw_ss/index.jsp?classcode=717" target="_blank"><img src="images/test1.jpg" alt="深入学习实践科学发展观" width="176" height="39" /></a>
                                </div></td>
                        </tr>
                        <tr>
                            <td class="titl" style="width: 184px"><a href="http://kxfz.people.com.cn/GB/index.html" target="_blank" style="font-size: 10px">就业网</a>
                                <p></p></td>
                        </tr>
                        <tr>
                            <td><div class="zhuanTi">
                                <a href="http://tw.cnu.edu.cn/" target="_blank"><img src="images/test2.jpg" alt="科学发展观学习读本" width="176" height="39" /></a>
                            </div></td>
                        </tr>
                        <tr>
                            <td class="titl"><a href="http://theory.people.com.cn/GB/40557/130316/index.html" target="_blank" style="font-size: 10px">团旗飘飘</a>
                                <p></p></td>
                        </tr>
                        <tr>
                            <td><div class="zhuanTi">
                                <a href="http://202.204.213.10/list1.php?name=%C8%CB%B2%C5%C5%E0%D1%F8&d_id=8&x_id=88" target="_blank"><img src="images/test3.jpg" alt="首都大学生创意文化节" width="176" height="39" /></a>
                            </div></td>
                        </tr>
                        <tr>
                            <td class="titl"><a href="http://www.ccccc.org.cn" target="_blank" style="font-size: 10px">特色实验班</a></td>
                        </tr>
                    </table>
                </div>
                <!--校园文化-->
                <div id="XYWH">
                    <div id="artiXYWH">
                    <img id="artiXYWH1" src="images/ArtiXYWH.jpg" alt="校园文化" />
                    <span id="XYWH_nav1"><a href="front/picture/pictureList.uhtml?preClassCode=xy&columnId=${huoCt.id }" style="font-size: 15px">活动掠影</a>&nbsp;&nbsp;&nbsp;</span>
                    <span id="XYWH_nav2"><a href="front/picture/pictureList.uhtml?preClassCode=xy&columnId=${fengCt.id }" style="font-size: 15px">校园风光</a>&nbsp;&nbsp;&nbsp;</span>

                    <div id="XYWH_box">
                        <table id="XYWH_tab" cellpadding="0" cellspacing="0" style="height: 151px">
                            <tr>
                                <td width="50%" style="border-right:none; border-bottom:none;"><table cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td class="XYWH_img"><img src="images/test11.jpg" alt="活动掠影" /><br />
                                            <a href="front/picture/pictureList.uhtml?preClassCode=xy&columnId=${huoCt.id }">活动掠影</a></td>
                                        <td style="padding-left:4px;padding-top:0px;margin-top: 0px;height: 65px">
                                         
                                         <c:forEach items="${huoFiles }" var="huoFile" varStatus="status">
                                         
                                           &nbsp; &nbsp; <a href="front/picture/pictureList.uhtml?preClassCode=xy&columnId=${huoCt.id }">
                                           <img style="width:70px;height:55px;" src="front/download/lookImage.uhtml?savePath=${huoFile.savePath}" alt="活动掠影" />
                                           </a>
                                            
                                         </c:forEach>
                                        </td>
                                    </tr>
                                </table></td>
                                <td style="border-bottom:none;">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td class="XYWH_img"><img src="images/test12.jpg" alt="校园风光" /><br>
                                            <a href="front/picture/pictureList.uhtml?preClassCode=xy&columnId=${fengCt.id }">校园风光</a></td>
                                            <td  style="padding-left:4px;padding-top:0px;margin-top: 0px;height: 65px">
                                              
                                                <c:forEach items="${fengFiles }" var="fengFile" varStatus="status">
                                                 <a href="front/picture/pictureList.uhtml?preClassCode=xy&columnId=${fengCt.id }">
		                                            <img style="width:70px;height:55px;" src="front/download/lookImage.uhtml?savePath=${fengFile.savePath}" alt="校园风光" />
		                                            </a>
		                                         </c:forEach>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            </div>
            <div id="Box3" style="width: 714px; height: 185px; position: relative;">

                <div id="artiser" style="width: 188px;border-right-width: 0px;height: 171px;">
                    <img id="artiser1" src="images/Aritser.jpg" alt="为您服务" />
                    <div class="serMain" style=" width: 186px; padding-left: 2px; margin: 0px;border-right-width: 0px; padding-right: 0px;
    height: 130px;">
                        <table cellpadding="0" cellspacing="0" border="0" width="188" style="margin-top: 10px;margin-left: 5px">
                            <tr height="50">
                                <td width="107" style="width: 90px"><a href="http://map.baidu.com/?newmap=1&ie=utf-8&daddr=%20&fr=alab0&s=s%26c%3D131%0D%0A%26wd%3D%E5%8C%97%E4%BA%AC" target="_blank"><img src="images/gjcx.jpg" alt="公交查询"  width="90"/></a></td>
                                <td><a href="http://qq.ip138.com/train/" target="_blank"><img src="images/lcsk.jpg" alt="列车时刻" width="90" /></a></td>
                            </tr>
                            <tr>
                                <td><a href="http://qq.ip138.com/day/" target="_blank"><img src="images/wnl.jpg" alt="万年历"  width="90"/></a></td>
                                <td><a href="http://www.cnu.edu.cn/pages/info_details.jsp?seq=2425&boardid=71103&classcode=71103" target="_blank"><img src="images/xl.jpg" alt="校历"  width="90"/></a></td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div id="AritJDD" style="
                    position: absolute;
                    right: 0px;
                    top: 0px;
                ">
                    <img id="AritJDD1" src="images/AritJDD.jpg" alt="多项" usemap="#AritJDDMap" />
                    <map id="AritJDDMap" name="AritJDDMap">
                        <area shape="rect" coords="446,2,511,23" href="http://cpc.people.com.cn/GB/64184/64186/index.html" target="_blank" alt="更多" title="更多">
                    </map>

                    <div class="JDDMain">
                        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="512" height="140">
                            <param name="movie" value="General/images/jddd.swf" />
                            <param name="quality" value="high" />
                            <param name="wmode" value="transparent" />
                            <embed src="images/jddd.swf" quality="high" wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="512" height="140"></embed>
                        </object>
                    </div>
                </div>
            </div>
        </div>
        <!--**********************************E S L***************************************-->
        <div id="area2">
            <!--下载专区-->
            <div id="AritLoad">
                <img id="AritLoad1" src="images/ArtiLoad.jpg" alt="下载专区" usemap="#AritLoad1Map" />
                <map id="AritLoad1Map" name="AritLoad1Map">
                    <area shape="rect" coords="175,2,252,23" href="front/download/more.uhtml?classCode=down" alt="更多" title="更多">
                </map>
  
                <ul>
                 <c:forEach items="${xiaFiles }" var="xiaFile">
                         <li style="color:#FF6600;">
                          <a  title="${xiaFile.originName}" href="front/download/down.uhtml?savePath=${xiaFile.savePath }&originName=${xiaFile.originName}" style="">
                               <font color="${xiaFile.titleColor.toString()}">
                                <myc:strout value="${xiaFile.originName}" length="16" suffix="..." /> 
                                </font>
                           </a>
                         </li>
                    </c:forEach>
                </ul>
            </div>
            <!--回音壁-->
            <div id="artiHYB" style="width: 237px">
                <img id="AritHYB1" src="images/AritHYB.jpg" alt="回音壁" usemap="#AritHYBMap" />
                <map id="AritHYBMap" name="AritHYBMap">
                    <area shape="rect" coords="151,2,224,23" href="front/question/list.uhtml?page=1&classCode=huiyin" alt="更多" title="更多">
                </map>

                <ul>
                <c:forEach items="${questions }" var="question">
                        <li>
                          <a  title="${question.title}" href="front/question/hot.uhtml?questionId=${question.id}&classCode=huiyin" style="">
                                <myc:strout value="${question.title}" length="13" suffix="..." /> 
                           </a>
                           <span class="pRight">
                             <fmt:formatDate value="${question.createTime }" pattern="MM-dd" />
                           </span>
                         </li>
                  </c:forEach>
                </ul>
            </div>

            <!--学习标兵-->
            <div id="AritSS">
                <img id="AritSS1" src="images/AritSS.jpg" alt="师生楷模" usemap="#AritSSMap" />
                <map id="AritSSMap" name="AritSSMap">
                    <area shape="rect" coords="176,2,233,23" href="front/news/siglenews.uhtml?classCode=biao" alt="更多" title="更多">
                </map>
                <div class="TGCont">
                    <table id="SSKM" border="0" cellpadding="0" cellspacing="0">

                        <tr height="79">
                            <td valign="top" width="89">
                                <div>
                                <a href="front/news/siglenews.uhtml?classCode=biao&newsId=${biaoNew.id }">
                                <img src="front/news/lookImage.uhtml?savePath=${biaoPic.savePath}" alt="" width="80" height="110" />
                                </a>
                                </div>
                            </td>
                            <td valign="top" width="132">
                                <h3>>>&nbsp;&nbsp;</h3>
                                <a href="front/news/siglenews.uhtml?classCode=biao&newsId=${biaoNew.id }">
                                <font color="${biaoNew.titleColor.toString()}">
                                <myc:strout value="${biaoNew.title}" length="35" suffix="..." /> 
                                </font>
                                </a>
                            </td>
                        </tr>
                        <tr><td style="height:10px;"></td></tr>
                    </table>
                </div>
            </div>

            <!--荣誉表彰-->
            <div id="AritBM">
                <img id="AritBM1" src="images/AritBM.jpg" alt="部门荣誉" width="237" height="40" />
                <div class="AritBM">
                    <div class="AritBMCont1">
                        <a href="front/news/siglenews.uhtml?classCode=biaozhang&newsId=${biaozhangNew.id }"  >
                        <img src="front/news/lookImage.uhtml?savePath=${biaozhangPic.savePath}" width="190" height="125" style="border:none; position:relative; left:-4px; top:48px;" />
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>