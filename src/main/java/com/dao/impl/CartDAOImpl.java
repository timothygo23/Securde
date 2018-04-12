package com.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

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

	@Transactional
	public List<Cart> getUserCart(int acc_id) {
		List<Cart> cart = null;
		
		if(sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Cart> query = session.createNativeQuery("select * from cart "
														     + "WHERE account_id =:acc_id" , Cart.class);
			query.setParameter("acc_id", acc_id);
			cart = query.getResultList();
		}
		
		for(Cart c : cart) {
			System.out.println("Product: " + c.getProduct_id() + "| Account: " + c.getAccount_id() + "|QTY: " + c.getQuantity());
		}
		
		return cart;
	}

}
