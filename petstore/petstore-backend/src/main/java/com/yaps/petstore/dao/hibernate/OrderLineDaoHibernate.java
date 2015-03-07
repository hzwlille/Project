package com.yaps.petstore.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.yaps.petstore.dao.OrderLineDao;
import com.yaps.petstore.model.OrderLine;


/**
 * 
 * @author zou
 *
 */

@Repository
public class OrderLineDaoHibernate extends AbstractGenericDaoHibernate<OrderLine,Long> implements OrderLineDao {

}
