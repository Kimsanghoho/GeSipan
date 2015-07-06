/**
 * 
 */
var Member = {
	nochk : function(url) {
	    alert("동의하지 않으면 가입하실 수 없습니다");
	},
	 agreeChk : function() {
	    var req = document.form.req.checked;
	    var num = 0;
	    if (req == true) {
	        num = 1;
	    }
	    if (num == 1) {
	        document.form.submit();
	    } else {
	        alert("개인정보 약관에 동의하셔야 합니다.");
	    }
	},
	
	searchId : function(url) {
	    window.open(
	                    url,"searchId",
	                    "scrollbars,toolbar=no,location=no,directories=no,status=no,menubar=yes,resizable=yes,width=600,height=500,top=200,left=400");
	 
	},
	 
	searchPass : function(url) {
	    window.open(
	                    url,"searchPass",
	                    "scrollbars,toolbar=no,location=no,directories=no,status=no,menubar=yes,resizable=yes,width=600,height=500,top=200,left=400");
	},
	 join : function() {
	 
	    document.frmJoin.submit();
	},
	 
	login : function() {
	 
	    document.frmLogin.submit();
	}
}