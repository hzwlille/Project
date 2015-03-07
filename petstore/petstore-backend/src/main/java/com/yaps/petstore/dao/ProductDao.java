package com.yaps.petstore.dao;


import java.util.List;

import com.yaps.petstore.model.Product;


public interface ProductDao extends GenericDao<Product, Long> {

    public List<Product> findByCategoryId(Long categoryId);

    
	public List<Product> findByCategoryName(String categoryName);
    
}
