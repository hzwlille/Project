package com.yaps.petstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaps.petstore.model.Address;

import static com.yaps.petstore.utils.ConstantUtils.*;

public class AddressDaoTest  extends AbstractBaseDaoTestCase{

	
	
	@Autowired
    private AddressDao addressDao;
    
	
    private Address address = null;
    
    @Before
    public void setUp(){
    	loadAddress();
    }

    
    @After
    public void tearDown(){
    	address = null;
    	addressDao = null;
    }


    @Test
    public void testCreateAddress() throws Exception {
    	addressDao.saveOrUpdate(address);
    	assertTrue("primary key assigned", address.getId() != null);
    }  
    
    @Test
    public void testUpdateAddress() throws Exception {

    	addressDao.saveOrUpdate(address);
    	
    	address.setCity(ADDRESS_CITY + "MDF");
    	address.setCountry(ADDRESS_COUNTRY + "MDF");
    	address.setState(ADDRESS_STATE + "MDF");
    	address.setStreet1(ADDRESS_STREET_1 + "MDF");
    	address.setStreet2(ADDRESS_STREET_2 + "MDF");
    	address.setZipcode(ADDRESS_ZIP_CODE + "MDF");
        
    	addressDao.saveOrUpdate(address);
        
        Address addressMdf = addressDao.get(address.getId());
        assertEquals(address,addressMdf);
    }  

    @Test
    public void testGetAddress() throws Exception {

    	addressDao.saveOrUpdate(address);
        
        Address myAddress = addressDao.get(address.getId());

        assertNotNull(myAddress);
        assertEquals(address,myAddress);
    }   

    @Test
    public void testRemoveAddress() throws Exception {

    	addressDao.saveOrUpdate(address);
        
    	Address myAddress = addressDao.get(address.getId());

        assertNotNull(myAddress);
        assertEquals(address,myAddress);
        
        addressDao.delete(address.getId());

        assertTrue(addressDao.findAll().size() == 0);
    }   
    
    
    @Test
    public void testGetAddresses() throws Exception {

    	addressDao.saveOrUpdate(address);
        
    	assertTrue(addressDao.findAll().size() == 1);
        
        //Add another element
    	Address address2 = getAnotherAddress();

    	addressDao.saveOrUpdate(address2);
    	
    	//Verify that the list contains 2 elements 
        assertTrue(addressDao.findAll().size() == 2);

    }

    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */    
	private void loadAddress() {
		address = new Address();
		address.setCity(ADDRESS_CITY);
		address.setCountry(ADDRESS_COUNTRY);
		address.setState(ADDRESS_STATE);
		address.setStreet1(ADDRESS_STREET_1);
		address.setStreet2(ADDRESS_STREET_2);
		address.setZipcode(ADDRESS_ZIP_CODE);
	}
	
	private Address getAnotherAddress() {
		Address myAddress = new Address();
		myAddress.setCity(ADDRESS_CITY  + "2");
		myAddress.setCountry(ADDRESS_COUNTRY + "2");
		myAddress.setState(ADDRESS_STATE + "2");
		myAddress.setStreet1(ADDRESS_STREET_1 + "2");
		myAddress.setStreet2(ADDRESS_STREET_2 + "2");
		myAddress.setZipcode(ADDRESS_ZIP_CODE + "2");
		return myAddress;
	}	
   
    
}
