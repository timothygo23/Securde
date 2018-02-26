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
import com.beans.Customer;
import com.dao.impl.AccountDAOImpl;
import com.services.AccountService;
import com.services.SessionAttributes;

@Controller
public class AccountController {
	
	@Autowired
	private AccountDAOImpl accountDAO;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		
		return mv;
	}
	
	@RequestMapping(value="/registerAccount", method=RequestMethod.POST)
	public ModelAndView registerAccount(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String fName = requestParams.get("fName"),
			   lName = requestParams.get("LName"),
			   phoneNum = requestParams.get("phoneNum"),
			   email = requestParams.get("email"),
			   password = requestParams.get("password"),
			  //question = requestParams.get("question"),
			   answer = requestParams.get("answer");
		
		//Checking purposes.
//		System.out.println("Name: " + fName + " " + lName);
//		System.out.println("Phone#: " + phoneNum);
//		System.out.println("email: " + email);
//		System.out.println("Password: " + password);
//		System.out.println("Answer: " + answer);
		
		//add account to db
		Account account = new Account(email, password, Account.CUSTOMER);
		Customer customer = new Customer(fName, lName, phoneNum);
		//registerService.addAccount(account, customer);
		
		mv.setViewName("successRegister");
		return mv;
	}
	
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
		
		Account account = accountService.logIn(email, password);
		if(account!=null){
			HttpSession session = request.getSession();
			/* TODO cookies */
			
			/* session */
			session.setAttribute(SessionAttributes.ACC, account);	
			if(account.getAccount_type() == Account.ADMIN){
				//redirect to admin page
			}if(account.getAccount_type() == Account.BRAND_MANUFACTURER){
				session.setAttribute(SessionAttributes.BM_INFO, accountDAO.getBrandManufacturer(account.getAccount_id()));
				//redirect to brandmanufacturer page
			}else if(account.getAccount_type() == Account.CUSTOMER){
				session.setAttribute(SessionAttributes.CUSTOMER_INFO, accountDAO.getCustomer(account.getAccount_id()));
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
}