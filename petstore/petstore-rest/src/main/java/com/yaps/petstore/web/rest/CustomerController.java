package com.yaps.petstore.web.rest;

import com.sun.jersey.api.core.InjectParam;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.CustomerDTO;
import com.yaps.petstore.service.customer.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/customer-management")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());

    @InjectParam
    CustomerService customerService;

@Path("/customers")
@GET
    public List<CustomerDTO> getCustomers() throws CheckException {
        return Collections.EMPTY_LIST;
    }


    public CustomerDTO getCustomer(Long customerId) throws CheckException {
        return null;
    }


    public CustomerDTO addCustomer(final CustomerDTO customerDto) throws CheckException {
        logger.info("start addCustomer");

        logger.debug("customerDto  = {}", customerDto.toString());

        return customerService.createCustomer(customerDto);
    }


    public CustomerDTO updateCustomer(Long customerId, final CustomerDTO customerDto) throws CheckException {
        logger.info("start updateCustomer");

        logger.debug("customerDto  = {}", customerDto.toString());

        return null;
    }


    public void deleteCustomer(Long customerId) throws CheckException {
        logger.info("start deleteCustomer");

        logger.debug("id to delete  = {}", customerId);

    }


}

