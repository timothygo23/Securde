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
		
		RedirectView rv = new RedirectView();
		
		Account account = loggingService.logIn(email, password);
		if(account!=null){
			HttpSession session = request.getSession();
			/* TODO cookies */
			
			/* session */
			session.setAttribute(SessionAttributes.ACC, account);	
			if(account.getAccount_type() == Account.ADMIN){
				//redirect to admin page
			}if(account.getAccount_type() == Account.BRAND_MANUFACTURER){
				session.setAttribute(SessionAttributes.BM_INFO, accountDao.getBrandManufacturer(account.getAccount_id()));
				//redirect to brandmanufacturer page
			}else if(account.getAccount_type() == Account.CUSTOMER){
				session.setAttribute(SessionAttributes.CUSTOMER_INFO, accountDao.getCustomer(account.getAccount_id()));
				rv.setUrl("home");
			}
		}else{
			/*
			 * TODO don't redirect, show login failed in the page
			 */
			System.out.println("login failed");
			rv.setUrl("login");
		}
		
		return rv;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		loggingService.logOut(request);
		
		return mv;
	}
	
}
