package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	//info
	@Id
	@Column(name="account_id")
	private int account_id;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="contact_number")
	private String contact_number;
	
	//address
	@Column(name="address1")
	private String address1;	
	@Column(name="address2")
	private String address2;
	@Column(name="city")
	private String city;
	@Column(name="province")
	private String province;
	@Column(name="zip_code")
	private String zip_code;
	
	//payment
	@Column(name="credit_card_num")
	private String credit_card_num;
	
	public Customer() {}

	public Customer(int account_id, String first_name, String last_name, String contact_number, String address1, String address2, String city,
			String province, String zip_code, String credit_card_num) {
		this.account_id = account_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.contact_number = contact_number;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.zip_code = zip_code;
		this.credit_card_num = credit_card_num;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getCredit_card_num() {
		return credit_card_num;
	}

	public void setCredit_card_num(String credit_card_num) {
		this.credit_card_num = credit_card_num;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

}
