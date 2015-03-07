package com.yaps.petstore.service.creditcard;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.CreditCardDTO;

public interface CreditCardService {

    void verifyCreditCard(final CreditCardDTO creditCardDto) throws CheckException;	
	
}
