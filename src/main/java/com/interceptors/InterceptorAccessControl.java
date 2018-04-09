package com.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Account;
import com.services.SessionAttributes;
import com.utility.RestrictedPages;

/**
 * Access control (Authorization) handler.
 * 
 */
public class InterceptorAccessControl implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, 
			                 HttpServletResponse response,
			                 Object handler) {
		
		
		HttpSession session = request.getSession();
		Account account;
		String path = request.getServletPath();
		
		account = identifyUser(session);
		
		//if account null, dont do anything.
		try {
			//perform access control.
			//any resources path will be ignored.

	        if(!path.matches(".*resources.*")) {
				System.out.println(path);
				//restricted
				if(RestrictedPages.isRestricted(account.getAccount_type(), path)) {
					
					/*TODO Perform redirection (PAGE NOT FOUND)*/
					
					return false;
				}
				//not restricted
				else {
					return true;
				}
			
			}
	        
	        else {
				System.out.println("IGNORED: " + path);
	        }
		}
		
		
		catch(Exception e) {}
		
		//TODO if no user exist given the type, do not proceed with other handlers. redirect to home (gonna do this later)
		return true;

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
