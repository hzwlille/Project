package com.yaps.petstore.service.creditcard.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.barkbank.client.CreditCardFacade;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.CreditCardDTO;
import com.yaps.petstore.service.creditcard.CreditCardService;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

	Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class.getName());
	
	@Autowired
	private CreditCardFacade paymentService; 
	
    
	public CreditCardFacade getPaymentService() {
		return paymentService;
	}

	
	

	public void setPaymentService(CreditCardFacade paymentService) {
		this.paymentService = paymentService;
	}


	@Transactional(readOnly=true)	
	public void verifyCreditCard(CreditCardDTO creditCardDto) throws CheckException {
		
		
		try {
			paymentService.verifyCreditCard(creditCardDto);			
		}catch (Exception e){
			logger.error("payment exception {}",e.getMessage());
		}

	}

}
