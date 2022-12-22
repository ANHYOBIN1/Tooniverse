<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tooniverse.*, java.util.*"%>
<jsp:useBean id="tBean" class="com.tooniverse.ToonBean" />
<jsp:useBean id="tMgr" class="com.tooniverse.ToonMgr" />
<%
request.setCharacterEncoding("UTF-8");
int num = 1;
ArrayList<ToonBean> tlist = tMgr.getAllToonList();
int showToon = 0;
if(tlist.size()>=10){
	showToon = 10;
}else{
	showToon = tlist.size();
}

%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style>
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
.toonIndex {
	display: inline-block;
	margin: 20px
}
.contentP {
	overflow: hidden;
}
a{	
text-decoration: none;
color:tomato
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<div class="wrap">
		<div class="container">
		<h1>따끈따끈한 업데이트 목록</h1>
			<%
			for (int i = 0; i < showToon ; i++) {
				String[] tt = tlist.get(i).getToon_toon().split("ahb6920");
			%>
			<div class="<%=i%>_toon toonIndex"
				style="width: 33%; height: 300px; border: 1px solid red;">
				<a href="read.jsp?num=<%=i + 1%>&toonNum=<%=tlist.get(i).getToon_id()%>"> <img alt="그림"
					src="fileServer/<%=tt[tt.length - 1]%>"
					style="width: 48.9%; height: 200px;  float: left;">
					<%
						if(tt.length>1){
					%>
							<img alt="그림"
					src="fileServer/<%=tt[tt.length - 2]%>"
					style="width: 48.9%; height: 200px; float: right;">
					<%
						}else{
					%>
						<img alt="그림"
						src="fileServer/<%=tt[tt.length - 1]%>"
						style="width: 48.9%; height: 2
						00px;  float: right;">	
					<%		
						}
					%>
				</a>
				<p class="contentP" style="clear: both">
					<a href="searchList.jsp?searchWord=<%=tlist.get(i).getArtist_id()%>"><small><%=tlist.get(i).getArtist_id()%></small></a><br><%=tlist.get(i).getSeries_name()%>
					<%=tlist.get(i).getSeries_title()%></p>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>