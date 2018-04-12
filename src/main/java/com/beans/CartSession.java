package com.beans;

import java.util.ArrayList;
import java.util.List;

public class CartSession {
	public static final String CART_ITEM_LIST= "cartItemList";
	private int cartID;
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
	
	public void deleteCartItem(int cartItemID) {
		cartItemList.remove(cartItemID);
		
		int index = 0;
		for(CartItem ci : cartItemList) {
			ci.setId(index);
			index++;
		}
	}
	
	public void addProducts(Product p, int qty, String size) {
		int id = 0;
		
		if(size.length() > 1) {
			size = changeSizeName(size);
		}

		if(cartItemList.size() != 0) {
			id = cartItemList.get(cartItemList.size()-1).getId()+1;
		}
		this.cartItemList.add(new CartItem(p, id, qty, size));
	}
	
	public void setCartID(int id) {
		this.cartID = id;
	}
	 
	public int getCartID() {
		return this.cartID;
	}
	
	private String changeSizeName(String size) {
		
		switch(size) {
			case "Small": return "S"; 
			case "Medium": return "M";
			case "Large": return "L";
		}
		
		return "E"; //error
	}

	public CartItem getLastCartItem() {
		// TODO Auto-generated method stub
		return cartItemList.get(cartItemList.size()-1);
	}
}
