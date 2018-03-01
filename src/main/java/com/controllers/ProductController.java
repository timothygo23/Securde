package com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@RequestMapping(value="/new_product", method=RequestMethod.GET)
	public ModelAndView newProduct (HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("newProduct");
		
		return mv;
	}
	
	@RequestMapping(value="/add_product", method=RequestMethod.POST)
	public RedirectView addProduct(@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttribute) {
		
		String product_name = requestParams.get("product_name");
		String product_description = requestParams.get("product_description");
		int catalog_id = Integer.parseInt(requestParams.get("catalog_id"));
		int price = Integer.parseInt(requestParams.get("price"));
		String brand_name = requestParams.get("brand_name");
		
		Product product = new Product (product_name, product_description, catalog_id, price, brand_name);
		int product_id = productDAO.add(product);
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("product_id", product_id);
		rv.setUrl("new_product_availability");
		
		return rv;
	}
	
	public RedirectView getProduct (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		//List<Product> products = productDAO.getAllProducts();
		Product product = productDAO.getProduct(Integer.parseInt(request.getParameter("product_id")));
		
		/*ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("product", product);
		mv.addObject("products", products);
		mv.setViewName("editProduct");*/
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("product", product);
		
		return rv;
	}
	
	@RequestMapping(value="/edit_get_product", method=RequestMethod.GET)
	public RedirectView editProductRedirect (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		RedirectView rv = getProduct(request, redirectAttribute);
		rv.setUrl("edit_product");
		
		return rv;
	}
	
	@RequestMapping(value="/edit_product", method=RequestMethod.GET)
	public ModelAndView editProduct (HttpServletRequest request, @ModelAttribute("product") Product product) {
		List<Product> products = productDAO.getAllProducts();
		
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("products", products);
		mv.addObject("product", product);
		mv.setViewName("editProduct");
		
		return mv;
	}
	
	@RequestMapping(value="/update_product", method=RequestMethod.POST)
	public RedirectView updateProduct (@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttribute) {
		
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		String product_name = requestParams.get("product_name");
		String product_description = requestParams.get("product_description");
		int catalog_id = Integer.parseInt(requestParams.get("catalog_id"));
		int price = Integer.parseInt(requestParams.get("price"));
		String brand_name = requestParams.get("brand_name");
		
		Product product = new Product (product_name, product_description, catalog_id, price, brand_name);
		product.setProduct_id(product_id);
		
		productDAO.edit(product);
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("product_id", product_id);
		rv.setUrl("edit_product_availability");
		
		return rv;
	}
	
	@RequestMapping(value="/delete_get_product", method=RequestMethod.GET)
	public RedirectView deleteProductRedirect (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		RedirectView rv = getProduct(request, redirectAttribute);
		rv.setUrl("delete_product");
		
		return rv;
	}
	
	@RequestMapping(value="/delete_product", method=RequestMethod.GET)
	public ModelAndView deleteProduct (HttpServletRequest request, @ModelAttribute("product") Product product) {
		List<Product> products = productDAO.getAllProducts();
		
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("products", products);
		mv.addObject("product", product);
		mv.setViewName("deleteProduct");
		
		return mv;
	}
	
	@RequestMapping(value="/remove_product", method=RequestMethod.POST)
	public RedirectView removeProduct (@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttribute) {
		
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		
		//productDAO.delete(product_id);
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("product_id", product_id);
		rv.setUrl("remove_product_availability");
		
		return rv;
	}
	
}
