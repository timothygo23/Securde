package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Account;
import com.beans.CartItem;
import com.beans.CartSession;
import com.beans.Customer;
import com.beans.Order;
import com.beans.ProductAvailability;
import com.dao.impl.AccountDAOImpl;
import com.dao.impl.OrderDAOImpl;
import com.dao.impl.ProductAvailabilityDAOImpl;
import com.services.ModelAndViewService;
import com.services.SessionAttributes;
import com.services.impl.AccountServiceImpl;

@Controller
public class OrderController {
	
	@Autowired
	private OrderDAOImpl orderDAO;
	@Autowired
	private AccountDAOImpl accountDAO;
	@Autowired
	private ProductAvailabilityDAOImpl productAvailabilityDAO;
	@Autowired
	private AccountServiceImpl accountService;
	@Autowired
	private ModelAndViewService modelService;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value="/order", method=RequestMethod.GET)
	public ModelAndView orderPage (HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("order");
		
		//check if theres an account logged in
		Customer customerInfo = (Customer) mv.getModel().get("info");
		
		//if cart is empty or account is null redirect to cart page
		if(customerInfo == null){
			response.sendRedirect("home"); //no account logged in
		}else if(customerInfo.getCredit_card_num() == null){
			//go to set up payment details view
			mv.setViewName("setup_payment");
		}else{
			//proceed with order page
			HttpSession session = request.getSession();
			ArrayList<CartItem> cartItems = (ArrayList<CartItem>)session.getAttribute(CartSession.CART_ITEM_LIST);
			
			if(cartItems.size() > 0){
				int totalPrice = 0;
				for(CartItem ci : cartItems){
					totalPrice += ci.getPrice();
				}
				
				mv.addObject("cartItems", cartItems);
				mv.addObject("totalPrice", totalPrice);
				mv.addObject("customerInfo", customerInfo);
				
				String error = (String)session.getAttribute(SessionAttributes.ERROR);
				if(error != null){
					mv.addObject(SessionAttributes.ERROR, error);
					session.removeAttribute(SessionAttributes.ERROR); //remove it after
				}
			}else{
				response.sendRedirect("checkout");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/confirm_order", method=RequestMethod.POST)
	public ModelAndView orderConfirmPage (@RequestParam Map<String, String> requestParams, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("orderConfirmed");
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute(SessionAttributes.ACC);
		String password = requestParams.get("password");
		
		if(!accountService.isLockedout(account.getEmail())){
			logger.info("Purchase authenticating '{}'", account.getEmail());
			
			Account tempAccount = accountService.logIn(account.getEmail(), password);
			if(tempAccount != null){
				logger.info("Successful purchase authentication '{}'", account.getEmail());
				
				//DO ORDER HERE
				ArrayList<CartItem> cartItems = (ArrayList<CartItem>)session.getAttribute(CartSession.CART_ITEM_LIST);
				Customer customerInfo = (Customer)session.getAttribute(SessionAttributes.CUSTOMER_INFO);
				
				int cartId = customerInfo.getCart_num();
				Date purchaseDate = new Date();
				Date arrivalDate = new Date();
				Calendar purchaseCal = Calendar.getInstance();
				purchaseCal.setTime(purchaseDate);
			    Calendar arrivalCal = Calendar.getInstance();  
			    arrivalCal.setTime(arrivalDate);  
			    arrivalCal.add(Calendar.DATE, 7);
			    
			    logger.info("Creating order for cart '{}'", cartId);
			    
			    Order order = new Order();
			    order.setCart_id(cartId);
			    order.setPurchase_date(purchaseCal);
			    order.setArrival_date(arrivalCal);
			    
			    //increment cart
			    customerInfo.setCart_num(customerInfo.getCart_num() + 1);
			    accountDAO.updateCustomerInfo(customerInfo);
			    
			    //update product availability
			    for(CartItem ci : cartItems){
			    	/*List<ProductAvailability> pas = productAvailabilityDAO.getProductAvailability(ci.getProduct().getProduct_id());
			    	
			    	for(ProductAvailability pa : pas){
			    		if(pa.getSize().equals(ci.getSize())){
			    			System.out.println(pa.getQuantity() - ci.getQty());
			    			pa.setQuantity(pa.getQuantity() );
			    			productAvailabilityDAO.edit(pa);
			    			break;
			    		}
			    	}*/
			    }
			    
			    orderDAO.add(order);
			    
			    logger.info("Updating db...");
			    
			    logger.info("Order # '{}' successfully placed.", order.getOrder_id());
				
			    mv.addObject("orderNum", order.getOrder_id());
			    session.removeAttribute(CartSession.CART_ITEM_LIST);
			}else{
				logger.info("Failed purchase authentication attempt '{}'", account.getEmail());
				accountService.failedLogin(account.getEmail());
				session.setAttribute(SessionAttributes.ERROR, "Incorrect password.");
				response.sendRedirect("order");
			}
		}else{
			//log user out fail close
			logger.info("Purchase Authentication error Account '{}' is locked out, maximum number of login attempts exceeded", account.getEmail());
			accountService.logOut(request);
			response.sendRedirect("home");
		}
		
		return mv;
	}

	public RedirectView getOrder (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		//List<Product> products = productDAO.getAllProducts();
		logger.info("Requesting order");
		Order order = orderDAO.getOrder(Integer.parseInt(request.getParameter("order_id")));
		logger.info("{}", order);
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("order", order);
		
		return rv;
	}
	
	@RequestMapping(value="/override_get_order", method=RequestMethod.GET)
	public RedirectView editProductRedirect (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		RedirectView rv = getOrder(request, redirectAttribute);
		rv.setUrl("override_order_redirect");
		
		return rv;
	}
	
	@RequestMapping(value="/override_order", method=RequestMethod.GET)
	public ModelAndView overrideOrders (HttpServletRequest request) {
		logger.info("Requesting orders");
		List<Order> orders = orderDAO.getAllOrders();
		
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("orders", orders);
		mv.setViewName("overrideOrders");
		
		logger.info("Redirecting to override orders page");
		
		return mv;
	}
	
	@RequestMapping(value="/override_order_redirect", method=RequestMethod.GET)
	public ModelAndView overrideOrdersRedirect (HttpServletRequest request, @ModelAttribute("order") Order order) {
		logger.info("Requesting orders");
		List<Order> orders = orderDAO.getAllOrders();
		
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("orders", orders);
		mv.addObject("order", order);
		mv.setViewName("overrideOrders");
		
		logger.info("Redirecting to override orders page");
		
		return mv;
	}
	
	@RequestMapping(value="/delete_order", method=RequestMethod.POST)
	public RedirectView deleteOrders(@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttribute) {
		logger.info("Deleting orders");
		int order_id = Integer.parseInt(requestParams.get("order_id"));
		logger.info("{}", orderDAO.getOrder(order_id));
		orderDAO.delete(order_id);
		
		RedirectView rv = new RedirectView();
		rv.setUrl("home");
		
		return rv;
	}

}
