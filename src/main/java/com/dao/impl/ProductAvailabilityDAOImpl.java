package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.beans.ProductAvailability;

public class ProductAvailabilityDAOImpl {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(ProductAvailability productAvailability) {
		sessionFactory.getCurrentSession().save(productAvailability);
	}

	@Transactional
	public void edit(ProductAvailability productAvailability) {
		sessionFactory.getCurrentSession().update(productAvailability);
	}

	@Transactional
	public void delete(int product_avail_id) {
		sessionFactory.getCurrentSession().delete(getProductAvailability(product_avail_id));
	}

	@Transactional
	public ProductAvailability getProductAvailability(int product_avail_id) {
		return (ProductAvailability)sessionFactory.getCurrentSession().get(ProductAvailability.class, product_avail_id);
	}

	@Transactional
	public List<ProductAvailability> getAllProductAvailabilitys() {
		List<ProductAvailability> ProductAvailability = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			ProductAvailability = session.createQuery("from product_availability", ProductAvailability.class).getResultList();
		}
		
		return ProductAvailability;
	}
}
