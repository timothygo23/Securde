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
import com.json.CatalogJSON;
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
		
		/*
		 * cause we're reusing the catalog.jsp for search results,
		 * it needs to know what products to get.
		 */
		mv.addObject("request", "catalog");
		
		return mv;
	}
	
	@RequestMapping(value="/catalog/get_catalogs", method=RequestMethod.GET)
	public void getCatalogs(HttpServletResponse response) throws IOException{
		//gets data from the db
		List<Catalog> catalogs = catalogDAO.getAllCatalogs();
		
		//converts the list into a json and sends it as a response
		Gson gson = new Gson();
		String jsonString = gson.toJson(catalogs);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value="/catalog/get_products_catalogs", method=RequestMethod.GET)
	public void getProductsOfCatalog(HttpServletResponse response, @RequestParam Map<String,String> requestParam) throws IOException{
		int catalog_id = Integer.parseInt(requestParam.get("catalog"));
		
		//gets data from db
		Catalog catalog = catalogDAO.getCatalog(catalog_id);
		List<Product> products = productDAO.getProductsOfCatalog(catalog_id);
		
		//puts data in a custom class
		CatalogJSON cJson = new CatalogJSON();
		cJson.setCatalogName(catalog.getCatalog_name());
		cJson.setProducts(products);
		
		//converts that class into a json and sends it as a response
		Gson gson = new Gson();
		String jsonString = gson.toJson(cJson);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView searchPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("catalog");
		
		/*
		 * cause we're reusing the catalog.jsp for search results,
		 * it needs to know what products to get.
		 */
		mv.addObject("request", "search");
		
		return mv;	
	}

	@RequestMapping(value="/search/get_searched_products", method=RequestMethod.GET)
	public void getSearchedProducts(HttpServletResponse response, @RequestParam Map<String,String> requestParam) throws IOException{
		String searchKey = requestParam.get("search_key");
		
		//get from db
		List<Product> products = productDAO.getSearched(searchKey);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(products);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
}
