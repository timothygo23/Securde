package com.dao;

import java.util.List;

import com.beans.Account;
import com.beans.BrandManufacturer;
import com.beans.Customer;

public interface AccountDAO {
	public Account get(int account_id);
	public void addBrandManufacturer(Account account, BrandManufacturer brandManufacturer);
	public void addCustomer(Account account, Customer customer);
	public BrandManufacturer getBrandManufacturer(int account_id);
	public List<BrandManufacturer> getAllBrandManufacturers();
	public Customer getCustomer(int account_id);
	public List<Customer> getAllCustomers();
	public void addAdmin (Account account);
	public boolean isSaltUnique(byte[] salt);

}
