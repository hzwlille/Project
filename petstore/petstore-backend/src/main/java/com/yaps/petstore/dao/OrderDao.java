package com.yaps.petstore.dao;

import java.util.List;

import com.yaps.petstore.model.Order;


public interface OrderDao extends GenericDao<Order, Long> {

	public List<Order> findByCustomerId(Long customerId);	
	
}
