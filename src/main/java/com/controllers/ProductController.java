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
import com.beans.ProductAvailability;
import com.dao.impl.CatalogDAOImpl;
import com.dao.impl.ProductAvailabilityDAOImpl;
import com.dao.impl.ProductDAOImpl;
import com.google.gson.Gson;
import com.json.ProductJSON;
import com.services.ModelAndViewService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductDAOImpl productDAO;
	@Autowired
	private CatalogDAOImpl catalogDAO;
	@Autowired
	private ProductAvailabilityDAOImpl paDAO;
	@Autowired
	private ModelAndViewService modelService;
	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public ModelAndView productPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("product");
		
		return mv;
	}
	
	@RequestMapping(value="/product/get_product_info", method=RequestMethod.GET)
	public void getProductInfo (HttpServletResponse response, @RequestParam Map<String, String> requestParams) throws IOException {
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		
		Product product = productDAO.getProduct(product_id);
		List<ProductAvailability> productAvailability = paDAO.getProductAvailability(product.getProduct_id()); 
		Catalog catalog = catalogDAO.getCatalog(product.getCatalog_id());
		
		ProductJSON pJson = new ProductJSON();
		pJson.setProduct(product);
		pJson.setProductAvailability(productAvailability);
		pJson.setCatalog(catalog);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(pJson);
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
	
}
