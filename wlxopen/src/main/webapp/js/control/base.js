	//分页查询
	function topage(page){
   		var form = document.forms[0];
		form.page.value= page;
		form.submit();
	}
	
	/* 全选 */
	function selectAll(checkNode){
		var checkeds=document.getElementsByName("checkeds");
		var state=checkNode.checked;
		for( var i = 0;i <checkeds.length;i++)
		  checkeds[i].checked=state;
	}
	/**
	 * 查询，第一页
	 */
	function query() {
		var form = document.forms[0];
		form.page.value=1;
		form.submit();
	}

	/**
	 * 对模块的操作
	 * @param model 要操作的模块
	 * @param method 模块操作行为
	 * @returns {Boolean} 
	 */
	function _action(model,method) {
		//如果未选中则不操作
		var checkeds=document.getElementsByName("checkeds");
		var flage = false;
		for( var i = 0;i <checkeds.length;i++)
			if(checkeds[i].checked ){
				flage = true;
				break;
			}
		if( flage){
			if( method=="delete")
			{
				if( !confirm("确定删除"))
				{
					return false;
				}
			}
			var form = document.forms[0];
			form.action="control/"+model+"/"+method+".action";
			          
			form.submit();
	    }
	}
	
	 /* 提交操作 */
	function submit_action(choice) {
		
    	var state = $("#state");
    	
		if(choice=="save")
			state.val("WAITING");
		if(choice=="publish")
			state.val("PUBLISH");
		
		return true;
	}