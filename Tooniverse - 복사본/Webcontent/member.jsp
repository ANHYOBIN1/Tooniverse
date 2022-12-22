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
		<div class="logo"><a><img alt="로고" src="img/logo.png"></a></div>
		<form action="memberProc.jsp" method="post" name="regFrm">
		<fieldset>
			<div class=".wrap">
				<ul>
					<li>
						<label for="inputId">아이디</label><input type="text" id="inputId" name="id" onkeyup="inputIdChk();"><span id="idchk"></span>
					</li>
					<li>
						<label for="pwd">비밀번호</label><input type="password" name="pwd" id="pwd">
					</li>
					<li>
						<label for="repwd">비밀번호확인</label><input type="password" name="re_pwd" id="repwd"><span id="pwchk"></span>
					</li>
					<li>
						<label for="name">이름</label><input type="text" name="name" id="name">
					</li>
					<li>
						<div class="jobchk">
							<label>성별</label>
							<select name="gender" id="gender">
								<option value="0" selected>성별</option>
								<option value="M">남</option>
								<option value="F">여</option>
							</select>
						</div>
					</li>
					<li>
						<label>생년월일</label><input type="text" name="birthday" class="bir" placeholder="ex)951211">&emsp;
					</li>
					<li>
						<label>이메일</label><input type="email" name="email">
					</li>
					<li>
						<label for="postcode">우편번호</label>
						<input name="zipcode" placeholder="우편번호" id="postcode" readonly>&emsp;
						<input type="button" value="우편번호찾기" onclick="findAddr()"> 
					</li>
					<li>
						<label>주소</label>
						<input name="address" placeholder="주소" size="70" class="bigaddress" id="addr">
					</li>
					<li>
						<label>상세주소</label>
						<input name="detailaddress" placeholder="상세주소">
					</li>
					<li>
						<div class="chkin">
							<label>취미</label>
							<input type="checkbox" name="hobby" value="1">인터넷
							<input type="checkbox" name="hobby" value="2">여행
							<input type="checkbox" name="hobby" value="3">게임
							<input type="checkbox" name="hobby" value="4">영화
							<input type="checkbox" name="hobby" value="5">운동
						</div>
					</li>
					<li>
						<div class="jobchk">
							<label>직업</label>
							<select name="job" id="job">
								<option value="0" selected>선택하세요</option>
								<option value="회사원">회사원</option>
								<option value="컴퓨터">컴퓨터</option>
								<option value="공무원">공무원</option>
								<option value="법조인">법조인</option>
								<option value="의사">의사</option>
								<option value="변호사">변호사</option>
								<option value="자영업자">자영업자</option>
							</select>
						</div>
					</li>
				</ul>
							<input type="submit" value="회원가입">&emsp;
							<input type="reset" value="다시쓰기">&emsp;
							<input type="button" value="로그인" onclick="location.href='index.jsp'">
			</div>
			</fieldset>
		</form>
	</div>

<script type="text/javascript">
	$(function(){
		function pwchk(){
            /////////////비밀번호체크
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
              if(rePw == user_pw){$("#pwchk").html("<b style='color:green;'>사용 가능한 비밀번호입니다.</b>")}
              else{$("#pwchk").html("<b style='color:red';>비밀번호가 일치하지 않습니다.</b>");}
            }else{
              $("#pwd").css("outline","3px solid red")
              $("#pwchk").html("<b style='color:red;>8~15자리의 영소문자+숫자+!@#$%</b>");
            }
        }
		
		$("#repwd").on("input",function(){
            pwchk();
        })
        
        $("#pwd").on("input",function(){
            pwchk();
        })
		
        
        //// 여기에 남은거 이메일 정규식 등등, 빈칸있으면 alert로 각각 뭐가 비었는지 뜨게/////////////////
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
					$("#idchk").html("<b style='color:red;'>사용 불가능한 아이디입니다.</b>");
				}else{
					$("#idchk").html("<b style='color:green;'>사용 가능한 아이디입니다.</b>");
				}
				console.log(result);
			},
			error:function(){
				console.log("inputIdChk()오류발생");
			}
		})
	}
	
	
</script>
</body>
</html>