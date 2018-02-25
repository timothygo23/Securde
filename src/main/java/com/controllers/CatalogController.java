package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.services.ModelAndViewService;

@Controller
public class CatalogController {
	
	@Autowired
	private ModelAndViewService modelService;

	@RequestMapping(value="/catalog", method=RequestMethod.GET)
	public ModelAndView catalogPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("catalog");
		
		return mv;
	}
	
}
