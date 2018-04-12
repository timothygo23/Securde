package com.beans;

public class CartItem {
	private int id;
	private int qty;
	private String size;
	private Product product;
	private int price;
	
	public CartItem(Product product, int cartId, int qty, String size) {
		this.product = product;
		this.id = cartId;
		this.qty = qty;
		this.price = product.getPrice() * qty;
		this.size = size;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getSize() {
		return size;
	}
}
