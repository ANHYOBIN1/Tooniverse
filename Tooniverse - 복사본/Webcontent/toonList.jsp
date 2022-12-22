<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tooniverse.*, java.util.*" %>
<!DOCTYPE html>
<jsp:useBean id="tMgr" class="com.tooniverse.ToonMgr"/>
<%
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<link rel="icon" src="" alt="에러">
<style type="text/css">
	body{background-color: #444; color: white;}
	.container{width: 1200px; margin: 0 auto; background-color: #eee; color: #000}
	.toon{width: 900px; border: 3px solid #111; height: 250px; margin: 0 auto; border-radius: 20px; margin-bottom: 20px; }
	.Lside{width: 25%; float: left}
	.Rside{width: 74.5%; float: right; padding: 10px}
	.Lside img{width: 240px; height: 240px; padding: 10px}
	.Rside_1{padding: 20px; padding-left: 0px}
	.Rside_2{padding: 20px; padding-left: 0px}
	#loading{position: fixed; top: calc(50% - 250); left: calc(50% - 250px); visibility: hidden;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
<img alt="로딩" src="img/loading.gif" id="loading">
<input type="hidden" value="0" id="tryMore">
<%
	ArrayList<ToonBean> tlist = tMgr.getAllToonList();
	int endNum = 10;
	for(int i = 0; i < endNum; i++){
		String[] tt = tlist.get(i).getToon_toon().split("ahb6920");
%>		
	<div class="toon">
		<div class="Lside"><a href="read.jsp?num=<%=i+1 %>&toonNum=<%=tlist.get(i).getToon_id()%>"><img alt="만화표지" src="fileServer/<%=tt[tt.length-1] %>"></a></div>
		<div class="Rside">
			<div class="Rside_1">
			<span style="font-size: 25px; "><%=tlist.get(i).getSeries_name()%> <%=tlist.get(i).getSeries_title() %></span><br>
			<%=tlist.get(i).getArtist_id() %> 작가 / <%=tlist.get(i).getToon_date() %> / 추천 수 : <%=tlist.get(i).getToon_like() %>
			</div>
			<div class="Rside_2">작가의말 : <%=tlist.get(i).getToon_content() %></div>
		</div>
	</div>
<%
	}
%>
</div>
</body>
<script type="text/javascript">
	var FirstEndNum = <%=endNum%>-5;
	
	
	$(function(){
		$(window).scroll(function(){
			var maxSize = <%=tlist.size()%>
		    if($(window).scrollTop()+0>=$(document).height() - $(window).height()&&FirstEndNum<=100){
		    	if(FirstEndNum > maxSize){
		    		alert("가장 밑바닥입니다");
		    		return;
		    	}
		    	loading();
		    	setTimeout(() => morePage(), 1500);
		    	setTimeout(() => loading2(), 1500);
		    	FirstEndNum += 5;
		    }
		})
	})
	
	
	function morePage(){
		$.ajax({
			url:"paging.do",
			data:{
				Num1:FirstEndNum,
			},
			type:"post",
			success:function(result){
				//ArrayList로 담아온 ReplyBean이 지금 result에 담겨있음 
				let value = "";
				//console.log(result)
				for(let t = 0; t < result.length; t++){
					var toon_toon = result[t].toon_toon.split('ahb6920')
					value += "<div class='toon'>"+"<div class='Lside'>"+"<a href='read.jsp?num=" + (FirstEndNum+t+1) +"&toonNum="+result[t].toon_id+"'>"
							+"<img alt='만화표지' src='fileServer/"+ toon_toon[toon_toon.length-1] +"'></a></div>"
							+"<div class='Rside'>"
							+"<div class='Rside_1'>"
							+"<span style='font-size: 25px;''>"
							+result[t].series_name+" "+result[t].series_title
							+"</span><br>"
							+result[t].artist_id+" 작가 / "+result[t].toon_date+" / 추천 수 : "+result[t].toon_like
							+"</div>"
							+"<div class='Rside_2'>작가의말 : "+result[t].toon_content+"</div>"
							+"</div>"
							+"</div>";
				}
				$(".container").append(value);
			},
			error:function(){
				alert("통신 실패");
			}
		});
	}
	
	function loading(){
		$("#loading").css({"visibility":"visible"});
	}
	function loading2(){
		$("#loading").css({"visibility":"hidden"});
	}
	
</script>
</html>