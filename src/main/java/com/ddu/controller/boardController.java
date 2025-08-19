package com.ddu.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ddu.dao.BoardDao;
import com.ddu.dto.BoardDto;

@WebServlet("*.do")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public boardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actiondo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actiondo(request, response);
	}
	private void actiondo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String uri =request.getRequestURI();
		//System.out.println("URI : " + uri);
		String conPath =request.getContextPath();
		//System.out.println("CONPATH : " + conPath );
		
		String comm =(uri.substring(conPath.length()+ 1)); // 최종 요청 값
		String viewPage = "";
		
		BoardDao bDao = new BoardDao();
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		
		if(comm.equals("boardList.do")) { // 게시판 모든 글 목록 보기 요청
			bDtos = bDao.boardList();
			request.setAttribute("bDtos", bDtos);		
			viewPage = "boardList.jsp";
		} else if (comm.equals("writeForm.do")) { // 글쓰기 폼으로 이동 요청
			viewPage = "writeForm.jsp";
		} else if (comm.equals("modifyForm.do")) { // 글 수정 폼으로 이동 요청
			viewPage = "modifyForm.jsp";
		} else if (comm.equals("deleteForm.do")) { // 글 삭제 폼으로 이동 요청
			viewPage = "deleteForm.jsp";
		} else if (comm.equals("delete.do")) { // 글 삭제 확인
			viewPage = "boardList.do";
		} else if (comm.equals("contentView.do")) { // 글 내용 확인 요청
			viewPage = "contentView.jsp";
		}  else if (comm.equals("index.do")) { // 홈 화면으로 이동 요청
			viewPage = "index.jsp";
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		// viewPage(실행시킬 jsp 파일 이름)에게 웹서블릿 내에서 제작한 request 객체를 전달한 후 viewPage로 이동해라
	}

}
