function brdDel() {
	$('#form0').attr('action', host+contextPath+'/boardDelete').submit();
};

function brdUpd() {
	$('#form0').attr('action', host+contextPath+'/boardForm').submit();
};

function brdList(){
	$('#form0').attr('action', host+contextPath+'/boardList').submit();
}

function replySubmit(){
	if ( $.trim($("#rewriter1").val()) == "") {
		alert("작성자를 입력해주세요.");
		$("#rewriter1").focus();
		return;
	}
	if ($.trim($("#rememo1").val()) == "") {
		alert("글 내용을 입력해주세요.");
		$("#rememo1").focus();
		return;
	}
	
	$('#form1').attr('action', host+contextPath+'/boardReplySave').submit();
}

function replyDelete(reno){
	if(!confirm("삭제하시겠습니까?")){
		return;
	}
	
	document.form2.reno.value=reno;
	$('#form2').attr('action', host+contextPath+'/boardReplyDelete').submit();
}

/*수정시 폼을 보여주는 함수*/
var updateReno = updateRememo = null;
function replyUpdate(reno){
	var form = document.form2;
	var reply = document.getElementById("reply"+reno);
	var replyDiv = document.getElementById("replyDiv");
	
	replyDiv.style.display = "";
	
	if (updateReno) {
		document.body.appendChild(replyDiv);
		var oldReno = document.getElementById("reply"+updateReno);
		oldReno.innerText = updateRememo;
	}
	/*수정폼을 append*/
	form.reno.value=reno;
	form.rememo.value = reply.innerText;
	reply.innerText ="";
	reply.appendChild(replyDiv);
	
	/*현재 입력하는 댓글 번호로 현재 입력하던 내용을 취소하고 이전 내용을 화면에 출력하는 데 이용*/
	updateReno   = reno;
	updateRememo = form.rememo.value;
	form.rememo.focus();
}

/*수정저장*/
function replyUpdateSave(){
	if ($.trim($('#rememo2').val())=="") {
		alert("글 내용을 입력해주세요.");
		$('#rememo2').focus();
		return;
	}
	$('#form2').attr('action', host+contextPath+'/boardReplySave').submit();
} 

/*수정취소*/
function replyUpdateCancel(){
	var form = document.form2;
	var replyDiv = document.getElementById("replyDiv");
	document.body.appendChild(replyDiv);
	replyDiv.style.display = "none";
	
	var oldReno = document.getElementById("reply"+updateReno);
	oldReno.innerText = updateRememo;
	updateReno = updateRememo = null;
} 

/*대댓글폼 append*/
function replyReply(reno){
	var form = document.form3;
	var reply = document.getElementById("reply"+reno);
	var replyDia = document.getElementById("replyDialog");
	replyDia.style.display = "";
	
	if (updateReno) {
		replyUpdateCancel();
	} 
	
	form.rememo.value = "";
	form.reparent.value=reno;
	reply.appendChild(replyDia);
	form.rewriter.focus();
}

/*대댓글취소*/
function replyReplyCancel(){
	hideDiv("replyDialog");
} 

function hideDiv(id){
	var div = document.getElementById(id);
	div.style.display = "none";
	document.body.appendChild(div);
}

/*대댓글저장*/
function replyReplySave(){
	if ($.trim($('#rewriter3').val())=="") {
		alert("작성자를 입력해주세요.");
		$('#rewriter3').focus();
		return;
	}
	if ($.trim($('#rememo3').val())=="") {
		alert("글 내용을 입력해주세요.");
		$('#rememo3').focus();
		return;
	}
	
	$('#form3').attr('action', host+contextPath+'/boardReplySave').submit();
}