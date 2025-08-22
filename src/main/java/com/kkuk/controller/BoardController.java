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
			
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("searchType", searchType);
		}else {
			bDtos = boardDao.boardList(1);
			if(!bDtos.isEmpty()) {
				totalBoardCount = bDtos.get(0).getBno();}
		}
			int totalPage = (int)Math.ceil((double)totalBoardCount / boardDao.PAGE_SIZE);
			int startPage = (((page-1) / PAGE_GROUP_SIZE)*PAGE_GROUP_SIZE)+1;
			int endPage = startPage + (PAGE_GROUP_SIZE -1);
			if(endPage > totalPage) {
				endPage=totalPage;
			}
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchKeyword", searchKeyword);
			request.setAttribute("bDtos", bDtos);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", page);
			
			viewPage = "boardList.jsp";
			
		}else if(comm.equals("/board_edit.do")) {
			viewPage = "board_edit.jsp";
		}else if(comm.equals("/board_view.do")) {
			viewPage = "board_view.jsp";
		}else if(comm.equals("/index.do")) {
			viewPage = "index.jsp";
		}else if(comm.equals("/login.do")) {
			viewPage = "login.jsp";
		}else if(comm.equals("/mypage.do")) {
			viewPage = "mypage.jsp";
		}else if(comm.equals("/notice_edit.do")) {
			viewPage = "notice_edit.jsp";
		}else if(comm.equals("/notice_view.do")) {
			viewPage = "notice_view.jsp";
		}else if(comm.equals("/notice.do")) {
			viewPage = "notice.jsp";
		}else if(comm.equals("/shop.do")) {
			viewPage = "shop.jsp";
		}else if(comm.equals("/signup.do")) {
			viewPage = "signup.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}
}
