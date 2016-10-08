
$(document).ready(function(){
	$("#addform").bind("submit",function(event){
		var classCode = $("#classCode").val();
		var result =true;
		$.ajax({  
			type: "POST",  
	        url: "control/column/ajaxCheckCode.action", //orderModifyStatus  
	        data: {"classCode":classCode},
		    dataType:"json",  
	        async:false,  
	        cache:false,
	        success: function(data){ 
	        	var status = data.status;
				var message = data.message;
				if( status != 1){
					//分类码重复，
					alert(message);
					result =false;
				}
	        }
		});
		//取消事件
		if( !result)
			event.preventDefault();
	});
});

	//查询
	function topage(page)
	{
		var form = document.forms[0];
		form.page.value= page;
		form.submit();
	}
	/* 导航 */
	function nagivation(parentId,parentName,doubleParentId,doubleParentName){
		var form = document.getElementById("nagform");
		form.parentId.value=parentId;
		form.parentName.value=parentName;
		form.doubleParentId.value=doubleParentId;
		form.doubleParentName.value=doubleParentName;
		form.submit();
	}
	
	/**
	*visible:true 上线栏目 ；false 下线栏目
	 id:要操作栏目的id
	*/
	function turnColumnState(id,visible) {
		 var hitmsg = "";
		if( visible)
			hitmsg ="确定要上线该栏目？"
		else 
			hitmsg ="确定要下线该栏目？"
		if( confirm(hitmsg)){
			var form = document.forms[0];
			form.action="control/column/turnColumnState.action?column.id="+id+"&column.visible="+visible;
			form.submit();
		}
	}
	
	/* 删除确认 */
	function deleteColumn(id) {
		//检测是否有子类
		var child = $("#child"+id+"1").attr("href");
		
		if( child ){
			alert("请先删除子类!");
		}else{
			if( confirm("确定要删除吗？")){
				var form = document.forms[0];
				form.action="control/column/delete.action?column.id="+id;
				/* var len =form.typeDes.length;
				var i=0;
				for( i=0;i<len ;i++){
					form.typeDes[i].value=null;
				}  */
				form.submit();
			}
		}
		
	}
	
	
	/*
	确认编辑，和后台交互
	*/
	function edit(id){
		//编辑按钮
		var edita = $("#edit"+id);
		//确定按钮
		var okspan = $("#ok"+id);
		//取消按钮
		var cancelspan = $("#cancel"+id);
		//获取所有编辑框和显示span
		//栏目名称
		var namespan = $("#name"+id);
		var nameinput=$("#iname"+id);
		//分类码
		var classCodespan = $("#classCode"+id);
		var classCodeinput=$("#iclassCode"+id);
		//分类说明
		var typeDesspan = $("#typeDes"+id);
		var typeDesselect=$("#itypeDes"+id);
		//浏览程序
		var readUrlspan = $("#readUrl"+id);
		var readUrlinput=$("#ireadUrl"+id);
		//管理程序
		var manageUrlspan = $("#manageUrl"+id);
		var manageUrlinput=$("#imanageUrl"+id);
		//组别
		var groupTypespan = $("#groupType"+id);
		var groupTypeinput=$("#igroupType"+id);
		//顺序
		var sequencespan = $("#sequence"+id);
		var sequenceinput=$("#isequence"+id);
		
		var name = nameinput.val();
		var classCode = classCodeinput.val();
		var typeDes = typeDesselect.val();
		var readUrl =readUrlinput.val();
		var manageUrl =manageUrlinput.val();
		var groupType=groupTypeinput.val();
		var sequence=sequenceinput.val();
		
		var parentId = $("#parentId").val();
		$.post("control/column/ajaxEdit.action",{"parentId":parentId,"column.id":id,"column.name":name,"column.classCode":classCode,"typeDes":typeDes,"column.readUrl":readUrl,"column.manageUrl":manageUrl,"column.groupType":groupType,"column.sequence":sequence},
		  function(data){
			var status = data.status;
			var message = data.message;
			if( status == 1){
				/*修改成功，将显示内容改为已修改的*/
				conveterValue(id,true);
			}else{
				//修改失败，将编辑框的名称改回原样
				alert(message);
				conveterValue(id,false);
			}
			//显示内容，隐藏编辑框			
			editState(false, id);
			
		},"json");
	}
	
	/**
	state:true   显示内容替换为编辑内容
	state:false  将编辑内容替换为显示内容
	*/
	function conveterValue(id, state){
		//获取所有编辑框和显示span
		//栏目名称
		var namespan = $("#name"+id+" a:only-child");
		var nameinput=$("#iname"+id);
		//分类码
		var classCodespan = $("#classCode"+id);
		var classCodeinput=$("#iclassCode"+id);
		//分类说明
		var typeDesspan = $("#typeDes"+id);
		var typeDesselect=$("#itypeDes"+id);
		//浏览程序
		var readUrlspan = $("#readUrl"+id);
		var readUrlinput=$("#ireadUrl"+id);
		//管理程序
		var manageUrlspan = $("#manageUrl"+id);
		var manageUrlinput=$("#imanageUrl"+id);
		//组别
		var groupTypespan = $("#groupType"+id);
		var groupTypeinput=$("#igroupType"+id);
		//顺序
		var sequencespan = $("#sequence"+id);
		var sequenceinput=$("#isequence"+id);

		var yname = namespan.text();
		var yclassCode = classCodespan.text();
		var ytypeDes = typeDesspan.text();
		var yreadUrl =readUrlspan.text();
		var ymanageUrl =manageUrlspan.text();
		var ygroupType=groupTypespan.text();
		var ysequence=sequencespan.text();
		
		var name = nameinput.val();
		var classCode = classCodeinput.val();
		var typeDes = typeDesselect.val();
		var readUrl =readUrlinput.val();
		var manageUrl =manageUrlinput.val();
		var groupType=groupTypeinput.val();
		var sequence=sequenceinput.val();
		//显示内容替换为编辑内容
		if( state){
			namespan.text(name);
			classCodespan.text(classCode);
			
			switch(parseInt(typeDes)){//列表类\介绍类\系统类\其它类
			case 0:
				typeDesspan.text("列表类");
				break;
			case 1:
				typeDesspan.text("介绍类");
				break;
			case 2:
				typeDesspan.text("系统类");
				break;
			case 3:
				typeDesspan.text("其它类");
				break;
			}
			readUrlspan.text(readUrl);
			manageUrlspan.text(manageUrl);
			groupTypespan.text(groupType);
			sequencespan.text(sequence);
		}else{//将编辑内容替换为显示内容
			nameinput.val(yname);
			classCodeinput.val(yclassCode);
			var selectedValue;
			var typeDes =ytypeDes.trim();
			if( typeDes=="列表类")
				selectedValue=0;
			if( typeDes=="介绍类")
				selectedValue=1;
			if( typeDes=="系统类")
				selectedValue=2;
			if( typeDes=="其它类")
				selectedValue=3;
			
			typeDesselect.val(selectedValue);
			readUrlinput.val(yreadUrl);
			manageUrlinput.val(ymanageUrl);
			groupTypeinput.val(ygroupType);
			sequenceinput.val(ysequence);
		}
		
	}
	/*
	放弃编辑
	*/
	function dropEdit(id) {
		conveterValue(id,false);
		//隐藏编辑框
		editState(false,id);
	}
	
	/*
	显示编辑框
	 点击编辑按钮时显示编辑框同时显示“确认”、“取消按钮”
	*/
	function displayEditButton(id) {
		//显示编辑框
		editState(true,id);
	}
	/*
	 编辑框状态
	 state为true显示编辑框隐藏内容
	 state为false隐藏编辑框显示内容
	*/
	function editState(state,id) {
		//编辑按钮
		var edita = $("#edit"+id);
		//确定按钮
		var okspan = $("#ok"+id);
		//取消按钮
		var cancelspan = $("#cancel"+id);
		//获取所有编辑框和显示span
		//栏目名称
		var namespan = $("#name"+id);
		var nameinput=$("#iname"+id);
		//分类码
		var classCodespan = $("#classCode"+id);
		var classCodeinput=$("#iclassCode"+id);
		//分类说明
		var typeDesspan = $("#typeDes"+id);
		var typeDesselect=$("#itypeDes"+id);
		//浏览程序
		var readUrlspan = $("#readUrl"+id);
		var readUrlinput=$("#ireadUrl"+id);
		//管理程序
		var manageUrlspan = $("#manageUrl"+id);
		var manageUrlinput=$("#imanageUrl"+id);
		//组别
		var groupTypespan = $("#groupType"+id);
		var groupTypeinput=$("#igroupType"+id);
		//顺序
		var sequencespan = $("#sequence"+id);
		var sequenceinput=$("#isequence"+id);
		//显示编辑框
		if(state){
			edita.hide();
			okspan.show();
			cancelspan.show();
			namespan.hide();
			nameinput.attr("type","text");
			classCodespan.hide();
			classCodeinput.attr("type","text");
			typeDesspan.hide();
			typeDesselect.show();
			readUrlspan.hide();
			readUrlinput.attr("type","text");
			manageUrlspan.hide();
			manageUrlinput.attr("type","text");
			groupTypespan.hide();
			groupTypeinput.attr("type","text");
			sequencespan.hide();
			sequenceinput.attr("type","text");
		}else{//隐藏编辑框
			edita.show();
			okspan.hide();
			cancelspan.hide();
			namespan.show();
			nameinput.attr("type","hidden");
			classCodespan.show();
			classCodeinput.attr("type","hidden");
			typeDesspan.show();
			typeDesselect.hide();
			readUrlspan.show();
			readUrlinput.attr("type","hidden");
			manageUrlspan.show();
			manageUrlinput.attr("type","hidden");
			groupTypespan.show();
			groupTypeinput.attr("type","hidden");
			sequencespan.show();
			sequenceinput.attr("type","hidden");
		}
	}
	/**
	添加子栏目进行校验
	**/
	function checkAdd(){
		var name = $("#name").val();
		var classCode = $("#classCode").val;
		if( name.trim()==""||classCode.trim()==""){
			alert("请填写栏目名称和分类码");
			return false;
		}
		alert(classCode.trim().length);
		if( classCode.trim().length>15){
			alert("分类码长度不能大于15");
			return false;
		}
		return true;
	}