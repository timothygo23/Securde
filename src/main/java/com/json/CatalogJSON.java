package com.json;

import java.util.List;

import com.beans.Catalog;
import com.beans.Product;

public class CatalogJSON {
	private String catalogName;
	private List<Product> products;
	
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
