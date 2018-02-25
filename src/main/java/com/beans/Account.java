package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	public static final int ADMIN = 1;
	public static final int BRAND_MANUFACTURER = 2;
	public static final int CUSTOMER = 3;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="account_id")
	private int account_id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="account_type")
	private int account_type;

	public Account() {}
	
	public Account(int account_id, String email, String password, int account_type) {
		this.account_id = account_id;
		this.email = email;
		this.password = password;
		this.account_type = account_type;
	}

	public Account(String email, String password, int account_type) {
		this.email = email;
		this.password = password;
		this.account_type = account_type;
	}
	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccount_type() {
		return account_type;
	}

	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	
}
