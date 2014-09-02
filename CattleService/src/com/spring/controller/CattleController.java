package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.common.Const;
import com.spring.common.Utils;
import com.spring.dao.CattleDao;
import com.spring.dao.RegInfoDao;
import com.spring.vo.CattleVO;
import com.spring.vo.MemberVO;
import com.spring.vo.RegInfoVO;

@Controller
public class CattleController{
	
	@Autowired
	private CattleDao cattleDao;
	
	@Autowired
	private RegInfoDao regInfoDao; 
	
	@Autowired
	private Gson gson;
	
	@ResponseBody
	@RequestMapping(value="/cattle/GetInfoList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String getCattleInfoList(
				HttpServletRequest req
			) {
		MemberVO memberInfo = (MemberVO)req.getSession().getAttribute("member_info");
		String member_idx = memberInfo.getMember_idx(); 
		
		System.out.println("[member_idx] >> "+ member_idx);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<CattleVO> list = null;
		try {
			list = cattleDao.selectCattleList(member_idx);
			resultMap.put(Const.KEY_CODE,Const.CODE_SUCCESS);
			resultMap.put(Const.KEY_MSG,Const.MSG_SUCCESS);
			resultMap.put(Const.KEY_RESULT,list); 
		} catch (Exception e) {
			resultMap.put(Const.KEY_CODE,Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG,e.getMessage());
		}
		
		return gson.toJson(resultMap); 
	}
	
	@ResponseBody
	@RequestMapping(value="/cattle/GetInfoOne", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String getCattleInfoOne(
			HttpServletRequest req,
			@RequestParam(value="cattle_idx", required=true) String cattle_idx
			){
		
		MemberVO memberInfo = (MemberVO)req.getSession().getAttribute("member_info");
		String member_idx = memberInfo.getMember_idx();
		
		Map<String, Object> resultMap = new HashMap<String, Object>(); 

		CattleVO cattleVo = new CattleVO(); 
		cattleVo.setMember_idx(member_idx); 
		cattleVo.setCattle_idx(cattle_idx); 
		CattleVO resCattleVo = null;
		List<RegInfoVO> regInfoList = null; 
		
		try {
			resCattleVo = cattleDao.selectCattleOne(cattleVo);
			System.out.println("[cattle_idx] >> " + resCattleVo.getCattle_idx());
			regInfoList = regInfoDao.selectRegInfoList(resCattleVo.getCattle_idx());
			
			resCattleVo.setRegDateList(regInfoList);
			resultMap.put(Const.KEY_CODE, "000");
			resultMap.put(Const.KEY_MSG, "message");
			resultMap.put(Const.KEY_RESULT, resCattleVo);
			
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, e.getMessage());
		} 
		
		return gson.toJson(resultMap); 
	}
	
	
	@ResponseBody
	@RequestMapping(value="/cattle/ReadyInfoList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String getCattleReadyList(
			HttpServletRequest req,
			@RequestParam(value="cattle_idx", required=true) String cattle_idx,
			@RequestParam(value="remain_date", required=true) String remain_date
			){

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		MemberVO memberInfo = (MemberVO)req.getSession().getAttribute("member_info");
		String member_idx = memberInfo.getMember_idx();
		
		Map map = new HashMap();
		map.put("member_idx", member_idx);
		map.put("cattle_idx", cattle_idx);
		map.put("remain_date", remain_date);
		
		List<CattleVO> list = null; 
		try {
			list = cattleDao.selectCattleReadyList(map);
			resultMap.put(Const.KEY_CODE, Const.CODE_SUCCESS); 
			resultMap.put(Const.KEY_MSG, Const.MSG_SUCCESS); 
			resultMap.put(Const.KEY_RESULT, list); 
		} catch (Exception e) {
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, e.getMessage());
		} 
		
		return gson.toJson(resultMap); 
	}
	
	
	@ResponseBody
	@RequestMapping(value="/cattle/DeleteInfoOne", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String deleteCattleOne(
			HttpServletRequest req,
			@RequestParam(value="cattle_idx", required=true) String cattle_idx	
			){
		
		MemberVO memberInfo = (MemberVO)req.getSession().getAttribute("member_info");
		String member_idx = memberInfo.getMember_idx();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		CattleVO cattleVo = new CattleVO(); 
		cattleVo.setMember_idx(member_idx);
		cattleVo.setCattle_idx(cattle_idx);
		
		
		try {
			cattleDao.deleteCattleOne(cattleVo);
			resultMap.put(Const.KEY_CODE, Const.CODE_SUCCESS); 
			resultMap.put(Const.KEY_MSG, Const.MSG_SUCCESS); 
		} catch (Exception e) {
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, e.getMessage());
		} 
		
		return gson.toJson(resultMap); 
	}
	
	@ResponseBody
	@RequestMapping(value="/cttle/DeleteExpiredInfo", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String deleteExpiredInfo(
			HttpServletRequest req
			){
		MemberVO memberInfo = (MemberVO)req.getSession().getAttribute("member_info");
		String member_idx = memberInfo.getMember_idx();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			cattleDao.deleteExpireInfo(member_idx);
			resultMap.put(Const.KEY_CODE, Const.CODE_SUCCESS); 
			resultMap.put(Const.KEY_MSG, Const.MSG_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, e.getMessage());
		}
		
		return gson.toJson(resultMap); 
	}
	
	@ResponseBody
	@RequestMapping(value="/cattle/UpdateOutDate", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String updateCattleOutDate(
			HttpServletRequest req,
			@RequestParam(value="cattle_idx", required=true) String cattle_idx,
			@RequestParam(value="update_value", required=true) String update_value
			){
		
		MemberVO memberInfo = (MemberVO)req.getSession().getAttribute("member_info");
		String member_idx = memberInfo.getMember_idx();
		
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		
		Map map = new HashMap();
		map.put("member_idx", member_idx);
		map.put("cattle_idx", cattle_idx);
		map.put("update_value", update_value);
		
		try {
			cattleDao.updateOutDate(map);
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, Const.MSG_SUCCESS); 
		} catch (Exception e) {
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, e.getMessage());
		} 
		
		return gson.toJson(resultMap); 
	}
	
	@ResponseBody
	@RequestMapping(value="/cattle/InsertCattleInfo", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String insertCattleInfo(
			HttpServletRequest req,
			@RequestParam(value="cattle_no", required=true) String cattle_no,
			@RequestParam(value="reg_date", required=true) String reg_date,
			@RequestParam(value="noti_date", required=true) String noti_date
			){
		MemberVO memberInfo = (MemberVO)req.getSession().getAttribute("member_info");
		String member_idx = memberInfo.getMember_idx();
		
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		
		CattleVO cattleVo = new CattleVO(); 
		cattleVo.setCattle_no(cattle_no);
		cattleVo.setCattle_noti_yn("Y");
		cattleVo.setMember_idx(member_idx);
		
		int deliveryDateTerm = 285; 
		int notiDateTerm = deliveryDateTerm - Integer.parseInt(noti_date);
		
		String cattle_out_date = Utils.setOperationDate("plus", reg_date, deliveryDateTerm);
		String cattle_noti_date = Utils.setOperationDate("plus", reg_date, notiDateTerm); 
		cattleVo.setCattle_noti_date(cattle_noti_date);
		cattleVo.setCattle_out_date(cattle_out_date);
		
		try {
			cattleDao.insertCattleInfo(cattleVo);
			
			RegInfoVO regInfoVo = new RegInfoVO(); 
			regInfoVo.setCattle_idx(cattleDao.getMaxCattleIdx());
			regInfoVo.setReg_date(reg_date);
			regInfoDao.insertReg(regInfoVo);
			
			resultMap.put(Const.KEY_CODE, Const.CODE_SUCCESS); 
			resultMap.put(Const.KEY_MSG, Const.MSG_SUCCESS); 
		} catch (Exception e) {
			resultMap.put(Const.KEY_CODE, Const.CODE_EXCEPTION); 
			resultMap.put(Const.KEY_MSG, e.getMessage());
		} 
		
		return gson.toJson(resultMap); 
	}
}
