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
	public List<Cart> getUserCart(int acc_id, int cart_num) {
		List<Cart> cart = null;
		
		if(sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Cart> query = session.createNativeQuery("select * from cart " 
														     + "WHERE account_id =:acc_id " 
					                                         + "AND cart_id =:cart_num", Cart.class);
			query.setParameter("acc_id", acc_id);
			query.setParameter("cart_num", cart_num);
			cart = query.getResultList();
		}
		
		return cart;
	}
	
	@Transactional
	public void deleteCart(Cart cart) {
		
		if(sessionFactory != null) {
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Cart> query = session.createNativeQuery("delete from cart " +
																"WHERE cart_id =:cart_id AND " +
																"account_id =:account_id AND " +
																"product_id =:product_id AND " +
																"quantity =:quantity AND " +
																"product_avail_id =:pai ", 
																Cart.class);
			query.setParameter("cart_id", cart.getCart_id());
			query.setParameter("account_id", cart.getAccount_id());
			query.setParameter("product_id", cart.getProduct_id());
			query.setParameter("quantity", cart.getQuantity());
			query.setParameter("pai", cart.getProduct_avail_id());
			
			query.executeUpdate();
		}
		

	}

}
