<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <base href="<%=basePath%>">     
<title>栏目帮助</title>      

<style type="text/css">
.title{
font-style: normal;
font-weight:bold;
text-align: center;
}

</style>
</head>
<body>

<table border="1px">
<thead>
<tr><td colspan="4"><h4>最后一级栏目的分类码命名规则，*代表任意名称，对于不是最后一级的栏目命名可以随意，不要超过15个字符。</h4></td></tr>
<tr><td class="title">栏目名称</td><td class="title">分类码命名</td><td class="title">浏览链接</td><td class="title">管理链接</td></tr>
</thead>
<tbody>

<tr>
<td class="title">新闻类栏目</td><td>*</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>学生组织</td><td>xszz</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>规章制度</td><td>gzzd</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>思想教育</td><td>sxjy</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>资助管理</td><td>zzgl</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>心理咨询</td><td>xlzx</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>就业工作</td><td>jygz</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>国防教育</td><td>gfjy</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>研究生院</td><td>yjsy</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>


<tr>
<td>学工新闻</td><td>xue</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>

<tr>
<td>通知公告</td><td>tong</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>就业实习信息</td><td>job</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>学习标兵</td><td>biao</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td>荣誉表彰</td><td>biaozhang</td>
<td>control/news/list.action?</td><td>control/news/list.action?</td>
</tr>
<tr>
<td class="title">图片类的栏目</td><td>*</td>
<td>control/download/list.action?type=IMAGE&</td><td>control/download/list.action?type=IMAGE&</td>
</tr>
<tr>
<td class="title">校园风光类的栏目</td><td>xy_*</td>
<td>control/download/list.action?type=IMAGE&</td><td>control/download/list.action?type=IMAGE&</td>
</tr>
<tr>
<td>活动剪影</td><td>xy_huo</td>
<td>control/download/list.action?type=IMAGE&</td><td>control/download/list.action?type=IMAGE&</td>
</tr>
<tr>
<td>校园风光</td><td>xy_feng</td>
<td>control/download/list.action?type=IMAGE&</td><td>control/download/list.action?type=IMAGE&</td>
</tr>
<tr>
<td class="title">下载文件类的栏目</td><td>down_*</td> 
<td>control/download/list.action?type=NO_IMAGE&</td><td>control/download/list.action?type=NO_IMAGE&</td>
</tr>
<tr>
<td class="title">回音壁</td><td>*</td>
<td>control/question/list.action?</td><td>control/question/list.action?</td>
</tr>
</tbody>
</table>

<table border="1px">
<thead>
<tr><td colspan="3"><h4>一级栏目的分类码命名规则，*代表任意名称，不要超过15个字符。</h4></td></tr>
<tr><td class="title">栏目名称</td><td class="title">分类码命名</td><td class="title">说明</td></tr>
</thead>
<tbody>

<tr>
<td class="title">系统栏目</td><td>system</td>
<td>不可修改</td>
</tr>
</tbody>
</table>
</body>
</html>