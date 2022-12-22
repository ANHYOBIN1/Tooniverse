<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="com.tooniverse.memberMgr"/>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String msg = "로그인에 실패하였습니다.";
	String bb = "";
	
	boolean result = memberMgr.loginId(id, pwd);
	if(result){
		session.setAttribute("idKey", id);
		msg = "로그인하였습니다.";
		bb = (String)session.getAttribute("idKey");
		int isAdmin = memberMgr.isAdmin(id);
		System.out.println(isAdmin);
		if(isAdmin >= 1){
			session.setAttribute("admin", isAdmin);
		}else{
			session.setAttribute("admin", 0);
		}
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	
<script type="text/javascript">
	bb = "<%=bb%>";
	console.log("<%=bb %>");	
	alert("<%=msg %>");
	if(bb == 'ADMIN'){
		location.href = "ADMINPAGE.jsp"
	}else {
		location.href = "index.jsp";
	}
		
</script>
</head>
<body>
	
</body>
</html>