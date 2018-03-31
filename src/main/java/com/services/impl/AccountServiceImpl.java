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
import com.beans.BrandManufacturer;
import com.beans.Customer;
import com.dao.AccountDAO;
import com.services.AccountService;
import com.utility.Hash;

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
														+ "where email =:email" , Account.class);
			query.setParameter("email", email);
			
			List<Account> accounts = query.getResultList();
			if(!accounts.isEmpty()){
				Account tempAcc = query.getResultList().get(0);

				//test for password
				if(Hash.compare(tempAcc.getPassword(), password, tempAcc.getSalt()))
					account = tempAcc;
			}
			
		}
		
		return account;
	}
	
	public void logOut(HttpServletRequest request){
		request.getSession().invalidate();
		//TODO remove cookies
	}
}
