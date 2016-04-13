
$(function(){
	//在搜索框中按“回车键”开始查询
	 $("#name").keydown(function(event){
		 if(event.keyCode==13) //Enter
		 {
			 topage(1);
		 }
	 });
});
//访问methodName方法，参数为id,比如访问ajaxDelete方法
function _action(methodName,id){
	var form = document.forms[0];
	form.action="admin/control/wordtheme/"+ methodName+".html?id="+id;
	form.submit();
}


/**
 * 屏蔽主题
 * @param id
 */
function ajaxDisable(id) {
	if( confirm("确定要屏蔽吗？")){
		$.get("admin/control/wordtheme/ajaxDisable.html",{id:id},function(content){
			var status = content.status;
			var message = content.message;
			if( status == 1){
				//屏蔽成功，隐藏“已开启”，显示“已屏蔽”
				var disInp=$("#disvisible"+id);
				disInp.show();
				var enInp=$("#envisible"+id);
				enInp.hide();
			}else{
				//删除失败给出提示
				alert(message);
			}
		},"json");
	}
}
/**
 * 开启主题
 * @param id
 */
function ajaxEnable(id) {
	if( confirm("确定要开启吗？")){
		$.get("admin/control/wordtheme/ajaxEnable.html",{id:id},function(content){
			var status = content.status;
			var message = content.message;
			if( status == 1){
				//开启成功，隐藏“已屏蔽”，显示“已开启”
				var disInp=$("#disvisible"+id);
				disInp.hide();
				var enInp=$("#envisible"+id);
				enInp.show();
			}else{
				//删除失败给出提示
				alert(message);
			}
		},"json");
	}
}

/**
 * 删除主题
 * @param id
 */
function ajaxDelete(id) {
	if( confirm("确定要删除吗？")){
		$.get("admin/control/wordtheme/ajaxDelete.html",{id:id},function(content){
			var status = content.status;
			var message = content.message;
			if( status == 1){
				//删除成功，移除记录
				var themeTr=$("#theme"+id);
				themeTr.remove();
			}else{
				//删除失败给出提示
				alert(message);
			}
		},"json");
	}
}
 //分页查询单词主题，page:第几页
function topage(page)
{
	var form = document.forms[0];
	form.page.value= page;
	form.submit();
}
var count =0;
//编辑主题名,id：被编辑主题的id
function editName(id) {
	
	//获取编辑小图标节点
	var editNode = $("#e"+id);
	//主题名称节点
	var spanNode =$("#s"+id);
	
	//判断当前处于编辑状态还是显示状态，判断依据：编辑框的type属性是否为"hidden",如果为hidden则是显示状态，否则为编辑状态。
	//如果是编辑状态则进行显示，如果是显示状态则调成编辑状态。
	
	//编辑框节点
	var inputNode = $("#i"+id);
	var val = inputNode.attr("type");
	if( val=="hidden")//当前为显示状态，进入编辑状态
	{
		//x号按钮
		var xNode = $("#de"+id);
		xNode.show();//x号按钮显示
		
		//去掉“编辑”图标，换成"对号"小图标glyphicon-ok  glyphicon-pencil
		editNode.removeClass("glyphicon-pencil");
		editNode.addClass("glyphicon-ok");
		
		//显示节点隐藏
		spanNode.hide();
		//编辑节点显示
		inputNode.attr("type","text");
		//给编辑框绑定键盘事件，当按"enter"键时去访问后台
		inputNode.bind("keydown",{id:id},keyDownHandler);
		//聚焦
		inputNode.focus();
		
	}else{//当前为编辑状态，进入显示状态
		//去掉“对号”图标，换成"编辑"小图标glyphicon-ok  glyphicon-pencil
		editNode.removeClass("glyphicon-ok");
		editNode.addClass("glyphicon-pencil");
		//访问后台进行数据修改
		ajaxeditName(id);
		//解绑编辑节点绑定的键盘事件
		 $("#i"+id).unbind("keydown");
	}
}
//对键盘按下事件的处理函数
function keyDownHandler(event){
	 if(event.keyCode==13) //处理Enter事件
	 {
		//获取编辑小图标节点
		var editNode = $("#e"+event.data.id);
		 //调用ajax修改函数修改姓名
		 ajaxeditName(event.data.id);
		 //解除键盘绑定事件,因为每次修改都会绑定一个键盘事件，所以当有键盘事件发生时，就会执行多个绑定函数，尽管函数都一样。
		 $("#i"+event.data.id).unbind("keydown");
		 
		//去掉“对号”图标，换成"编辑"小图标glyphicon-ok  glyphicon-pencil
		editNode.removeClass("glyphicon-ok");
		editNode.addClass("glyphicon-pencil");
		//x号按钮
		var xNode = $("#de"+id);
		xNode.hide();//x号按钮隐藏
	 }
}
//放弃编辑名称
function dropEditName(id){
	//获取编辑小图标节点
	var editNode = $("#e"+id);
	//x号按钮
	var xNode = $("#de"+id);
	//编辑框
	var inputNode = $("#i"+id);
	//主题名称的span标签
	var spanNode =$("#s"+id);
	//修改之前的名称
	var beforeName = spanNode.text().trim();
	
	//去掉“对号”图标，换成"编辑"小图标glyphicon-ok  glyphicon-pencil
	editNode.removeClass("glyphicon-ok");
	editNode.addClass("glyphicon-pencil");
	
	inputNode.attr("type","hidden");
	//将编辑内容设为原来内容
	inputNode.val(beforeName);
	spanNode.show();
	
	//x号按钮隐藏
	xNode.hide();
	//解绑编辑节点绑定的键盘事件
	$("#i"+id).unbind("keydown");
}
//调用ajax修改主题名
function ajaxeditName(id) {
	//主题名称的span标签
	var spanNode =$("#s"+id);
	//x号按钮
	var xNode = $("#de"+id);
	
	//修改之前的名称
	var beforeName = spanNode.text().trim();
	//编辑框
	var inputNode = $("#i"+id);
	//修改后的名称
	var afterName = inputNode.val().trim();
	//比较修改后的名称是否满足格式以及是否不和原名称一样，如果满足则修改，否则给出提示并不修改
	if( afterName!="" && afterName!=beforeName){
		//判断是否超过20个字符
		if( afterName.length<=20){
			//修改
			$.get("admin/control/wordtheme/ajaxeditContent.html",{id:id,content:afterName},function(content){
				var status = content.status;
				var message = content.message;
				if( status == 1){//修改成功，将原名称修改为新名称显示
					spanNode.text(afterName);
				}else{//修改失败，将编辑框的名称改回原样，
					inputNode.val(beforeName);
					//给出提示
					alert(message);
				}
				//隐藏编辑框，显示内容
				inputNode.attr("type","hidden");
				spanNode.show();
				xNode.hide();//x号按钮隐藏
			},"json");
		}else{
			alert("主题名称不要超过20个字符!");
		}
	}else{//没改变，隐藏编辑框，显示内容
		inputNode.attr("type","hidden");
		//将编辑内容设为原来内容
		inputNode.val(beforeName);
		spanNode.show();
		xNode.hide();//x号按钮隐藏
	}
}