package com.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Account;
import com.dao.impl.CartDAOImpl;
import com.services.SessionAttributes;
import com.utility.Restriction;

/**
 * Access control (Authorization) handler.
 * 
 */
public class InterceptorAccessControl implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, 
			                 HttpServletResponse response,
			                 Object handler) throws IOException {

		HttpSession session = request.getSession();
		String path = request.getServletPath();
		String contextPath = request.getContextPath();
		Restriction restriction = new Restriction();
		Account account;
		int rCode;
		
		account = identifyUser(session);

    	//System.out.println(path);
		//if path is resources, don't do anything. just proceed
		if(!path.matches(".*resources.*")) {

			//disable backing to secure page after loging out.
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        response.setDateHeader("Expires", 0);
	        
			//if account null, dont do anything. (no current user)
			try {
				//perform access control.
				
				//restricted
				rCode = restriction.isRoleRestricted(account.getAccount_type(), path);
				if(rCode == Restriction.YES_ERROR) {
					response.sendRedirect(contextPath + "/404");
					return false;
				}
				else if(rCode == Restriction.YES_HOME) {
					response.sendRedirect(contextPath + "/home");
					return false;
				}
				//not restricted
				else {
					return true;
				}
				
			}catch(Exception e) {}
			
			//-1 account type indicating it is PUBLIC or no role.
			rCode = restriction.isRoleRestricted(-1, path);
			if(rCode == Restriction.YES_ERROR) {
				response.sendRedirect(contextPath + "/404");
				return false;
			}
			else {
				return true;
			}
			
		}
		
		else {
			return true;
		}

	}
	
	public void postHandle(HttpServletRequest request, 
			               HttpServletResponse response,
			               Object handler,
			               ModelAndView modelAndView) throws Exception {
		//System.out.println("Post-handle: (AcessControl)");
		
	}
	
	public void afterCompletion(HttpServletRequest request, 
			                    HttpServletResponse response, 
			                    Object handler, 
			                    Exception ex) {
		//System.out.println("After completion: (AcessControl)");
	}
	
	
	/**
	 * Identifies the user based from the gathered session attribute.
	 * @return the account.
	 */
	private Account identifyUser(HttpSession session) {
		
		Account account = null;
		
		//check if admin
		try {
			account = (Account) session.getAttribute(SessionAttributes.ACC);
		}
		catch(Exception e1) {
			
			//check if bm
			try {
				account = (Account) session.getAttribute(SessionAttributes.BM_INFO);
			}
			catch(Exception e2) {
				
				//check if user
				try {
					account = (Account) session.getAttribute(SessionAttributes.CUSTOMER_INFO);
				}
				catch(Exception e3) {}
			}
		}
		
		return account;	
	}
	

}
