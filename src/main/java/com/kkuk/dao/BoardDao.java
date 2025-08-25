package com.kkuk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kkuk.dto.BoardDto;
import com.kkuk.dto.MemberDto;

public class BoardDao {

	
	private String driverName = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/jspndb"; 
	private String username = "root"; // DB 로그인한 아이디
	private String password = "12345"; // DB 비밀번호

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public static final int PAGE_SIZE = 10;
	
	
	
	public List<BoardDto> boardList(int page){ // 게시판 모든 글 + 내림차순
		String sql = "SELECT row_number() OVER (order by b.bnum ASC) AS bno, "
				+ " b.bnum, b.btitle, b.bcontent, b.memberid, b.bdate, b.bhit "
				+ " FROM board b"
				+ " LEFT JOIN members m ON b.memberid = m.memberid "
				+ " ORDER BY bno DESC "
				+ " LIMIT ? OFFSET ?";
		int offset = (page -1) * PAGE_SIZE;
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		
		try {
			Class.forName(driverName); 			
			conn = DriverManager.getConnection(url, username, password);
		
			pstmt = conn.prepareStatement(sql); //pstmt 객체 생성(sql 삽입)			
			pstmt.setInt(1, PAGE_SIZE); //limit 10으로 고정
			pstmt.setInt(2, offset); //0 10 20 ...
			rs = pstmt.executeQuery(); //모든 글 리스트(모든 레코드) 반환
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				int bnum = rs.getInt("bnum");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String memberid = rs.getString("memberid");
				int bhit = rs.getInt("bhit");
				String bdate = rs.getString("bdate");				
				
				
				MemberDto memberDto = new MemberDto();
				memberDto.setMemberid(memberid);
				
				
				BoardDto bDto = new BoardDto(bno, bnum, btitle, bcontent, memberid, bhit, bdate, memberDto);
				bDtos.add(bDto);
			}	
		}catch (Exception e) {
			System.out.println("DB 에러 발생! 게시판 목록 가져오기 실패!");
			e.printStackTrace(); //에러 내용 출력
		} finally { //에러의 발생여부와 상관 없이 Connection 닫기 실행 
			try {
				if(rs != null) { //rs가 null 이 아니면 닫기(pstmt 닫기 보다 먼저 실행)
					rs.close();
				}				
				if(pstmt != null) { //stmt가 null 이 아니면 닫기(conn 닫기 보다 먼저 실행)
					pstmt.close();
				}				
				if(conn != null) { //Connection이 null 이 아닐 때만 닫기
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			return bDtos;
		}
		public List<BoardDto> searchList(String searchKeyword, String searchType, int page){
			String sql = "SELECT row_number() OVER (order by b.bnum ASC) AS bno,"
					+ "b.bnum, b.btitle, b.bcontent, b.memberid, b.bdate, b.bhit "
					+ "FROM board b "
					+ "LEFT JOIN members m ON b.memberid = m.memberid "
					+ " WHERE " + searchType + " LIKE ?"
					+ " ORDER BY bno DESC"
					+ " LIMIT ? OFFSET ?";
			
			List<BoardDto> bDtos = new ArrayList<BoardDto>();
			int offset = (page -1) * PAGE_SIZE;
			
			try {
				Class.forName(driverName); //MySQL 드라이버 클래스 불러오기			
				conn = DriverManager.getConnection(url, username, password);
				
				pstmt = conn.prepareStatement(sql); //pstmt 객체 생성(sql 삽입)			
				pstmt.setString(1, "%" + searchKeyword + "%");
				pstmt.setInt(2, PAGE_SIZE);
				pstmt.setInt(3, offset);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int bno = rs.getInt("bno");
					int bnum = rs.getInt("bnum");
					String btitle = rs.getString("btitle");
					String bcontent = rs.getString("bcontent");
					String memberid = rs.getString("memberid");
					int bhit = rs.getInt("bhit");
					String bdate = rs.getString("bdate");				
					
					
					
					
					MemberDto memberDto = new MemberDto();
					memberDto.setMemberid(memberid); 
					
					
					BoardDto bDto = new BoardDto(bno, bnum, btitle, bcontent, memberid, bhit, bdate, memberDto);
					bDtos.add(bDto);
					
				}	
				
			} catch (Exception e) {
				System.out.println("DB 에러 발생! 게시판 검색문제 실패!");
				e.printStackTrace(); //에러 내용 출력
			} finally { //에러의 발생여부와 상관 없이 Connection 닫기 실행 
				try {
					if(rs != null) { 
						rs.close();
					}				
					if(pstmt != null) { 
						pstmt.close();
					}				
					if(conn != null) { 
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return bDtos;
		}
				
		public void boardWrite(String btitle, String bcontent, String memberid) { // 게시판 글 작성
			String sql = "INSERT INTO board(btitle, bcontent, memberid, bhit) VALUES (?,?,?,0)";
			
			try {
				Class.forName(driverName); //MySQL 드라이버 클래스 불러오기			
				conn = DriverManager.getConnection(url, username, password);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, btitle);
				pstmt.setString(2, bcontent);
				pstmt.setString(3, memberid);			
				
				pstmt.executeUpdate();
		
			}catch (Exception e) {
				System.out.println("DB 에러 발생! 게시판 검색문제 실패!");
				e.printStackTrace(); //에러 내용 출력
			} finally { //에러의 발생여부와 상관 없이 Connection 닫기 실행 
				try {
								
					if(pstmt != null) { 
						pstmt.close();
					}				
					if(conn != null) { 
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
		}
		
		public void boardUpdate(String bnum, String btitle, String bcontent) {
			String sql = "UPDATE board SET btitle=?, bcontent=? WHERE bnum=?";
			
			try {
				Class.forName(driverName); //MySQL 드라이버 클래스 불러오기			
				conn = DriverManager.getConnection(url, username, password);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, btitle);
				pstmt.setString(2, bcontent);
				pstmt.setString(3, bnum);
				
				pstmt.executeUpdate();
			}catch (Exception e) {
				System.out.println("DB 에러 발생! 게시판 글 수정 실패!");
				e.printStackTrace(); //에러 내용 출력
			}finally { //에러의 발생여부와 상관 없이 Connection 닫기 실행 
				try {
					if(pstmt != null) { 
						pstmt.close();
					}				
					if(conn != null) {
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		public BoardDto contentView(String bnumber) {
			String sql = "SELECT row_number() OVER (order by bnum ASC) AS bno,"
					+ "b.bnum, b.btitle, b.bcontent, b.memberid, b.bdate, b.bhit "
					+ "FROM board b "
					+ "LEFT JOIN members m ON b.memberid = m.memberid "
					+ "WHERE bnum=?";	
			BoardDto bDto = null;
			
			try {
				Class.forName(driverName); //MySQL 드라이버 클래스 불러오기			
				conn = DriverManager.getConnection(url, username, password);
				//커넥션이 메모리 생성(DB와 연결 커넥션 conn 생성)
				
				pstmt = conn.prepareStatement(sql); //pstmt 객체 생성(sql 삽입)			
				pstmt.setString(1, bnumber);			
				rs = pstmt.executeQuery(); //해당 번호글의 레코드 1개 또는 0개가 반환
				
				while(rs.next()) {
					int bno = rs.getInt("bno");
					int bnum = rs.getInt("bnum");
					String btitle = rs.getString("btitle");
					String bcontent = rs.getString("bcontent");
					String memberid = rs.getString("memberid");
					int bhit = rs.getInt("bhit");
					String bdate = rs.getString("bdate");
					
					
					MemberDto memberDto = new MemberDto();
					memberDto.setMemberid(memberid); 
					
					bDto = new BoardDto(bno, bnum, btitle, bcontent, memberid, bhit, bdate, memberDto);
				}	
				
			} catch (Exception e) {
				System.out.println("DB 에러 발생! 게시판 글 가져오기 실패!");
				e.printStackTrace(); //에러 내용 출력
			} finally { //에러의 발생여부와 상관 없이 Connection 닫기 실행 
				try {
					if(rs != null) { //rs가 null 이 아니면 닫기(pstmt 닫기 보다 먼저 실행)
						rs.close();
					}				
					if(pstmt != null) { //stmt가 null 이 아니면 닫기(conn 닫기 보다 먼저 실행)
						pstmt.close();
					}				
					if(conn != null) { //Connection이 null 이 아닐 때만 닫기
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return bDto;
		}
		
		public void updateBhit(String bnum) {
			String sql = "UPDATE board SET bhit=bhit+1 WHERE bnum=?";
			
			try {
				Class.forName(driverName); //MySQL 드라이버 클래스 불러오기			
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, bnum);
				pstmt.executeUpdate();
				
			}catch(Exception e){
				System.out.println("DB 에러 발생! 게시판 글 삭제 실패!");
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) { //stmt가 null 이 아니면 닫기(conn 닫기 보다 먼저 실행)
						pstmt.close();
					}				
					if(conn != null) { //Connection이 null 이 아닐 때만 닫기
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void boardDelete(String bnum) {
			String sql = "DELETE FROM board WHERE bnum = ?";
			
			try {
				Class.forName(driverName); //MySQL 드라이버 클래스 불러오기			
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, bnum);
				pstmt.executeUpdate();
				
			}catch(Exception e){
				System.out.println("DB 에러 발생! 게시판 글 삭제 실패!");
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) { //stmt가 null 이 아니면 닫기(conn 닫기 보다 먼저 실행)
						pstmt.close();
					}				
					if(conn != null) { //Connection이 null 이 아닐 때만 닫기
						conn.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		public List<BoardDto> getAdminPosts(int page) {
		    String sql = "SELECT row_number() OVER (ORDER BY b.bnum ASC) AS bno, "
		               + "b.bnum, b.btitle, b.bcontent, b.memberid, b.bdate, b.bhit "
		               + "FROM board b "
		               + "WHERE b.memberid = '관리자' "
		               + "ORDER BY bno DESC "
		               + "LIMIT ? OFFSET ?";
		    
		    int offset = (page - 1) * PAGE_SIZE;
		    List<BoardDto> bDtos = new ArrayList<>();

		    try {
		        Class.forName(driverName);
		        conn = DriverManager.getConnection(url, username, password);
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, PAGE_SIZE);
		        pstmt.setInt(2, offset);
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		            
		        	int bno = rs.getInt("bno");
					int bnum = rs.getInt("bnum");
					String btitle = rs.getString("btitle");
					String bcontent = rs.getString("bcontent");
					String memberid = rs.getString("memberid");
					int bhit = rs.getInt("bhit");
					String bdate = rs.getString("bdate");				
					
					
					
					
					MemberDto memberDto = new MemberDto();
					memberDto.setMemberid(memberid); 
					
					
					BoardDto bDto = new BoardDto(bno, bnum, btitle, bcontent, memberid, bhit, bdate, memberDto);
					bDtos.add(bDto);
		            
		        
		        }
		    } catch (Exception e) {
		        System.out.println("DB 에러 발생! 관리자 게시글 목록 실패!");
		        e.printStackTrace();
		    } finally {
		        try { if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn != null) conn.close(); } catch (Exception e) {}
		    }

		    return bDtos;
		}
		public List<BoardDto> searchAdminList(String searchKeyword, String searchType, int page) {
		    String sql = "SELECT row_number() OVER (ORDER BY b.bnum ASC) AS bno, "
		               + "b.bnum, b.btitle, b.bcontent, b.memberid, b.bdate, b.bhit "
		               + "FROM board b "
		               + "WHERE b.memberid = '관리자' AND " + searchType + " LIKE ? "
		               + "ORDER BY bno DESC "
		               + "LIMIT ? OFFSET ?";
		    
		    int offset = (page - 1) * PAGE_SIZE;
		    List<BoardDto> bDtos = new ArrayList<>();

		    try {
		        Class.forName(driverName);
		        conn = DriverManager.getConnection(url, username, password);
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, "%" + searchKeyword + "%");
		        pstmt.setInt(2, PAGE_SIZE);
		        pstmt.setInt(3, offset);
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		        	int bno = rs.getInt("bno");
					int bnum = rs.getInt("bnum");
					String btitle = rs.getString("btitle");
					String bcontent = rs.getString("bcontent");
					String memberid = rs.getString("memberid");
					int bhit = rs.getInt("bhit");
					String bdate = rs.getString("bdate");	
					
					MemberDto memberDto = new MemberDto();
					memberDto.setMemberid(memberid); 
					
					
					BoardDto bDto = new BoardDto(bno, bnum, btitle, bcontent, memberid, bhit, bdate, memberDto);
					bDtos.add(bDto);
		        }
		    } catch (Exception e) {
		        System.out.println("DB 에러 발생! 관리자 게시글 검색 실패!");
		        e.printStackTrace();
		    } finally {
		        try { if (rs != null) rs.close(); if (pstmt != null) pstmt.close(); if (conn != null) conn.close(); } catch (Exception e) {}
		    }

		    return bDtos;
		}

		
}
