package com.yaps.petstore.dao;




import java.util.List;

import com.yaps.petstore.model.Item;


public interface ItemDao extends GenericDao<Item, Long>   {

    public List<Item> findByProductId(Long productId);

    public List<Item> findByItemName(String itemName);    
	
}
