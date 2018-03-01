package com.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Product;
import com.beans.ProductAvailability;
import com.dao.impl.ProductAvailabilityDAOImpl;
import com.services.ModelAndViewService;

@Controller
public class ProductAvailablityController {
	
	@Autowired
	private ProductAvailabilityDAOImpl paDAO;
	@Autowired
	private ModelAndViewService modelService;
	
	@RequestMapping(value="/new_product_availability", method=RequestMethod.GET)
	public ModelAndView newProductAvailability (HttpServletRequest request, @ModelAttribute("product_id") String product_id) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("product_id", product_id);
		mv.setViewName("newProductAvailability");
		
		return mv;
	}
	
	@RequestMapping(value="/add_product_availability", method=RequestMethod.POST)
	public RedirectView addProductAvailability(@RequestParam Map<String, String> requestParams) {
		
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		int quantity_s = Integer.parseInt(requestParams.get("quantity_s"));
		int quantity_m = Integer.parseInt(requestParams.get("quantity_m"));
		int quantity_l = Integer.parseInt(requestParams.get("quantity_l"));
		
		ProductAvailability productAvailabilitySmall = new ProductAvailability (product_id, "S", quantity_s);
		ProductAvailability productAvailabilityMedium = new ProductAvailability (product_id, "M", quantity_m);
		ProductAvailability productAvailabilityLarge = new ProductAvailability (product_id, "L", quantity_l);
		
		paDAO.add(productAvailabilitySmall);
		paDAO.add(productAvailabilityMedium);
		paDAO.add(productAvailabilityLarge);
		
		RedirectView rv = new RedirectView();
		rv.setUrl("home");
		
		return rv;
	}
	
	@RequestMapping(value="/edit_product_availability", method=RequestMethod.GET)
	public ModelAndView editProduct (HttpServletRequest request, @ModelAttribute("product_id") String product_id) {
		
		List<ProductAvailability> productAvailabilities = paDAO.getProductAvailability(Integer.parseInt(product_id));
		ProductAvailability productAvailabilitySmall = productAvailabilities.get(0);
		ProductAvailability productAvailabilityMedium = productAvailabilities.get(1);
		ProductAvailability productAvailabilityLarge = productAvailabilities.get(2);
		
		ModelAndView mv = modelService.createModelAndView(request);
		mv.addObject("product_id", product_id);
		mv.addObject("small", productAvailabilitySmall);
		mv.addObject("medium", productAvailabilityMedium);
		mv.addObject("large", productAvailabilityLarge);
		
		mv.setViewName("editProductAvailability");
		
		return mv;
	}
	
	@RequestMapping(value="/update_product_availability", method=RequestMethod.POST)
	public RedirectView updateProductAvailability(@RequestParam Map<String, String> requestParams) {
		
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		int product_avail_id_small = Integer.parseInt(requestParams.get("product_avail_id_small"));
		int product_avail_id_medium = Integer.parseInt(requestParams.get("product_avail_id_medium"));
		int product_avail_id_large = Integer.parseInt(requestParams.get("product_avail_id_large"));
		int quantity_s = Integer.parseInt(requestParams.get("quantity_s"));
		int quantity_m = Integer.parseInt(requestParams.get("quantity_m"));
		int quantity_l = Integer.parseInt(requestParams.get("quantity_l"));
		
		ProductAvailability productAvailabilitySmall = new ProductAvailability (product_id, "S", quantity_s);
		productAvailabilitySmall.setProduct_avail_id(product_avail_id_small);
		ProductAvailability productAvailabilityMedium = new ProductAvailability (product_id, "M", quantity_m);
		productAvailabilityMedium.setProduct_avail_id(product_avail_id_medium);
		ProductAvailability productAvailabilityLarge = new ProductAvailability (product_id, "L", quantity_l);
		productAvailabilityLarge.setProduct_avail_id(product_avail_id_large);
		
		paDAO.edit(productAvailabilitySmall);
		paDAO.edit(productAvailabilityMedium);
		paDAO.edit(productAvailabilityLarge);
		
		RedirectView rv = new RedirectView();
		rv.setUrl("home");
		
		return rv;
	}

}
