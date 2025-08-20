package com.ddu.dto;

public class BoardDto {
	private int bno;
	private int bnum;
	private String btitle;
	private String bcontent;
	private String member_id;
	private int bhit;
	private String bdate;
	
	
	private MemberDto memberDto; // 회원정보 클래스로 선언한 객체를 멤버 변수 영입

	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int bno, int bnum, String btitle, String bcontent, String member_id, int bhit, String bdate,
			MemberDto memberDto) {
		super();
		this.bno = bno;
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.member_id = member_id;
		this.bhit = bhit;
		this.bdate = bdate;
		this.memberDto = memberDto;
	}

	public BoardDto(int bnum, String btitle, String bcontent, String member_id, int bhit, String bdate,
			MemberDto memberDto) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.member_id = member_id;
		this.bhit = bhit;
		this.bdate = bdate;
		this.memberDto = memberDto;
	}

	public BoardDto(int bno, int bnum, String btitle, String bcontent, String member_id, int bhit, String bdate) {
		super();
		this.bno = bno;
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.member_id = member_id;
		this.bhit = bhit;
		this.bdate = bdate;
	}

	public BoardDto(int bnum, String btitle, String bcontent, String member_id, int bhit, String bdate) {
		super();
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.member_id = member_id;
		this.bhit = bhit;
		this.bdate = bdate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		this.memberDto = memberDto;
	}
	
}
