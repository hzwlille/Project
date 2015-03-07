package com.yaps.petstore.service.customer.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.NotFoundException;
import com.yaps.petstore.dao.AddressDao;
import com.yaps.petstore.dao.CustomerDao;
import com.yaps.petstore.model.Customer;
import com.yaps.petstore.model.CustomerDTO;
import com.yaps.petstore.service.customer.CustomerService;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	public static final String CUSTOMER_INVALID_ID = "Invalid id";
	public static final String CUSTOMER_INVALID_EMAIL = "Invalid email";	
	public static final String CUSTOMER_IS_NULL = "customer object is null";
	public static final String CUSTOMER_INVALID_INPUT = "Invalid input";
	public static final String CUSTOMER_DO_NOT_EXIST = "Customer do not exist";

	@Autowired
	protected CustomerDao customerDao;
	
	@Autowired
	protected AddressDao addressDao;

	@Autowired
	protected Mapper mapper;
	
	
	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}	
	
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	
	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	
	@Transactional(readOnly=true)
	public CustomerDTO authenticate(String email, String password)
			throws CheckException {
		
		if (email == null || "".equals(email))
            throw new CheckException(CUSTOMER_INVALID_INPUT);
		
		Customer customer = customerDao.findByEmail(email);
		
		customer.matchPassword(password);
				
        CustomerDTO customerWorkDto = mapper.map(customer, CustomerDTO.class);		
		return customerWorkDto;
	}

	
	@Transactional
	public CustomerDTO createCustomer(CustomerDTO customerDto) throws CheckException {
		if (customerDto == null)
            throw new CheckException(CUSTOMER_IS_NULL);
        
        Customer customer = mapper.map(customerDto, Customer.class);
        
		customerDao.saveOrUpdate(customer);
		
        CustomerDTO customerWorkDto = mapper.map(customer, CustomerDTO.class);
        
        return customerWorkDto;
	}

	
	@Transactional(readOnly=true)
	public void deleteCustomer(Long customerId) throws CheckException {
		if (customerId == null || "".equals(customerId))
            throw new CheckException(CUSTOMER_INVALID_ID);
		
		customerDao.delete(customerId);

	}

	
	@Transactional(readOnly=true)
	public CustomerDTO findCustomer(Long customerId) throws CheckException {
		if (customerId == null || "".equals(customerId))
            throw new CheckException(CUSTOMER_INVALID_ID);

		Customer customer = customerDao.get(customerId) ;
        CustomerDTO customerWorkDto = mapper.map(customer, CustomerDTO.class);
        return customerWorkDto;
	}

	
	@Transactional(readOnly=true)
	public List<CustomerDTO> findCustomers() {
		List<Customer> lst = customerDao.findAll();
		List<CustomerDTO> lstDto = new ArrayList<CustomerDTO>();
		for (Customer customer:lst){
	        CustomerDTO customerDto = mapper.map(customer, CustomerDTO.class);
	        lstDto.add(customerDto);
		}
		
		return lstDto;		
	}

	
	@Transactional
	public void updateCustomer(CustomerDTO customerDto) throws CheckException {
		if (customerDto == null)
            throw new CheckException(CUSTOMER_IS_NULL);
        
		if (customerDto.getId() == null)
            throw new CheckException(CUSTOMER_INVALID_ID);
		
        Customer customer = mapper.map(customerDto, Customer.class);
        
		customerDao.saveOrUpdate(customer);	
	}

	
	@Transactional(readOnly=true)
	public CustomerDTO findCustomerById(String email) throws CheckException, NotFoundException {
		if (email == null || "".equals(email))
            throw new CheckException(CUSTOMER_INVALID_EMAIL);

		Customer customer =  customerDao.findByEmail(email);

		if (customer == null){
			throw new NotFoundException(CUSTOMER_DO_NOT_EXIST);
		}
		
		CustomerDTO customerWorkDto = mapper.map(customer, CustomerDTO.class);
        return customerWorkDto;
	}

}
