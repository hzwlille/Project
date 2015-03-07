package com.yaps.petstore.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.yaps.petstore.dao.CategoryDao;
import com.yaps.petstore.model.Category;


/**
 * 
 * @author zou
 *
 */
@Repository
public class CategoryDaoHibernate extends AbstractGenericDaoHibernate<Category,Long> implements CategoryDao {

}
