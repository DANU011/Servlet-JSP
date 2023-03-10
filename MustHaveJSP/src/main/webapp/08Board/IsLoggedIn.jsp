<%@ page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if (session.getAttribute("UserId") == null) { // "UserId" 있는지 확인
    JSFunction.alertLocation("로그인 후 이용해주십시오.",
                             "../06Session/LoginForm.jsp", out); // 로그인 안했으면 경고문
    return;
}
%>