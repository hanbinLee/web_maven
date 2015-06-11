function registerForm(form){
	//JavaScript
	/*var check=false;
	var str="";
	
	for(var i=0;i<form.interestValue.length;i++){
		if(form.interestValue[i].checked==true){
			str+=form.interestValue[i].value+",";
			check=true;
		}
	}	
	//alert(str);
	if(check==false){
		alert("하나라도 체크하세요");
		form.interestValue[0].focus();
		return false;
	}
	form.interest.value=str;*/
	
	//jQuery
	if($("input[name='id']").val()==""){
		alert("아이디를 입력하세요");
		$("input[name='id']").focus();
		return false;
	}
	
	if($("input[name='password']").val()==""){
		alert("비밀번호를 입력하세요");
		$("input[name='password']").focus();
		return false;
	}
	
	var count=$("input[name='mailing']:checked").length;
	if(count==0){
		alert("메일 수신여부를 선택하세요");
		return false;
	}
	
	var str="";
	$("input[name='interestValue']:checked").each(function(){
		str+=$(this).val() + ",";
	});
	if(str==""){
		alert("반드시 하나는 체크해야 합니다.");
		$("input[name='interestValue']").focus();
		return false;
	}
	alert(str);
	
	$("input[name='interest']").attr("value", str);
}

function idCheck(form, root){
	//JavaScript
	/*alert(form.id.value + "," + root);
	if(form.id.value==""){
		alert("아이디를 반드시 입력하세요.");
		form.id.focus();
		return false();
	}*/
	
	if($("input[name='id']").val()==""){
		alert("아이디 입력하세요");
		$("input[name='id']").focus();
		return false;
	}
	var url=root+"/member/idCheck.do?id="+ form.id.value;
	window.open(url, "", "width=250, height=150");
}

function zipSearch(form, root){
	//alert(form.zipcode.value + "," + root);
	
	/*if(form.zipcode.value==""){
		alert("우편번호를 입력하세요");
		form.zipcode.focus();
		return false();
	}*/
	
	var url=root+"/member/zipcode.do";
	window.open(url, "", "width=400, height=400, scrollbars=yes");

}


function sendAddress(zipcode, address){
	
	//alert(zipcode + "\n" + address);
	
	/*opener.memberForm.zipcode.value=zipcode;
	opener.memberForm.address.value=address;*/
	
	$(opener.document).find("input[name='zipcode']").val(zipcode);	//$(opener.document)=register.jsp
	$(opener.document).find("input[name='address']").val(address);
	self.close();
}









