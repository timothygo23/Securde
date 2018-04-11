package com.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.beans.EmailToken;
import com.beans.SecretQuestion;
import com.dao.impl.AccountDAOImpl;
import com.dao.impl.AttemptLoginDAOImpl;
import com.dao.impl.EmailTokenDAOImpl;
import com.dao.impl.SecretQuestionDAOImpl;
import com.services.AccountService;
import com.services.EmailService;
import com.services.SessionAttributes;
import com.services.impl.AccountServiceImpl;
import com.utility.Hash;
import com.utility.SaltGenerator;

@Controller
public class AccountController {
	
	@Autowired
	private AccountDAOImpl accountDAO;
	@Autowired
	private SecretQuestionDAOImpl secretQuestionDAO;
	@Autowired
	private AccountServiceImpl accountService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailTokenDAOImpl emailTokenDAO;
	
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
			   lName = requestParams.get("lName"),
			   phoneNum = requestParams.get("phoneNum"),
			   email = requestParams.get("email"),
			   password = requestParams.get("password"),
			   cpassword = requestParams.get("cpassword"),
			   question = requestParams.get("question"),
			   answer = requestParams.get("answer");
		
		//Checking purposes.
//		System.out.println("Name: " + fName + " " + lName);
//		System.out.println("Phone#: " + phoneNum);
//		System.out.println("email: " + email);
//		System.out.println("Password: " + password);
//		System.out.println("Answer: " + answer);
		
		/*boolean wrongInput = false;
		String errorMessage = "";
		
		if(fName.trim().equals("") || lName.trim().equals("") ||
				phoneNum.trim().equals("") || email.trim().equals("") ||
				password.trim().equals("") || cpassword.trim().equals("") || answer.trim().equals("")){
			wrongInput = true;
			errorMessage = "Empty fields.";
		}else{
			//input validation for password and email
			if(!password.equals(cpassword)){
				wrongInput = true;
				errorMessage = "Password does not match.";
			}
		}
		
		if(wrongInput){
			mv.setViewName("register");
			mv.addObject("error", errorMessage);
			return mv;
		}*/
		
		//security part
		byte[] salt = SaltGenerator.getInstance().generate();
		while(!accountDAO.isSaltUnique(salt)){
			salt = SaltGenerator.getInstance().generate();
		}
		
		//add account to db
		Account account = new Account(email, Hash.hash(password, salt), Account.CUSTOMER, salt);
		Customer customer = new Customer(fName, lName, phoneNum);
		SecretQuestion secretQuestion = new SecretQuestion();
		secretQuestion.setQuestion(question);
		secretQuestion.setAnswer(Hash.hash(answer, salt));
		
		try{
			int account_id = accountDAO.addCustomer(account, customer);
			secretQuestion.setAccount_id(account_id);
			secretQuestionDAO.add(secretQuestion);
			mv.setViewName("successRegister");
		}catch(Exception e){
			mv.setViewName("register");
			mv.addObject("error", "Duplicate Email");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		//check if there's an error to be displayed.
		HttpSession session = request.getSession();
		String error = (String)session.getAttribute(SessionAttributes.ERROR);
		if(error != null){
			mv.addObject(SessionAttributes.ERROR, error);
			session.removeAttribute(SessionAttributes.ERROR); //remove it after
		}
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
		HttpSession session = request.getSession();
		
		if(!accountService.isLockedout(email)){
			Account account = accountService.logIn(email, password);
			if(account!=null){
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
				accountService.failedLogin(email);
				session.setAttribute(SessionAttributes.ERROR, "Incorrect email or password");
				rv.setUrl("login");
			}
		}else{
			session.setAttribute(SessionAttributes.ERROR, "Account is locked out.");
			rv.setUrl("login");
		}
		
		return rv;
	}
	
	@RequestMapping(value="/forgot_password", method=RequestMethod.POST)
	public ModelAndView forgotPasswordPage(@RequestParam Map<String, String> requestParams, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forgotPassword");
		
		String email = requestParams.get("email");
		Account account = accountDAO.getByEmail(email);
		SecretQuestion secretQuestion = null;
		
		if(account != null){
			secretQuestion = secretQuestionDAO.getByAccount_ID(account.getAccount_id());
			mv.addObject("question", secretQuestion.getQuestion());
			mv.addObject("email", email);
		}else{
			response.sendRedirect("login");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/secret_question", method=RequestMethod.POST)
	public ModelAndView secretQuestionPage(@RequestParam Map<String, String> requestParams, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		
		String email = requestParams.get("email");
		String answer = requestParams.get("answer");
		Account account = accountDAO.getByEmail(email);
		SecretQuestion secretQuestion = null;
		
		if(account != null)
			secretQuestion = secretQuestionDAO.getByAccount_ID(account.getAccount_id());
		
		if(email == null || answer == null || account == null ||email.trim().equals("") || answer.trim().equals("")){
			mv.setViewName("login");
			response.sendRedirect("login");
		}else if(Hash.compare(secretQuestion.getAnswer(), answer, account.getSalt())){
			//simple email body
			String link = emailService.generateLink(request.getContextPath());
			String token = link.split("=")[1];
			String body = "Reset your password using the link below; <br>" +
						link;
			
			//send email
			emailService.sendMail(email, "Reset Password Request", body);
			
			//save to db
			long minute = 60000;
			EmailToken emailToken = new EmailToken();
			emailToken.setEmail(email);
			emailToken.setToken(token);
			emailToken.setExpirationDate(new Date(new Date().getTime() + minute * 30));
			
			emailTokenDAO.deleteExpired(); //delete expired
			if(emailTokenDAO.doesEmailExist(email)){
				//just edit
				emailTokenDAO.edit(emailToken);
			}else{
				//add
				emailTokenDAO.add(emailToken);
			}
			mv.setViewName("successSecretQuestion");
		}else{
			mv.setViewName("login");
			response.sendRedirect("login");
		}

		return mv;
	}
	
	@RequestMapping(value="/reset_password", method=RequestMethod.GET)
	public ModelAndView resetPasswordPage(@RequestParam Map<String, String> requestParams, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("resetPassword");
		
		/*
		 * TODO: check if token is expired
		 * check if there is a token
		 */
		String token = requestParams.get("token");
		
		if(token == null){
			//tried to access page without putting a token
			response.sendRedirect("home");
		}else{
			emailTokenDAO.deleteExpired(); //check if there are expired rows
			EmailToken emailToken = emailTokenDAO.get(token);
			
			if(emailToken == null){
				//invalid/expired token
				response.sendRedirect("home");
			}else{
				mv.addObject("email", emailToken.getEmail());
			}
		}

		return mv;
	}
	
	@RequestMapping(value="/confirm_reset_pass", method=RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam Map<String, String> requestParams, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		
		String email = requestParams.get("email");
		String password = requestParams.get("newpassword");
		Account account = null;
		
		if(email != null)
			account = accountDAO.getByEmail(email);
		
		if(account != null){
			//update password
			account.setPassword(Hash.hash(password, account.getSalt()));
			accountDAO.update(account);
			
			//delete token
			emailTokenDAO.delete(email);
			
			mv.setViewName("successPasswordChange");
		}else{
			mv.setViewName("login");
			response.sendRedirect("login");
		}
		
		return mv;
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
		
		try{
			accountDAO.addBrandManufacturer(account, brandManufacturer);
			mv.setViewName("successRegister");
		}catch(Exception e){
			mv.setViewName("register");
			mv.addObject("error", "Duplicate Email");
		}
		
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
		Account account = new Account(email, Hash.hash(password, salt), Account.ADMIN, salt);
		
		try{
			accountDAO.addAdmin(account);
			mv.setViewName("successRegister");
		}catch(Exception e){
			mv.setViewName("register");
			mv.addObject("error", "Duplicate Email");
		}
		
		return mv;
	}
}
