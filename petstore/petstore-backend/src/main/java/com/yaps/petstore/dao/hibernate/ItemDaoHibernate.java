package com.yaps.petstore.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yaps.petstore.dao.ItemDao;
import com.yaps.petstore.model.Item;


/**
 * 
 * @author zou
 *
 */
@Repository
public class ItemDaoHibernate extends AbstractGenericDaoHibernate<Item,Long> implements ItemDao {


	@SuppressWarnings("unchecked")
	public List<Item> findByProductId(Long productId) {
		return  (List<Item>)getSessionFactory().getCurrentSession().createQuery("from Item where product.idea = ? ").setParameter(0, productId).list();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Item> findByItemName(String searchedItemName) {
		String query = "from Item i where i.name like :name";
		return (List<Item>)getSessionFactory().getCurrentSession().createQuery(query).setParameter("name", "%"+searchedItemName+"%").list();
	}


}
