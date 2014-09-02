package com.spring.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.spring.vo.RegInfoVO;

public class RegInfoDaoImpl extends SqlMapClientDaoSupport implements RegInfoDao{

	@Override
	public List<RegInfoVO> selectRegInfoList(String cattle_idx) {
		return (List<RegInfoVO>)getSqlMapClientTemplate().queryForList("select_reg_info_list", cattle_idx);
	}

	@Override
	public RegInfoVO selectRegInfoOne(RegInfoVO regInfoVo) {
		return (RegInfoVO)getSqlMapClientTemplate().queryForObject("select_reg_info_one", regInfoVo);
	}

	@Override
	public void deleteRegInfoOne(RegInfoVO regInfoVo) {
		getSqlMapClientTemplate().delete("delete_reg_info_one", regInfoVo);
	}

	@Override
	public void deleteRegInfoList(RegInfoVO regInfoVo) {
		getSqlMapClientTemplate().delete("delete_reg_info_list", regInfoVo); 
	}

	@Override
	public void insertReg(RegInfoVO regInfoVo) {
		getSqlMapClientTemplate().insert("insert_reg_info", regInfoVo); 
		
	}

}
