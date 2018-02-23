package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckoutController {

	@RequestMapping(value="/checkout", method=RequestMethod.GET)
	public ModelAndView checkoutPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("checkout");
		
		return mv;
	}
	
}
