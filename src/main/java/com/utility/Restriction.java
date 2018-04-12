package com.utility;

import com.beans.Account;

/**
 * A class that handles restricted pages for each type of available users.
 * Note: Edit list when adding / removing url patterns.
 */
public class Restriction {
	
	//response codes.
	public static final int YES_HOME = 1;
	public static final int YES_ERROR = 2;
	public static final int NO = 3;
	
	//TODO finish whitelisting.
	//Whitelist of path only viewable by said users.
	private final String[] ADMIN_LIST = {"/create_admin",
	            						 "/register_admin",
	            						 "/create_bm",
	            						 "/register_bm",
	            						 "/override_order",
	            						 "/override_get_order",
	            						 "/override_order_redirect",
	            						 "/delete_order"};
	
	//actions / pages only allowed for BM.
	private final String[] BM_LIST = {"/new_product",
			                          "/add_product",
			                          "/edit_product",
			                          "/edit_get_product",
			                          "/delete_product",
			                          "/delete_get_product",
			                          "/new_product_availability",
			                          "/add_product_availability",
			                          "/edit_product_availability",
			                          "/update_product_availability",
			                          "/remove_product_availability",
			                          "/remove_product",
			                          "/delete_product",
									  "/delete_get_product"};
	
	//actions / pages only allowed for customer.
	private final String[] CUSTOMER_LIST = {"/checkout", 
										    "/saveProductToCart",
										    "/removeAllProductsFromCart",
										    "/removeOneProdCart"};
	
	//Only public (unauthorized) can access these.
	private final String[] PUBLIC_LIST = {"/login",
			   							  "/authentication", 
            							  "/register", 
            							  "/registerAccount",
            							  "/checkout",
            							  "/forgot_password",
            							  "/secret_question",
            							  "/reset_password",
            							  "/confirm_reset_pass",
            							  "/saveProductToCart",
										  "/removeAllProductsFromCart",
										  "/removeOneProdCart"};
	
	//general list, basically anyone can access these.
	private final String[] GENERAL_LIST = {"/",
			                               "/logout",
	            						   "/order",
	            						   "/setup_payment_details",
			                               "/home",
			                               "/catalog",
		            					   "/catalog/get_catalogs",
		            					   "/catalog/get_products_catalogs",
		            					   "/catalog/get_all_brands",
		            					   "/search",
		            					   "/search/get_searched_products",
		            					   "/product",
		            					   "/product/get_product_info",
			                               "/404"};
	
	/**
	 * Checks whether the given authorized user is authorized to view action / page.
	 * @param accType -  the account type
	 * @param path - the path that the user wants to access
	 * @return true if the user is restricted, false if not.
	 */
	public int isRoleRestricted(int accType, String path) {
		
		//TODO perform restriction true/false here 
		int restricted;
		
		switch(accType) {
			case Account.ADMIN: restricted = check(ADMIN_LIST, path);
			                    break;
			case Account.BRAND_MANUFACTURER: restricted = check(BM_LIST, path); 
					            break;
			case Account.CUSTOMER: restricted = check(CUSTOMER_LIST, path);
			                    break;
			default: //Treat any unkown role as public.
				     restricted = check(PUBLIC_LIST, path);

		}
		
		return restricted;
	}
	
	private int check(String[] list, String path) {
		
		
		if(hasPath(list, path) || hasPath(GENERAL_LIST, path)) {
			return NO;
		}
		
		//what kind of restriction is it.
		else {
			
			//No, go back home.
			if(hasPath(PUBLIC_LIST, path)) {
				return YES_HOME;
			}
			
			//No, not found.
			else return YES_ERROR;
			
		}
		
	}
	
	/**
	 * Checks if the given list contains the given path.
	 * @param list - the given list (BM, ADMIN, CUSTOMER, ETC)
	 * @param path - the url path.
	 * @return true if has path, false if not.
	 */
	private boolean hasPath(String[] list, String path) {
		
		for(int i = 0; i < list.length; i++) {
			if(path.equals(list[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
