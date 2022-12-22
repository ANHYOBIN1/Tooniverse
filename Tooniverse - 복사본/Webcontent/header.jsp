<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("idKey");
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
		*{
            box-sizing: border-box;
            margin: 0; padding: 0;
        }
        body{}
		header{
            width: 100%;
        }
        .headWrap{width: 100%; display: flex; justify-content: space-evenly;}
        .logo{
            width: 20%;
        }
        .logo a img{width: 40%}
        .header{
            width: 60%;
            text-align: right;
        }
        .gnb{clear: both; width: 100%; margin: 0 auto; border-top: 1px solid white; text-align: center}
        .gnb ul li{display: inline-block; width: 210px; height: 70px; 
        list-style: none; text-align: center; padding-top: 1.4rem; color: white;}
        .gnb ul li a{text-decoration: none;  color: white;}
        .toonSearch{width: 100%; border-top: 1px solid white;border-bottom: 1px solid white; text-align: right; height: 40px; padding-top: 7px}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="headWrap">
		<div class="logo">
			<a href="index.jsp"><img alt="로고오류" src="img/logo.png"></a>
	    </div>
	    <div class="header">
			<jsp:include page="login.jsp"/>
	    </div>
    </div>
	<div class="gnb">
      	<ul>
      		<li><a href="writeToon.jsp">웹툰 그리기</a></li>
      		<li><a href="toonList.jsp">웹툰 보러가기</a></li>
      		<%if(id!=null&&!id.equals("")) {%>
      		<li><a href="myPage.jsp">마이페이지</a></li>
      		<%} %>
      	</ul>
    </div>
    <div class="toonSearch">
    	<jsp:include page="search.jsp"/>
    </div>
</body>
</html>