package com.cattle.main.model;

public class FertilizationInfo {
	private String fi_seq; 				
	private String fi_no; 
	private String fi_noti_date; 
	private String fi_delivery_date;
	private String member_seq; 
	
	public FertilizationInfo(){}

	public String getFi_seq() {
		return fi_seq;
	}

	public void setFi_seq(String fi_seq) {
		this.fi_seq = fi_seq;
	}

	public String getFi_no() {
		return fi_no;
	}

	public void setFi_no(String fi_no) {
		this.fi_no = fi_no;
	}

	public String getFi_noti_date() {
		return fi_noti_date;
	}

	public void setFi_noti_date(String fi_noti_date) {
		this.fi_noti_date = fi_noti_date;
	}

	public String getFi_delivery_date() {
		return fi_delivery_date;
	}

	public void setFi_delivery_date(String fi_delivery_date) {
		this.fi_delivery_date = fi_delivery_date;
	}

	public String getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(String member_seq) {
		this.member_seq = member_seq;
	}
	
}
