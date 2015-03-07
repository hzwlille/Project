package com.yaps.petstore.model;

import java.util.List;

public class FinalCartDTO implements AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 520440095782893659L;

	private List<ShoppingCartItemDTO> cartItems;
	
	private double total;

	public List<ShoppingCartItemDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<ShoppingCartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
	
	
}
