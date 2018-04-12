package com.json;

import java.util.Arrays;

public class Filter {
	private String[] brands;
	private String[] sizes;
	private String[] priceRange;
	
	public String[] getBrands() {
		return brands;
	}
	public void setBrands(String[] brands) {
		this.brands = brands;
	}
	public String[] getSizes() {
		return sizes;
	}
	public void setSizes(String[] sizes) {
		this.sizes = sizes;
	}
	public String[] getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(String[] priceRange) {
		this.priceRange = priceRange;
	}
	@Override
	public String toString () {
		return "Filter [brands=" + Arrays.toString(brands) + ", sizes=" + Arrays.toString(sizes) + ", priceRange=" + Arrays.toString(priceRange) + "]";
	}
}
