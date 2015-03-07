package com.yaps.petstore.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreditCardTest {

	private CreditCard creditCard;
	
	@Before
	public void setUp() throws Exception {
		creditCard = new CreditCard();
	}

	@After
	public void tearDown() throws Exception {
		creditCard = null;
	}

	@Test
	public void testIsDirty1() throws Exception{
		creditCard.setCreditCardNumber("creditCardNumber");

		assertTrue(creditCard.isDirty());
	}
	
	@Test
	public void testIsDirty2() throws Exception{
		creditCard.setCreditCardType("creditCardType");
		
		assertTrue(creditCard.isDirty());
	}	
	
	@Test
	public void testIsDirty3() throws Exception{
		creditCard.setCreditCardExpiryDate("creditCardExpiryDate");
		
		assertTrue(creditCard.isDirty());
	}	
	
	
	@Test
	public void testIsNotirty() throws Exception{
		assertFalse(creditCard.isDirty());
	}	
}
