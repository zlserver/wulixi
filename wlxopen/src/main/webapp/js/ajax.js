/*------------------AJAX document------------------
* author: Leo Chen
* Date: 09-8-26
* -----------------------------------------------*/
function fNext(id, ty) {
	var url = "General/include/ajaxProgram.asp"; 
	url = url + "?id=" + id;
	url = url + "&ty=" + ty;
	url = url + "&sid=" + Math.random(); //alert(url);
	
	if (window.XMLHttpRequest) {
		// 非IE浏览器
		req = new XMLHttpRequest();
		req.onreadystatechange = processStateChange;
            
		try {
			req.open("GET", url, true);
		} catch (e) {
			alert("Server Communication Problem "+e);
		} 
		req.send(null);
	} else if (window.ActiveXObject) {
		// IE
		req = new ActiveXObject("Microsoft.XMLHTTP");
		
		if (req) {
			req.onreadystatechange=processStateChange;
			req.open("GET", url, true);
			req.send();
		}
	}
}

function processStateChange() {
	if (req.readyState == 1) {
		document.getElementById('D1').innerHTML = "<li><img style='margin:70px 0px 0px 166px;' src=/General/images/icon_buffer.gif /></li>"
	}
	
	if (req.readyState == 4) { // 完成
		if (req.status == 200) { // 响应正常
			sText = req.responseText; //返回值
			document.getElementById('D1').innerHTML = sText;
		} else {
			alert("Problem with server response:  "+ req.statusText);
		}
	}
}