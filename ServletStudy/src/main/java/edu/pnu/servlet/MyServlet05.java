package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet05")
public class MyServlet05 extends HttpServlet {
	
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//super.service(req, resp);
	System.out.println("MyServlet05");
	resp.setContentType("text/html; charset=utf-8");
	PrintWriter out=resp.getWriter();
	out.println("<table border=1 style='border-collapse : collapse; text-align:center;'>"
			+ "<th>번호</th>"
			+ "<th>나라</th>"
			+ "<th>수도</th>"
			+ "<th>인구</th>"
			+"<tr> <td>1</td> <td>대한민국</td> <td>서울</td> <td>1000만</td> </tr>"
			+"<tr> <td>2</td> <td>미국</td> <td>워싱턴</td> <td>70만</td> </tr>"
			+"<tr> <td>3</td> <td>일본</td> <td>도쿄</td> <td>1400만</td> </tr>"
			+"<tr> <td>4</td ><td>중국</td > <td>베이징</td> <td>2100만</td> </tr>"
//			+"<tr><td>5<td>중 국<td>베 이 징<td>0</td></tr>"
		
			+ "<table>");

	out.close();
}
}
