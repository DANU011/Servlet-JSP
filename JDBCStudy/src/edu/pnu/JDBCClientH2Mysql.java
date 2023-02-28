package edu.pnu;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBCClientH2Mysql {

//	public void StudyStatement1(Connection con) throws Exception { // 받은 con으로 질의
//
//		
//		
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery("select * from board");
//
//		while (rs.next()) {
//
//			System.out.println(rs.getString(1));
//			
//		}
//		System.out.println();
//		rs.close();
//		st.close();
//	}
	
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
	
	public void StudyStatement1(Connection con) throws Exception { //받은 con으로 질의 

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from board");

		
		
		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");//rowCount
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}
	
	public void StudyStatement2(Connection con) throws Exception { //받은 con으로 질의 

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from member");

		//printColumnName(rs); //메서드 하나 더 존재
		
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

//	public void StudyStatement2(Connection con) throws Exception { // 받은 con으로 질의
//
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery("select * from member");
//
//		while (rs.next()) {
//
//			System.out.println(rs.getString(1));
//		}
//		System.out.println();
//		rs.close();
//		st.close();
//	}



	public static void main(String[] args) throws Exception { // static이라서 만들지 않아도 되지만

		JDBCClientH2Mysql cli = new JDBCClientH2Mysql(); // 클라이언트이름 new 해서 인스턴스 만드는 이유? 위에서는 인스턴스 필요하므로 메서드 사용하려고

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musthave", "musthave", "tiger");) {// 드라이브-겟
																														// 가져옴

			System.out.println("<=== StudyStatement1 ===>");
			cli.StudyStatement1(con);// con 넘겨줌
			System.out.println();

			System.out.println("<=== StudyStatement2 ===>");
			cli.StudyStatement2(con);// con 넘겨줌
			System.out.println();


		}
	}
}