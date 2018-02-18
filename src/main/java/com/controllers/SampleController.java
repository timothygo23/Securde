package com.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Account;
import com.beans.BrandManufacturer;
import com.dao.AccountDao;

@Controller
public class SampleController {
	
	@Autowired
	private AccountDao accountDao;
	
	@RequestMapping(value="/")
	public ModelAndView sampleUrlMapping(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SampleJSP");
		
		//testing db (working)
		/*Account account = new Account();
		account.setEmail("timyooo@gmail.com");
		account.setPassword("1234");
		account.setAccount_type(Account.BRAND_MANUFACTURER);
		
		BrandManufacturer brandManufacturer = new BrandManufacturer();
		brandManufacturer.setBrand_name("Nike");
		
		accountDao.addBrandManufacturer(account, brandManufacturer);*/
		
		return mv;
	}
	
	@RequestMapping(value="/tim/hi/{userId}")
	public ModelAndView samplePathVariable(@PathVariable Map<String, String> pVar){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SampleJSP");
		
		return mv;
	}
	
	@RequestMapping(value="/sampleParam/hi", method=RequestMethod.POST)
	public ModelAndView sampleRequestParam(@RequestParam(value="userId", defaultValue="-1") String userId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SampleJSP");
		
		return mv;
	}
	
	@RequestMapping(value="/sampleParamss/hi", method=RequestMethod.POST)
	public ModelAndView sampleAlternateRequestParam(@RequestParam Map<String, String> requestParams){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SampleJSP");
		
		return mv;
	}
	
	@RequestMapping(value="/sampleParams/hi", method=RequestMethod.POST)
	public ModelAndView sampleModelAttribute(@ModelAttribute("entity") Account entity){
		//maps the parameters' name to the same attribute name in the SampleEntity
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SampleJSP");
		
		return mv;
	}
}
