package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.EmailToken;
import com.dao.EmailTokenDAO;

@Repository
public class EmailTokenDAOImpl implements EmailTokenDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(EmailToken emailToken) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.save(emailToken);
		}
	}

	@Transactional
	public void edit(EmailToken emailToken) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.update(emailToken);
		}
	}

	@Transactional
	public void delete(String email) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			EmailToken emailToken = session.find(EmailToken.class, email);
			if(emailToken != null)
				session.delete(emailToken);
		}
	}
	
	@Transactional
	public void deleteExpired() {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			List<EmailToken> tokens = session.createQuery("from EmailToken where expirationDate < NOW()", EmailToken.class)
					.getResultList();
			
			for(EmailToken t : tokens){
				session.delete(t);
			}
		}
	}
	
	

	@Transactional
	public EmailToken get(String token) {
		EmailToken emailToken = null;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			List<EmailToken> tokens = session.createQuery("from EmailToken where token =:token", EmailToken.class)
					.setParameter("token", token).getResultList();
			
			if(tokens != null && !tokens.isEmpty())
				emailToken = tokens.get(0);
		}
		
		return emailToken;
	}

	@Transactional
	public boolean doesEmailExist(String email) {
		EmailToken emailToken = null;
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			emailToken = session.find(EmailToken.class, email);
		}
		return  emailToken != null;
	}

}
