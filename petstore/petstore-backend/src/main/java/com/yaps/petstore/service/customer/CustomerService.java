package com.yaps.petstore.service.customer;

import java.util.List;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.NotFoundException;
import com.yaps.petstore.model.Customer;
import com.yaps.petstore.model.CustomerDTO;

/**
 * This interface gives a remote view of the CustomerServiceBean. Any distant manualyLoadedClient that wants to call
 * a method on the CustomerServiceBean has to use this interface. Because it extends the EJBObject interface
 * (which extends Remote), every method must throw RemoteException.
 */
public interface CustomerService  {

    // ======================================
    // =           Business methods         =
    // ======================================

    /**
     * Given an id and password, this method checks if the password is the right one for
     * this customer identifier. If it matches, the method returns a Customer with
     * all the customer data.
     *
     * @param email
     * @param password   to be checked if it's the right one
     * @return CustomerDTO customer data
     * @throws CheckException  is thrown if a invalid data or password is found
     */
    CustomerDTO authenticate(String email, String password) throws  CheckException;

    /**
     * Given a Customer object, this method creates a Customer. It first transforms
     * a Customer into a Customer domain object, uses the Customer object to apply the
     * business rules for creation. It then transforms back the Customer object into a
     * Customer.
     *
     * @param customerDto
     * @return Customer DTO
     * @throws CheckException
     */
    CustomerDTO createCustomer(CustomerDTO customerDto) throws  CheckException;

    /**
     * Given an id this method uses the Customer domain object to load all the data of this
     * object. It then transforms this object into a Customer and returns it.
     *
     * @param customerId identifier
     * @return Customer
     * @throws CheckException          is thrown if a invalid data is found
     */
    CustomerDTO findCustomer(Long customerId) throws  CheckException;
    
    
    /**
     * Given an email (the business model unique identifier), this method uses the Customer domain object to load all the data of this
     * object. It then transforms this object into a CustomerDTO and returns it. 
     * 
     * @param email
     * @return
     * @throws CheckException
     * @throws NotFoundException
     */
    CustomerDTO findCustomerById(String email) throws  CheckException, NotFoundException;    

    /**
     * Given an id, this method finds a Customer domain object and then calls its deletion
     * method.
     *
     * @param customerId identifier
     * @throws CheckException  is thrown if a invalid data is found
     */
    void deleteCustomer(Long customerId) throws CheckException;

    /**
     * Given a Customer object, this method updates a Customer. It first transforms
     * a Customer into a Customer domain object and uses the Customer object to apply the
     * business rules for modification.
     *
     * @param customer cannot be null.
     * @throws CheckException  is thrown if a invalid data is found
     */
    void updateCustomer(CustomerDTO customerDto) throws CheckException;

    /**
     * This method return all the customers from the system. It uses the Customer domain object
     * to get the data. It then transforms this collection of Customer object into a
     * collection of Customer and returns it.
     *
     * @return a collection of Customer
     */
    List<CustomerDTO> findCustomers() ;
}
