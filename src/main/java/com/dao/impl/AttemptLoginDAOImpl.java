package com.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.AttemptLogin;
import com.dao.AttemptLoginDAO;

@Repository
public class AttemptLoginDAOImpl implements AttemptLoginDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void add(AttemptLogin attemptLogin) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.save(attemptLogin);
		}
	}

	@Transactional
	public void edit(AttemptLogin attemptLogin) {
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			session.update(attemptLogin);
		}
	}

	@Transactional
	public AttemptLogin get(int account_id) {
		AttemptLogin attemptLogin = null;
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			attemptLogin = session.find(AttemptLogin.class, account_id);
		}
		return attemptLogin;
	}

}
