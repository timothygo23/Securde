package com.services.impl;

import java.util.Date;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Account;
import com.beans.AttemptLogin;
import com.beans.BrandManufacturer;
import com.beans.Customer;
import com.dao.AccountDAO;
import com.dao.impl.AttemptLoginDAOImpl;
import com.services.AccountService;
import com.utility.Hash;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private int loginThreshold = 5;
	private int lockMinutes = 5;

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
			
			if(account != null){
				AttemptLogin attemptLogin = session.find(AttemptLogin.class, account.getAccount_id());
				attemptLogin.setLoginAttempts(0);
				attemptLogin.setLastLogin(new Date());
				session.update(attemptLogin);
			}
			
		}
		
		return account;
	}
	
	/*
	 * this function checks if the current account is locked
	 */
	@Transactional
	public boolean isLockedout(String email){
		boolean locked = false;
		
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			
			//gets the account of the email
			TypedQuery<Account> query = session.createNativeQuery("select * from account "
					+ "where email =:email" , Account.class);
			query.setParameter("email", email);

			List<Account> accounts = query.getResultList();
			Account account = null;
			if(accounts != null && !accounts.isEmpty()){
				account = query.getResultList().get(0);
			}
			
			if(account != null){
				AttemptLogin attemptLogin = session.find(AttemptLogin.class, account.getAccount_id());
				
				if(attemptLogin == null){
					//create new one, return false
					attemptLogin = new AttemptLogin();
					attemptLogin.setAccount_id(account.getAccount_id());
					attemptLogin.setLoginAttempts(0);
					session.save(attemptLogin);
					return false;
				}else{
					//check if locked
					if(attemptLogin.getLoginAttempts() >= loginThreshold){
						//check last failed login attempt
						Date now = new Date(new Date().getTime());
						if(attemptLogin.getLastFailedLogin().getTime() + 60000 * lockMinutes > now.getTime()){
							//the account is still locked
							return true;
						}else{
							//reset
							attemptLogin.setLoginAttempts(0);
							attemptLogin.setLastFailedLogin(null);
							session.update(attemptLogin);
							return false;
						}
					}else{
						return false;
					}
				}
			}
		}
		
		return locked;
	}
	
	/*
	 * this function increments the login attempt of the account
	 */
	@Transactional
	public void failedLogin(String email){
		if(sessionFactory != null){
			Session session = sessionFactory.getCurrentSession();
			
			//gets the account of the email
			TypedQuery<Account> query = session.createNativeQuery("select * from account "
					+ "where email =:email" , Account.class);
			query.setParameter("email", email);

			List<Account> accounts = query.getResultList();
			Account account = null;
			if(accounts != null && !accounts.isEmpty()){
				account = query.getResultList().get(0);
			}
			
			if(account != null){
				AttemptLogin attemptLogin = session.find(AttemptLogin.class, account.getAccount_id());
				attemptLogin.setLoginAttempts(attemptLogin.getLoginAttempts() + 1);
				attemptLogin.setLastFailedLogin(new Date());
				session.update(attemptLogin);
			}
		}
	}
	
	public void logOut(HttpServletRequest request){
		request.getSession().invalidate();
		//TODO remove cookies
	}
}
