package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClientMySQL3 {

	public void StudyStatement1(Connection con) throws Exception { // 받은 con으로 질의

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select count(Language) from countrylanguage where language='english'");

		while (rs.next()) {

			System.out.println(rs.getString(1));
			
		}
		System.out.println();
		rs.close();
		st.close();
	}

	public void StudyStatement2(Connection con) throws Exception { // 받은 con으로 질의

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select countrylanguage.Language\r\n" + "from country, countrylanguage\r\n"
				+ "where country.code=countrylanguage.CountryCode and country.name='South Korea';");

		while (rs.next()) {

			System.out.println(rs.getString(1));
		}
		System.out.println();
		rs.close();
		st.close();
	}

	public void StudyStatement3(Connection con) throws Exception { // 받은 con으로 질의

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select country.continent, country.name\r\n"
				+ "from countrylanguage, country\r\n" + "where country.code=countrylanguage.CountryCode \r\n"
				+ "and countrylanguage.language='english' and countrylanguage.IsOfficial='F'");

		while (rs.next()) {

			System.out.println(rs.getString(1));
		}
		System.out.println();
		rs.close();
		st.close();
	}

	public void StudyStatement4(Connection con) throws Exception { // 받은 con으로 질의

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select country.continent, count(country.name)\r\n"
				+ "from countrylanguage, country\r\n" + "where country.code=countrylanguage.CountryCode\r\n"
				+ "and countrylanguage.language='english' \r\n" + "group by country.continent");

		while (rs.next()) {

		
			System.out.println(rs.getString(1));
		}
		System.out.println();
		rs.close();
		st.close();
	}

	public static void main(String[] args) throws Exception { // static이라서 만들지 않아도 되지만

		JDBCClientMySQL3 cli = new JDBCClientMySQL3(); // 클라이언트이름 new 해서 인스턴스 만드는 이유? 위에서는 인스턴스 필요하므로 메서드 사용하려고

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");) {// 드라이브-겟
																														// 가져옴

			System.out.println("<=== StudyStatement1 ===>");
			cli.StudyStatement1(con);// con 넘겨줌
			System.out.println();

			System.out.println("<=== StudyStatement2 ===>");
			cli.StudyStatement2(con);// con 넘겨줌
			System.out.println();

			System.out.println("<=== StudyStatement3 ===>");
			cli.StudyStatement3(con);// con 넘겨줌
			System.out.println();

			System.out.println("<=== StudyStatement4 ===>");
			cli.StudyStatement4(con);// con 넘겨줌
			System.out.println();

		}
	}
}
