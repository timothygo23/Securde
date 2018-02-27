package com.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Product;

@Repository
public class ProductDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(Product product) {
		sessionFactory.getCurrentSession().save(product);
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
	public List<Product> getProductsOfCatalog(int catalog_id){
		List<Product> product = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("from Product where catalog_id =:ci", Product.class);
			query.setParameter("ci", catalog_id);
			
			product = query.getResultList();
		}
		
		return product;
	}
	
}
