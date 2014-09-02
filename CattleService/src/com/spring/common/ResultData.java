package com.spring.common;


public class ResultData{
	
	private String code = "000"; 
	private String msg = Const.MSG_SUCCESS; 
	private Object result;
	
	public ResultData(){
	}

	public ResultData(String code, String msg, Object result) {
		this.code = code;
		this.msg = msg;
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}