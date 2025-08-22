package com.ddu.command;

import com.ddu.dao.BoardDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BWriteCommand implements BCommand {
	
	public void excute(HttpServletRequest request , HttpServletResponse response) {
		BoardDao boardDao =new BoardDao(); 
		
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String member_id = request.getParameter("member_id");
		
		boardDao.boardWrite(btitle, bcontent,member_id);
	}
	
}
