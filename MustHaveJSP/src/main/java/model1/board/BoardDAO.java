package model1.board;

import java.util.List;
import java.util.Map; //1
import java.util.Vector;

import javax.servlet.ServletContext;

import org.eclipse.jdt.internal.compiler.problem.AbortCompilationUnit;

import com.mysql.cj.protocol.PacketReceivedTimeHolder;

import common.JDBConnect;

public class BoardDAO extends JDBConnect { //jdbc==controller
	public BoardDAO(ServletContext application) {
		super(application);
	}

	// 검색 조건에 맞는 게시물의 개수 반환
	public int selectCount(Map<String, Object> map) { // 2 게시물 수를 알려주는 메서드 정의 매개변수 Map<String, Object> 컬렉션에 게시뭂 검색을 위한
														// 조건이 있음.
		int totalCount = 0; // 결과(게시물 수)를 담을 변수

		// 게시물 수를 얻어오는 쿼리문 작성 3
		String query = "SELECT COUNT(*) FROM board "; // sql이 제공하는 count(*) 함수 사용
		if (map.get("searchWord") != null) { // 4 if문을 써서 검색어가 있으면 map 컬렉션에 "searchWord"키로 저장된 값이 있을 때만 where절 추가
			query += "WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}
		// jdbc 기본적으로 예외처리 해줘야함
		try { // 5
			stmt = con.createStatement(); // 쿼리문 생성 6 정적쿼리문 실행 Statement
			rs = stmt.executeQuery(query); // 쿼리 실행 7
			rs.next(); // 커서를 첫번째 행으로 이동 8
			totalCount = rs.getInt(1); // 첫 번째 칼럼 값을 가져옴 9 게시물 추출, count(*)정수 반환>getInt
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount; // 10 >jsp로 반환
	}

	// 검색 조건에 맞는 게시물 목록 반환
	public List<BoardDTO> selectList(Map<String, Object> map) { // 1
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 결과(게시물 목록)을 담을 변수

		String query = "SELECT * FROM board ";
		if (map!=null && map.get("searchWord") != null) {
			query += "WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "ORDER BY num DESC ";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) { // 결과순회 ResultSet에 저장된 행이 있는지 확인: 있으면 true 커서 첫번째 행으로 이동, 더 이상 존재하지 않을 때 까지 반복
				// 한행(게시물 하나)의 내용 dto에 저장
				BoardDTO dto = new BoardDTO();

				dto.setNum(rs.getString("num")); // 일련번호
				dto.setTitle(rs.getString("title")); // 제목
				dto.setContent(rs.getString("content")); // 내용
				dto.setPostdate(rs.getDate("postdate")); // 작성일
				dto.setId(rs.getNString("id")); // 작성자 아이디
				dto.setVisitcount(rs.getString("visitcount")); // 조회수 //다 돌고나면 쿼리문으로 받아온 게시물 목록이 모두 list 컬렉션인 bbs에 저장

				bbs.add(dto); // dto를 list 컬렉션에 담음

			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs; // 쿼리 결과를 모두 담은 list 컬렉션을 jsp로 반환
	}

	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();

		String query = "SELECT * from board ";

		// 검색조건 추가
		if (map.get("searchWord") != null) {
			query += " where  " + map.get("searchField") + " Like '%" + map.get("searchWord") + "%' ";
		}
		query += " order by num desc limit ?,?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, (int) map.get("Start") - 1);
			psmt.setInt(2, (int) map.get("pageSize"));

			rs = psmt.executeQuery();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));

				bbs.add(dto);
			}
		}

		catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}

		return bbs;
	}

	public int insertWrite(BoardDTO dto) {
		int result = 0;

		try {
			// insert 쿼리 작성
			String query = "INSERT INTO board (" + "title,content,id,visitcount) " + " VALUES ( " + " ?, ?, ?, 0)";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();

		// 쿼리문 준비
		String query = "SELECT B.*, M.name" + " FROM member M INNER JOIN board B " + " ON M.id=B.id " + " WHERE num=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();

			// 결과처리
			if (rs.next()) {
				dto.setNum(rs.getString(1)); // 일련번호
				dto.setTitle(rs.getString(2)); // 제목
				dto.setContent(rs.getString("content")); // 내용
				dto.setPostdate(rs.getDate("postdate")); // 작성일
				dto.setId(rs.getNString("id")); // 작성자 아이디
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 상세 보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}

	public void updateVisitCount(String num) {
		String query = "UPDATE board SET " + " visitcount=visitcount+1 " + " WHERE num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}

	public int updateEdit(BoardDTO dto) {
		int result = 0;
		try {
			String query = "UPDATE board SET " + " title=?, content=? " + " WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	public int deletePost(BoardDTO dto) {
		int result = 0;

		try {
			String query = "DELETE FROM board WHERE num=?";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();

		}
		return result;
	}

}
