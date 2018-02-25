package com.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Account;
import com.beans.BrandManufacturer;
import com.beans.Customer;

@Component
public class ModelAndViewService {

	/**
	 * This function is used as a way to check if there is already a user logged in and add it
	 * to the model and view object.
	 * @param request used to get the session/cookies
	 * @return returns a ModelAndView that already contains user info if there's a user logged in 
	 */
	public ModelAndView createModelAndView(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(session.getAttribute(SessionAttributes.ACC) == null){
			/*
			 * TODO check cookies
			 */
		}else{ //account in the session exists
			Account account = (Account)session.getAttribute(SessionAttributes.ACC);
			if(account.getAccount_type() == Account.BRAND_MANUFACTURER){
				mv.addObject("info", (BrandManufacturer)session.getAttribute(SessionAttributes.BM_INFO));
			}else if(account.getAccount_type() == Account.CUSTOMER){
				mv.addObject("info", (Customer)session.getAttribute(SessionAttributes.CUSTOMER_INFO));
			}
			mv.addObject("account", account);
		}
		
		return mv;
	}
	
}
