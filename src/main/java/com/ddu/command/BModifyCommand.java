package com.ddu.command;

import com.ddu.dao.BoardDao;
import com.ddu.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BModifyCommand implements BCommand {
	
	public void excute(HttpServletRequest request , HttpServletResponse response) {
		BoardDao boardDao =new BoardDao();
		
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		boardDao.contentModify(btitle, bcontent, bnum);
		
		BoardDto bDto = boardDao.contentView(bnum);
		request.setAttribute("bDto", bDto);
		
	}
}
