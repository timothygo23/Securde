package com.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart implements Serializable{
	
	@Id
	@Column(name="cart_id")
	private int cart_id;
	@Id
	@Column(name="account_id")
	private int account_id;
	@Id
	@Column(name="product_id")
	private int product_id;
	@Id
	@Column(name="quantity")
	private String quantity;
	@Id
	@Column(name="product_avail_id")
	private int product_avail_id;
	
	public Cart() {}

	public Cart(int cart_id, int account_id, int product_id, String quantity, int product_avail_id) {
		this.cart_id = cart_id;
		this.account_id = account_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.product_avail_id = product_avail_id;
	}
	
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public int getProduct_avail_id() {
		return product_avail_id;
	}
	public void setProduct_avail_id(int product_avail_id) {
		this.product_avail_id = product_avail_id;
	}
	
	

}
