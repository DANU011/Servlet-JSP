package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.text.AbstractDocument.Content;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect { // 커넥션 풀 상속
	public MVCBoardDAO() {
		super();
	}
	// 검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		//쿼리문 준비
		String query = "select count(*) from mvcboard";
		//검색 조건이 있다면 where절로 추가
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " " + " like '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}

	// 검색 조건이 있다면 where절로 추가
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		//
		String query = "SELECT * FROM mvcboard";
		//
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%' ";
		}
		
		query += " order by idx desc limit ?,?";// 게시물 구간은 인파라미터로

		try {
			psmt = con.prepareStatement(query); //동적 쿼리문 생성
			psmt.setInt(1, (int)map.get("start")-1); //인파라미터 설정
			psmt.setInt(2, (int)map.get("pageSize"));
			rs = psmt.executeQuery(); //쿼리문 실행

			//반환된 게시물 목록을 list 컬렉션에 추가
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();

				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setPass(rs.getString("pass"));
				dto.setVisitcount(rs.getInt("visitcount"));

				board.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board; //목록반환
	}

	// 게시글 데이터를 받아 DB에 추가합니다(파일 업로드 지원).
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO mvcboard ( " + "  name, title, content, ofile, sfile, pass) " + " VALUES ( "
					+ " ?,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 주어진 일련번호에 해당하는 게시물을 DTO에 담아 반환합니다.
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO(); //DTO 객체 생성
		String query = "SELECT * FROM mvcboard where idx=?";
		try {
			psmt =con.prepareStatement(query);
			psmt.setString(1, idx);
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostDate(rs.getDate("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setPass(rs.getString("pass"));
				dto.setVisitcount(rs.getInt("visitcount"));
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 증가 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	//주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
	public void updateVisitCount(String idx) {
		// TODO Auto-generated method stub
		String query = "update mvcboard set "
				+" visitcount=visitcount+1 "
				+" where idx=?";
		try {
			psmt =con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
				
		
	}
	// 다운로드 횟수를 1 증가시킵니다.
	public void downCountPlus(String idx) {
		String sql = "update mvcboard set "
				+ " downcount=downcount+1 "
				+ " where idx=? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지 확인합니다.
	public boolean confirmPassword(String pass, String idx) {
		boolean isCorr = true;
		try {
			String sql = "select count(*) from mvcboard where pass=? and idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) {
				isCorr = false;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
	}
	
	//지정한 일련번호의 게시물을 삭제합니다.
	public int deletePost(String idx) {
		int result = 0;
		try {
			String query ="delete from mvcboard where idx=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 삭제 중 예외 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	// 게시글 데이터를 받아 DB에 저장되어있던 내용을 갱신합니다.(파일 업로드 지원)/
	public int updatePost(MVCBoardDTO dto) {
		int result = 0;
		try {
			
			String query = "update mvcboard SET title=?, name=?, content=?, ofile=?, sfile=? where idx=? and pass=?";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setInt(6, Integer.parseInt(dto.getIdx()));
			psmt.setString(7, dto.getPass());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
}
