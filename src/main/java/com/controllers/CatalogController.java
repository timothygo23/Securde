package com.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Catalog;
import com.dao.impl.CatalogDAOImpl;
import com.google.gson.Gson;
import com.services.ModelAndViewService;

@Controller
public class CatalogController {
	
	@Autowired
	private CatalogDAOImpl catalogDAO;
	
	@Autowired
	private ModelAndViewService modelService;

	@RequestMapping(value="/catalog", method=RequestMethod.GET)
	public ModelAndView catalogPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("catalog");
		
		return mv;
	}
	
	@RequestMapping(value="/catalog/get_catalogs", method=RequestMethod.GET)
	public void getCatalogs(HttpServletResponse response) throws IOException{
		List<Catalog> catalogs = catalogDAO.getAllCatalogs();
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(catalogs);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
}
