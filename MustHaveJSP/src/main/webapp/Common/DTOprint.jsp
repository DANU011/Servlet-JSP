<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardDAO dao = new BoardDAO(application); //땡겨옴
	List<BoardDTO> aList = dao.selectList(null); //map 검색조건으로 null
	
	request.setAttribute("aList", aList); //같은 이름으로 저장
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board DTO출력</title>
</head>
<body>
<table border="1">
<%for(int idx=0; idx<aList.size();idx++ ){
	pageContext.setAttribute("idx", idx);%> <!-- el 불가 -->
	<tr>
		<td>${ aList[idx].num } </td>
		<td>${ aList[idx].title } </td>
		<td>${ aList[idx].content } </td>
		<td>${ aList[idx].postdate } </td>
		<td>${ aList[idx].id } </td>
		<td>${ aList[idx].visitcount } </td>
	<tr>
<% } %>

</table>
</body>
</html>