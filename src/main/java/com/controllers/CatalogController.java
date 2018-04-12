package com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beans.BrandManufacturer;
import com.beans.Catalog;
import com.beans.Product;
import com.dao.impl.AccountDAOImpl;
import com.dao.impl.CatalogDAOImpl;
import com.dao.impl.ProductDAOImpl;
import com.google.gson.Gson;
import com.json.CatalogJSON;
import com.json.Filter;
import com.services.ModelAndViewService;

@Controller
public class CatalogController {
	
	@Autowired
	private AccountDAOImpl accountDAO;
	@Autowired
	private CatalogDAOImpl catalogDAO;
	@Autowired
	private ProductDAOImpl productDAO;
	@Autowired
	private ModelAndViewService modelService;

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value="/catalog", method=RequestMethod.GET)
	public ModelAndView catalogPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("catalog");
		logger.info("Redirecting to catalog page");
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
		logger.info("Requesting catalogs");
		logger.info("Parsing catalog list as JSON");
		//converts the list into a json and sends it as a response
		Gson gson = new Gson();
		String jsonString = gson.toJson(catalogs);
		logger.info("Sending data to AJAX call");
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value="/catalog/get_products_catalogs", method=RequestMethod.GET)
	public void getProductsOfCatalog(HttpServletResponse response, @RequestParam Map<String,String> requestParam) throws IOException{
		int catalog_id;
		Filter filter = null;
		
		if(requestParam.get("filter") != null){
			logger.info("Requesting filtered catalogs");
			//catalog with filter
			catalog_id = Integer.parseInt(requestParam.get("value"));
			
			//set filters
			filter = new Filter();
			filter.setBrands(requestParam.get("brands").split("/"));
			filter.setSizes(requestParam.get("sizes").split("/"));
			filter.setPriceRange(requestParam.get("priceRange").split("-"));
		}else{
			catalog_id = Integer.parseInt(requestParam.get("catalog"));
		}
		
		logger.info("Requesting catalog from [catalog_id = {}]", catalog_id);
		//gets data from db
		Catalog catalog = catalogDAO.getCatalog(catalog_id);
		List<Product> products = productDAO.getProductsOfCatalog(catalog_id, filter);
		
		//puts data in a custom class
		CatalogJSON cJson = new CatalogJSON();
		cJson.setCatalogName(catalog.getCatalog_name());
		cJson.setProducts(products);
		
		logger.info("Parsing catalog list as JSON");
		//converts that class into a json and sends it as a response
		Gson gson = new Gson();
		String jsonString = gson.toJson(cJson);
		logger.info("Sending data to AJAX call");
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value="/catalog/get_all_brands", method=RequestMethod.GET)
	public void getAllBrands(HttpServletResponse response, @RequestParam Map<String,String> requestParam) throws IOException{
		List<BrandManufacturer> bm = accountDAO.getAllBrandManufacturers();
		
		logger.info("Parsing brand manufacturer list as JSON");
		//converts the class into a json and sends it as a response
		Gson gson = new Gson();
		String jsonString = gson.toJson(bm);
		logger.info("Sending data to AJAX call");
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}

	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView searchPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("catalog");
		logger.info("Redirecting to catalog page");
		/*
		 * cause we're reusing the catalog.jsp for search results,
		 * it needs to know what products to get.
		 */
		mv.addObject("request", "search");
		
		return mv;	
	}

	@RequestMapping(value="/search/get_searched_products", method=RequestMethod.GET)
	public void getSearchedProducts(HttpServletResponse response, @RequestParam Map<String,String> requestParam) throws IOException{
		String searchKey;
		Filter filter = null;
		
		if(requestParam.get("filter") != null){
			logger.info("Requesting search key");
			//search with filter
			searchKey = requestParam.get("value");
			logger.info("{}", searchKey);
			logger.info("Setting filters");
			//set filters
			filter = new Filter();
			filter.setBrands(requestParam.get("brands").split("/"));
			filter.setSizes(requestParam.get("sizes").split("/"));
			filter.setPriceRange(requestParam.get("priceRange").split("-"));
			logger.info("{}", filter);
		}else{
			searchKey = requestParam.get("search_key");
		}
		
		logger.info("Requesting products from [searchKey = {}, filter = {}]", searchKey, filter);
		//get from db
		List<Product> products = productDAO.getSearched(searchKey, filter);
		
		logger.info("Parsing product list as JSON");
		Gson gson = new Gson();
		String jsonString = gson.toJson(products);
		logger.info("Sending data to AJAX call");
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
}
