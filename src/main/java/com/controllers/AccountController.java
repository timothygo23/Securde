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
import com.beans.BrandManufacturer;
import com.beans.Customer;
import com.dao.impl.AccountDAOImpl;
import com.services.AccountService;
import com.services.SessionAttributes;
import com.utility.Hash;
import com.utility.SaltGenerator;

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
		
		//security part
		byte[] salt = SaltGenerator.getInstance().generate();
		while(!accountDAO.isSaltUnique(salt)){
			salt = SaltGenerator.getInstance().generate();
		}
		
		//add account to db
		Account account = new Account(email, Hash.hash(password, salt), Account.CUSTOMER, salt);
		Customer customer = new Customer(fName, lName, phoneNum);
		accountService.addAccount(account, customer);
		
		mv.setViewName("successRegister");
		return mv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public RedirectView logOut(HttpServletRequest request){
		accountService.logOut(request);
		return new RedirectView("login");
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
				rv.setUrl("home");
			}if(account.getAccount_type() == Account.BRAND_MANUFACTURER){
				session.setAttribute(SessionAttributes.BM_INFO, accountDAO.getBrandManufacturer(account.getAccount_id()));
				rv.setUrl("home");
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
	
	@RequestMapping(value="/create_bm", method=RequestMethod.GET)
	public ModelAndView createBrandManufacturer() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createBrandManufacturer");
		
		return mv;
	}
	
	@RequestMapping(value="/register_bm", method=RequestMethod.POST)
	public ModelAndView registerBrandManufacturer(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String brand_name = requestParams.get("brand_name");
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		
		//security part
		byte[] salt = SaltGenerator.getInstance().generate();
		while(!accountDAO.isSaltUnique(salt)){
			salt = SaltGenerator.getInstance().generate();
		}
		
		//add account to db
		Account account = new Account(email, Hash.hash(password, salt), Account.BRAND_MANUFACTURER, salt);
		BrandManufacturer brandManufacturer = new BrandManufacturer(brand_name);
		accountDAO.addBrandManufacturer(account, brandManufacturer);
		
		mv.setViewName("successRegister");
		return mv;
	}
	
	@RequestMapping(value="/create_admin", method=RequestMethod.GET)
	public ModelAndView createAdmin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createAdmin");
		
		return mv;
	}
	
	@RequestMapping(value="/register_admin", method=RequestMethod.POST)
	public ModelAndView registerAdmin(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		
		//security part
		byte[] salt = SaltGenerator.getInstance().generate();
		while(!accountDAO.isSaltUnique(salt)){
			salt = SaltGenerator.getInstance().generate();
		}
		
		//add account to db
		Account account = new Account(email, Hash.hash(password, salt), Account.BRAND_MANUFACTURER, salt);
		accountDAO.addAdmin(account);
		
		mv.setViewName("successRegister");
		return mv;
	}
}
