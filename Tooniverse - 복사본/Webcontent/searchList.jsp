<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tooniverse.*, java.util.*" %>
<jsp:useBean id="tMgr" class="com.tooniverse.ToonMgr"/>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("UTF-8");
	String searchWord = request.getParameter("searchWord");
	//작품명
	ArrayList<ToonBean> tlist_1 = tMgr.getSearchResult_1(searchWord);
	
	//작가명
	ArrayList<ToonBean> tlist_2 = tMgr.getSearchResult_2(searchWord);
	
	
	
%>
<html>
<head>
<style type="text/css">
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	background-color: #444;
	color: white;
}

header {
	background-color: #444;
	color: white;
}

.wrap {
	width: 1200px;
	margin: 40px auto;
}
.searchInfo{
	background-color: #ddd;
	color: black;
}
.searchBlock{margin: 10px}
p{padding: 5px; border: 5px solid black;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<div class="wrap">
		<div class="searchInfo">
			<p>'<%=searchWord %>' 검색결과</p>
			<p>'<%=searchWord %>' 으로 시작하는 작품명 검색결과</p>
			<div class="searchBlock">
				<ul>
			<%
				for(int i = 0; i < tlist_1.size(); i++){
			%>
					<li><b><a href="read.jsp?toonNum=<%=tlist_1.get(i).getToon_id()%>"><%=tlist_1.get(i).getSeries_name() %></a></b>&ensp;&ensp;<%=tlist_1.get(i).getSeries_title() %> <%=tlist_1.get(i).getToon_date() %></li>
			<%		
				}
			%>
				</ul>
			</div>
			<p>'<%=searchWord %>' 으로 시작하는 작가명 검색결과</p>
			<div class="searchBlock">
				<ul>
			<%
				for(int i = 0; i < tlist_2.size(); i++){
			%>
					<li><b><a href="read.jsp?toonNum=<%=tlist_2.get(i).getToon_id()%>"><%=tlist_2.get(i).getArtist_id() %></a></b>&ensp;&ensp;<%=tlist_2.get(i).getSeries_name() %></a></b>&ensp;&ensp;<%=tlist_2.get(i).getSeries_title() %>    <%=tlist_2.get(i).getToon_date() %></li>
			<%		
				}
			%>
				</ul>
			</div>
		</div>
		
	</div>
</body>
</html>