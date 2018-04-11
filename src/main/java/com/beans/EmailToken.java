package com.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="email_token")
public class EmailToken {
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="token")
	private String token;
	
	@Column(name="expirationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;
	
	public EmailToken() {}
	
	public EmailToken(String email, String token, Date expirationDate){
		this.email = email;
		this.token = token; 
		this.expirationDate = expirationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
