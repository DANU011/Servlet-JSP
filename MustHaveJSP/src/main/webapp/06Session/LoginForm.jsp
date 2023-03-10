<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>Session</title></head>
<body>
	<jsp:include page="../Common/Link.jsp" />
    <h2>로그인 페이지</h2>
    <span style="color: red; font-size: 1.2em;"> 
        <%= request.getAttribute("LoginErrMsg") == null ? // LoginErrMsg 속성이 있는지 확인
                "" : request.getAttribute("LoginErrMsg") // 있으면 출력, 없으면 아무것도 출력하지 않음.
                %>
    </span>
    <%
    if (session.getAttribute("UserId") == null) {  // 로그인 상태 확인 / session객체의 UserId속성을 읽었는데 없으면
        // 로그아웃 상태 - 값이 null 아래 스크립트와 폼 실행 로그인 상태 - 6 실행
    %>
    <script>
    function validateForm(form) { //validateForm() - js 유효성 검사 함수 , id와 pw 중 빈 값이 있으면 경고창을 띄워줌.
    	
        if (!form.user_id.value) {
            alert("아이디를 입력하세요.");
            return false;
        }
        if (form.user_pw.value == "") {
            alert("패스워드를 입력하세요.");
           
            return false;
        }
    }
    </script>
    <form action="LoginProcess.jsp" method="post" name="loginFrm" 
        onsubmit="return validateForm(this);"> 
        아이디 : <input type="text" name="user_id" /><br />
        패스워드 : <input type="password" name="user_pw" /><br />
        <input type="submit" value="로그인하기" /> 
    </form> <!-- onsubmit이 validateForm()을 호출 > 통과하면 폼에 입력된 값이 post 방식으로 LoginProcess.jsp에 전송 -->
    <%
    } else { // 로그인된 상태 6
    %>
        <%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다.<br />
        <a href="Logout.jsp">[로그아웃]</a>
    <%
    }
    %>
</body>
</html>