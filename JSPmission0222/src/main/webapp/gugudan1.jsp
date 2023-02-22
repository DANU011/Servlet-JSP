<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String stringDan = request.getParameter("dan");
	int dan = Integer.parseInt(stringDan);
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan1</title>
</head>
<body>
	<h2>
		<%= dan %>단을 출력합니다.
	</h2>

<%	for(int i = 2 ; i < 10; i++){ %>
		<%= dan %> * <%= i %> = <%= dan * i %><br/>
<%	} %> <!-- 문자로 넣어주면 쪼개지 않아도 가능 -->

</body>
</html>