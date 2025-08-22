package com.ddu.command;
import com.ddu.dao.BoardDao;
import com.ddu.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BContentCommand implements BCommand {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BoardDao boardDao = new BoardDao();
		
		int bnum = Integer.parseInt(request.getParameter("bnum")); // 유저가 선택한 글의 번호
		boardDao.updateBhit(bnum); // 조회수 증가
		
		BoardDto bDto = boardDao.contentView(bnum);	
			if (bDto == null) { // 해당글이 존재 하지 않을때
				 request.setAttribute("deleteMsg", "해당글은 존재하지 않는 글 입니다.");
				// response.sendRedirect("boardList.do?msg=1"); // -> 2번째 방법 
				// return;
			} else {
				request.setAttribute("bDto", bDto);
			}
	}

}
