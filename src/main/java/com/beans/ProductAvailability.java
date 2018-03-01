package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_availability")
public class ProductAvailability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_avail_id")
	private int product_avail_id;
	@Column(name="product_id")
	private int product_id;
	@Column(name="size")
	private String size;
	@Column(name="quantity")
	private int quantity;
	
	public ProductAvailability() {}
	
	public ProductAvailability(int product_id, String size, int quantity) {
		this.product_id = product_id;
		this.size = size;
		this.quantity = quantity;
	}

	public int getProduct_avail_id() {
		return product_avail_id;
	}

	public void setProduct_avail_id(int product_avail_id) {
		this.product_avail_id = product_avail_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
