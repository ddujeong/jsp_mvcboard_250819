package com.ddu.dto;

import java.sql.Date;
import java.sql.Time;

public class reservationDto {
	private int rnum; // 기본키 AI
	private String memberid; // 예약한 사람의 아이디(로그인 되어있는 아이디 )-> 회원 정보 테이블과 연동할 외래키
	private String rname; // 연동 X
	private String rphone; //연동 X
	private Date rDate;
	private Time rTime;
	private String createDate;
	public reservationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public reservationDto(int rnum, String memberid, String rname, String rphone, Date rDate, Time rTime,
			String createDate) {
		super();
		this.rnum = rnum;
		this.memberid = memberid;
		this.rname = rname;
		this.rphone = rphone;
		this.rDate = rDate;
		this.rTime = rTime;
		this.createDate = createDate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRphone() {
		return rphone;
	}
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public Time getrTime() {
		return rTime;
	}
	public void setrTime(Time rTime) {
		this.rTime = rTime;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}	
