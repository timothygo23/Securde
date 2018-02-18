package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="brand_manufacturer_info")
public class BrandManufacturer {
	
	@Id
	@Column(name="account_id")
	private int account_id;
	
	@Column(name="brand_name")
	private String brand_name;
	
	public BrandManufacturer() {}

	public BrandManufacturer(int account_id, String brand_name) {
		this.account_id = account_id;
		this.brand_name = brand_name;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
}
