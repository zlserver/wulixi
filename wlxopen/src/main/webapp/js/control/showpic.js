/*放大显示图片
 * 当鼠标移动到图片上时，进行图片放大显示
 */

$(document).ready(function() {
	
	//获取img标签  
    var imgs = $(".hitimg");
	var winimg = $("#winimg").width("350px").height("300px");
	//设置div的样式  
    var windiv = $("#windiv").css("border"," 1px solid #F9F9F9")  
    .width("350px").height("300px").css("position","absolute").css("z-index","99")  
    .css("background","white");  
  //隐藏  
    windiv.hide(); 
  //注册鼠标移动上上面事件  
    imgs.mouseover(function(event) {  
    	windiv.show();  
    	var imgNode = $(this); 
    	var strattr= imgNode.attr("src");
    	winimg.attr("src",strattr);
        //出现在鼠标右下方  
        //解决不同浏览器创建事件对象的差异  
        var myEvent = event || window.event;  
        windiv.css("left",myEvent.clientX+15+"px").css("top",myEvent.clientY+5+"px");  
    });  
    //注册鼠标离开时事件  
    imgs.mouseout(function() {  
    	windiv.hide();  
    });  
	
});