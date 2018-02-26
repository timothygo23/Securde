package com.dao;

import java.util.List;

import com.beans.Catalog;

public interface CatalogDAO {
	public void add(Catalog catalog);
	public void edit(Catalog catalog);
	public void delete(int catalog_id);
	public Catalog getCatalog(int catalog_id);
	public List<Catalog> getAllCatalogs();	
}
