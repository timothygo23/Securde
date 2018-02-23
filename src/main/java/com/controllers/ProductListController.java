package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductListController {

	@RequestMapping(value="/allproducts", method=RequestMethod.GET)
	public ModelAndView contactPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("product");
		
		return mv;
	}
	
}
