package com.yaps.petstore.service.cart.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.common.exception.CartIsEmptyException;
import com.yaps.petstore.common.exception.CartIsNullException;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.FinalCartDTO;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.ShoppingCartItemDTO;
import com.yaps.petstore.service.cart.ShoppingCartService;
import com.yaps.petstore.service.catalog.CatalogService;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService{

	public static final String CART_IS_NULL = "cart map is null";

	public static final String CART_IS_EMPTY = "cart map is empty";	
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CatalogService catalogService;

	public CatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yaps.petstore.service.cart.ShoppingCartService#getCartItems(java.util.Map)
	 */
	@Transactional(readOnly=true)
	public FinalCartDTO getCartItems(Map<Long, Integer> map)
			throws CheckException {

		logger.debug("start getCartItems");

		if (map == null){
			throw new CartIsNullException(CART_IS_NULL);
		}
            

		if (map.keySet().size() == 0){
			throw new CartIsEmptyException(CART_IS_EMPTY);
		}
		
		
		final ArrayList<ShoppingCartItemDTO> carts = new ArrayList<ShoppingCartItemDTO>();
		double total = 0.0;

		FinalCartDTO finalCart = new FinalCartDTO();		
		finalCart.setCartItems(carts);
		
		final Iterator<Long> it = map.keySet().iterator();
        
        while (it.hasNext()) {
            final Long key = it.next();
            
            final Integer value = map.get(key);
            
            ShoppingCartItemDTO ciDTO ;
                ItemDTO itemDTO = catalogService.findItem(key);
                logger.debug("apres itemDTO = CatalogDelegate.findItem(key)");
                
                // convert catalog item to cart item
                ciDTO = new ShoppingCartItemDTO(itemDTO.getId(),
                        itemDTO.getName(),
                        itemDTO.getProduct().getDescription(),
                        value.intValue(),
                        itemDTO.getUnitCost());
        
                carts.add(ciDTO);
                
                total = total + (value.doubleValue() * itemDTO.getUnitCost()); 
                
                logger.debug("apres items.add(ciDTO)");
        }
		finalCart.setTotal(total);
        
        logger.debug("end getCartItems");
        
        return finalCart;
	}

}
