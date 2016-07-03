<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" target="_blank">   
<title>底部</title> 
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

        <h3 class="bottLink"><a href="javascript:void()">短信平台</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="">物理系首页</a>
        &nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="login.jsp" >后台管理</a></h3>
        <p>地址:北京市海淀区西三环北路105号&nbsp;邮编：100048<br>
            版权所有：首都师范大学物理系<br>
            <span>当前访问总量：<b>5</b>&nbsp;&nbsp;人次</span></p>
    </div>
</div>
</body>
</html>