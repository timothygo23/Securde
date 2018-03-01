package com.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Order;
import com.dao.impl.OrderDAOImpl;
import com.services.ModelAndViewService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderDAOImpl orderDAO;
	@Autowired
	private ModelAndViewService modelService;
	
	public RedirectView getOrder (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		//List<Product> products = productDAO.getAllProducts();
		Order order = orderDAO.getOrder(Integer.parseInt(request.getParameter("order_id")));
		
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
		List<Order> orders = orderDAO.getAllOrders();
		
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("orders", orders);
		mv.setViewName("overrideOrders");
		
		return mv;
	}
	
	@RequestMapping(value="/override_order_redirect", method=RequestMethod.GET)
	public ModelAndView overrideOrdersRedirect (HttpServletRequest request, @ModelAttribute("order") Order order) {
		List<Order> orders = orderDAO.getAllOrders();
		
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("orders", orders);
		mv.addObject("order", order);
		mv.setViewName("overrideOrders");
		
		return mv;
	}
	
	@RequestMapping(value="/delete_order", method=RequestMethod.POST)
	public RedirectView deleteOrders(@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttribute) {
		
		int order_id = Integer.parseInt(requestParams.get("order_id"));
		
		orderDAO.delete(order_id);
		
		RedirectView rv = new RedirectView();
		rv.setUrl("home");
		
		return rv;
	}

}
