package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClientMYSQL {

	public static void main(String[] args) throws Exception {
		
		//MYSQL 접속을 위한 JDBC 드라이버 로드
		Class.forName("com.mysql.cj.jdbc.Driver");
		/*Class라는 이름의 클래스의 forName 메서드를 호출 static 메서드 > .으로 바로 호출해서 사용가능*/
		
		//MYSQL Database 연결 객체 생성
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","musthave","tiger");
		/*DriverManager클래스 getConnection 메서드 > 접속할 db  url("jdbc:mysql://localhost:3306/world","musthave","tiger") 유저, pw /
		 * con이라는 객체에 넘겨줌*/
		
		//질의를 위한 객체 생성
		Statement st = con.createStatement();
		
		
		//SQL 질의
		ResultSet rs = st.executeQuery("select Name,Continent,Population,HeadOfState from country");
		/*ResultSet에 리턴되어 돌아옴.*/
		
		//질의 결과 Parsing, =커서 프로세싱(cursor processing)
		while(rs.next()) { //다음 결과 레코드로 이동 /*당겨오고*/
			for(int i=1;i<=4;i++) { /*1부터 시작*/
				if(i!=1)System.out.print(",");
					System.out.print((rs.getString(i))); /*더 이상 읽을게 없으면 rs.next가 false 후 나옴*/
			}
			System.out.println();
		}
		//생성된 객체 연걸을 모두 해제 /*프로세싱 끝나고 다 닫아줌. 내가 연건 내가 다 닫아줌. new 한건 공식적으로 닫는 메서드 없으므로 어쩔 수 없음.*/
		rs.close();
		st.close();
		con.close();
	}
}
