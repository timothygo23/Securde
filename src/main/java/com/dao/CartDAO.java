package com.dao;

import java.util.List;

import com.beans.Cart;

public interface CartDAO {
	public void add(Cart cart);
	public void edit(Cart cart);
	public void delete(int cart_id);
	public Cart getCart(int cart_id);
	public List<Cart> getUserCart(int acc_id);
	public List<Cart> getAllCarts();	
}
