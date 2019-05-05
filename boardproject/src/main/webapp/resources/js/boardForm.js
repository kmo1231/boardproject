function formSave() {
	var form1 = document.form1;
	
	if(form1.brdwriter.value.trim()==""){
		alert("작성자를 입력해 주세요");
		form1.brdwriter.focus();
		return;
	} else if(form1.brdtitle.value.trim()==""){
		alert("제목을 입력해 주세요");
		form1.brdtitle.focus();
		return;
	} else if(form1.brdmemo.value.trim()==""){
		alert("내용을 입력해 주세요");
		form1.brdmemo.focus();
		return;
	}
	
	form1.submit();
};