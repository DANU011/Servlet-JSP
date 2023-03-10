package fileupload;

import common.JDBConnect;

public class MyfileDAO extends JDBConnect {
	// 새로운 게시물을 입력합니다.
	public int insertFile(MyfileDTO dto) {
		int applyResult = 0;
		try {
			String query = "insert into myfile ( "
					+" idx, name, title, ofile, sfile)"
					+" values ( "
					+" , ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(1, dto.getCate());
			psmt.setString(1, dto.getOfile());
			psmt.setString(1, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
			
		}
		return applyResult;
	}

}