package com.spring.vo;

public class RegInfoVO {
	private String reg_idx; 
	private String reg_date; 
	private String cattle_idx;
	
	
	public RegInfoVO() {
	}
	public RegInfoVO(String reg_idx, String reg_date, String cattle_idx) {
		super();
		this.reg_idx = reg_idx;
		this.reg_date = reg_date;
		this.cattle_idx = cattle_idx;
	}
	public String getReg_idx() {
		return reg_idx;
	}
	public void setReg_idx(String reg_idx) {
		this.reg_idx = reg_idx;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getCattle_idx() {
		return cattle_idx;
	}
	public void setCattle_idx(String cattle_idx) {
		this.cattle_idx = cattle_idx;
	}
	
}
