<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String id = (String)session.getAttribute("idKey");
	if(id==null||id.equals("null")){
%>
	<script type="text/javascript">
		alert("ERR : CANNOT ACCESS HERE")
		location.href="index.jsp";
	</script>
<%
	}else{
		if(session.getAttribute("admin")!=null){
			
			if((int)session.getAttribute("admin")<2){
		%>
			<script type="text/javascript">
				alert("ERR : CANNOT ACCESS HERE")
				location.href="index.jsp";
			</script>
		<%
			}
		}
	}
%>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>관리자페이지입니다</h1>
	<a href="index.jsp">홈으로 가기</a>
	
	<form action="registAdmin.do" method="post">
		<h3>관리자 목록 추가</h3>
		<select id="noAdminMemList" name="registAdmin">
		</select>
		<input type="submit" value="관리자 권한 추가">
	</form>
	<form action="deleteAdmin.do" method="post">
		<h3>관리자 목록 제거</h3>
		<select id="adminMemList" name="deleteAdmin">
		</select>
		<input type="submit" value="관리자 권한 제거">
	</form>
</body>
<script type="text/javascript">
	
	$(function(){
		getNoAdminList();
		getAdminList();
	})
	
	function getAdminList(){
		$.ajax({
			url : "AdminChk.ad",
			type : "post",
			data : {
				want : "yes"
			},
			success : function(result){
				let value = "";
				for(let i = 0; i < result.length; i++){
					value += "<option value='"+result[i].mem_id+"'>"+result[i].mem_id+"</option>";
				}
				$("#adminMemList").html(value)
			},
			error : function(){
				alert("getAdminList() ajax error")
			}
		})
	}
	function getNoAdminList(){
		$.ajax({
			url : "AdminChk.ad",
			type : "post",
			data : {
				want : "no"
			},
			success : function(result){
				let value = "";
				for(let i = 0; i < result.length; i++){
					value += "<option value='"+result[i].id+"'>"+result[i].id+"</option>";
				}
				$("#noAdminMemList").html(value)
			},
			error : function(){
				alert("getAdminList() ajax error")
			}
		})
	}
</script>
</html>