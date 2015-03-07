package com.yaps.petstore.model;
import org.junit.Test;

import com.yaps.petstore.common.exception.CheckException;

public class CustomerTest {
	
	//le test vérifie que si le password est null
	//la méthode renvoie une exception de type InvalidPasswordException
	@Test(expected=CheckException.class)
	public void testPasswordNull() throws Exception {
		Customer customer1 = new Customer();
		customer1.matchPassword(null);
	
	}

	//le test vérifie que si le password est une chaîne de caractère vide, la méthode renvoie une exception de type InvalidPasswordException
	@Test(expected=CheckException.class)
	public void testPasswordVide() throws Exception {
		Customer customer1 = new Customer();
		customer1.matchPassword(" ");
	}
		
	
	
	//le test vérifie que si le password passé en paramètre n’est pas égale au password du client (Customer), la méthode renvoie la valeur faux.
	@Test( expected=CheckException.class)
	public void testPasswordDifferent() throws Exception{
		Customer customer1 = new Customer();
		customer1.setPassword("trypassword");
		customer1.matchPassword("tryfauxpassword");
	}
	
	//le test vérifie que si le password passé en paramètre est égale au password du client (Customer), la méthode la valeur vraie.
	@Test
	public void testPasswordEgaux()throws Exception{
		Customer customer1 = new Customer();
		customer1.setPassword("trypassword");
		customer1.matchPassword("trypassword");
	}
}
