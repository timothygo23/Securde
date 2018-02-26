package com.dao;

import java.util.List;

import com.beans.Order;

public interface OrderDAO {
	public void add(Order order);
	public void edit(Order order);
	public void delete(int order_id);
	public Order getOrder(int order_id);
	public List<Order> getAllOrders();	
}
