function brdDel() {
	$('#form0').attr('action', host+contextPath+'/boardDelete').submit();
};

function brdUpd() {
	$('#form0').attr('action', host+contextPath+'/boardForm').submit();
};

function brdList(){
	$('#form0').attr('action', host+contextPath+'/boardList').submit();
}

// 댓글등록
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
	
	var formData = $("#form1").serialize();
	$.ajax({
		url: "boardReplySaveAjaxJSP",
		type: "post",
		data: formData,
		success: function(result){
			if(result!=""){
				$("#replyList").append(result);
				alert("저장되었습니다.");
				//저장한 후 초기화
				$('#rewriter1').val("");
				$('#rememo1').val("");
			} else{
				alert("서버에 오류가 있어서 저장되지 않았습니다.");
			}
		}
	})
}

// 댓글삭제
function replyDelete(reno){
	if(!confirm("삭제하시겠습니까?")){
		return;
	}
	
	$.ajax({
		url: host+contextPath+'/boardReplyDeleteAjax',
		type: "POST",
		data: {
				"brdno": $('#brdno2').val(),
				"reno": reno
				},
		success : function(result){
			if(result=="OK"){
				$('#replyItem'+reno).remove();
				alert('삭제되었습니다');
			} else{
				alert('댓글이 있어 삭제할 수 없습니다.');
			}
		}
		
	})
	
}

// 수정시 폼을 보여주는 함수
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

// 수정저장
function replyUpdateSave(){
	if ($.trim($('#rememo2').val())=="") {
		alert("글 내용을 입력해주세요.");
		$('#rememo2').focus();
		return;
	}
	$('#form2').attr('action', host+contextPath+'/boardReplySave').submit();
} 

// 수정취소
function replyUpdateCancel(){
	hideDiv("#replyDiv");
	
	$("#reply"+updateReno).text(updateRememo);
	updateReno = updateRememo = null;
} 

// 대댓글폼 append
function replyReply(reno){
	$("#replyDialog").show();
	
	if (updateReno) {
		replyUpdateCancel();
	} 
	
	$("#reparent3").val(reno);
	$("#rememo3").val("");
	$("#replyDialog").appendTo($($("#reply"+reno)));
	$("#rewriter3").focus();
}

// 대댓글취소
function replyReplyCancel(){
	hideDiv("#replyDialog");
} 

// 영역감추기함수
function hideDiv(id){
	$(id).hide();
	$(id).appendTo(document.body);
}

// 대댓글저장
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
	
	var formData = $("#form3").serialize();
	$.ajax({
		url: "boardReplySaveAjaxJSP",
		type:"post", 
		data : formData,
		success: function(result){
			if (result!=="") {
				var parent = $("#reparent3").val();
				$("#replyItem"+parent).after(result);
				$("#replyDialog").hide();
				alert("저장되었습니다.");
				
				//저장한 후 초기화
				$('#rewriter3').val("");
				$('#rememo3').val("");
			} else{
				alert("서버에 오류가 있어서 저장되지 않았습니다.");
			}
		}
	})
}