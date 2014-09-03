package com.cattle.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cattle.member.model.Member;

public class CattleInterceptor implements HandlerInterceptor{
	
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
		Member member = (Member)req.getSession().getAttribute("member_info");
		
		if(member == null){
			System.out.println("need Login!");
			//String redirectUrl = req.getContextPath()+"/api/member/LoginError";
			//res.sendRedirect(redirectUrl+"?login_status="+Const.CODE_LOG_OFF);
			//res.sendError(404, "memberLogOff");
			return false; 
		}
		
		return true; 
	}

}
