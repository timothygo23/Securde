package com.json;

import java.util.List;

import com.beans.Catalog;
import com.beans.Product;
import com.beans.ProductAvailability;

public class ProductJSON {
	private Product product;
	private List<ProductAvailability> productAvailability;
	private Catalog catalog;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<ProductAvailability> getProductAvailability() {
		return productAvailability;
	}
	public void setProductAvailability(List<ProductAvailability> productAvailability) {
		this.productAvailability = productAvailability;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
}
