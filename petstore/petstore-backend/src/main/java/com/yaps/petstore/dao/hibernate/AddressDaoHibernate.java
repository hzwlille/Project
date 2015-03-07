package com.yaps.petstore.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.yaps.petstore.dao.AddressDao;
import com.yaps.petstore.model.Address;


/**
 * 
 * @author zou
 *
 */

@Repository
public class AddressDaoHibernate extends AbstractGenericDaoHibernate<Address,Long> implements AddressDao {

}
