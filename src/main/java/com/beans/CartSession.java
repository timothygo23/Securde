package com.beans;

import java.util.ArrayList;

public class CartSession {
	public static final String CART_ITEM_LIST= "cartItemList";
	private ArrayList<CartItem> cartItemList;
	
	public CartSession() {
		this.cartItemList = new ArrayList<CartItem>();
	}

	public ArrayList<CartItem> getCartItemList() {
		return cartItemList;
	}	
	
	public void setCartItemList(ArrayList<CartItem> cartList) {
		this.cartItemList = cartList;
	}
	
	public void addProducts(Product p) {
		int id = 1;
		if(cartItemList.size() != 0) {
			id = cartItemList.get(cartItemList.size()-1).getId()+1;
		}
		this.cartItemList.add(new CartItem(p, id));
	}
}
