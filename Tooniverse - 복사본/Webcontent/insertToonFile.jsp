<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tooniverse.*, java.util.*"%>
<jsp:useBean id="tBean" class="com.tooniverse.ToonBean" />
<jsp:useBean id="tMgr" class="com.tooniverse.ToonMgr" />
<jsp:useBean id="uMgr" class="com.tooniverse.UploadMgr" />
<%
String id = (String) session.getAttribute("idKey");
ArrayList<String> myToonNameList = uMgr.getMyToomNameList(id);
if(id==null||id.equals("")){
%>
<script type="text/javascript">
	alert("로그인 후 이용할 수 있는 기능입니다.");
	location.href="index.jsp"
</script>
<%
}
%>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#newSeriesBtn").click(function(){
			$("#newSeriesName2").text($("#newSeriesName").val())
			$.ajax({
				url:"newSeriesDAO.do",
				type:"get",
				data:{
					newSeriesName:$("#newSeriesName").val(),
					id : "<%=id%>"
				},
				success:function(result){
					if(result=="YY"){
						alert("이미 존재하는 웹툰명입니다.");
					}else if(result=="stop"){
						alert("이름을 입력하세요")
					}else{
						$("select").append("<option>"+$("#newSeriesName").val()+"</option>");
					}
				},
				error:function(){
					console.log("ajax통신오류발생");
				}
			})
		})
	})
</script>
<script type="text/javascript">
	count = 1;
	addCount = 1;
	
	function fileExist(){
		var file = $(".test").val()
		if(file){
			console.log(file);
			frm.submit();
		}else{
			location.href='uploadFail.jsp';
		}
	}
	
	
	// 행 추가 버튼
	function addInputBox(){
		for(i = 1; i <= count; i++){
			//i가 지역변수임
			if(!document.getElementsByName("test" + i)[0]){
				addCount = i;
				break;
			}
			else {
				addCount = count;
			}
		}
		
		addStr = "<tr><td><input type=checkbox name=checkList value="+ addCount +
		"></td><td><input type='file' name='test"+addCount+"' class='test' value='filegogos'></td></tr> " + addCount;
		
		table = document.getElementById("dynamic_table");
		newRow = table.insertRow();
		newCell = newRow.insertCell();
		newCell.innerHTML = addStr;
		count++;
	}
	
	
	// 행 삭제 버튼
	function deleteInputBox() {
		table = document.getElementById("dynamic_table");
		rowCnt = table.rows.length;
		chk = 0;
		//1보다 클떄만 삭제할수 있음
		if(rowCnt > 0){
			for(i = 0; i < frm.checkList.length; i++){
				if(frm.checkList[i].checked == true){
					table.deleteRow(i);
					i--;
					count--;
					chk++;
				}
			}
			if(chk <= 0){
				alert("삭제할 행을 체크해주세요");
			}
		}else{
			alert("더이상 삭제할 행이 없습니다");
		}
	}
</script>
<script>
	
	$(function(){
		howMySeries();
	})
	// select태그 네임 series_name
	function howMySeries(){
		$.ajax({
			url:"howMySeries.do",
			type:"post",
			data:{
				series_name:$("#series_name option:selected").val()
			},
			success:function(result){
				let result2 = parseInt(result)+1;
				$("#numBox").attr("value", (result2));
			},
			error:function(){
				alert("총 몇화인지불러오는데실패")
			}
		})
	}
	
</script>
<html>
<head>
<style type="text/css">
body {
	background-color: #444;
	color: white;
	list-style: none;
}

.container {
	width: 1200px;
	margin: 0 auto;
}
ul li{list-style: none;}
#numBox{width: 40px}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<fieldset><legend>완성파일로 웹툰 업로드하기</legend>
			<h3>파일을 업로드하려면 행 추가버튼을 클릭하세요</h3>
			<h3>파일 업로드하실때 알아서 뒤에 번호를 맞춰주세요(ex: 김치맨01, 김치맨02, 김치맨03, ... , 김치맨10, 김치맨11)</h3>
			<li><input id="newSeriesName" name="newSeriesName"><button id="newSeriesBtn">신규작품추가</button></li>
			<form action="uploadtoon" name="frm" method="post" enctype="multipart/form-data">
				<div class="toonInfo">
					<ul>
						<li>작품명<select id="series_name" name="series_name" onchange="howMySeries();"><%
						for (int i = 0; i < myToonNameList.size(); i++) {
						%>
							<option><%=myToonNameList.get(i)%></option>
						<%
						}
						%>
						</select></li>
						<li>몇화?<input type="number" name="series_title" id="numBox" readonly>화</li>
						<li>작가의 말<br><textarea rows="9" cols="40" name="toon_content"></textarea></li>
					</ul>
				</div>
				<input type="button" value="행 추가" onclick="addInputBox();">&emsp;
				<input type="button" value="행 삭제" onclick="deleteInputBox();">
				<p/>
				<input type="hidden" name="count">
				<input type="hidden" name="newSeriesName2" id="newSeriesName2">
				<table border="1" id="dynamic_table">
				</table>
				<input type="hidden" name="id" value="<%=id%>"> 
				<input type="button" onclick="fileExist();" value="UPLOAD">
			</form>
			<button>미리보기</button>
		</fieldset>
	</div>
</body>
</html>