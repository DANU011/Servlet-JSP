<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - UseBean</title>
</head>
<body>
    <h2>useBean 액션 태그</h2>
    <h3>자바빈즈 생성하기</h3>
    <jsp:useBean id="person" class="common.Person" scope="request" /> 
    <!-- common 패키지의 Person클래스로 person이라는 자바빈즈 생성 > request 영역에 저장 
    자바코드로 바꾼다면
    Person person = (Person)request.getAttribute("person")
    if(person==null){
    person = new Person();
    request.setAttribute("person",person);
    }
    --> 

    <h3>setProperty 액션 태그로 자바빈즈 속성 지정하기</h3>
    <jsp:setProperty name="person" property="name" value="임꺽정" /> 
    <jsp:setProperty name="person" property="age" value="41" /> 
	<!-- person 자바빈즈의 name과 age멤버 변수에 값 설정 
	이때 세터 사용--> 
    
    <h3>getProperty 액션 태그로 자바빈즈 속성 읽기</h3>
    <ul>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li> 
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
    </ul>
    <!-- 게터를 이용해 값 추출 -->
</body>
</html>
<!-- 액션테그로 자바빈즈를 생성할 때는 기본생성자를 사용하고, 값을 설정할 때는 세터, 값을 추출할 때는 게터 사용 -->