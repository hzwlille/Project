package com.barkbank.verifier.service;

import com.barkbank.verifier.service.impl.CreditCardVerify;

public interface Verifyer {

	public CreditCardVerify verifyNumber(String creditCardNumber);

}
