package com.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Logging handler. (Logging can be done here)
 * 
 */
public class InterceptorLogging implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, 
			                 HttpServletResponse response,
			                 Object handler) {
		
		//System.out.println("Pre-handle: (InterceptorLogging)");
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request, 
			               HttpServletResponse response,
			               Object handler,
			               ModelAndView modelAndView) throws Exception {
		//System.out.println("Post-handle: (InterceptorLogging)");
		
	}
	
	public void afterCompletion(HttpServletRequest request, 
			                    HttpServletResponse response, 
			                    Object handler, 
			                    Exception ex) {
		//System.out.println("After completion: (InterceptorLogging)");
	}
	
	

}
