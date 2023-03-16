package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect{ //커넥션 풀 상속
	public MVCBoardDAO() {
		super();
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		//
		String query = "select count(*) from mvcboard";
		//
		if (map.get("searchWord") != null) {
			query += " where " + map.get("searchField")+" "
					+ " like '%" + map.get("searchWord")+"%'";
		}
		try {
			stmt= con.createStatement();
			rs=stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
	//
	public List<MVCBoardDTO> selectListPage(Map<String,Object> map) {
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		//
		String query = " "
				+ "select * from ( "
				+ "		select Tb.*, rownum rNum from ( "
				+ "			select * from mvcboard ";
		//
		if(map.get("searchWord") != null) {
			query += " where "+map.get("searchField")
					+ " like '%"+map.get("searchWord") + "%' ";
		}
		
		query += "			Order by idx desc "
				+ "		) Tb"
				+ " ) "
				+ "where rNum between ? and ?"; //
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs=psmt.executeQuery();
			
			//
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPosDate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	}


