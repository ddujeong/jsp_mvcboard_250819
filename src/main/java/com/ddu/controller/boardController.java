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

import com.ddu.command.BCommand;
import com.ddu.command.BWriteCommand;
import com.ddu.dao.BoardDao;
import com.ddu.dao.MemberDao;
import com.ddu.dto.BoardDto;

@WebServlet("*.do")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int PAGE_GROUP_SIZE = 5;
	BCommand bCommand;
       
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
		//List<BoardDto> countDtos = new ArrayList<BoardDto>();
		
		
	if(comm.equals("/boardList.do")) { // 게시판 모든 글 목록 보기 요청
			request.setCharacterEncoding("utf-8"); 
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");
			int page = 1;
			int totalBoardCount = 0; // 모든 글의 갯수
			
			if (request.getParameter("page") == null) { // 참이면 링크타고 게시판으로 들어온 경우
				page = 1;
			} else { // 유저가 보고 싶은 페이지 번호를 누른 경우
				page = Integer.parseInt(request.getParameter("page"));
				// 유저가 클릭한 보고싶은 페이지 번호
			}
			if (searchType != null && searchKeyword != null && !searchKeyword.strip().isEmpty()) { // 유저가 검색 결과 리스트를 원하는 경우
				bDtos = bDao.contentSearch(searchKeyword, searchType, 1);
				if (!bDtos.isEmpty()) {
					totalBoardCount = bDtos.get(0).getBno();
				}
				bDtos = bDao.contentSearch(searchKeyword, searchType, page);
				request.setAttribute("searchType", searchType);
				request.setAttribute("searchKeyword", searchKeyword);
			} else { // 전체 글 리스트를 원하는 경우
				bDtos = bDao.boardList(1);
				if (!bDtos.isEmpty()) {
					totalBoardCount = bDtos.get(0).getBno();
				}
				bDtos = bDao.boardList(page);
			}
			int totalPage = (int)Math.ceil((double)totalBoardCount / BoardDao.PAGE_SIZE);
			// 모든 글의 갯수 구해서 소수점을 올려주는 방정식 
			int startPage = (((page -1) /PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE) + 1 ; 
			// int endPage = Math.min(startPage + (PAGE_GROUP_SIZE -1), totalPage) ;
			// 마지막 페이지 그룹의 경우에는 실제 마지막 페이지로 표시 (그룹의 마지막 페이지, 총 페이지) 중 작은 수를 구함
			int endPage = (startPage + (PAGE_GROUP_SIZE -1)) ;
			
			request.setAttribute("bDtos", bDtos);
			request.setAttribute("totalPage",totalPage); // 전체 글 갯수로 계산한 전체 페이지 수
			request.setAttribute("currentPage", page); // 현재 페이지 넘버
			request.setAttribute("startPage",startPage); // 그룹으로 나눈 페이지의 시작
			request.setAttribute("endPage",endPage); // 그룹으로 나눈 페이지의 끝
			request.setAttribute("totalBoardCount", totalBoardCount);
			System.out.println("startPage : " + startPage);
			System.out.println("endPage : " +endPage);
			System.out.println("totalPage : " +totalPage);
			System.out.println(startPage + (PAGE_GROUP_SIZE -1));
			
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

//			int bnum = Integer.parseInt(request.getParameter("bnum"));
//			String btitle = request.getParameter("btitle");
//			String bcontent = request.getParameter("bcontent");
//			
//			bDao.contentModify(btitle, bcontent, bnum);
//			
//			BoardDto bDto = bDao.contentView(bnum);
//			request.setAttribute("bDto", bDto);

			//BModifyCommand bModifyCommand =new BModifyCommand(); 
			//bModifyCommand.excute(request, response);
			
			bCommand = new BWriteCommand();
			bCommand.excute(request, response);
			
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
				
				System.out.println(bnum);
			
			viewPage = "contentView.jsp";
		
		} else if (comm.equals("/index.do")) { // 홈 화면으로 이동 요청
			viewPage = "index.jsp";
		} else if (comm.equals("/writeOk.do")) { // 홈 화면으로 이동 요청
//			request.setCharacterEncoding("utf-8");
//			
//			String btitle = request.getParameter("btitle");
//			String bcontent = request.getParameter("bcontent");
//			String member_id = request.getParameter("member_id");
//			
//			bDao.boardWrite(btitle, bcontent,member_id);
			
			BWriteCommand bWriteCommand = new BWriteCommand();
			bWriteCommand.excute(request, response);
			
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
