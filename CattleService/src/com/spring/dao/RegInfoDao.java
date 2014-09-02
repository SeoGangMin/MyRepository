package com.spring.dao;

import java.util.List;

import com.spring.vo.RegInfoVO;

public interface RegInfoDao {
	
	//수정날짜 리스트
	public List<RegInfoVO> selectRegInfoList(String cattle_idx);
	
	//수정날짜
	public RegInfoVO selectRegInfoOne(RegInfoVO regInfoVo);
	
	//수정날짜 단일 삭제
	public void deleteRegInfoOne(RegInfoVO regInfoVo);
	
	//수정날짜 다중 삭제
	public void deleteRegInfoList(RegInfoVO regInfoVo); 
	
	//수정정보 등록
	public void insertReg(RegInfoVO regVo);
	
}
