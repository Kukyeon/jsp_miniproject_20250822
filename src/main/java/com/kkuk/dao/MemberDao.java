package com.kkuk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kkuk.dto.MemberDto;

public class MemberDao {
	
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_ID_EXISTENT = 1;
	public static final int MEMBER_ID_NONEXISTENT = 0;
	
	
	private String driverName = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/jspndb"; 
	private String username = "root"; // DB 로그인한 아이디
	private String password = "12345"; // DB 비밀번호

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int sqlResult = 0;
	
	
		public int idCheck(String id, String pw) { // 아이디 존재여부 확인
			 String sql = "SELECT * FROM members WHERE memberid = ? AND memberpw = ?";
			 
			 try{ 
					Class.forName(driverName); // MySQL 드라이버 클래스 불러오기
					conn = DriverManager.getConnection(url, username, password);
					pstmt = conn.prepareStatement(sql); // pstmt 객체 생성
					
					pstmt.setString(1, id);
					pstmt.setString(2, pw);
					
					rs = pstmt.executeQuery(); 
					
					
					if(rs.next()) { 
						sqlResult = 1; // 존재하는 아이디
						
					}else { 
						sqlResult = 0; // 존재하지않는 아이디
					}
					
				} catch (Exception e) {
					System.out.println("DB 에러 아이디 존재 여부 체크 실패");
					e.printStackTrace(); // 에러 내용 출력
				} finally { // 에러 발생 여부와 상관없이 커넥션 닫아줘야함
					try{
						if(rs != null){ // rs가 null 아니면 닫기 (spstmt 보다 먼저 실행되어야함)
							rs.close();
						}
						if(pstmt != null){ // pstmt가 null 아니면 닫기 (conn 보다 먼저 실행되어야함)
							pstmt.close();
						}
						if(conn != null){ // 커넥션이 null이 아닐때만 닫기
							conn.close();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				return sqlResult; // 1로 반환되면 로그인 성공, 0으로 반환되면 로그인 실패
		}
	
	
	
		public int insertMember(MemberDto memberDto) { //회원가입 고고
		String sql = "INSERT INTO members(memberid, memberpw, membername, memberemail) VALUES(?,?,?,?)";
		
		try{
			Class.forName(driverName); 
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql); // pstmt 객체 생성
			
			pstmt.setString(1, memberDto.getMemberid());
			pstmt.setString(2, memberDto.getMemberpw());
			pstmt.setString(3, memberDto.getMembername());
			pstmt.setString(4, memberDto.getMemberemail());
			
			
			sqlResult = pstmt.executeUpdate();
			
			} catch (Exception e) {
				System.out.println("DB 에러 회원가입 실패");
				e.printStackTrace(); // 에러 내용 출력
			} finally {
				try{
					if(pstmt != null){ // stmt가 null 아니면 닫기 (conn 보다 먼저 실행되어야함)
						pstmt.close();
					}
					if(conn != null){ // 커넥션이 null이 아닐때만 닫기
						conn.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			if(sqlResult == 1) {
				return MEMBER_JOIN_SUCCESS;
			} else {
				return MEMBER_JOIN_FAIL;
			}
			}
	
	
	
	
	
		public int loginCheck(String mid, String mpw) { // 로그인 체크 아이디 비밀번호 확인
		String sql = "SELECT * FROM members WHERE memberid = ? AND memberpw = ? ";
		
		
		try{
			Class.forName(driverName); 
			conn = DriverManager.getConnection(url, username, password);
			
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체 생성
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			
			rs = pstmt.executeQuery(); //  아이디와 비밀번호가 일치하는 레코드1개 또는 0개가 반환
			
			
			if(rs.next()) {	//참이면 로그인성공
				sqlResult = 1;
			}else { // 거짓이면 로그인실패
				sqlResult = 0;
			}
			
			
		} catch (Exception e) {
			System.out.println("DB 에러 아이디 존재 여부 체크 실패");
			e.printStackTrace(); // 에러 내용 출력
		} finally {
			try{
				if(rs != null){ // rs가 null 아니면 닫기 (spstmt 보다 먼저 실행되어야함)
					rs.close();
				}
				if(pstmt != null){ // pstmt가 null 아니면 닫기 (conn 보다 먼저 실행되어야함)
					pstmt.close();
				}
				if(conn != null){ // 커넥션이 null이 아닐때만 닫기
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return sqlResult;  // 로그인 성공 = 1 , 로그인실패 = 0 
	}
		
	public int updatemember(String memberid, String memberpw, String memberemail) {
		String sql = "UPDATE members SET memberpw = ?, memberemail = ? WHERE memberid = ?";

		int sqlResult = 0;
		
		try{ // 에러 날 가능성이 높기때문에 예외처리 필수 트라이 캣치
			Class.forName(driverName); // MySQL 드라이버 클래스 불러오기
			conn = DriverManager.getConnection(url, username, password);
			//conn 커넥션이 메모리에 생성이됨 (DB와의 연결 커넥션 conn 생성)
			// stmt = conn.createStatement(); // stmt 객체 생성
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체 생성
			
			pstmt.setString(1, memberpw);
			pstmt.setString(2, memberemail);
			pstmt.setString(3, memberid);
			
			int Result = pstmt.executeUpdate(); // 성공시 sqlResult 값이 1로 변환
			
			
			if(Result > 0) { 
				sqlResult = 1; 
				
			}else { 
				sqlResult = 0;
			}
			
			
		} catch (Exception e) {
			System.out.println("DB 에러 아이디 삭제 에러로 인해 실패");
			e.printStackTrace(); // 에러 내용 출력
		} finally { // 에러 발생 여부와 상관없이 커넥션 닫아줘야함
			try{
				
				if(pstmt != null){ // pstmt가 null 아니면 닫기 (conn 보다 먼저 실행되어야함)
					pstmt.close();
				}
				if(conn != null){ // 커넥션이 null이 아닐때만 닫기
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return sqlResult; // 1로 반환되면 삭제 성공, 0으로 반환되면 삭제 실패
		
		
	}
	
	public MemberDto getMemberInfo(String memberid) {
		
		String sql = "SELECT * FROM members WHERE memberid = ? ";
		MemberDto memberDto = new MemberDto();
		
		try{ // 에러 날 가능성이 높기때문에 예외처리 필수 트라이 캣치
			Class.forName(driverName); // MySQL 드라이버 클래스 불러오기
			conn = DriverManager.getConnection(url, username, password);
			//conn 커넥션이 메모리에 생성이됨 (DB와의 연결 커넥션 conn 생성)
			// stmt = conn.createStatement(); // stmt 객체 생성
			
			pstmt = conn.prepareStatement(sql); // pstmt 객체 생성
			pstmt.setString(1, memberid);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) { 
				 memberDto.setMemberid(rs.getString("memberid"));
				 memberDto.setMemberpw(rs.getString("memberpw"));
				
				 memberDto.setMemberemail(rs.getString("memberemail"));
				 
			
			}
			
		} catch (Exception e) {
			System.out.println("DB 에러 아이디 존재 여부 체크 실패");
			e.printStackTrace(); // 에러 내용 출력
		} finally { // 에러 발생 여부와 상관없이 커넥션 닫아줘야함
			try{
				if(rs != null){ // rs가 null 아니면 닫기 (spstmt 보다 먼저 실행되어야함)
					rs.close();
				}
				if(pstmt != null){ // pstmt가 null 아니면 닫기 (conn 보다 먼저 실행되어야함)
					pstmt.close();
				}
				if(conn != null){ // 커넥션이 null이 아닐때만 닫기
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return memberDto; 
	
	}
	
}

