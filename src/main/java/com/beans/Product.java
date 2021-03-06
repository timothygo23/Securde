package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int product_id;
	@Column(name="product_name")
	private String product_name;
	@Column(name="product_description")
	private String product_description;
	@Column(name="catalog_id")
	private int catalog_id;
	@Column(name="price")
	private int price;
	@Column(name="brand_name")
	private String brand_name;
	
	public Product() {}
	
	public Product(String product_name, String product_description, int catalog_id, int price,
			String brand_name) {
		this.product_name = product_name;
		this.product_description = product_description;
		this.catalog_id = catalog_id;
		this.price = price;
		this.brand_name = brand_name;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public int getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(int catalog_id) {
		this.catalog_id = catalog_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	@Override
	public String toString () {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_description=" + product_description + ", catalog_id=" + catalog_id + ", price=" + price + ", brand_name=" + brand_name + "]";
	}
}
