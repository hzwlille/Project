package com.yaps.petstore.barkbank.client;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.CreditCardDTO;

public interface CreditCardFacade {

	
	public String verifyCreditCard(CreditCardDTO creditCardDTO) throws CheckException;	
	
}