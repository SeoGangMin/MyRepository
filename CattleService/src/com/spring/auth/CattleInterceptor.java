package com.spring.auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.spring.common.Const;
import com.spring.dao.MemberDao;
import com.spring.vo.MemberVO;

public class CattleInterceptor implements HandlerInterceptor{
	
	@Autowired
	private MemberDao memberDao; 

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler, ModelAndView mView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle");
		MemberVO member = (MemberVO)req.getSession().getAttribute("member_info");
		
		if(member == null){
			System.out.println("need Login!");
			//String redirectUrl = req.getContextPath()+"/api/member/LoginError";
			//res.sendRedirect(redirectUrl+"?login_status="+Const.CODE_LOG_OFF);
			res.sendError(404, "memberLogOff");
			return false; 
		}
		
		return true; 
	}

}
