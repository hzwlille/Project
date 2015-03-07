package com.yaps.petstore.barkbank.client.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yaps.petstore.barkbank.client.impl.CreditCardFacadeImpl;
import com.yaps.petstore.model.CreditCardDTO;


@ContextConfiguration({ "classpath:/cxf-client.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditCardFacadeImplIntegrationTest {

	@Autowired
	CreditCardFacadeImpl facade;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCreditCardService() throws Exception {
		CreditCardDTO creditCardDTO = new CreditCardDTO();
		creditCardDTO.setCreditCardExpiryDate("202001");
		creditCardDTO.setCreditCardType("VISA");	
		creditCardDTO.setCreditCardNumber("00001111222233334444");
		
		String result = facade.verifyCreditCard(creditCardDTO );
		
		
	}

}
