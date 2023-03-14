<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<meta charset="UTF-8">
<head><title>표현 언어(EL) - 컬렉션</title></head>
<body>
<h2>List 컬렉션</h2>
<%
List<Object> aList = new ArrayList<Object>();
aList.add("청해진");
aList.add(new Person("장보고", 28));
pageContext.setAttribute("Ocean", aList); //페이지 영역에 저장
%>
<ul> <!-- list 배열처럼 인덱스로 출력가능 -->
    <li>0번째 요소 : ${ Ocean[0] }</li>
    <li>1번째 요소 : ${ Ocean[1].name }, ${ Ocean[1].age }</li>
    <li>2번째 요소 : ${ Ocean[2] }<!--출력되지 않음--></li>
</ul>
<h2>Map 컬렉션</h2>
<%
Map<String, String> map = new HashMap<String, String>();
map.put("한글", "훈민정음");
map.put("Eng", "English");
pageContext.setAttribute("King", map); //페이지 영역에 저장
%>
<ul>
    <li>영문 Key : ${ King["Eng"] }, ${ King['Eng'] }, ${ King.Eng }</li>
    <li>한글 Key : ${ King["한글"] }, ${ King['한글'] }, 
    \${King.한글 } <!--에러 > 키가 한글이면 .은 사용할 수 없음. EL코드 주석 '\' 사용. EL주석은 html코드로 인식되어 화면에 출력됨.-->
    </li>
</ul>
</body>
</html>