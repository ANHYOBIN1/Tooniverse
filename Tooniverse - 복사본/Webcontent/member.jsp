<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{background-color: #444; color: white;}
	.container{margin: 0 auto; width: 1400px}
	form{width: 1400px; margin: 0 auto; text-align: center;}
	li{list-style:none; width: 700px; margin: 0 auto; margin-bottom: 30px;}
	label{display: block; text-align: left; width: 700px; font-size: 30px; margin-bottom: 10px}
	table{margin: 0 auto}
	tr, td{padding: 30px 5px;}
	.logo{text-align: center;}
	.wrap{width: 700px; padding: 0; margin: 0 auto;}
	ul li input{border: 0; outline: 0.6px solid #999; height: 40px; width: 700px}
	input[type="checkbox"]{
		
	}
	.chkin{width: 700px; height: 80px}
	.chkin input{width: 30px; height: 30px; margin-left: 10px}
	.jobchk{width: 700px; height: 80px}
	.jobchk select{width: 700px; height: 40px}
	ul{padding: 0}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function findAddr(){
	    new daum.Postcode({
	    	
	        oncomplete: function(data) {
	        	
	        	
				document.getElementById('postcode').value = data.zonecode;
				/* document.getElementById('addr').value = data.roadAddress; */
				/* document.getElementById('addr').value = data.jibunAddress; */
	        	roadAddr = data.roadAddress;
				jibunAddr = data.jibunAddress;
				extraAddr = '';
				
				if(roadAddr != ''){
					
					if(data.bname != ''){
						extraAddr += data.bname;
					}
					if(data.buildingName != ''){
						extraAddr += (extraAddr != '') ? ', ' + data.buildingName : data.buildingName;
					}
					roadAddr += (extraAddr != '') ? '(' + extraAddr + ')' : '';
					document.getElementById('addr').value = roadAddr;
					
				}else if(jibunAddr != ''){
					document.getElementById('addr').value = jibunAddress;
				}
				regFrm.detailaddress.focus();
				
				
	        }
	    }).open();
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="logo"><a><img alt="??????" src="img/logo.png"></a></div>
		<form action="memberProc.jsp" method="post" name="regFrm">
		<fieldset>
			<div class=".wrap">
				<ul>
					<li>
						<label for="inputId">?????????</label><input type="text" id="inputId" name="id" onkeyup="inputIdChk();"><span id="idchk"></span>
					</li>
					<li>
						<label for="pwd">????????????</label><input type="password" name="pwd" id="pwd">
					</li>
					<li>
						<label for="repwd">??????????????????</label><input type="password" name="re_pwd" id="repwd"><span id="pwchk"></span>
					</li>
					<li>
						<label for="name">??????</label><input type="text" name="name" id="name">
					</li>
					<li>
						<div class="jobchk">
							<label>??????</label>
							<select name="gender" id="gender">
								<option value="0" selected>??????</option>
								<option value="M">???</option>
								<option value="F">???</option>
							</select>
						</div>
					</li>
					<li>
						<label>????????????</label><input type="text" name="birthday" class="bir" placeholder="ex)951211">&emsp;
					</li>
					<li>
						<label>?????????</label><input type="email" name="email">
					</li>
					<li>
						<label for="postcode">????????????</label>
						<input name="zipcode" placeholder="????????????" id="postcode" readonly>&emsp;
						<input type="button" value="??????????????????" onclick="findAddr()"> 
					</li>
					<li>
						<label>??????</label>
						<input name="address" placeholder="??????" size="70" class="bigaddress" id="addr">
					</li>
					<li>
						<label>????????????</label>
						<input name="detailaddress" placeholder="????????????">
					</li>
					<li>
						<div class="chkin">
							<label>??????</label>
							<input type="checkbox" name="hobby" value="1">?????????
							<input type="checkbox" name="hobby" value="2">??????
							<input type="checkbox" name="hobby" value="3">??????
							<input type="checkbox" name="hobby" value="4">??????
							<input type="checkbox" name="hobby" value="5">??????
						</div>
					</li>
					<li>
						<div class="jobchk">
							<label>??????</label>
							<select name="job" id="job">
								<option value="0" selected>???????????????</option>
								<option value="?????????">?????????</option>
								<option value="?????????">?????????</option>
								<option value="?????????">?????????</option>
								<option value="?????????">?????????</option>
								<option value="??????">??????</option>
								<option value="?????????">?????????</option>
								<option value="????????????">????????????</option>
							</select>
						</div>
					</li>
				</ul>
							<input type="submit" value="????????????">&emsp;
							<input type="reset" value="????????????">&emsp;
							<input type="button" value="?????????" onclick="location.href='index.jsp'">
			</div>
			</fieldset>
		</form>
	</div>

<script type="text/javascript">
	$(function(){
		function pwchk(){
            /////////////??????????????????
            const regExp_userPw = new RegExp("^[a-zA-Z0-9!@#$%]{8,15}$")
            let user_pw = document.getElementById("pwd").value
            let rePw = document.getElementById("repwd").value
            if(user_pw == rePw){
              $("#repwd").css("outline","3px solid blue")
            }else{
              $("#repwd").css("outline","3px solid red")
            }
            
            if(!user_pw){
              $("#pwd").css("outline","3px solid black")
            }else if(regExp_userPw.test(user_pw)){
              $("#pwd").css("outline","3px solid blue")
              if(rePw == user_pw){$("#pwchk").html("<b style='color:green;'>?????? ????????? ?????????????????????.</b>")}
              else{$("#pwchk").html("<b style='color:red';>??????????????? ???????????? ????????????.</b>");}
            }else{
              $("#pwd").css("outline","3px solid red")
              $("#pwchk").html("<b style='color:red;>8~15????????? ????????????+??????+!@#$%</b>");
            }
        }
		
		$("#repwd").on("input",function(){
            pwchk();
        })
        
        $("#pwd").on("input",function(){
            pwchk();
        })
		
        
        //// ????????? ????????? ????????? ????????? ??????, ??????????????? alert??? ?????? ?????? ???????????? ??????/////////////////
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //////////////////////////////////////////////////////////////////////////////
        
        
        
	})
	
	function inputIdChk(){
		
		$.ajax({
			url:"memberDAO.do",
			data:{
				id:$("#inputId").val()
			},
			type:"post",
			success:function(result){
				if(result == "idyesyes"){
					$("#idchk").html("<b style='color:red;'>?????? ???????????? ??????????????????.</b>");
				}else{
					$("#idchk").html("<b style='color:green;'>?????? ????????? ??????????????????.</b>");
				}
				console.log(result);
			},
			error:function(){
				console.log("inputIdChk()????????????");
			}
		})
	}
	
	
</script>
</body>
</html>