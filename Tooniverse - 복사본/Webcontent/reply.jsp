<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="tBean" class="com.tooniverse.ToonBean"/>
<jsp:useBean id="tMgr" class="com.tooniverse.ToonMgr"/>
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)session.getAttribute("idKey");
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
%>
<html>

<head>
<style type="text/css">
	*{list-style: none}
	.SH{display: none;}
	.att{border:1px solid tomato;}
	.att td{border:1px solid tomato;}
	li{list-style: none}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 800px; margin: 0 auto">
		<p>한줄의견</p>
		
			<input type="hidden" value="<%=toonNum%>">
			이름 : <input name="name" id="" <%if(id==null||id.equals("null")){%> value="로그인하세요" <%}else{%> value="<%=id %>" <%} %>readonly>
			<textarea style="width: 800px; resize: none;" rows="5" id="replyContent">부적절한 표현 사용하지마세요</textarea><button id="insertReply" onclick="insertReply()">댓글 등록</button>
		
		<table id="reply">
			<thead>
				<td>이름</td>
				<td>내용</td>
				<td>날짜</td>
				<td>좋아요</td>
				<td>좋아요버튼</td>
				<td>싫어요버튼</td>
				<td>대댓글</td>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
		
	<script type="text/javascript">
	var id = "<%=id%>";
	
	$(function(){
		replyList();
		
	})
	
	function press(v){
		console.log("프레스함수눌림");
		$("#ror"+v).toggleClass("SH");
		$.ajax({
			url:"ror.do",
			type : "post",
			data : {
				rNum : v
			},
			success : function(rList){
				console.log(rList)
				let rorVal = "<td colspan='7'>";
				for(let i = 0; i < rList.length; i++){
					rorVal += "<li>"+rList[i].ror_name+"&emsp;:"+"&emsp;"+rList[i].ror_content+"</li>"
				}
				rorVal += "<li><input type='text' class='input"+v+"'><button value='"+v+"' onclick='inserRor(this.value);'>대댓등록</button></li></td>"
				$("#ror"+v).after().html(rorVal);
			},
			error : function(){
				console.log("대댓글 ajax 통신오류")
			}
		})
	}
	
	
	function replyList(){
		$.ajax({
			url:"reply.do",
			type:"post",
			data:{
				toonNum:"<%=toonNum%>"
			},
			success:function(result){
				//ArrayList로 담아온 ReplyBean이 지금 result에 담겨있음 
				let value = "";
				for(let i = 0; i < result.length; i++){
					value += "<tr class='reply"+result[i].no+" att'><td>" 
					+ result[i].name + "</td><td>" 
					+ result[i].content + "</td><td>" 
					+ result[i].redate + "</td><td>" 
					+ result[i].re_like + "</td><td><button onclick='pressLikeRepl(this.value,"+result[i].no+");' value='"+(i+1)
					+"'>좋아용</button></td><td><button onclick='pressUnLikeRepl(this.value,"+result[i].no+");' value='"+(i+1)+"'>싫어용</button></td>"
					+"<td><button value='"+result[i].no+"' onclick='press(this.value)'>댓글보기</button></td></tr>"
					+"<tr class='SH' id='ror"+result[i].no+"'></tr>"
				}
				$("#reply tbody").html(value);
			},
			error:function(){
				alert("통신 실패");
			}
		});
	}
	
	function inserRor(t){
		if(id==null||id=='null'){
			alert("로그인하고눌러");
			return;
		}
		console.log(t);
		$.ajax({
			url:"inserRor.do",
			type:"post",
			data:{
				tNum : t,
				toonNum : <%=toonNum%>,
				cont : $(".input"+t).val(),
				id : "<%=id%>"
			},
			success:function(){
				replyList()
				press(t);
			},
			error:function(){
				console.log("inserRor() 함수 ajax통신에러")
			}
		})
	}
	
	
	
	function insertReply(){
		let id1 = '<%=id%>';
		if(id1==null||id1=='null'){
			alert("로그인부터하고댓글다세요");
			return;
		}
		
		console.log(id1);
		$.ajax({
			url:"insert.ay",
			data:{
				toonNum:"<%=toonNum %>",
				content:$("#replyContent").val(),
				nick:"<%=id%>"
			},
			type:"post",
			success:function(result){
				replyList();
			}
			,error:function(){
				console.log("ajax통신오류:insertReply()에서");
			}
		});
		
	}
	
	function pressLikeRepl(a, b){
		if(id==null||id=='null'){
			alert("로그인하고눌러");
			return;
		}
		$.ajax({
			url : "ReplLikeChk.do",
			type : "post",
			data : {
				eotrmfqjsghwhg : a,
				aucquseotrmf : b,
				id : "<%=id%>"
			},
			success : function(result){
				if(result=="nono"){
					alert("한번 좋아요/싫어요를 누르면 변경 불가능합니다.")
				}
				replyList();
			},
			error : function(){
				alert("ajax통신 실패 : pressLikeRepl()")
			}
		})
	}
	
	function pressUnLikeRepl(a, b){
		if(id==null||id=='null'){
			alert("로그인하고눌러");
			return;
		}
		$.ajax({
			url : "ReplLikeChk.do",
			type : "post",
			data : {
				eotrmfqjsghtlfg : a,
				aucquseotrmf : b,
				id : "<%=id%>"
			},
			success : function(result){
				if(result=="nono"){
					alert("한번 좋아요/싫어요를 누르면 변경 불가능합니다.")
				}
				replyList();
			},
			error : function(){
				alert("ajax통신 실패 : pressUnLikeRepl()")
			}
		})
	}
	
	
</script>
<hr style="margin: 200px 0">
		
</body>
</html>