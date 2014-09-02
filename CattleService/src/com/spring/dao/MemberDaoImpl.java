package com.spring.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.spring.vo.MemberVO;

public class MemberDaoImpl extends SqlMapClientDaoSupport implements MemberDao{

	@Override
	public void insertMember(MemberVO memberVo) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("insert_member",memberVo);
		//return (Integer)getSqlMapClientTemplate().insert("insert_member",memberVo);
	}

	/*@Override
	public int updateMember(MemberVO memberVo) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().update("update_member", memberVo);
	}

	@Override
	public int deleteMember(String memberIdx) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().delete("delete_member", memberIdx);
	}*/

	@Override
	public MemberVO selectMember(MemberVO memberVo) {
		// TODO Auto-generated method stub
		return (MemberVO)getSqlMapClientTemplate().queryForObject("select_member", memberVo);
	}

	

}
