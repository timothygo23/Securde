package com.dao;

import java.util.List;

import com.beans.Product;
import com.json.Filter;

public interface ProductDAO {
	public int add(Product product);
	public void edit(Product product);
	public void delete(int product_id);
	public Product getProduct(int product_id);
	public List<Product> getAllProducts();
	public List<Product> getProductsOfCatalog(int catalog_id, Filter filter);
}
