package com.cattle.member.service;

import java.util.List;

import javax.xml.ws.BindingType;

import org.springframework.beans.factory.annotation.Autowired;

import com.cattle.member.dao.MemberDao;
import com.cattle.member.model.Member;

public class MemberService {
	@Autowired
	private MemberDao memberDao; 
	
	public List<Member> getMemberInfo() throws Exception {
		return (List<Member>)this.memberDao.getMemberInfo();
	}
}
