function boardWrite() {
	location.href = host + contextPath + "/boardForm";
}

function searchSubmit(){
	$('#form1').submit();
}

function searchPageSubmit(page){
	$('#page').value=page;
	$('#form1').submit();
}


