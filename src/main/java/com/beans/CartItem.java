package com.beans;

public class CartItem {
	private int id;
	private Product product;
	
	public CartItem(Product product, int cartId) {
		this.product = product;
		this.id = cartId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
