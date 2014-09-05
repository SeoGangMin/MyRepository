package com.cattle.member.model;

public class Member {
	private String member_seq; 
	private String member_name; 
	private String member_pwd; 
	private String member_phone; 
	private String member_reg_date; 
	private String member_renew_date;
	
	
	public Member(){}
	
	public String getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(String member_seq) {
		this.member_seq = member_seq;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
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
	
	
}
