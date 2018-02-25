	package com.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public RedirectView authenticate(@RequestParam Map<String, String> requestParams){
		/*
		 * TODO validate inputs
		 */
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		
		Account account = loggingService.logIn(email, password);
		if(account!=null){
			/*
			 * TODO create cookies/session to save current user logged in
			 * TODO redirect to home
			 */
			System.out.println("welcome back: " + account.getEmail());
		}else{
			/*
			 * TODO don't redirect, show login failed in the page
			 */
			System.out.println("login failed");
		}
		
		return new RedirectView("home");
	}
	
}
