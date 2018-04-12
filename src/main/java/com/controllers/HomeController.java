package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.impl.AccountDAOImpl;
import com.services.ModelAndViewService;

@Controller
public class HomeController {
	
	@Autowired
	private AccountDAOImpl accountDao;
	
	@Autowired
	private ModelAndViewService modelService;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("index");
		logger.info("Redirecting to home page");
		
		return mv;
	}

}
