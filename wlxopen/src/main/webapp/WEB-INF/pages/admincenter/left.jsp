<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>jQuery</title>
	<link rel="stylesheet" href="../lib/jquery.treeview.css" />
	<link rel="stylesheet" href="../red-treeview.css" />
	<link rel="stylesheet" href="../demo/screen.css" />
	<script src="../lib/jquery.js" type="text/javascript"></script>
	<script src="../lib/jquery.cookie.js" type="text/javascript"></script>
	<script src="../lib/jquery.treeview.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$("#browser").treeview();
		});
	</script>

	</head>
<body bgcolor="#CCCCCC">
<div id="main">
<ul id="browser" class="filetree">
<li><img src="../images/folder.gif" /> hello word</span>
		<ul>
		<li onClick='parent.window.set_content();'>this is first HTML！<img src="../images/file.gif" /></li>
	</ul>
	</li>
<li><img src="../images/folder.gif" /> 123</span>
		<ul>
		<li>blabla </li>
		<li>blabla </li>
		<li><img src="../images/folder.gif" /> 123</span>
				<ul>
				<li>blabla </li>
				<li>blabla </li>
				<li>blabla </li>
			</ul>
			</li>
	</ul>
	</li>
<li><img src="../images/folder.gif" />123</span>
		<ul>
		<li><img src="../images/folder.gif" />1234</span>
				<ul id="folder21">
				<li> more text</li>
				<li>and here, too</li>
				<li>123</span></li>
			</ul>
			</li>
	</ul>
	</li>
<li><img src="../images/folder.gif" />123</span>
		<ul>
		<li><img src="../images/folder.gif" />1234</span>
				<ul id="folder21">
				<li> more text</li>
				<li>and here, too</li>
				<li>123</span></li>
			</ul>
			</li>
	</ul>
	</li>
<li><img src="../images/folder.gif" />123</span>
		<ul>
		<li><img src="../images/folder.gif" />1234</span>
				<ul id="folder21">
				<li> more text</li>
				<li>and here, too</li>
				<li>123</span></li>
			</ul>
			</li>
	</ul>
	</li>
<li><img src="../images/folder.gif" />123</span>
		<ul>
		<li><img src="../images/folder.gif" />1234</span>
				<ul id="folder21">
				<li> more text</li>
				<li>and here, too</li>
				<li>123</span></li>
			</ul>
			</li>
	</ul>
	</li>
<li class="closed">this is closed! <img src="../images/folder.gif" /> </li>
</body>
</html>