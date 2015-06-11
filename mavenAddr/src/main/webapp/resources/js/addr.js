function checkForm(form){   }

function selectForm(form){  }

function deleteCheck(form,path){ 
	var url=path+"/deleteOk.do?name="+form.name.value;
	//alert(url);
	
	
	var value=confirm("정말로 삭제하시겠습니까?");
	
	if(value){
		location.href=url;
	}else{
		alert("취소하였습니다.");
	}
}