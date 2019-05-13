function boardWrite() {
	location.href = host + contextPath + "/boardForm?bgno="+$("#bgno").val();
}

function searchSubmit(){
	$('#form1').submit();
}

function searchPageSubmit(page){
	$('#page').val(page);
	$('#form1').submit();
}