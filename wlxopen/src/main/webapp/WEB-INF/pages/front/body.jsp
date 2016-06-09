<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                        <area shape="rect" coords="616,2,695,23" href="#" alt="更多" title="更多">
                    </map>

                    <div class="NewsMain">
                        <table cellpadding="0" cellspacing="0" border="0" width="695">
                            <tr>
                                <td width="247" align="center">
                                    <div id="fgImg">
                                       <img src="front/news/lookImage.uhtml?savePath=${xuePic.savePath}" alt="" width="240" height="210" />
                                   </div>
                                    <!--新闻链接-->
                                    <a href="#"></a>
                                </td>
                                <td valign="top">
                                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                                    
                                     <c:forEach items="${xueNews}" var="xueNew">
                                        <tr height="22">
                                            <td width="21" align="center"><img src="images/titlK.gif" /></td>
                                            <td>
                                            <a  title="${xueNew.title}">
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
                        <area shape="rect" coords="260,2,318,23" href="#" alt="更多" title="更多">
                    </map>
                    <div class="TGCont">
                        <ul>
                        <c:forEach items="${tongNews }" var="tongNew">
                            <li style="color:#FF6600;">
                             <a  title="${tongNew.title}" href="" style="">
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
                        <area shape="rect" coords="260,2,325,23" href="#" alt="更多" title="更多">
                    </map>
                    
                    <ul>
                       <c:forEach items="${jobNews }" var="jobNew">
                            <li style="color:#FF6600;">
                             <a  title="${jobNew.title}" href="" style="">
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
                    <span id="XYWH_nav1"><a href="#" style="font-size: 15px">活动掠影</a>&nbsp;&nbsp;&nbsp;</span>
                    <span id="XYWH_nav2"><a href="#" style="font-size: 15px">校园风光</a>&nbsp;&nbsp;&nbsp;</span>

                    <div id="XYWH_box">
                        <table id="XYWH_tab" cellpadding="0" cellspacing="0" style="height: 151px">
                            <tr>
                                <td width="50%" style="border-right:none; border-bottom:none;"><table cellpadding="0" cellspacing="0" border="0">
                                    <tr>
                                        <td class="XYWH_img"><img src="images/test11.jpg" alt="活动掠影" /><br />
                                            <a href="#">活动掠影</a></td>
                                        <td style="padding-left:4px; padding-top:4px;">
                                         <c:forEach items="${huoFiles }" var="huoFile">
                                           &nbsp; &nbsp; <a ><img style="width:60px;height:55px;" src="front/download/lookImage.uhtml?savePath=${huoFile.savePath}" alt="活动掠影" /></a>
                                         </c:forEach>
                                        </td>
                                    </tr>
                                </table></td>
                                <td style="border-bottom:none;">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td class="XYWH_img"><img src="images/test12.jpg" alt="校园风光" /><br><a href="#">校园风光</a></td>
                                            <td  style="padding-left:4px; padding-top:4px;">
                                                <c:forEach items="${fengFiles }" var="fengFile">
		                                            &nbsp; &nbsp; <a ><img style="width:60px;height:55px;" src="front/download/lookImage.uhtml?savePath=${fengFile.savePath}" alt="校园风光" /></a>
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
                    <area shape="rect" coords="175,2,252,23" href="#" alt="更多" title="更多">
                </map>

                <ul>
                 <c:forEach items="${xiaFiles }" var="xiaFile">
                         <li style="color:#FF6600;">
                          <a  title="${xiaFile.originName}" href="" style="">
                               <font color="${xiaFile.titleColor.toString()}">
                                <myc:strout value="${xiaFile.originName}" length="15" suffix="..." /> 
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
                    <area shape="rect" coords="151,2,224,23" href="#" alt="更多" title="更多">
                </map>

                <ul>
                <c:forEach items="${questions }" var="question">
                        <li>
                          <a  title="${question.title}" href="" style="">
                                <myc:strout value="${question.title}" length="13" suffix="..." /> 
                           </a>
                           <span class="pRight">
                             <fmt:formatDate value="${question.createTime }" pattern="MM-dd" />
                           </span>
                         </li>
                  </c:forEach>
                </ul>
            </div>

            <!--师生楷模-->
            <div id="AritSS">
                <img id="AritSS1" src="images/AritSS.jpg" alt="师生楷模" usemap="#AritSSMap" />
                <map id="AritSSMap" name="AritSSMap">
                    <area shape="rect" coords="176,2,233,23" href="#" alt="更多" title="更多">
                </map>
                <div class="TGCont">
                    <table id="SSKM" border="0" cellpadding="0" cellspacing="0">

                        <tr height="79">
                            <td valign="top" width="89">
                                <div><a href=""><img src="front/news/lookImage.uhtml?savePath=${biaoPic.savePath}" alt="" width="80" height="110" /></a></div>
                            </td>
                            <td valign="top" width="132">
                                <h3>>>&nbsp;&nbsp;</h3>
                                <p>
                                <font color="${biaoNew.titleColor.toString()}">
                                <myc:strout value="${biaoNew.title}" length="35" suffix="..." /> 
                                </font>
                                </p>
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
                        <a href="" target="_parent" ><img src="front/news/lookImage.uhtml?savePath=${biaozhangPic.savePath}" width="190" height="125" style="border:none; position:relative; left:-4px; top:48px;" /></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div id="footer">
    <div id="linkArea">

        <p><select name="dw" id="dw" onchange="javascript:window.open(this.options[this.selectedIndex].value,'dw','height=400,width=600,resizable=yes,left=200,top=150,toolbar=yes,location=yes,directories = yes,status=yes,menubar=yes,scrollbars=yes')"><option><a href="">教学单位</a></option>
            <option value="http://chinese.cnu.edu.cn/" class="text">文学院</option>
            <option value="http://history.cnu.edu.cn/"  class="text">历史学院</option>
            <option value="http://zf.cnu.edu.cn"  class="text">政法学院</option>
            <option value="http://www.cnu.edu.cn/music"  class="text">音乐学院</option>
            <option value="http://art.cnu.edu.cn"  class="text">美术学院</option>
            <option value="http://www.waiyucnu.com/"  class="text">外国语学院</option>
            <option value="http://jky.cnu.edu.cn"  class="text">教育科学学院</option>
            <option value="http://www.cnu.edu.cn/mathpage"  class="text">数学科学学院</option>
            <option value="http://202.204.213.10"  class="text">物理系</option>
            <option value="http://www.cnu.edu.cn/hxx/index.htm"  class="text">化学系</option>
            <option value="http://202.204.209.200/"  class="text">生命科学学院</option>
            <option value="http://cret.cnu.edu.cn"  class="text">资环学院</option>
            <option value="http://www.ie.cnu.edu.cn"  class="text">信息工程学院</option>
            <option value="http://cmet.cnu.edu.cn/"  class="text">教育技术系</option>
            <option value="http://219.142.63.18"  class="text">初等教育学院</option>
            <option value="http://202.204.210.3/">继续教育学院</option>
            <option value="http://www.ciecnu.cn/"  class="text">国际文化学院</option>
            <option value="http://kede.cnu.edu.cn/" class="text">科德学院</option>
            <option value="http://202.204.212.40/"  class="text">大英教研部</option>
            <option value="http://www.cnu.edu.cn/tyb/index.asp"  class="text">体育教研部</option>
            <option value="http://202.204.208.66/cnustu/twolesson/index.asp"  class="text">马克思主义教育学院</option>
            <option value="http://202.204.214.131/"  class="text">图书馆</option>
            <option value="http://jjy.cnu.edu.cn"  class="text">基础教育发展研究院</option>
            <option value="http://www.cnu.edu.cn/sfs/index.html">中国书法文化研究院</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;
            <select name="bc" id="bc" onchange="javascript:window.open(this.options[this.selectedIndex].value,'bc','height=400,width=600,resizable=yes,left=200,top=150,toolbar=yes,location=yes,directories = yes,status=yes,menubar=yes,scrollbars=yes')"><option><a href="">机关部处</a></option>
                <option value="http://info.cnu.edu.cn/" class="text">学校办公室</option>
                <option value="http://www.cnu.edu.cn/zuzhibu/index.htm" class="text">党委组织部</option>
                <option value="http://202.204.209.121/xcb/" class="text">党委宣传部</option>
                <option value="http://www.cnu.edu.cn/tzb/" class="text">统战部</option>
                <option value="http://202.204.208.76:8083/jjw/jjw/index.jsp" class="text">纪委监察室</option>
                <option value="http://www.cnu.edu.cn/ltxgbc" class="text">离退休干部处</option>
                <option value="http://202.204.208.76/jwc/index.jsp" class="text">教务处</option>
                <option value="http://www.cnu.edu.cn/yjsb/bridge.htm" class="text">研究生部</option>
                <option value="http://202.204.209.122:8099/index.php" class="text">研究生工作部</option>
                <option value="http://202.204.208.104" class="text"> 科技处</option>
                <option value="http://202.204.208.104/s10028sk" class="text"> 社会科学处</option>
                <option value="http://www.cnu.edu.cn/equip" class="text">条件装备处</option>
                <option value="http://rsc.cnu.edu.cn" class="text">人事处</option>
                <option value="http://202.204.209.123:800/cwcmh/index.asp" class="text">财务处</option>
                <option value="http://www.cnu.edu.cn/bwc" class="text"> 保卫处</option>
                <option value="http://www.cnu.edu.cn/jjc" class="text"> 基建处</option>
                <option value="http://www.cnu.edu.cn/fangguan/" class="text"> 房土处</option>
                <option value="http://www.cnu.edu.cn/hqbgs/index.asp" class="text">后勤管理办公室</option>
                <option value="http://www.cnu.edu.cn/xcbbgs/index.htm" class="text">校产办</option>
                <option value="http://www.cnu.edu.cn/studentdept/index.asp" class="text"> 学生处</option>
                <option value="http://tw.cnu.edu.cn" class="text"> 团　委</option>
                <option value="http://www.cnu.edu.cn/gh/index.asp" class="text"> 工　会</option>
                <option value="http://www.cnu.edu.cn/lx/lx-new-campus/xxqjs.htm" class="text">良乡校区</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;
            <select name="qt" id="qt" onchange="javascript:window.open(this.options[this.selectedIndex].value,'qt','height=400,width=600,resizable=yes,left=200,top=150,toolbar=yes,location=yes,directories = yes,status=yes,menubar=yes,scrollbars=yes')"><option><a href="">其他单位</a></option>
                <option value="http://www.cnu.edu.cn/gjs/index.htm"> 高教研究所</option>
                <option value="http://qys.cnu.edu.cn/">青教研究所</option>
                <option value="http://gaoshi.cnu.edu.cn/">高师培训中心</option>
                <option value="http://202.204.208.66/xinxibu/">数字校园中心</option>
                <option value="http://www.art789.com">高等美术中心</option>
                <option value="http://www.cnu.edu.cn/otherunit/logistics.asp">后勤集团</option>
                <option value="http://202.204.217.235">校医院</option>
                <option value="http://www.cnu.edu.cn/xiaobao">首都师大报</option>
                <option value="http://www.cnu.edu.cn/kjfwzx/index.html">科教服务中心</option>
                <option value="http://www.cnuschool.org/xxjj/index.htm">首师大附中</option>
                <option value="http://www.cnuwjschool.org/next/xx-jianjie.htm">附属实验学校</option>
                <option value="http://211.160.73.102/servlet/Node?Node=1">附属育新学校</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;
            <select name="yq" 20id="yq" onchange="javascript:window.open(this.options[this.selectedIndex].value,'yq','height=400,width=600,resizable=yes,left=200,top=150,toolbar=yes,location=yes,directories = yes,status=yes,menubar=yes,scrollbars=yes')"><option><a href="">友情链接</a></option>
                <option value="http://www.jpkcnet.com/new/"> 国家精品课程</option>
                <option value="http://www.core.org.cn/OcwWeb/index.htm">MIT开放式课程</option>
                <option value="http://www.edu.cn/topic_652/20061013/t20061013_200245.shtml">大学基础课程系列报告论坛</option>
                <option value="http://www.core.org.cn">中国开放式教育资源共享协会</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;
            <select name="sz" id="sz"  onchange="javascript:window.open(this.options[this.selectedIndex].value,'sz','height=400,width=600,resizable=yes,left=200,top=150,toolbar=yes,location=yes,directories = yes,status=yes,menubar=yes,scrollbars=yes')"><option><a href="">思政网站</a></option></select></p>
    </div>

    <div id="bottArea">

        <h3 class="bottLink"><a href="">短信平台</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="/index.asp">物理系首页</a>
        &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.jsp" target="_blank">后台管理</a></h3>
        <p>地址:北京市海淀区西三环北路105号&nbsp;邮编：100048<br>
            版权所有：首都师范大学物理系<br>
            <span>当前访问总量：<b>5</b>&nbsp;&nbsp;人次</span></p>
    </div>
</div>

</body>
</html>