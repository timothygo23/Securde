package com.utility;

/**
 * A class that handles restricted pages for each type of available users.
 *
 */
public class RestrictedPages {
	
	//TODO finish whitelisting.
	//Whitelist of path only viewable by said users.
	private final String[] ADMIN_LIST = {"/create_bm",
            							 "/create_admin",
            							 "/register_admin",
            							 "/catalog/get_catalogs",
            							 "/override_order",
            							 "/delete_order"};
	private final String[] BM_LIST = {};
	private final String[] CUSTOMER_LIST = {"/checkout"};
	
	private final String[] GENERAL_LIST = {"/register", //general list, basically anyone can access these.
			                               "/login",    //If user is already logged in, accessing register or login would redirect to home.
			                               "/home",
			                               "/404"};
	
	/**
	 * 
	 * @param accountType -  the account type
	 * @param path - the path that the user wants to access
	 * @return true if the user is restricted, false if not.
	 */
	public static boolean isRestricted(int accountType, String path) {
		
		//TODO perform restriction true/false here 
		
		return false;
	}
}
