	package com.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Account;
import com.dao.AccountDao;
import com.services.LoggingService;
import com.services.SessionAttributes;

@Controller
public class LoginController {
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private LoggingService loggingService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value="/authentication", method=RequestMethod.POST)
	public RedirectView authenticate(@RequestParam Map<String, String> requestParams, HttpServletRequest request){
		/*
		 * TODO validate inputs
		 */
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		
		Account account = loggingService.logIn(email, password);
		if(account!=null){
			HttpSession session = request.getSession();
			
			/*
			 * TODO check user type of the account; if brand manufacturer, get info using accountDao
			 * if customer, get info using accountDao
			 * if admin, just use the account
			 * TODO create cookies/session to save account (and either bm info or customer info) logged in
			 * TODO redirect to home
			 */
			
			session.setAttribute(SessionAttributes.ACC, account);
		}else{
			/*
			 * TODO don't redirect, show login failed in the page
			 */
			System.out.println("login failed");
		}
		
		return new RedirectView("home");
	}
	
}
