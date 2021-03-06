package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.beans.Account;
import com.beans.Cart;
import com.beans.CartItem;
import com.beans.CartSession;
import com.beans.Catalog;
import com.beans.Customer;
import com.beans.Order;
import com.beans.Product;
import com.beans.ProductAvailability;
import com.dao.impl.CartDAOImpl;
import com.dao.impl.CatalogDAOImpl;
import com.dao.impl.ProductAvailabilityDAOImpl;
import com.dao.impl.ProductDAOImpl;
import com.google.gson.Gson;
import com.json.ProductJSON;
import com.services.ModelAndViewService;import com.services.SessionAttributes;

@Controller
public class ProductController {
	
	@Autowired
	private ProductDAOImpl productDAO;
	@Autowired
	private CatalogDAOImpl catalogDAO;
	@Autowired
	private CartDAOImpl cartDAO;
	@Autowired
	private ProductAvailabilityDAOImpl paDAO;
	@Autowired
	private ModelAndViewService modelService;

	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public ModelAndView productPage(HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("product");

		logger.info("Redirecting to new product page");
		
		return mv;
	}
	
	@RequestMapping(value="/product/get_product_info", method=RequestMethod.GET)
	public void getProductInfo (HttpServletResponse response, @RequestParam Map<String, String> requestParams) throws IOException {
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		
		Product product = productDAO.getProduct(product_id);
		List<ProductAvailability> productAvailability = paDAO.getProductAvailability(product.getProduct_id()); 
		Catalog catalog = catalogDAO.getCatalog(product.getCatalog_id());
		
		logger.info("{}", product);
		for (ProductAvailability pa : productAvailability) {
			logger.info("{}", pa);
		}
		logger.info("{}", catalog);
		
		ProductJSON pJson = new ProductJSON();
		pJson.setProduct(product);
		pJson.setProductAvailability(productAvailability);
		pJson.setCatalog(catalog);
		
		logger.info("Parsing product as JSON");
		Gson gson = new Gson();
		String jsonString = gson.toJson(pJson);
		logger.info("Sending data to AJAX call");
		response.setContentType("application/json");
		response.getWriter().write(jsonString);
	}
	
	@RequestMapping(value="/new_product", method=RequestMethod.GET)
	public ModelAndView newProduct (HttpServletRequest request) {
		ModelAndView mv = modelService.createModelAndView(request);
		mv.setViewName("newProduct");
		logger.info("Redirecting to new product page");
		
		return mv;
	}
	
	@RequestMapping(value="/add_product", method=RequestMethod.POST)
	public RedirectView addProduct(@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttribute) {
		logger.info("Creating Product");
		
		String product_name = requestParams.get("product_name");
		String product_description = requestParams.get("product_description");
		int catalog_id = Integer.parseInt(requestParams.get("catalog_id"));
		int price = Integer.parseInt(requestParams.get("price"));
		String brand_name = requestParams.get("brand_name");
		
		Product product = new Product (product_name, product_description, catalog_id, price, brand_name);
		
		logger.info("{}", product);
		logger.info("Updating database");
		
		int product_id = productDAO.add(product);
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("product_id", product_id);
		rv.setUrl("new_product_availability");
		
		logger.info("Redirecting to new product availability page");
		
		return rv;
	}
	
	public RedirectView getProduct (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		//List<Product> products = productDAO.getAllProducts();
		Product product = productDAO.getProduct(Integer.parseInt(request.getParameter("product_id")));
		logger.info("{}", product);
		
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
		logger.info("Redirecting to edit product page");
		
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
		logger.info("Updating product");
		
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		String product_name = requestParams.get("product_name");
		String product_description = requestParams.get("product_description");
		int catalog_id = Integer.parseInt(requestParams.get("catalog_id"));
		int price = Integer.parseInt(requestParams.get("price"));
		String brand_name = requestParams.get("brand_name");
		
		Product product = new Product (product_name, product_description, catalog_id, price, brand_name);
		product.setProduct_id(product_id);
		
		logger.info("{}", product);
		
		productDAO.edit(product);
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("product_id", product_id);
		rv.setUrl("edit_product_availability");
		
		logger.info("Redirecting to edit product availability page");
		
		return rv;
	}
	
	@RequestMapping(value="/delete_get_product", method=RequestMethod.GET)
	public RedirectView deleteProductRedirect (HttpServletRequest request, RedirectAttributes redirectAttribute) {
		RedirectView rv = getProduct(request, redirectAttribute);
		rv.setUrl("delete_product");
		
		logger.info("Redirecting to delete product page");
		
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
		logger.info("Deleting product");
		int product_id = Integer.parseInt(requestParams.get("product_id"));
		
		//productDAO.delete(product_id);
		logger.info("{}", productDAO.getProduct(product_id));
		
		RedirectView rv = new RedirectView();
		redirectAttribute.addFlashAttribute("product_id", product_id);
		rv.setUrl("remove_product_availability");
		
		return rv;
	}
	
	@RequestMapping(value="/saveProductToCart", method=RequestMethod.POST)
	public void saveProductToCart(@RequestParam Map<String, String> requestParams, HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Saving product to cart");
		
		String productId = requestParams.get("productId");
		int qty = Integer.parseInt(requestParams.get("quantity"));
		String size = requestParams.get("size");
		
		HttpSession session = request.getSession();
		Product p = productDAO.getProduct(Integer.parseInt(productId));
		logger.info("{}", p);
		
		CartSession cs = new CartSession();
		
		logger.info("Validating cart session");
		if(session.getAttribute(CartSession.CART_ITEM_LIST) == null) {
			cs.addProducts(p, qty, size);
			session.setAttribute(CartSession.CART_ITEM_LIST, cs.getCartItemList());
			
			logger.info("Adding product to cart");
			
			for(CartItem test : cs.getCartItemList()) {
				//System.out.println("Product: " + test.getProduct().getProduct_name() + " | ID: " + test.getId());
			}
			session.setAttribute(cs.CART_ITEM_LIST, cs.getCartItemList());
		}
		
		else {
			cs.setCartItemList((ArrayList<CartItem>) session.getAttribute(cs.CART_ITEM_LIST));
			cs.addProducts(p, qty, size);
			session.setAttribute(cs.CART_ITEM_LIST, cs.getCartItemList()); 
		}
		
		logger.info("Adding product to cart");
		if(session.getAttribute(SessionAttributes.CUSTOMER_INFO) != null) {
			//saved cartItem to db.
			saveCartDBHelper(session, cs.getLastCartItem()); //get last inserted item
		}
		
		response.getWriter().write("Done!");
	}
	
	private void saveCartDBHelper(HttpSession session, CartItem ci) {
		Cart cart = new Cart();
		Customer customer = (Customer) session.getAttribute(SessionAttributes.CUSTOMER_INFO);
		ProductAvailability pa;
	
		
		cart.setQuantity(ci.getQty()+"");
		cart.setProduct_id(ci.getProduct().getProduct_id());
		cart.setAccount_id(customer.getAccount_id());
		pa = paDAO.getProductAvailabilityByFeature(ci.getSize(), ci.getProduct().getProduct_id());
		cart.setProduct_avail_id(pa.getProduct_avail_id());
		cart.setCart_id(customer.getCart_num());
	    cartDAO.add(cart);
		
	}
	
	@RequestMapping(value="/removeAllProductsFromCart", method=RequestMethod.POST)
	public void removeAllProductFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Removing all products from cart");
		HttpSession session = request.getSession();
		
		logger.info("Validating cart session");
		if(session.getAttribute(CartSession.CART_ITEM_LIST) != null) {
			if(session.getAttribute(SessionAttributes.CUSTOMER_INFO) != null)
				deleteAllProdFromCartFromDB(session);
			
			session.removeAttribute(CartSession.CART_ITEM_LIST);
		}
		response.getWriter().write("DONE");
	}
	
	private void deleteAllProdFromCartFromDB(HttpSession session) {
		ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute(CartSession.CART_ITEM_LIST);
		Cart cart = new Cart();
		Customer customer = (Customer) session.getAttribute(SessionAttributes.CUSTOMER_INFO);
		ProductAvailability pa;
		
		for(CartItem ci : cartItemList) {

			cart.setQuantity(ci.getQty()+"");
			cart.setProduct_id(ci.getProduct().getProduct_id());
			cart.setAccount_id(customer.getAccount_id());
			pa = paDAO.getProductAvailabilityByFeature(ci.getSize(), ci.getProduct().getProduct_id());
			cart.setProduct_avail_id(pa.getProduct_avail_id());
			cart.setCart_id(customer.getCart_num());
			
			cartDAO.deleteCart(cart);

		}
	}

	@RequestMapping(value="/removeOneProdCart", method=RequestMethod.POST)
	public void removeSingleProductFromCart(@RequestParam("cartItemID") String cartItemID, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute(CartSession.CART_ITEM_LIST);
		//System.out.println("The ID: " + cartItemID);
		
		logger.info("Removing a product from cart");
		
		CartSession cs = new CartSession();
		cs.setCartItemList(cartItemList);
		
		deleteCertainProductFromCartFromDB(session, cartItemList, Integer.parseInt(cartItemID)); //db delete
		cs.deleteCartItem(Integer.parseInt(cartItemID)); //session delete
		
		
		if(cartItemList.size() != 0) {
			session.setAttribute(CartSession.CART_ITEM_LIST, cs.getCartItemList());
		}
		
		else {
			session.removeAttribute(CartSession.CART_ITEM_LIST);
		}
		
		response.getWriter().write("DONE");
	}
	
	private void deleteCertainProductFromCartFromDB(HttpSession session, ArrayList<CartItem> cartItemList, int cartItemID) {
		Cart cart = new Cart();
		Customer customer = (Customer) session.getAttribute(SessionAttributes.CUSTOMER_INFO);
		ProductAvailability pa;
		
		CartItem ci = cartItemList.get(cartItemID);
		

		cart.setQuantity(ci.getQty()+"");
		cart.setProduct_id(ci.getProduct().getProduct_id());
		cart.setAccount_id(customer.getAccount_id());
		pa = paDAO.getProductAvailabilityByFeature(ci.getSize(), ci.getProduct().getProduct_id());
		cart.setProduct_avail_id(pa.getProduct_avail_id());
		cart.setCart_id(customer.getCart_num());
	
		
		cartDAO.deleteCart(cart);
	}
	
	@RequestMapping(value="/checkSavedCart", method=RequestMethod.GET)
	public RedirectView checkSavedCart(HttpServletRequest request) {
		RedirectView rv = new RedirectView();
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute(SessionAttributes.CUSTOMER_INFO);
		int acc_id = customer.getAccount_id();
		
		//if user, give the CURRENT saved cart.
		List<Cart> cart = cartDAO.getUserCart(acc_id, customer.getCart_num());
		
		//check if there's anyting saved in this user's cart.
		//if yes, replace the current cart.
		
		if(cart != null && cart.size() != 0) {
			CartSession cs = new CartSession();
			
			Product p;
			ProductAvailability pa;
			logger.info("Restoring saved cart from db..");
			for(Cart c : cart) {
				//System.out.println(c.getProduct_avail_id());
				pa = paDAO.getProductAvailabilityById(c.getProduct_avail_id());
				p = productDAO.getProduct(c.getProduct_id());
	
				cs.addProducts(p, Integer.parseInt(c.getQuantity()), pa.getSize());
			}
			session.setAttribute(CartSession.CART_ITEM_LIST, cs.getCartItemList());
		}
		//if not, save current cart as user's cart.
		else {
			logger.info("Saving new cart to db..");
			//System.out.println("No cart..");
			ArrayList<CartItem> cartItemList = (ArrayList<CartItem>) session.getAttribute(CartSession.CART_ITEM_LIST);
			
			if(cartItemList != null && cartItemList.size() != 0)
				for(CartItem ci : cartItemList) {
					saveCartDBHelper(session, ci);
				}
			
		}
		rv.setUrl("home");
		return rv;
	}
	
	
}
