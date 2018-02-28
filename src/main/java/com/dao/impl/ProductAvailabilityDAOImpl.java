package com.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.ProductAvailability;

@Repository
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
	public List<ProductAvailability> getProductAvailability(int product_id) {
		List<ProductAvailability> ProductAvailability = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("from ProductAvailability where product_id =:product_id", ProductAvailability.class);
			query.setParameter("product_id", product_id);
			
			ProductAvailability = query.getResultList();
		}
		
		return ProductAvailability;
	}

	@Transactional
	public List<ProductAvailability> getAllProductAvailabilitys() {
		List<ProductAvailability> ProductAvailability = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			ProductAvailability = session.createQuery("from ProductAvailability", ProductAvailability.class).getResultList();
		}
		
		return ProductAvailability;
	}
}
