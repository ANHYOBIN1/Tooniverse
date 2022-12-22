<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
<style type="text/css">
body {
	background-color: #444;
	color: white;
}

.container {
	width: 1200px;
	margin: 0 auto
}

.container a {
	font-size: 50px;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".gnb ul li:first-child").html(
				"<div style='background-color:red;'>웹툰 그리기</div>");
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<a href="insertToonFile.jsp"><img src="img/insertToonFile.png"
			alt="파일첨부로업로드하기"></a><br> <a href="canvas.html"><img
			src="img/canvasUpload.png" alt="직접그리기"></a>
	</div>
</body>
</html>