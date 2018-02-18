package com.services;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Account;

@Component
public class LoggingService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Account logIn(String email, String password){
		Account account = null;

		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Account> query = session.createNativeQuery("select * from account "
														+ "where email = '" + email + "' and "
															+ "password = '" + password + "'", Account.class);
			List<Account> accounts = query.getResultList();
			if(!accounts.isEmpty())
				account = query.getResultList().get(0);
		}
		
		return account;
	}
	
	public void logOut(){
		//TODO remove session
		//TODO remove cookies
	}
}
