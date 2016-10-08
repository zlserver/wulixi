/*添加窗口通知js*/
function checkcontent() {
	var title =$("#q_title").val();
	var content =$("#q_content").val();
	var answer = $("#q_answer").val();
	if( !title || title.trim()==""){

		alert("请输入问题");
		return false;
	}
	if( !content || content.trim()==""){

		alert("请输入问题描述");
		return false;
	}
	if( !answer || answer.trim()==""){

		alert("请输入问题答案");
		return false;
	}
	
	return true;
}