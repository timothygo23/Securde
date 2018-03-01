package com.dao;

import java.util.List;

import com.beans.ProductAvailability;

public interface ProductAvailabilityDAO {
	public void add(ProductAvailability productAvailability);
	public void edit(ProductAvailability productAvailability);
	public void delete(int product_avail_id);
	//public ProductAvailability getProductAvailability(int product_avail_id);
	public List<ProductAvailability> getProductAvailability(int product_id);
	public List<ProductAvailability> getAllProductAvailabilitys();
}
