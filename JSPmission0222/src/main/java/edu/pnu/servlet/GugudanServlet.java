package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
	private static final long serialVersUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		String type = req.getParameter("type");
		String snum = req.getParameter("num");
		int num =2;
		if(snum != null) num=Integer.parseInt(snum);
		resp.setContentType("text/html; charset=utf-8");
		try (PrintWriter out =resp.getWriter();){
			if(type == null || type.equals("list")) {
				out.printf("<h2>%d단입니다.</h2>", num);
				out.println("<ul>");
				for(int i =1; i<=9; i++) {
					out.printf("<li>%d*%d = %d</li>",num,i,num*i);
				}
				out.println("</ul>");
			}else {
				out.println("<table border=\"1\" style=\"width:280px; text-aline:center;\">");
				out.println("<tr><td>"+num+"단입니다.</tr></td>\n");
				for(int i = 1; i<=9 ; i++) {
				out.printf("<tr><td>%d*%d=%d</td></tr\n", num, i, num*i);
			}
			out.println("</tabel>");
			}
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
