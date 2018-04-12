package com.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.dao.impl.EmailTokenDAOImpl;
import com.dao.impl.SecretQuestionDAOImpl;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
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
		logger.info("Generating salt...");
		byte[] salt = SaltGenerator.getInstance().generate();
		while(!accountDAO.isSaltUnique(salt)){
			salt = SaltGenerator.getInstance().generate();
		}
		
		logger.info("Creating account");
		//add account to db
		Account account = new Account(email, Hash.hash(password, salt), Account.CUSTOMER, salt);
		Customer customer = new Customer(fName, lName, phoneNum);
		SecretQuestion secretQuestion = new SecretQuestion();
		secretQuestion.setQuestion(question);
		secretQuestion.setAnswer(Hash.hash(answer, salt));
		
		logger.info("Creating Customer {}", account);
		logger.info("Updating database...");
		
		try{
			int account_id = accountDAO.addCustomer(account, customer);
			secretQuestion.setAccount_id(account_id);
			secretQuestionDAO.add(secretQuestion);
			mv.setViewName("successRegister");
			logger.info("Registration success");
		}catch(Exception e){
			mv.setViewName("register");
			mv.addObject("error", "Duplicate Email");
			logger.error(e.getMessage());
			logger.info("Registration failed, duplicate email");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/setup_payment_details", method=RequestMethod.POST)
	public void setupPaymentDetails(@RequestParam Map<String, String> requestParams, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Customer customerInfo = (Customer)session.getAttribute(SessionAttributes.CUSTOMER_INFO);
		
		if(customerInfo != null){
			String address1 = requestParams.get("address1");
			String address2 = requestParams.get("address2");
			String city = requestParams.get("city");
			String province = requestParams.get("province");
			String zipCode = requestParams.get("zipCode");
			String creditCard = requestParams.get("cardNumber");
			
			logger.info("Setting up customer info of '{}", customerInfo.getFirst_name());
			customerInfo.setAddress1(address1);
			customerInfo.setAddress2(address2);
			customerInfo.setCity(city);
			customerInfo.setProvince(province);
			customerInfo.setZip_code(zipCode);
			customerInfo.setCredit_card_num(creditCard);
			
			//update db info
			accountDAO.updateCustomerInfo(customerInfo);
			logger.info("Updating database...");
			
			logger.info("Renewing session for '{}'", customerInfo.getFirst_name());
			
			logger.info("Redirecting to order page");
			response.sendRedirect("order");
		}else{
			response.sendRedirect("home");
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		logger.info("Redirecting to login page");
		
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
		logger.info("Logging out");
		accountService.logOut(request);
		return new RedirectView("login");
	}
	
	@RequestMapping(value="/authentication", method=RequestMethod.POST)
	public RedirectView authenticate(@RequestParam Map<String, String> requestParams, HttpServletRequest request) throws Exception{
		/*
		 * TODO validate inputs
		 */
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		
		RedirectView rv = new RedirectView();
		HttpSession session = request.getSession();
		
		if(!accountService.isLockedout(email)){
			logger.info("Logging in '{}'", email);
			Account account = accountService.logIn(email, password);
			if(account!=null){
				/* session */
				//renew session when logging in.
				logger.info("Renewing session for '{}'", email);
				request.changeSessionId();
				logger.info("{}", account);
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
				logger.info("Failed login attempt with the account '{}'", email);
				accountService.failedLogin(email);
				session.setAttribute(SessionAttributes.ERROR, "Incorrect email or password");
				rv.setUrl("login");
			}
		}else{
			logger.info("Account '{}' is locked out, maximum number of login attempts exceeded", email);
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
			logger.info("Password recovery failed");
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
			
			logger.info("Password email recovery sent to {}" + email);
		}else{
			mv.setViewName("login");
			logger.info("Password recovery failed");
			response.sendRedirect("login");
		}

		return mv;
	}
	
	@RequestMapping(value="/reset_password", method=RequestMethod.GET)
	public ModelAndView resetPasswordPage(@RequestParam Map<String, String> requestParams, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("resetPassword");
		logger.info("Redirecting to reset password page");
		
		/*
		 * TODO: check if token is expired
		 * check if there is a token
		 */
		logger.info("Requesting token");
		String token = requestParams.get("token");
		
		if(token == null){
			logger.info("Non-existent token, redirecting to home page");
			//tried to access page without putting a token
			response.sendRedirect("home");
		}else{
			logger.info("Validating token");
			logger.info("Removing previously expired tokens");
			emailTokenDAO.deleteExpired(); //check if there are expired rows
			EmailToken emailToken = emailTokenDAO.get(token);
			
			if(emailToken == null){
				//invalid/expired token
				logger.info("Invalid/expired token");
				response.sendRedirect("home");
			}else{
				mv.addObject("email", emailToken.getEmail());
			}
		}

		return mv;
	}
	
	@RequestMapping(value="/confirm_reset_pass", method=RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam Map<String, String> requestParams, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView();
		
		String email = requestParams.get("email");
		String password = requestParams.get("newpassword");
		Account account = null;
		
		logger.info("Validating email '{}'", email);
		if(email != null) {
			account = accountDAO.getByEmail(email);
		}
		
		logger.info("Validating account");
		if(account != null){
			logger.info("{}", account);
			//update password
			logger.info("Updating password");
			account.setPassword(Hash.hash(password, account.getSalt()));
			accountDAO.update(account);
			
			//delete token
			logger.info("Deleting token");
			emailTokenDAO.delete(email);
			
			//renew sessionid
			logger.info("Renewing session id");
			request.changeSessionId();
			
			mv.setViewName("successPasswordChange");
		}else{
			logger.info("Invalid account");
			mv.setViewName("login");
			response.sendRedirect("login");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/create_bm", method=RequestMethod.GET)
	public ModelAndView createBrandManufacturer() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createBrandManufacturer");
		logger.info("Redirecting to create brand manufacturer page");
		
		return mv;
	}
	
	@RequestMapping(value="/register_bm", method=RequestMethod.POST)
	public ModelAndView registerBrandManufacturer(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String brand_name = requestParams.get("brand_name");
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		
		logger.info("Generating salt");
		//security part
		byte[] salt = SaltGenerator.getInstance().generate();
		while(!accountDAO.isSaltUnique(salt)){
			salt = SaltGenerator.getInstance().generate();
		}
		
		logger.info("Creating account");
		//add account to db
		Account account = new Account(email, Hash.hash(password, salt), Account.BRAND_MANUFACTURER, salt);
		BrandManufacturer brandManufacturer = new BrandManufacturer(brand_name);
		
		logger.info("Creating Brand Manufacturer {}", account);
		logger.info("Updating database...");
		
		try{
			accountDAO.addBrandManufacturer(account, brandManufacturer);
			mv.setViewName("successRegister");
			logger.info("Registration success");
		}catch(Exception e){
			mv.setViewName("register");
			mv.addObject("error", "Duplicate Email");
			logger.error(e.getMessage());
			logger.info("Registration failed, duplicate email");
		}
		
		return mv;
	}
	
	@RequestMapping(value="/create_admin", method=RequestMethod.GET)
	public ModelAndView createAdmin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createAdmin");
		logger.info("Redirecting to create brand manufacturer page");
		
		return mv;
	}
	
	@RequestMapping(value="/register_admin", method=RequestMethod.POST)
	public ModelAndView registerAdmin(@RequestParam Map<String, String> requestParams, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String email = requestParams.get("email");
		String password = requestParams.get("password");
		
		logger.info("Generating salt");
		//security part
		byte[] salt = SaltGenerator.getInstance().generate();
		while(!accountDAO.isSaltUnique(salt)){
			salt = SaltGenerator.getInstance().generate();
		}
		
		logger.info("Creating account");
		//add account to db
		Account account = new Account(email, Hash.hash(password, salt), Account.ADMIN, salt);
		
		logger.info("{}", account);
		logger.info("Updating database...");
		
		try{
			accountDAO.addAdmin(account);
			mv.setViewName("successRegister");
			logger.info("Registration success");
		}catch(Exception e){
			mv.setViewName("register");
			mv.addObject("error", "Duplicate Email");
			logger.error(e.getMessage());
			logger.info("Registration failed, duplicate email");
		}
		
		return mv;
	}
}
