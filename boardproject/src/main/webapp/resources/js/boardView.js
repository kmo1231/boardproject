function brdDel() {
	$('#form1').attr('action', host+contextPath+'/boardDelete').submit();
};

function brdUpd() {
	$('#form1').attr('action', host+contextPath+'/boardForm').submit();
};

function brdList(){
	$('#form1').attr('action', host+contextPath+'/boardList').submit();
}