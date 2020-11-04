package com.tampro.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tampro.dto.UserDTO;
import com.tampro.service.UserService;
import com.tampro.utils.Constant;

public class FilterAdmin  implements HandlerInterceptor{

	@Autowired
	UserService userService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session  =request.getSession();
		UserDTO userDTO = (UserDTO) session.getAttribute(Constant.USER_INFO);
		if(userDTO.getRole() != Constant.ROLE_ADMIN) {
			response.sendRedirect(request.getContextPath() + "/access-denied");
			return false;
		}
		
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
