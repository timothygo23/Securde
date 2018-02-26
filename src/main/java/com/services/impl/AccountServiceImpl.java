package com.services.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Account;
import com.beans.Customer;
import com.dao.AccountDAO;
import com.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//Add account and customer.
	@Transactional
	public void addAccount(Account account, Customer customer){
		Session session = sessionFactory.getCurrentSession();
		
		customer.setAccount_id((int) session.save(account)); //"save" returns the ID of this entity.
		session.save(customer);
	}

	@Transactional
	public Account logIn(String email, String password){
		Account account = null;

		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			TypedQuery<Account> query = session.createNativeQuery("select * from account "
														+ "where email =:email and "
															+ "password =:password", Account.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			List<Account> accounts = query.getResultList();
			if(!accounts.isEmpty())
				account = query.getResultList().get(0);
		}
		
		return account;
	}
	
	public void logOut(HttpServletRequest request){
		request.getSession().invalidate();
		//TODO remove cookies
	}
}
