package com.yaps.petstore.dao;

import com.yaps.petstore.model.Customer;



public interface CustomerDao extends GenericDao<Customer, Long> {

	public Customer findByEmail(String email) ;
    
}
