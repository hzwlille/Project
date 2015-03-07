package com.barkbank.verifier.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barkbank.v1.CreditCard;
import com.barkbank.v1.CreditCardPort;
import com.barkbank.v1.CreditCardRequest;
import com.barkbank.v1.CreditCardResponse;
import com.barkbank.verifier.service.Verifyer;


@javax.jws.WebService(
        serviceName = "CXFCreditCardService",
        portName = "CXFCreditCardService",
        targetNamespace = "http://barkbank.com/v1",
        endpointInterface = "com.barkbank.v1.CreditCardPort",
        wsdlLocation="classpath:/cxf-server.xml")
public class CreditCardServiceImpl implements CreditCardPort{


	Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class.getName());
	
	private Verifyer verifyer;
	
	public Verifyer getVerifyer() {
		return verifyer;
	}

	public void setVerifyer(Verifyer verifyer) {
		this.verifyer = verifyer;
	}

	public CreditCardResponse verifyOperation(
			CreditCardRequest creditCardRequest) {

		logger.debug("start verifyOperation");
		
		CreditCardVerify algoResponse = verifyer.verifyNumber(creditCardRequest.getCreditCard().getCreditCardNumber());
		
		CreditCardResponse response = new CreditCardResponse();
		response.setReturn(algoResponse.name());
		
		logger.debug("end verifyOperation");
		return response;
	}
	
	
	

}
