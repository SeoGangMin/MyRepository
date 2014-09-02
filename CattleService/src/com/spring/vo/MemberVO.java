package com.spring.vo;

public class MemberVO {
	private String member_idx; 				//회원 번호
	private String member_name; 			//회원명
	private String member_pwd; 				//비밀번호
	private String member_phone;			//회원 핸드폰번호
	private String member_reg_date; 		//회원 가입일
	private String member_renew_date; 		//회원 갱신일
	
	public MemberVO() {
	}
	
	public MemberVO(String member_name, String member_pwd, String member_phone){
		this.member_name = member_name; 
		this.member_pwd = member_pwd;
		this.member_phone = member_phone;
	}

	public MemberVO(String member_idx, String member_name, String member_pwd, String member_phone,
			String member_reg_date, String member_renew_date) {
		super();
		this.member_idx = member_idx;
		this.member_name = member_name;
		this.member_pwd  = member_pwd; 
		this.member_phone = member_phone;
		this.member_reg_date = member_reg_date;
		this.member_renew_date = member_renew_date;
	}

	public String getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_reg_date() {
		return member_reg_date;
	}

	public void setMember_reg_date(String member_reg_date) {
		this.member_reg_date = member_reg_date;
	}

	public String getMember_renew_date() {
		return member_renew_date;
	}

	public void setMember_renew_date(String member_renew_date) {
		this.member_renew_date = member_renew_date;
	}

	public String getMember_pwd() {
		return member_pwd;
	}

	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
}
