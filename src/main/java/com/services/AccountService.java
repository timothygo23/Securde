package com.services;

import javax.servlet.http.HttpServletRequest;

import com.beans.Account;
import com.beans.BrandManufacturer;
import com.beans.Customer;

public interface AccountService {
	
	public Account logIn(String email, String password);
	public void logOut(HttpServletRequest request);

}
