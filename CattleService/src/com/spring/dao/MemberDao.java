package com.spring.dao;

import com.spring.vo.MemberVO;

public interface MemberDao {
	public MemberVO selectMember(MemberVO memberVo);
	public void insertMember(MemberVO memberVo);
	/*public int updateMember(MemberVO memberVo);
	public int deleteMember(String memberIdx);*/
}
