package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.AccountDao;
import com.services.LoggingService;

@Controller
public class HomeController {
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private LoggingService loggingService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("account", loggingService.logIn("timyooo@gmail.com", "1234"));	
		return mv;
	}
}
