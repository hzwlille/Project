package com.yaps.petstore.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yaps.petstore.dao.CustomerDao;
import com.yaps.petstore.model.Customer;


/**
 * 
 * @author zou
 *
 */

@Repository
public class CustomerDaoHibernate extends AbstractGenericDaoHibernate<Customer,Long> implements CustomerDao {

	public Customer findByEmail(String email) {
		@SuppressWarnings("unchecked")
		List<Customer> lst =   getSessionFactory().getCurrentSession().createQuery("from Customer where email = ? ").setParameter(0, email).list();
		return (lst.isEmpty() ? null : lst.get(0));
	}	
	

}
