package com.barkbank.verifier.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barkbank.verifier.service.Verifyer;

public class CreditCardVerifyerImpl implements Verifyer {

	Logger logger = LoggerFactory.getLogger(CreditCardVerifyerImpl.class.getName());
	
	public CreditCardVerify verifyNumber(String creditCardNumber) {
		logger.debug("start verifyAlgorithm");
		
		logger.debug("end verifyAlgorithm");		
		return null;
	}

}
