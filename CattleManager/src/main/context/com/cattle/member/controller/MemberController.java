package com.cattle.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cattle.member.model.Member;
import com.cattle.member.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService; 
	
	
	@RequestMapping(value="/list")
	@ResponseBody
	public String getMemberList(){
		List<Member> list = null;
		try {
			list = this.memberService.getMemberInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("[list] >> " + list.size());
		
		return null; 
	}
}
