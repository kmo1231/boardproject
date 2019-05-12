function formSave() {
	if($.trim($('#brdwriter').val())==""){
		alert("작성자를 입력해 주세요");
		$('#brdwriter').focus();
		return;
	} else if($.trim($('#brdtitle').val())==""){
		alert("제목을 입력해 주세요");
		$('#brdtitle').focus();
		return;
	} else if($.trim($('#brdmemo').val())==""){
		alert("내용을 입력해 주세요");
		$('#brdmemo').focus();
		return;
	}
	
	$('#form1').submit();
};