package com.spring.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CattleVO {
	private String cattle_idx; 
	private String cattle_no; 
	private String cattle_noti_yn; 
	private String cattle_noti_date;
	private String cattle_out_date;
	private String member_idx;
	private List<RegInfoVO> regDateList; 
	
	public CattleVO() {
	}

	public CattleVO(String cattle_idx, String cattle_no, String cattle_noti_yn,
			String cattle_noti_date, String cattle_out_date, String member_idx) {
		super();
		this.cattle_idx = cattle_idx;
		this.cattle_no = cattle_no;
		this.cattle_noti_yn = cattle_noti_yn;
		this.cattle_noti_date = cattle_noti_date;
		this.cattle_out_date = cattle_out_date;
		this.member_idx = member_idx;
	}

	public String getCattle_idx() {
		return cattle_idx;
	}

	public void setCattle_idx(String cattle_idx) {
		this.cattle_idx = cattle_idx;
	}

	public String getCattle_no() {
		return cattle_no;
	}

	public void setCattle_no(String cattle_no) {
		this.cattle_no = cattle_no;
	}

	public String getCattle_noti_yn() {
		return cattle_noti_yn;
	}

	public void setCattle_noti_yn(String cattle_noti_yn) {
		this.cattle_noti_yn = cattle_noti_yn;
	}

	public String getCattle_out_date() {
		return cattle_out_date;
	}

	public void setCattle_out_date(String cattle_out_date) {
		this.cattle_out_date = cattle_out_date;
	}

	public String getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}

	public List<RegInfoVO> getRegDateList() {
		return regDateList;
	}

	public void setRegDateList(List<RegInfoVO> regDateList) {
		this.regDateList = regDateList;
	}

	public String getCattle_noti_date() {
		return cattle_noti_date;
	}

	public void setCattle_noti_date(String cattle_noti_date) {
		this.cattle_noti_date = cattle_noti_date;
	}
	
}
