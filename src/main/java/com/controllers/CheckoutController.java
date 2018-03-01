package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.CartSession;
import com.services.ModelAndViewService;

@Controller
public class CheckoutController {

	@Autowired
	private ModelAndViewService modelService;
	
	@RequestMapping(value="/checkout", method=RequestMethod.GET)
	public ModelAndView checkoutPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("checkout");
		
		return mv;
	}
}
