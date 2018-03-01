package com.beans;

import java.util.ArrayList;

public class CartSession {
	public static final String PRODUCT_LIST= "productList";
	private ArrayList<Product> productList;
	
	public CartSession() {
		this.productList = new ArrayList<Product>();
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}	
	
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	
	public void addProducts(Product p) {
		this.productList.add(p);
	}
}
