package com.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attempt_login")
public class AttemptLogin {
	
	@Id
	@Column(name="account_id")
	private int account_id;
	
	@Column(name="loginAttempts")
	private int loginAttempts;
	
	@Column(name="lastLogin")
	private Date lastLogin;
	
	@Column(name="lastFailedLogin")
	private Date lastFailedLogin;
	
	public AttemptLogin () {}
	
	public AttemptLogin(int account_id, int loginAttempts, Date lastLogin, Date lastFailedLogin){
		this.account_id = account_id;
		this.loginAttempts = loginAttempts;
		this.lastFailedLogin = lastFailedLogin;
		this.lastLogin = lastLogin;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastFailedLogin() {
		return lastFailedLogin;
	}

	public void setLastFailedLogin(Date lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
	}
	
}
