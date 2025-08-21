package com.ddu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.ddu.dto.BoardDto;
import com.ddu.dto.MemberDto;

public class BoardDao {
	
	private String driverName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/jspdb";
	private String username = "root";
	private String password = "12345";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<BoardDto> boardList() { // 게시판의 모든 글 리스트를 가져와서 반환하는 메서드
		//String sql ="SELECT * FROM board ORDER BY bnum DESC";
		String sql ="SELECT row_number() OVER (order by bnum) AS bno, b.bnum, b.btitle,b.bcontent,b.member_id, m.member_email, b.bhit, b.bdate FROM board AS b INNER JOIN members AS m ON b.member_id = m.member_id ORDER BY bno DESC";
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bnum = rs.getInt("bnum");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String member_id = rs.getString("member_id");
				String member_email = rs.getString("member_email");
				int bhit = rs.getInt("bhit");
				String bdate = rs.getString("bdate");
				int bno = rs.getInt("bno");
				
				MemberDto memberDto = new MemberDto(); 
				memberDto.setMember_email(member_email);
				memberDto.setMember_id(member_id);
				
				
				BoardDto bDto = new BoardDto(bno, bnum, btitle, bcontent, member_id, bhit, bdate, memberDto);
				
				bDtos.add(bDto);
			}
		} catch(Exception e) {	
			System.out.println("로그인 체크 실패");
			e.printStackTrace();
		} finally { 
			try {
				if (rs != null) {
				rs.close();
				}
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 	
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}return bDtos; // 글(bDto) 여러개가 담긴 list 인 bDtos 를 반환
  }
	public void boardWrite(String btitle, String bcontent, String member_id ) {
		String sql = "INSERT INTO board(btitle,bcontent,member_id,bhit) VALUES(?,?,?,0)";
		// 새글 등록이므로 조회수는 0부터 시작 -> bhit 초기값은 0으로 입력
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1,btitle);
			pstmt.setString(2,bcontent);
			pstmt.setString(3,member_id);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println("글 생성 실패");
			e.printStackTrace();
		} finally { 
			try {
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 	
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
	}
	public BoardDto contentView(int bnum) { // 게시판의 글 목록에서 유저가 클릭한 글 번호의 글 dto 반환 메서드
		String sql ="SELECT b.bnum, b.btitle,b.bcontent,b.member_id, m.member_email, b.bhit, b.bdate FROM board AS b INNER JOIN members AS m ON b.member_id = m.member_id  WHERE bnum=?";
		BoardDto bDto =null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setInt(1, bnum);
			
			rs = pstmt.executeQuery();
			
			 if (rs.next()) {
				
				bDto =new BoardDto();
				bDto.setBnum(rs.getInt("bnum"));
				bDto.setBtitle(rs.getString("btitle"));
				bDto.setBcontent(rs.getString("bcontent"));
				bDto.setMember_id(rs.getString("member_id"));
				bDto.setBhit(rs.getInt("bhit"));
				bDto.setBdate(rs.getString("bdate"));
				
				MemberDto memberDto = new MemberDto();
				memberDto.setMember_email(rs.getString("member_email"));
				memberDto.setMember_id(rs.getString("member_id"));
				
				bDto.setMemberDto(memberDto);
				
				System.out.println(rs.getString("member_email"));
			
			 }
		} catch(Exception e) {	
			System.out.println("게시글 조회 실패");
			e.printStackTrace();
		} finally { 
			try {
				if (rs != null) {
				rs.close();
				}
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 	
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}return bDto; // 글(bDto) 담긴 bDto 를 반환
	
		
	}
	public List<BoardDto> contentSearch(String searchKeyword , String searchType) { // 게시판의 글 목록에서 유저가 클릭한 글 번호의 글 dto 반환 메서드
		String sql ="SELECT row_number() OVER (order by bnum) AS bno, "
				+ "b.bnum, b.btitle,b.bcontent,b.member_id, m.member_email, b.bhit, b.bdate "
				+ "FROM board AS b "
				+ "INNER JOIN members AS m ON b.member_id = m.member_id "
				+ "WHERE "+ searchType +" LIKE ? ORDER BY bno DESC";
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, "%"+searchKeyword.trim()+"%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int bnum = rs.getInt("bnum");
				int bno = rs.getInt("bno");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String member_email = rs.getString("member_email");
				String member_id = rs.getString("member_id");
				int bhit = rs.getInt("bhit");
				String bdate = rs.getString("bdate");
				
				MemberDto memberDto = new MemberDto(); 
				memberDto.setMember_email(member_email);
				memberDto.setMember_id(member_id);
				
				BoardDto bDto = new BoardDto(bno, bnum, btitle, bcontent, member_id, bhit, bdate, memberDto);
				
				bDtos.add(bDto);
			}
		} catch(Exception e) {	
			System.out.println("게시글 조회 실패");
			e.printStackTrace();
		} finally { 
			try {
				if (rs != null) {
				rs.close();
				}
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 	
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}return bDtos; // 글(bDto) 담긴 bDto 를 반환
	
		
	}
	public void contentDelete(int bnum ) {
		String sql = "DELETE FROM board WHERE bnum=?";
		// 새글 등록이므로 조회수는 0부터 시작 -> bhit 초기값은 0으로 입력
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setInt(1,bnum);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println("글 삭제 실패");
			e.printStackTrace();
		} finally { 
			try {
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 	
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
	}
	public void contentModify(String btitle, String bcontent, int bnum) {
		String sql = "UPDATE board SET btitle=?, bcontent=? WHERE bnum=?";
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1,btitle);
			pstmt.setString(2,bcontent);
			pstmt.setInt(3,bnum);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println("글 수정 실패");
			e.printStackTrace();
		} finally { 
			try {
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 	
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
	}public void updateBhit(int bnum) { // 조회수 증가시켜주는 메서드
		String sql = "UPDATE board SET bhit=bhit+1 WHERE bnum=?";
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);	
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setInt(1,bnum);
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {	
			System.out.println("조회수 업데이트 실패");
			e.printStackTrace();
		} finally { 
			try {
				if(pstmt != null){
				pstmt.close();
				}
				if(conn != null){ 	
				conn.close();
				} 
			} catch(Exception e) {
				e.printStackTrace();
			}	
	
		}
	}
}


