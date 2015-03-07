package com.yaps.petstore.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yaps.petstore.dao.OrderDao;
import com.yaps.petstore.model.Order;


/**
 * 
 * @author zou
 *
 */

@Repository
public class OrderDaoHibernate extends AbstractGenericDaoHibernate<Order,Long> implements OrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByCustomerId(Long customerId) {
		return (List<Order>) getSessionFactory().getCurrentSession().createQuery("from Order where customer.id = ? ").setParameter(0, customerId).list();
	}

}
