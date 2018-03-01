package com.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Product;
import com.dao.ProductDAO;
import com.json.Filter;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public int add(Product product) {
		return (Integer) sessionFactory.getCurrentSession().save(product);
	}

	@Transactional
	public void edit(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}

	@Transactional
	public void delete(int product_id) {
		sessionFactory.getCurrentSession().delete(getProduct(product_id));
	}

	@Transactional
	public Product getProduct(int product_id) {
		return (Product)sessionFactory.getCurrentSession().get(Product.class, product_id);
	}

	@Transactional
	public List<Product> getAllProducts() {
		List<Product> product = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			product = session.createQuery("from Product", Product.class).getResultList();
		}
		
		return product;
	}
	
	@Transactional
	public List<Product> getProductsOfCatalog(int catalog_id, Filter filter){
		List<Product> product = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			Query query = null;
			query = session.createQuery("from Product where catalog_id =:ci", Product.class);
			query.setParameter("ci", catalog_id);
			product = query.getResultList();
			
			if(filter == null){
				/*query = session.createQuery("from Product where catalog_id =:ci", Product.class);
				query.setParameter("ci", catalog_id);*/
			}else{
				//filtered query
				/*
				 * TODO FIX THIS WITH LEGIT SQL FILTER
				 */
				List<Product> removeProduct = new ArrayList<Product>();
				for(Product p : product){
					boolean pass = true;
					int filterPriceMin = 0;
					int filterPriceMax = 0;
					
					//price filter
					if(!filter.getPriceRange()[0].equals("")){
						if(filter.getPriceRange().length > 1){
							filterPriceMin = Integer.parseInt(filter.getPriceRange()[0]);
							filterPriceMax = Integer.parseInt(filter.getPriceRange()[1]);
						}else{
							filterPriceMin = Integer.parseInt(filter.getPriceRange()[0]);
							filterPriceMax = 0;
						}
						
						//price 
						if(p.getPrice() > filterPriceMin){
							if(filterPriceMax != 0){
								if(p.getPrice() > filterPriceMax){
									pass = false;
								}
							}
						}else{
							pass = false;
						}
					}
					
					//brands
					if(!filter.getBrands()[0].equals("")){
						if(!Arrays.asList(filter.getBrands()).contains(p.getBrand_name())){
							pass = false;
						}
					}
					
					if(!pass){
						removeProduct.add(p);
					}
				}
				
				for(Product rp : removeProduct){
					product.remove(rp);
				}
			}
			
			//product = query.getResultList();
		}
		
		return product;
	}
	
	@Transactional
	public List<Product> getSearched (String searchKey, Filter filter){
		List<Product> product = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			Query query = null;
			
			String searchKey1 = "'" + searchKey + "%'";
			String searchKey2 = "'% " + searchKey + "%'";
			
			query = session.createQuery("from Product "
					+ "where brand_name like " + searchKey1 + " or brand_name like " + searchKey2 + " or "
						+ "product_name like " + searchKey1 + " or product_name like " + searchKey2, Product.class);
			product = query.getResultList();
			
			if(filter == null){
				/*query = session.createQuery("from Product "
						+ "where brand_name like " + searchKey1 + " or brand_name like " + searchKey2 + " or "
							+ "product_name like " + searchKey1 + " or product_name like " + searchKey2, Product.class);*/
			}else{
				//filtered query
				/*
				 * TODO FIX THIS WITH LEGIT SQL FILTER
				 */
				List<Product> removeProduct = new ArrayList<Product>();
				for(Product p : product){
					boolean pass = true;
					int filterPriceMin = 0;
					int filterPriceMax = 0;
					
					//price filter
					if(!filter.getPriceRange()[0].equals("")){
						if(filter.getPriceRange().length > 1){
							filterPriceMin = Integer.parseInt(filter.getPriceRange()[0]);
							filterPriceMax = Integer.parseInt(filter.getPriceRange()[1]);
						}else{
							filterPriceMin = Integer.parseInt(filter.getPriceRange()[0]);
							filterPriceMax = 0;
						}
						
						//price 
						if(p.getPrice() > filterPriceMin){
							if(filterPriceMax != 0){
								if(p.getPrice() > filterPriceMax){
									pass = false;
								}
							}
						}else{
							pass = false;
						}
					}
					
					//brands
					if(!filter.getBrands()[0].equals("")){
						if(!Arrays.asList(filter.getBrands()).contains(p.getBrand_name())){
							pass = false;
						}
					}
					
					if(!pass){
						removeProduct.add(p);
					}
				}
				
				for(Product rp : removeProduct){
					product.remove(rp);
				}
			}
			
			//product = query.getResultList();
		}
		
		return product;
	}
	
}
