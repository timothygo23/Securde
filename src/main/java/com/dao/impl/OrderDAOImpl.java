package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Order;
import com.dao.OrderDAO;

@Repository
public class OrderDAOImpl implements OrderDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(Order order) {
		sessionFactory.getCurrentSession().save(order);
	}

	@Transactional
	public void edit(Order order) {
		sessionFactory.getCurrentSession().update(order);
	}

	@Transactional
	public void delete(int order_id) {
		sessionFactory.getCurrentSession().delete(getOrder(order_id));
	}

	@Transactional
	public Order getOrder(int order_id) {
		return (Order)sessionFactory.getCurrentSession().get(Order.class, order_id);
	}

	@Transactional
	public List<Order> getAllOrders() {
		List<Order> order = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			order = session.createQuery("from Order", Order.class).getResultList();
		}
		
		return order;
	}
	
}
