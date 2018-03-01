package com.beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int order_id;
	@Column(name="cart_id")
	private int cart_id;
	@Column(name="purchase_date")
	private Calendar purchase_date;
	@Column(name="arrival_date")
	private Calendar arrival_date;
	
	public Order() {}
	
	public Order(int order_id, int cart_id, Calendar purchase_date, Calendar arrival_date) {
		super();
		this.order_id = order_id;
		this.cart_id = cart_id;
		this.purchase_date = purchase_date;
		this.arrival_date = arrival_date;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public Calendar getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Calendar purchase_date) {
		this.purchase_date = purchase_date;
	}

	public Calendar getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Calendar arrival_date) {
		this.arrival_date = arrival_date;
	}
	
	@Transient
	public String getPurchaseDateAsString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return (String) dateFormat.format(this.purchase_date.getTime());
	}
	
	@Transient
	public String getArrivalDateAsString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return (String) dateFormat.format(this.arrival_date.getTime());
	}

}
