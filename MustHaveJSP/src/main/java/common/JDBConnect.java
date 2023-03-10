package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext; //application 내장 객체의 타입인 ServletContext를 받도로 임포트

public class JDBConnect {
	public Connection con; //데이터베이스와 연결을 담당
	public Statement stmt; // 인파라미터가 없는 정적 쿼리문을 실행할 때 사용
	public PreparedStatement psmt; //인파라미터가 있는 동적 쿼리문을 실행할 때 사용
	public ResultSet rs; //select 쿼리문의 결과를 저장할 때 사용
	
	// 기본 생성자
	public JDBConnect() { //jdbc드라이버를 이용해 db에 연결
		try {
			// JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver"); //forName > new키워드 대신 클래스명을 통해 직접 객체생성 > 메모리에 로드
			
			// DB에 연결
			String url = "jdbc:mysql://localhost:3306/musthave";
			String id = "musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 두번째 생성자
	public JDBConnect(String driver, String url, String id, String pwd) {
		System.out.println("driver :"+ driver);
		System.out.println("url :"+ url);
		System.out.println("id :"+ id);
		System.out.println("pwd :"+ pwd);
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			
			// DB에 연결
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자 1)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 세번째 생성자
	public JDBConnect(ServletContext application) { // 매개 변수로 application 내장객체를 받도록 정의. 내장객체를 이용해 web.xml로 접속정보를 직접 가져옴.
		try {
			// JDBC 드라이버 로드
			String driver = application.getInitParameter("MySQLDriver");
			Class.forName(driver);
			
			// DB에 연결
			String url = application.getInitParameter("MySQLURL");
			String id = application.getInitParameter("MySQLId");
			String pwd = application.getInitParameter("MySQLPwd");
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(인수 생성자 2)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JDBConnect jdbcConnect = new JDBConnect();
	}
	
	// 연결 해제(자원 반납)
	public void close() { //멤버 변수로 선언된 객체를 각각 닫음.
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
