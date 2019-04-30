function brdDel() {
	$('#form1').attr('action', host+contextPath+'/boardDelete').submit();
};

function brdUpd() {
	$('#form1').attr('action', host+contextPath+'/boardUpdate').submit();
};