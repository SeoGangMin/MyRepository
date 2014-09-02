package com.spring.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.common.Const;
import com.spring.common.ResultData;
import com.spring.dao.MemberDao;
import com.spring.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Gson gson; 
	
	@RequestMapping(value="/member/JoinMember", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insertMember(
			HttpServletRequest request,
			@RequestParam(value="member_name", required=true) String member_name,
			@RequestParam(value="member_pwd", required=true) String member_pwd,
			@RequestParam(value="member_phone", required=true) String member_phone
			){
		
		member_name = URLDecoder.decode(member_name);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {	
			
			MemberVO insertInfo = new MemberVO(member_name,member_pwd, member_phone); 
			
			MemberVO checkInfo = memberDao.selectMember(insertInfo);
			
			//가입이 되어있지 않은 경우
			if(checkInfo == null){
				System.out.println("checkinfo null");
				memberDao.insertMember(insertInfo);
				//int retVal = memberDao.insertMember(insertInfo);
				
				resultMap.put(Const.KEY_CODE, Const.CODE_SUCCESS);
				resultMap.put(Const.KEY_MSG, Const.MSG_SUCCESS);
				resultMap.put(Const.KEY_RESULT, memberDao.selectMember(insertInfo));
			}
			//가입이 되어있는 경우
			else{
				System.out.println("already join");
				resultMap.put(Const.KEY_CODE,Const.CODE_EXIST_MEMBER);
				resultMap.put(Const.KEY_MSG,Const.MSG_EXIST_MEMBER);
				resultMap.put(Const.KEY_RESULT,checkInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("exceptionMsg >> " +);
			resultMap.put(Const.KEY_CODE,Const.CODE_EXCEPTION);
			resultMap.put(Const.KEY_MSG,e.getMessage());
		}
		
		return gson.toJson(resultMap); 
	}
	
	
	@RequestMapping(value="/member/LoginMember", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String setLogin(
			HttpServletRequest request,
			@RequestParam(value="member_name", required=true) String member_name,
			@RequestParam(value="member_pwd", required=true) String member_pwd,
			@RequestParam(value="member_phone", required=true) String member_phone
			){
		
		member_name = URLDecoder.decode(member_name); 
		
		System.out.println("[member_name] >> " + member_name);
		
		Map<String, Object> resultMap = new HashMap<String, Object>(); 

		MemberVO memberInfo = new MemberVO(member_name, member_pwd, member_phone);
		
		MemberVO resMemberInfo = null; 
		
		try {
			resMemberInfo = memberDao.selectMember(memberInfo);
			
			if(resMemberInfo == null){
				resultMap.put(Const.KEY_CODE, "110"); 
				resultMap.put(Const.KEY_MSG,"no member info"); 
			}else{
				
				HttpSession session = request.getSession(true); 
				session.setAttribute(Const.S_KEY_MEMBER_INFO, resMemberInfo); 

				resultMap.put(Const.KEY_CODE, Const.CODE_SUCCESS);
				resultMap.put(Const.KEY_MSG, Const.MSG_SUCCESS); 
				resultMap.put(Const.KEY_RESULT, resMemberInfo);
			}
			
		} catch (Exception e) {
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, e.getMessage());
		}
		
		
		return gson.toJson(resultMap); 
	}
	
	
	@RequestMapping(value="/member/LoginError", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String loginError(
			@RequestParam(value="login_status", required=true) String status
			){
		System.out.println("[loginStatus] >> " + status);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMsg = null; 
		if(status.equals(Const.CODE_LOG_OFF)){
			errorMsg = "STATUS_LOG_OFF";
		}
		
		resultMap.put(Const.KEY_CODE, status);
		resultMap.put(Const.KEY_MSG, errorMsg);
		
		return gson.toJson(resultMap); 
	}
}
