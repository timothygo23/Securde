package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Cart;
import com.dao.CartDAO;

@Repository
public class CartDAOImpl implements CartDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(Cart cart) {
		sessionFactory.getCurrentSession().save(cart);
	}

	@Transactional
	public void edit(Cart cart) {
		sessionFactory.getCurrentSession().update(cart);
	}

	@Transactional
	public void delete(int cart_id) {
		sessionFactory.getCurrentSession().delete(getCart(cart_id));
	}

	@Transactional
	public Cart getCart(int cart_id) {
		return (Cart)sessionFactory.getCurrentSession().get(Cart.class, cart_id);
	}

	@Transactional
	public List<Cart> getAllCarts() {
		List<Cart> cart = null;
		
		if (sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			
			cart = session.createQuery("from cart", Cart.class).getResultList();
		}
		
		return cart;
	}

}
