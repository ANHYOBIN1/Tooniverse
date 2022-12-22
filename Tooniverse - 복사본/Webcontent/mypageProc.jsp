<%@page import="com.tooniverse.memberMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="mMgr" class="com.tooniverse.memberMgr"/>
<jsp:useBean id="soaka" class="com.tooniverse.MemberBean"/>
<jsp:setProperty name="soaka" property="*" />
<%	
	String id = soaka.getId();
	boolean result = mMgr.updateMember(soaka);
	System.out.println("아이디 제대로 들어왔나 : " + id);
	
	
	String msg = "회원정보 수정에 실패하였습니다.";
	String url = "member.jsp";
	if(result){
		msg = "회원정보 수정이 완료되었습니다.";
		url = "index.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert("<%=msg %>")
	location.href = '<%=url %>';
</script>
</head>
<body>
</body>
</html>