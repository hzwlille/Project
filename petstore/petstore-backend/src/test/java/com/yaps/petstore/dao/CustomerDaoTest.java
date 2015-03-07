package com.yaps.petstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaps.petstore.model.Address;
import com.yaps.petstore.model.CreditCard;
import com.yaps.petstore.model.Customer;

import static com.yaps.petstore.utils.ConstantUtils.*;

public class CustomerDaoTest extends AbstractBaseDaoTestCase {


	
	
	
	@Autowired
    private CustomerDao customerDao;
    
	
    private Customer customer = null;
	
	
	@Before
	public void setUp() throws Exception {
		loadCustomer();
	}


	@After
	public void tearDown() throws Exception {
		customer = null;
	}

	
    @Test
    public void testCreateCustomer() throws Exception {
    	customerDao.saveOrUpdate(customer);
    	assertTrue("primary key assigned", customer.getId() != null);
    }	

    
    @Test
    public void testUpdateCustomer() throws Exception {

    	customerDao.saveOrUpdate(customer);
    	
    	customer.setFirstname(CUSTOMER_FIRST_NAME + "MDF");
    	customer.setLastname(CUSTOMER_LAST_NAME+ "MDF");
        
    	customerDao.saveOrUpdate(customer);
        
        Customer custMdf = customerDao.get(customer.getId());
        assertEquals(customer,custMdf);
    }  
    
    @Test
    public void testUpdateCustomerAddress() throws Exception {
    	Customer aCustomer = loadCustomerForAddressUpdate();
    	customerDao.saveOrUpdate(aCustomer);
    	assertTrue("primary key assigned", aCustomer.getId() != null);
    	
    	aCustomer.getAddress().setCity(ADDRESS_CITY + "MDF");
    	aCustomer.getAddress().setCity(ADDRESS_COUNTRY + "MDF");
    	aCustomer.getAddress().setCity(ADDRESS_STATE + "MDF");
    	
    	customerDao.saveOrUpdate(aCustomer);
    	
    	Customer mdfCustomer = customerDao.get(aCustomer.getId());
        assertEquals(aCustomer,mdfCustomer);
    }    

    
    
    @Test
    public void testGetCustomer() throws Exception {

    	customerDao.saveOrUpdate(customer);
        
        Customer cust = customerDao.get(customer.getId());

        assertNotNull(customer);
        assertEquals(customer,cust);
    }
    
    
    @Test
    public void testRemoveCustomer() throws Exception {

    	customerDao.saveOrUpdate(customer);
        
    	Customer cust = customerDao.get(customer.getId());

        assertNotNull(cust.getId());
        assertEquals(customer,cust);
        
        customerDao.delete(customer.getId());

        assertTrue(customerDao.findAll().size() == 0);
    }   

    
    @Test
    public void testGetCustomers() throws Exception {

    	customerDao.saveOrUpdate(customer);
        
    	assertTrue(customerDao.findAll().size() == 1);
        
        //Add another element
    	Customer customer2 = getAnotherCustomer();

    	customerDao.saveOrUpdate(customer2);
    	
    	//Verify that the list contains 2 elements 
        assertTrue(customerDao.findAll().size() == 2);

    }

    
    @Test
    public void testFindCustomerByEmail() throws Exception {

    	customerDao.saveOrUpdate(customer);
        
        Customer cust = customerDao.findByEmail(CUSTOMER_EMAIL);

        assertNotNull(customer);
        assertEquals(customer,cust);
    }    
    
    
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */
	private void loadCustomer() {
		customer = new Customer();
		customer.setFirstname(CUSTOMER_FIRST_NAME);
		customer.setLastname(CUSTOMER_LAST_NAME);
		customer.setTelephone(CUSTOMER_TELEPHONE);
		customer.setEmail(CUSTOMER_EMAIL);
		customer.setCreditCard(getCreditCard());
		customer.setAddress(getAddress());
	}
	
	private Customer getAnotherCustomer() {
		Customer myCustomer = new Customer();
		myCustomer.setFirstname(CUSTOMER_FIRST_NAME + "2");
		myCustomer.setLastname(CUSTOMER_LAST_NAME + "2");
		myCustomer.setTelephone(CUSTOMER_TELEPHONE + "2");
		myCustomer.setEmail(CUSTOMER_EMAIL + "2");
		myCustomer.setCreditCard(getCreditCard());
		myCustomer.setAddress(getAddress());
		return myCustomer;
	}	

	private Customer loadCustomerForAddressUpdate() {
		Customer aCustomer = new Customer();
		aCustomer.setFirstname(CUSTOMER_FIRST_NAME);
		aCustomer.setLastname(CUSTOMER_LAST_NAME);
		aCustomer.setTelephone(CUSTOMER_TELEPHONE);
		aCustomer.setEmail(CUSTOMER_EMAIL);
		aCustomer.setAddress(getAddress());
		return aCustomer;
	}	

	private CreditCard getCreditCard(){
		CreditCard creditCard = new CreditCard();
		creditCard.setCreditCardNumber(CREDIT_CARD_NUMBER);
		creditCard.setCreditCardExpiryDate(CREDIT_CARD_DATE);
		creditCard.setCreditCardType(CREDIT_CARD_TYPE);
		return creditCard;
	}
	

	private Address getAddress(){
		Address address = new Address();
		address.setCity(ADDRESS_CITY);
		address.setCountry(ADDRESS_COUNTRY);
		address.setState(ADDRESS_STATE);
		address.setStreet1(ADDRESS_STREET_1);
		address.setStreet2(ADDRESS_STREET_2);
		address.setZipcode(ADDRESS_ZIP_CODE);
		return address;
	}
	
}
