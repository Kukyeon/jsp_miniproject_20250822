package com.kkuk.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kkuk.dao.BoardDao;
import com.kkuk.dao.MemberDao;
import com.kkuk.dto.BoardDto;
import com.kkuk.dto.MemberDto;

/**
 * Servlet implementation class BoardControll
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final int PAGE_GROUP_SIZE = 10;
    
    public BoardController() {
        super();}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	private void actionDo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String comm = uri.substring(conPath.length());
		String viewPage = "";
		HttpSession session = null;
		BoardDao boardDao = new BoardDao();
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		MemberDao memberDao = new MemberDao();
		System.out.println("comm = "+comm);
		
		if(comm.equals("/boardList.do")) {
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");
			int totalBoardCount = 0;
			int page = 1;
		
			if(request.getParameter("page") == null) {
				page = 1;
			}else {
				page = Integer.parseInt(request.getParameter("page"));
			}
		if(searchType != null && searchKeyword != null && !searchKeyword.strip().isEmpty()) {
			//List<BoardDto> bDtos = new ArrayList<BoardDto>();
			bDtos = boardDao.searchList(searchKeyword, searchType, 1);
			
			
			if(!bDtos.isEmpty()) {
				totalBoardCount = bDtos.get(0).getBno();}
			bDtos = boardDao.searchList(searchKeyword, searchType, page);
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("searchType", searchType);
		}else {
			bDtos = boardDao.boardList(page);
			if(!bDtos.isEmpty()) {
				totalBoardCount = bDtos.get(0).getBno();}
			bDtos = boardDao.boardList(page);
		}
			int totalPage = (int)Math.ceil((double)totalBoardCount / boardDao.PAGE_SIZE);
			int startPage = (((page-1) / PAGE_GROUP_SIZE)*PAGE_GROUP_SIZE)+1;
			int endPage = startPage + (PAGE_GROUP_SIZE -1);
			if(endPage > totalPage) {
				endPage=totalPage;
			}
			
			System.out.println("searchKeyword: " + searchKeyword);
			System.out.println("불러온 게시글 수: " + bDtos.size());
			System.out.println("totalBoardCount: " + totalBoardCount);
			
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("bDtos", bDtos);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", page);
			
			
			
			viewPage = "boardList.jsp";
			
		}else if(comm.equals("/edit.do")) { 
			
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			String bnum = request.getParameter("bnum");
			BoardDto bDto = boardDao.contentView(bnum);
			
			if(bDto.getMemberid().equals(sid)){
				request.setAttribute("bDto", bDto);
				viewPage = "board_edit.jsp";
			}else {
				response.sendRedirect("board_edit.jsp");
				return;
			}
			
		}else if(comm.equals("/edit2.do")) { 
			
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			String bnum = request.getParameter("bnum");
			BoardDto bDto = boardDao.contentView(bnum);
			
			if(bDto.getMemberid().equals(sid)){
				request.setAttribute("bDto", bDto);
				viewPage = "notice_edit.jsp";
			}else {
				response.sendRedirect("notice_edit.jsp");
				return;
			}
			
		}else if(comm.equals("/view.do")) { 
			
			// 조회 
			String bnum = request.getParameter("bnum");

			boardDao.updateBhit(bnum);
			BoardDto bDto = boardDao.contentView(bnum);
			request.setAttribute("bDto", bDto);

			viewPage = "board_view.jsp";
		

		}else if(comm.equals("/view2.do")) {  
			
			// 조회 
			String bnum = request.getParameter("bnum");
			
			boardDao.updateBhit(bnum);
			BoardDto bDto = boardDao.contentView(bnum);
			request.setAttribute("bDto", bDto);

			viewPage = "notice_view.jsp";
		

		}else if(comm.equals("/update.do")) { 
			
			String bnum = request.getParameter("bnum");
			String btitle = request.getParameter("title");
			String bcontent = request.getParameter("content");
			String memberid = request.getParameter("author");
			
			boardDao.boardUpdate(bnum, btitle, bcontent);
			BoardDto bDto = boardDao.contentView(bnum);
			request.setAttribute("bDto", bDto);
			
			
			viewPage = "board_view.jsp";
		}else if(comm.equals("/update2.do")) { 
			
			String bnum = request.getParameter("bnum");
			String btitle = request.getParameter("title");
			String bcontent = request.getParameter("content");
			String memberid = request.getParameter("author");
			
			boardDao.boardUpdate(bnum, btitle, bcontent);
			BoardDto bDto = boardDao.contentView(bnum);
			request.setAttribute("bDto", bDto);
			
			
			viewPage = "notice_view.jsp";
		}else if(comm.equals("/index.do")) { // 홈으로
			viewPage = "index.jsp";
		}else if(comm.equals("/loginOk.do")) { // 로그인확인
			
			request.setCharacterEncoding("utf-8");
			String loginId = request.getParameter("memberid");
			String loginPw = request.getParameter("password");
			
			int loginflag = memberDao.loginCheck(loginId, loginPw);
			if(loginflag == 1) {
				session = request.getSession();
				session.setAttribute("sessionId", loginId);
				response.sendRedirect("index.do?message=login");	
				return;
			}else {
				response.sendRedirect("login.do?msg=1");
				return;
			}
			
		
		}else if(comm.equals("/board_write.do")) { // 글쓰기
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			if(sid != null) {
				viewPage = "board_write.jsp";
			}else {
				response.sendRedirect("login.do");
				return;
			}
			
		}else if(comm.equals("/notice_write.do")) { //공지사항 글쓰기
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			if(sid != null) {
				viewPage = "notice_write.jsp";
			}else {
				response.sendRedirect("login.do");
				return;
			}
			
		}else if(comm.equals("/login.do")) {
			viewPage = "login.jsp";
		}else if(comm.equals("/mypage.do")) { // 내정보수정
			
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId"); 
			
			
			 if(sid == null){
			        response.sendRedirect("login.do");
			        return;
			    } else {
			       
			        memberDao = new MemberDao();
			        MemberDto bDto = memberDao.getMemberInfo(sid);
			        
			        request.setAttribute("bDto", bDto);
			        viewPage = "mypage.jsp";
			    }      
			  
			

		}else if(comm.equals("/mypageOk.do")) { //정보수정 요청
			
			session = request.getSession();
			String sid = (String) session.getAttribute("sessionId");

			String currentPw = request.getParameter("currentPassword");
			String newPw = request.getParameter("memberpw");
			String email = request.getParameter("memberemail");

			// DB에서 기존 회원 정보 가져오기
			MemberDto dto = memberDao.getMemberInfo(sid);

			// 현재 비밀번호가 맞는지 확인
			if (!dto.getMemberpw().equals(currentPw)) {
			    request.setAttribute("msg", "현재 비밀번호가 일치하지 않습니다.");
			    viewPage = "mypage.jsp";
			    
			}

			// 새 비밀번호가 없으면 기존 비밀번호 유지
			if (newPw == null || newPw.isEmpty()) {
			    newPw = currentPw;
			}

			// DB 업데이트
			int result = memberDao.updatemember(sid, newPw, email);

			if (result > 0) {
			    session.invalidate();
			    response.sendRedirect("index.do?message=signup1");
			    return;
			} else {
			    request.setAttribute("msg", "회원 정보 수정 실패.");
			    viewPage = "mypage.jsp";
			}

			
			  
			

		}else if(comm.equals("/edit.do")) { // 게시글 수정
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			String bnum = request.getParameter("bnum");
			BoardDto bDto = boardDao.contentView(bnum);
			
			if(bDto.getMemberid().equals(sid) || "관리자".equals(sid)){
				request.setAttribute("bDto", bDto);
				viewPage = "board_edit.jsp";
			}else {
				response.sendRedirect("board_edit.jsp");
				return;
			}
			
			viewPage = "notice_edit.jsp";
		}else if(comm.equals("/edit2.do")) { // 공지수정
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			String bnum = request.getParameter("bnum");
			BoardDto bDto = boardDao.contentView(bnum);
			
			if(bDto.getMemberid().equals(sid)){
				request.setAttribute("bDto", bDto);
				viewPage = "board_edit.jsp";
			}else {
				response.sendRedirect("board_edit.jsp");
				return;
			}
			
			viewPage = "edit2.do";
		}else if(comm.equals("/notice.do")) {
			
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");
			int totalBoardCount = 0;
			int page = 1;
		
			if(request.getParameter("page") == null) {
				page = 1;
			}else {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if(searchType != null && searchKeyword != null && !searchKeyword.strip().isEmpty()) {
			    bDtos = boardDao.searchAdminList(searchKeyword, searchType, 1);

			    if(!bDtos.isEmpty()) {
			        totalBoardCount = bDtos.get(0).getBno();
			    }

			    bDtos = boardDao.searchAdminList(searchKeyword, searchType, page);
			    request.setAttribute("searchKeyword", searchKeyword);
			    request.setAttribute("searchType", searchType);
			} else {
			    bDtos = boardDao.getAdminPosts(page);

			    if(!bDtos.isEmpty()) {
			        totalBoardCount = bDtos.get(0).getBno();
			    }

			    bDtos = boardDao.getAdminPosts(page);
			}

			int totalPage = (int)Math.ceil((double)totalBoardCount / boardDao.PAGE_SIZE);
			int startPage = (((page-1) / PAGE_GROUP_SIZE)*PAGE_GROUP_SIZE)+1;
			int endPage = startPage + (PAGE_GROUP_SIZE -1);
			if(endPage > totalPage) {
				endPage=totalPage;
			}
			
			System.out.println("searchKeyword: " + searchKeyword);
			System.out.println("불러온 게시글 수: " + bDtos.size());
			System.out.println("totalBoardCount: " + totalBoardCount);
			
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("bDtos", bDtos);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", page);
			
			
			viewPage = "notice.jsp";
		}else if(comm.equals("/insert.do")) {
			
			    String title = request.getParameter("title");
			    String content = request.getParameter("content");
			    String author = request.getParameter("author");

			    boardDao.boardWrite(title, content, author);

			    response.sendRedirect("boardList.do");
			    return;

		}else if(comm.equals("/insert2.do")) {
			 String title = request.getParameter("title");
			   String content = request.getParameter("content");
			   String author = request.getParameter("author");

			    boardDao.boardWrite(title, content, author);

			    response.sendRedirect("boardList.do");
			    return;
		}else if(comm.equals("/shop.do")) {
			viewPage = "shop.jsp";
		}else if(comm.equals("/signupOk.do")) { //회원가입
			
			request.setCharacterEncoding("UTF-8");

		    String memberid = request.getParameter("memberid");
		    String memberpw = request.getParameter("memberpw");
		    String membername = request.getParameter("membername");
		    String memberemail = request.getParameter("memberemail");

		    MemberDto memberDto = new MemberDto();
		    memberDto.setMemberid(memberid);
		    memberDto.setMemberpw(memberpw);
		    memberDto.setMembername(membername);
		    memberDto.setMemberemail(memberemail);

		    int result = memberDao.insertMember(memberDto);

		    if (result == MemberDao.MEMBER_JOIN_SUCCESS) {
		        response.sendRedirect("index.do?message=signup");
		    } else {
		        response.sendRedirect("signup.do");
		    }
		    return;
		}else if(comm.equals("/logout.do")) {
			session = request.getSession(false);
			if(session != null) {
				session.invalidate();
			}
			response.sendRedirect("index.do?message=logout");
			return;
		}else if(comm.equals("/delete.do")) {
			String bnum = request.getParameter("bnum");
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			
			BoardDto boardDto = boardDao.contentView(bnum);
			
			if(boardDto.getMemberid().equals(sid) || "관리자".equals(sid)) {
				boardDao.boardDelete(bnum);
				viewPage = "boardList.do";
			}else {
				response.sendRedirect("edit.do?error=1");
				return;
			}
		}else if(comm.equals("/delete2.do")) {
			String bnum = request.getParameter("bnum");
			session = request.getSession();
			String sid = (String)session.getAttribute("sessionId");
			
			BoardDto boardDto = boardDao.contentView(bnum);
			
			if(boardDto.getMemberid().equals(sid)) {
				boardDao.boardDelete(bnum);
				viewPage = "notice.do";
			}else {
				response.sendRedirect("edit2.do?error=1");
				return;
			}
		}
			
			
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}
}
