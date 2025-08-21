package com.ddu.controller;

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

import com.ddu.dao.BoardDao;
import com.ddu.dao.MemberDao;
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
		System.out.println("URI : " + uri);
		String conPath =request.getContextPath();
		System.out.println("CONPATH : " + conPath );
		
		String comm =(uri.substring(conPath.length())); // 최종 요청 값
		String viewPage = "";
		
		BoardDao bDao = new BoardDao();
		MemberDao mDao =new MemberDao();
		HttpSession session = null;
		// 세션 객체 생성 하는 법
		
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		
		if(comm.equals("/boardList.do")) { // 게시판 모든 글 목록 보기 요청
			request.setCharacterEncoding("utf-8"); 
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");
			
			if (searchType != null && searchKeyword != null && !searchKeyword.strip().isEmpty()) { // 유저가 검색 결과 리스트를 원하는 경우
				bDtos = bDao.contentSearch(searchKeyword, searchType);
			} else { // 전체 글 리스트를 원하는 경우
				bDtos = bDao.boardList();
			}
			request.setAttribute("bDtos", bDtos);		
			viewPage = "boardList.jsp";
		} else if (comm.equals("/writeForm.do")) { // 글쓰기 폼으로 이동 요청
			session = request.getSession();
			String sid =(String) session.getAttribute("session_id");
			if (sid != null) {
				viewPage = "writeForm.jsp";
			} else {
				response.sendRedirect("login.do?msg=2");
				return;
			}
		} else if (comm.equals("/modifyForm.do")) { // 글 수정 폼으로 이동 요청
			request.setCharacterEncoding("utf-8");
			String bnum = request.getParameter("bnum");
			BoardDto bDto = bDao.contentView(Integer.parseInt(bnum));
			
			request.setAttribute("bDto", bDto);
			viewPage = "modifyForm.jsp";
		
		} else if (comm.equals("/modify.do")) { // 글 수정 후 글내용 보기로 이동 요청
			request.setCharacterEncoding("utf-8");

			int bnum = Integer.parseInt(request.getParameter("bnum"));
			String btitle = request.getParameter("btitle");
			String bcontent = request.getParameter("bcontent");
			
			bDao.contentModify(btitle, bcontent, bnum);
			
			BoardDto bDto = bDao.contentView(bnum);
			request.setAttribute("bDto", bDto);
			
			viewPage = "contentView.do";
		
		} else if (comm.equals("/deleteForm.do")) { // 글 삭제 폼으로 이동 요청
			
			String bnum = request.getParameter("bnum");
			BoardDto bDto = bDao.contentView(Integer.parseInt(bnum));
			request.setAttribute("bDto", bDto);
			viewPage = "deleteForm.jsp";
			 
		} else if (comm.equals("/delete.do")) { // 글 삭제 확인
			String bnum = request.getParameter("bnum");
			bDao.contentDelete(Integer.parseInt(bnum));
			
			response.sendRedirect("boardList.do");
			return;
		
		} else if (comm.equals("/contentView.do")) { // 글 내용 확인 요청
			request.setCharacterEncoding("utf-8");
			int bnum = Integer.parseInt(request.getParameter("bnum")); // 유저가 선택한 글의 번호
			bDao.updateBhit(bnum); // 조회수 증가
			
			BoardDto bDto = bDao.contentView(bnum);	
				if (bDto == null) { // 해당글이 존재 하지 않을때
					 request.setAttribute("deleteMsg", "해당글은 존재하지 않는 글 입니다.");
					// response.sendRedirect("boardList.do?msg=1"); // -> 2번째 방법 
					// return;
				} else {
					request.setAttribute("bDto", bDto);
				}
				bDtos = bDao.boardList();
				request.setAttribute("bDtos", bDtos);	
			viewPage = "contentView.jsp";
		
		} /*
			 * else if (comm.equals("/search.do")) { // 검색 글 목록 확인 요청
			 * request.setCharacterEncoding("utf-8"); String searchType =
			 * request.getParameter("searchType"); String searchKeyword =
			 * request.getParameter("searchKeyword"); bDtos =
			 * bDao.contentSearch(searchKeyword); if (searchType !=null) { // 유저가 검색 결과 리스트를
			 * 워하는 경우
			 * 
			 * } if (bDtos.isEmpty() || searchKeyword ==null ||
			 * searchKeyword.trim().isEmpty()) { // 해당글이 존재 하지 않을때
			 * request.setAttribute("deleteMsg", "해당 제목의 게시글은 존재하지 않는 글 입니다.");
			 * response.sendRedirect("boardList.do?msg=1"); return; }
			 * request.setAttribute("bDtos", bDtos); viewPage = "boardList.jsp";
			 * 
			 * }
			 */ else if (comm.equals("/index.do")) { // 홈 화면으로 이동 요청
			viewPage = "index.jsp";
		} else if (comm.equals("/writeOk.do")) { // 홈 화면으로 이동 요청
			request.setCharacterEncoding("utf-8");
			
			String btitle = request.getParameter("btitle");
			String bcontent = request.getParameter("bcontent");
			String member_id = request.getParameter("member_id");
			
			bDao.boardWrite(btitle, bcontent,member_id);
			
			response.sendRedirect("boardList.do");
		    return; 
		    // 글을 작성한 후 강제로 boardList.do로 이동한 후 포워딩을 하지 않게 프로그램의 진행을 멈춤 (return!!!을 꼭 써야 멈춤)
		
		} else if (comm.equals("/login.do")) { 
			viewPage = "login.jsp";
		
		} else if (comm.equals("/loginOk.do")) { 
			request.setCharacterEncoding("utf-8");
			
			String login_id = request.getParameter("member_id");
			String login_pw = request.getParameter("member_pw");
			
			int loginFlag = mDao.loginCheck(login_id, login_pw);
			
			if (loginFlag == 1) {
				session =request.getSession();
				session.setAttribute("session_id", login_id);
			} else {
				response.sendRedirect("login.do?msg=1");
				return;
			}
			viewPage = "boardList.do";
		
		} else if (comm.equals("/logout.do")) { 
			session =request.getSession();
			session.invalidate();
			viewPage = "index.jsp";
		} else { // 없는 주소를 입력 했을 때 인덱스 페이지로 돌아가게 만들어줌
			viewPage = "index.jsp";
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		// viewPage(실행시킬 jsp 파일 이름)에게 웹서블릿 내에서 제작한 request 객체를 전달한 후 viewPage로 이동해라
	}

}
