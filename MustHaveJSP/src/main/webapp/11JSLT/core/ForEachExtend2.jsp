<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="common.Person"%>
<%@ page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>JSTL - forEach 2</title></head>
<body>
    <h4>List 컬렉션 사용하기</h4>
    <%
    LinkedList<Person> lists = new LinkedList<Person>();
    lists.add(new Person("맹사성", 34));
    lists.add(new Person("장영실", 44));
    lists.add(new Person("신숙주", 54));
    %>
    <c:set var="lists" value="<%= lists %>"/>
    <c:forEach items="${ lists }" var="list">
    <li>
        이름 : ${ list.name }, 나이 : ${ list.age }
    </li>
    </c:forEach>

    <h4>Map 컬렉션 사용하기</h4>
    <%
    Map<String,Person> maps = new HashMap<String,Person>();
    maps.put("1st", new Person("맹사성", 34));
    maps.put("2nd", new Person("장영실", 44));
    maps.put("3rd", new Person("신숙주", 54));
    %>
    <c:set var="maps" value="<%= maps %>" />
    <c:forEach items="${ maps }" var="map">
        <li>Key => ${ map.key }  <br />
            Value => 이름 : ${ map.value.name }, 나이 : ${ map.value.age }</li>
    </c:forEach>
    
    <h4>Map 컬렉션 - 업데이트</h4>
    <%
    Map<String,Person> maps2 = new HashMap<String,Person>();
    maps2.put("1st", new Person("맹사성", 34));
    maps2.put("1st", new Person("장영실", 44));
    maps2.put("1st", new Person("신숙주", 54));// 키 값이 같으면 마지막 값으로 업데이트
    %>
    <c:set var="maps2" value="<%= maps2 %>" />
    <c:forEach items="${ maps2 }" var="map">
        <li>Key => ${ map.key }  <br />
            Value => 이름 : ${ map.value.name }, 나이 : ${ map.value.age }</li>
    </c:forEach>
    <!-- Map<String,String> 으로 해보기, 구구단 이 방식으로 수정해보기 -->
</body>
</html>