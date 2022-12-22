<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tooniverse.*, java.util.*" %>
<!DOCTYPE html>
<jsp:useBean id="tBean" class="com.tooniverse.ToonBean"/>
<jsp:useBean id="tMgr" class="com.tooniverse.ToonMgr"/>
<%
// tBean = tMgr.readToonToToonNum(toonNum); / tBean = tMgr.readToon(num);
	request.setCharacterEncoding("UTF-8");
	int num = 0; 
	int toonNum = 0;
	
	
	
	if(request.getParameter("toonNum")!=null){
		toonNum = Integer.parseInt(request.getParameter("toonNum"));
		//System.out.println(toonNum);
		tBean = tMgr.readToonToToonNum(toonNum);
	}else{
		if(request.getParameter("num")!=null){
			toonNum = tMgr.readToon(num).getToon_id();
			tBean = tMgr.readToon(num);
		}
	}
	
			
	/* if(request.getParameter("num")!=null){
		num = Integer.parseInt(request.getParameter("num"));
		tBean = tMgr.readToon(num);
	}else{
		tBean = tMgr.readToonToToonNum(toonNum);
	} */
		
	
	String id = (String)session.getAttribute("idKey");
%>
<html>
<head>
<style type="text/css">
	body{background-color: #444; color: white;}
	ul{ list-style: none;}
	.container{background-color: #eee; width: 100%; text-align: center; margin: 0 auto}
	.toonInfo{color: #000; width: 800px; margin: 0 auto; border-top: 3px solid black; text-align: justify;}
	.toonBook{}
	.viewer{border-bottom: 1px solid black; padding: 12px 0; background-color: wheat;}
	.vote{border-bottom: 1px solid black; border-top: 1px solid black; padding: 10px 0;}
	img{margin: 0px auto; display: block;}
	.writersComment{color:black;}
	.userComment{color: black; border-top: 2px solid black;}
	.btn img{width: 150px; height: 150px;}
	#prePageBtn{position: fixed; top:46%;}
	#nextPageBtn{position: fixed; top:46%; right: 0}
	.SH{display: none}
	.att{border:1px solid tomato;}
	.att td{border:1px solid tomato;}
	li{list-style: none}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<a class="btn" id="prePageBtn"><img alt="뒤로가버리기" src="img/prePage.png" onclick="pregogo();"></a>
	<a class="btn" id="nextPageBtn"><img alt="앞으로가버리기" src="img/nextPage.png" onclick="nextgogo();"></a>
	<div class="container">
		<div class="toonInfo">
		<%
		if(session.getAttribute("admin")!=null){
			
			if((int)session.getAttribute("admin")>=1){
		%>
				<button onclick="deleteThisToon()">글 삭제(관리자)</button>
		<%
			}
		}
		%>
			<div class="viewer">
				<%=tBean.getSeries_name()%> <%=tBean.getSeries_title() %>
			</div>
			<div class="vote">
				등록일자 : <%=tBean.getToon_date() %>
			</div>
		</div>
		<div class="toonBook">
		<%
			
			String[] tt = tBean.getToon_toon().split("ahb6920");
			Arrays.sort(tt, Collections.reverseOrder());
			/* System.out.println(Arrays.toString(tt)); */
				for(int t = tt.length-1; t > -1; t--){
					if(tt[t].equals("null")){
						tt[t]="alterImg.png";
					}
		%>
			<img alt="툰툰" src="fileServer/<%=tt[t] %>">
		<%
				}
			
		%>
		</div>
		
		
		<div class="toonLikeDiv">
		좋아요기능넣을곳
			
			
		</div>
		<input type="hidden" value="0" id="thisLikeChk">
		
		<div class="writersComment">
			&lt;작가의말&gt;<br>
			<%=tBean.getToon_content() %>
			<hr>
		</div>
		<div class="userComment">
			<jsp:include page="reply.jsp"/>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		showToonLike();
	})
	
	function toonLikeOrUnlike(){
		let id = "<%=id%>";
		if(id==null||id=='null'){
			alert("로그인이나하세요")
			return;
		}
		
		if($("#thisLikeChk").val()==1){
			alert("좋아요를 해제하셨습니다");
		}else{
			alert("좋아요를 누르셨습니다");
		}
			
		$.ajax({
			url : "tl.do",
			type : "post",
			data : {
				thisChk : $("#thisLikeChk").val(),
				userId : "<%=id%>",
				toonNum : <%=toonNum%>
			},
			success : function(result){
				console.log("ajax통신성공 : toonLike()")
				location.reload();
			},
			error : function(){
				console.log("ajax통신오류 : toonLike()")
			}
		})
	}
				
	
	function showToonLike(){
		//하트모양 결정
		$.ajax({
			url : "lchk.ra",
			type : "post",
			data : {
				userId : "<%=id%>",
				toonNum : <%=toonNum%>
			},
			success : function(result){
				if(result=='yes'){
					$(".toonLikeDiv").html("<img src='img/like.png' alt='대체그림' id='toonLike' onclick='toonLikeOrUnlike();'>");
					$("#thisLikeChk").val(1);
				}else{
					$(".toonLikeDiv").html("<img src='img/unlike.png' alt='대체그림' id='toonLike' onclick='toonLikeOrUnlike();'>");
					$("#thisLikeChk").val(0);
				}
			},
			error : function(){
				console.log("ajax통신오류 : showToonLike()")
			}
		})
	}
	
	function pregogo(){
		$.ajax({
			url : "calP.ca",
			type : "post",
			data : {toonNum : <%=toonNum%>},
			success : function(result){
				//가야할 툰넘 도착
				result1 = result.split(' ');
				if(result1[0]=='0'){
					alert("1화입니다.")					
				}else{
					location.href="read.jsp?toonNum="+result1[0]+"&num="+result1[1];
				}
					
				/* location.href="read.jsp?toonNum="+result[0]+"&num="+result[1]; */
			},
			error : function(){
				console.log("calP함수가 제대로 동작하지 않음(ajax오류)");
			}
		})
	}
	
	function nextgogo(){
		$.ajax({
			url : "calN.ca",
			type : "post",
			data : {toonNum : <%=toonNum%>},
			success : function(result){
				result1 = result.split(' ');
				if(result1[0]=='0'){
					alert("가장마지막화입니다.")					
				}else{
					location.href="read.jsp?toonNum="+result1[0]+"&num="+result1[1];
				}
			},
			error : function(){
				console.log("calN함수가 제대로 동작하지 않음(ajax오류)");
			}
		})
	}
	
	function deleteThisToon(){
		$.ajax({
			url : "DTT.az",
			type : "post",
			data : {toonNum : <%=toonNum%>},
			success : function(result){
				console.log(result);
				alert("정상적으로 삭제되었습니다.");
				location.href="index.jsp";
			},
			error : function(){
				console.log("deleteThisToon함수가 제대로 동작하지 않음(ajax오류)");
			}
		})
	}
</script>
</html>