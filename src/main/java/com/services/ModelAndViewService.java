package com.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Account;

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
			/*
			 * TODO check if the account logged in is a brand manufacturer or customer
			 * TODO add the model of both the account and either bm or customer info
			 */
			Account account = (Account)session.getAttribute(SessionAttributes.ACC);
		}
		
		return mv;
	}
	
}
