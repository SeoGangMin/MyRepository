package com.spring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.spring.vo.CattleVO;


public class CattleDaoImpl extends SqlMapClientDaoSupport implements CattleDao{

	@Override
	public List<CattleVO> selectCattleList(String member_idx) {
		return (List<CattleVO>)getSqlMapClientTemplate().queryForList("select_list", member_idx);
	}

	@Override
	public CattleVO selectCattleOne(CattleVO cattleVo) {
		return (CattleVO)getSqlMapClientTemplate().queryForObject("select_one", cattleVo);
	}

	@Override
	public List<CattleVO> selectCattleReadyList(Map map) {
		return (List<CattleVO>)getSqlMapClientTemplate().queryForList("select_ready_list", map);
	}
	
	@Override
	public void deleteCattleOne(CattleVO cattleVo) {
		getSqlMapClientTemplate().delete("delete_cattle_one", cattleVo);
	}
	
	@Override
	public void updateOutDate(Map map) {
		getSqlMapClientTemplate().update("update_cattle_out_date", map);
	}
	
	@Override
	public void insertCattleInfo(CattleVO cattleVo) {
		getSqlMapClientTemplate().insert("insert_cattle_info", cattleVo);
	}

	@Override
	public void deleteExpireInfo(String member_idx) {
		getSqlMapClientTemplate().delete("delete_expire_info", member_idx); 
		
	}

	@Override
	public String getMaxCattleIdx() {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("select_max_idx");
	}
}
