package com.spring.dao;

import java.util.List;
import java.util.Map;

import com.spring.vo.CattleVO;


public interface CattleDao {

	//tb_cattle_info table rows
	public List<CattleVO> selectCattleList(String member_idx);
	
	//tb_cattle_list table row
	public CattleVO selectCattleOne(CattleVO cattleVo);
	
	//출산날짜에서 세팅된 날짜 만큼의 rows 반환
	public List<CattleVO> selectCattleReadyList(Map map);
	
	//delete row
	public void deleteCattleOne(CattleVO cattleVo);
	
	//출산날짜가 만료되면 삭제
	public void deleteExpireInfo(String member_idx);
	
	//출산날짜 업데이트
	public void updateOutDate(Map map);
	
	//cattle_info 추가
	public void insertCattleInfo(CattleVO cattleVo);
	
	//cattle max idx
	public String getMaxCattleIdx(); 
}
