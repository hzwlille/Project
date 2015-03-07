package com.yaps.petstore.web.rest;

import com.yaps.petstore.model.CustomerDTO;
import com.yaps.petstore.service.customer.CustomerService;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;


/**
 * Created with IntelliJ IDEA.
 * User: alexandre_godet
 * Date: 23/02/2014
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class CustomerControllerTest {

    @Ignore
    @Test
    public void testAddCustomer() throws Exception {
        CustomerController customerController = new CustomerController();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(32L);

        customerController.customerService = Mockito.mock(CustomerService.class);

        Mockito.when(customerController.customerService.createCustomer(customerDTO)).thenReturn(customerDTO);
        Mockito.when(customerController.customerService.findCustomer(customerDTO.getId())).thenReturn(customerDTO);

        CustomerDTO returnCustomer = customerController.addCustomer(customerDTO);

        Mockito.verify(customerController.customerService).createCustomer(customerDTO);
        Mockito.verify(customerController.customerService).findCustomer(32L);
        assertEquals(32L, returnCustomer.getId().longValue());

    }

    
}
