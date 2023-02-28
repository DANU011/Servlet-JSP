package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBCClientMySQL2 {


	public int printColumnName(ResultSet rs) throws Exception { //필드명을 찍어 주겠다
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		
		StringBuilder sb = new StringBuilder("#");//컬럼개수 가져와서 돌면서 찍음
		for (int i = 1 ; i <= count ; i++) {
			sb.append("," + meta.getColumnName(i));
		}
		System.out.println(sb);
		System.out.println("-".repeat(sb.length()));
		
		return count;
	}
	
	public void StudyStatement(Connection con) throws Exception { //받은 con으로 질의 

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from country limit 30");

		printColumnName(rs); //메서드 하나 더 존재
		
		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}

	public void StudyPrepareStatement(Connection con) throws Exception {
		
		PreparedStatement st = con.prepareStatement("select * from country where code=?");//statement 변수x prepareStatement where 절 변수

		st.setString(1, "KOR");//콤마를 자동으로 찍어서 넣어줌. setInt 숫자, setDate 날짜 (prepareStatement) 안쓰고 statement 써도됨.
		ResultSet rs = st.executeQuery();
		
		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) { //메인 시작점
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}	
	
	public static void main(String[] args) throws Exception  { //static이라서 만들지 않아도 되지만

		JDBCClientMySQL2 cli = new JDBCClientMySQL2(); //클라이언트이름 new 해서 인스턴스 만드는 이유? 위에서는 인스턴스 필요하므로 메서드 사용하려고

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");) {//드라이브-겟 가져옴
			
			System.out.println("<=== StudyStatement ===>");
			cli.StudyStatement(con);//con 넘겨줌
			System.out.println();

			System.out.println("<=== StudyPrepareStatement ===>");
			cli.StudyPrepareStatement(con);
		}		
	}
}
