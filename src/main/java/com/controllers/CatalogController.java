package com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Catalog;
import com.beans.Product;
import com.dao.impl.CatalogDAOImpl;
import com.dao.impl.ProductDAOImpl;
import com.google.gson.Gson;
import com.services.ModelAndViewService;

@Controller
public class CatalogController {
	
	@Autowired
	private CatalogDAOImpl catalogDAO;
	
	@Autowired
	private ProductDAOImpl productDAO;
	
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
	
	@RequestMapping(value="/catalog/get_products_catalogs", method=RequestMethod.GET)
	public void getProductsOfCatalog(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,String> requestParam) throws IOException{
		int catalog_id = Integer.parseInt(requestParam.get("catalog"));
		List<Product> products = productDAO.getProductsOfCatalog(catalog_id);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(products);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
}
