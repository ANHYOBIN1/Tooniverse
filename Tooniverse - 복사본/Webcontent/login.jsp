<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("idKey");
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	#fora{
		display: inline-block;
		padding-top: 1%;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(id == null){
			%>
			<form action="loginProc.jsp" method="post" name="loginFrm" id="fora">
						아 이 디
						<input type="text" name="id" maxlength="15" style="width: 120px">
						비밀번호
						<input name="pwd" type="password" maxlength="15" style="width: 120px">&emsp;&ensp;
						<input type="submit" value="로그인">&emsp;
			</form>
			<button onclick="javascript:location.href='member.jsp'">회원가입</button>&emsp;&ensp;
	<%
		}else{
	%>
			<%=id %> 님 반갑습니다!&emsp;&ensp;<button onclick="javascript:location.href='logout.jsp'">로그아웃</button>&emsp;&ensp;
	<%
			if(session.getAttribute("admin")!=null){
				
				if((int)session.getAttribute("admin")==2){
	%>
				<button onclick="location.href='ADMINPAGE.jsp'">관리자페이지로</button>
	<%
				}
			}
	%>
	<%
		}
	%>
</body>
</html>