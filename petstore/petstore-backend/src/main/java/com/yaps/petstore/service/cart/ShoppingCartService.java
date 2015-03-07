package com.yaps.petstore.service.cart;

import java.util.Map;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.FinalCartDTO;

public interface ShoppingCartService {

	
	public FinalCartDTO getCartItems(Map<Long,Integer> map) throws CheckException;
	
}
