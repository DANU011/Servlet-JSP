<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - response</title>
</head>
<body>
<%
String id= request.getParameter("user_id");
String pwd= request.getParameter("user_pwd");
if(id.equalsIgnoreCase("must") && pwd.equalsIgnoreCase("1234")){
	response.getRequestDispatcher("ResPonseMain.jsp?loginErr=1").forword(request, response);
}
%>
</body>
</html>