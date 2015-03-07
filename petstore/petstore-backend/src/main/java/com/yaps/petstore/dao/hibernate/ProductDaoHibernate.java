package com.yaps.petstore.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yaps.petstore.dao.ProductDao;
import com.yaps.petstore.model.Product;


/**
 * 
 * @author zou
 *
 */

@Repository
public class ProductDaoHibernate extends AbstractGenericDaoHibernate<Product,Long> implements ProductDao{

	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryId(Long categoryId) {
		return (List<Product>) getSessionFactory().getCurrentSession().createQuery("from Product where category.id = ? ").setParameter(0, categoryId).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryName(String categoryName) {
		return (List<Product>) getSessionFactory().getCurrentSession().createQuery("from Product where category.name = ? ").setParameter(0, categoryName).list();
	}	
  
}
