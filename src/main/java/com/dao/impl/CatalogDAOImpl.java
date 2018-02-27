package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Catalog;

@Repository
public class CatalogDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(Catalog catalog) {
		sessionFactory.getCurrentSession().save(catalog);
	}

	@Transactional
	public void edit(Catalog catalog) {
		sessionFactory.getCurrentSession().update(catalog);
	}

	@Transactional
	public void delete(int catalog_id) {
		sessionFactory.getCurrentSession().delete(getCatalog(catalog_id));
	}

	@Transactional
	public Catalog getCatalog(int catalog_id) {
		return (Catalog)sessionFactory.getCurrentSession().get(Catalog.class, catalog_id);
	}

	@Transactional
	public List<Catalog> getAllCatalogs() {
		List<Catalog> catalog = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			catalog = session.createQuery("from Catalog", Catalog.class).getResultList();
		}
		
		return catalog;
	}
	
}
