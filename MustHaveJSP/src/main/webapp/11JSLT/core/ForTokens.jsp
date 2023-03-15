<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta charset="UTF-8">
<head><title>JSTL - forTokens</title></head>
<body>
    <%
    String rgba = "Red,Green,Blue,Black";
    %>
    <h4>JSTL의 forTokens 태그 사용 ',' delims 구분자 지정</h4>
    <c:forTokens items="<%= rgba %>" delims="," var="color">
        <span style="color:${ color };">${ color }</span> <br />
    </c:forTokens>
    
     <%
    String rgba2 = "Red|Green|Blue|Black";
    %>
    <h4>JSTL의 forTokens 태그 사용 '|'</h4>
    <c:forTokens items="<%= rgba2 %>" delims="|" var="color">
        <span style="color:${ color };">${ color }</span> <br />
    </c:forTokens>
     

      
    <!-- 파일, 배열에서 어떻게 되는지 보기 -->
</body>
</html>
