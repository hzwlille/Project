package com.yaps.petstore.service.customer.impl;

import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_EMAIL;
import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_FIRST_NAME;
import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_LAST_NAME;
import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_TELEPHONE;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.NotFoundException;
import com.yaps.petstore.dao.AddressDao;
import com.yaps.petstore.dao.CustomerDao;
import com.yaps.petstore.model.Customer;
import com.yaps.petstore.model.CustomerDTO;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@Mock
	protected CustomerDao customerDaoMock;

	@Mock
	protected AddressDao addressDaoMock;

	private CustomerServiceImpl customerService;
	
	private Mapper mapper;
	

	@Before
	public void setUp() throws Exception {
		customerService = new CustomerServiceImpl();
		customerService.setAddressDao(addressDaoMock);
		customerService.setCustomerDao(customerDaoMock);
		
		mapper = new DozerBeanMapper();
		customerService.setMapper(mapper);
	}

	@After
	public void tearDown() throws Exception {
		mapper = null;
		customerService = null;
	}


	@Rule
	public ExpectedException exception = ExpectedException.none();

	
	@Test
	public void testInvalidInput() throws Exception {
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(CustomerServiceImpl.CUSTOMER_INVALID_INPUT));
		
		customerService.authenticate(null,"password");
	}

	
	@Test
	public void testInvalidPasswordEmpty() throws Exception {
		Long id = Long.valueOf(1);
		
		Customer customer = new Customer();
		customer.setId(id);
		customer.setPassword("password");
		when(customerDaoMock.findByEmail(CUSTOMER_EMAIL)).thenReturn(customer);

		exception.expect(CheckException.class);
		exception.expectMessage(containsString(Customer.INVALID_PASSWORD));
		
		customerService.authenticate(CUSTOMER_EMAIL,"");		
	}
	
	
	@Test
	public void testInvalidPasswordNull() throws Exception {
		Long id = Long.valueOf(1);
		
		Customer customer = new Customer();
		customer.setId(id);
		customer.setPassword("password");
		when(customerDaoMock.findByEmail(CUSTOMER_EMAIL)).thenReturn(customer);

		exception.expect(CheckException.class);
		exception.expectMessage(containsString(Customer.INVALID_PASSWORD));
		
		customerService.authenticate(CUSTOMER_EMAIL,null);		
	}	
	
	
	
	@Test
	public void testPasswordDontMatch() throws Exception {
		Customer customer = new Customer();
		customer.setPassword("falsePassword");
		when(customerDaoMock.findByEmail(CUSTOMER_EMAIL)).thenReturn(customer);

		exception.expect(CheckException.class);
		exception.expectMessage(containsString(Customer.PASSWORD_DONT_MATCH));
		
		customerService.authenticate(CUSTOMER_EMAIL,"password");		
	}
	

	@Test
	public void testAuthenticateOK() throws Exception {
		Long id = Long.valueOf(1);
		String password = "password";
		
		Customer customer = new Customer();
		customer.setId(id);
		customer.setPassword(password);
		CustomerDTO expectedCustomer = mapper.map(customer, CustomerDTO.class);
		
		when(customerDaoMock.findByEmail(CUSTOMER_EMAIL)).thenReturn(customer);

		CustomerDTO custDto = customerService.authenticate(CUSTOMER_EMAIL,password);
		
		assertNotNull(custDto);
		assertEquals(expectedCustomer,custDto);
		verify(customerDaoMock).findByEmail(CUSTOMER_EMAIL);
	}
	
	
	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */

	
	@Test
	public void testCreateCustomerWithNullObject() throws Exception {
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(CustomerServiceImpl.CUSTOMER_IS_NULL));
		
		customerService.createCustomer(null);
	}	
	
	@Test
	public void testCreateCustomer() throws Exception {
		Long id = Long.valueOf(1);
		CustomerDTO customerDto = getCustomerDTO(id);

		CustomerDTO aCustomerDto = customerService.createCustomer(customerDto);

		assertNotNull(aCustomerDto);
		assertEquals(customerDto,aCustomerDto);
		
	}

	@Test
	public void testFindCustomerWithNullId() throws Exception {
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(CustomerServiceImpl.CUSTOMER_INVALID_ID));
		
		customerService.findCustomer(null);
	}
	
	@Test
	public void testFindCustomerByEmailWithNullEmail() throws Exception {
		
		exception.expect(NotFoundException.class);
		exception.expectMessage(containsString(CustomerServiceImpl.CUSTOMER_DO_NOT_EXIST));
		
		when((customerDaoMock).findByEmail(CUSTOMER_EMAIL)).thenReturn(null);		
		
		customerService.findCustomerById(CUSTOMER_EMAIL);
	}	
	
	
	@Test
	public void testFindCustomer() throws Exception {
		Long id = Long.valueOf(1);
		Customer customer = getCustomer(id);
		CustomerDTO expectedCustomer = mapper.map(customer, CustomerDTO.class);
		
		when((customerDaoMock).get(id)).thenReturn(customer);

		CustomerDTO custDto = customerService.findCustomer(id);
		
		verify(customerDaoMock).get(id);
		assertEquals(expectedCustomer,custDto);
	}

	
	@Test
	public void testFindCustomerByEmail() throws Exception {
		Long id = Long.valueOf(1);
		Customer customer = getCustomer(id);
		CustomerDTO expectedCustomer = mapper.map(customer, CustomerDTO.class);
		
		when((customerDaoMock).findByEmail(CUSTOMER_EMAIL)).thenReturn(customer);

		CustomerDTO custDto = customerService.findCustomerById(CUSTOMER_EMAIL);
		
		verify(customerDaoMock).findByEmail(CUSTOMER_EMAIL);
		assertEquals(expectedCustomer,custDto);
	}

	
	
	@Test
	public void testDeleteCustomerWithNullId() throws Exception {
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(CustomerServiceImpl.CUSTOMER_INVALID_ID));
		
		customerService.deleteCustomer(null);
	}	
	
	@Test
	public void testDeleteCustomer() throws Exception {
		Long id = Long.valueOf(1);
		
		customerService.deleteCustomer(id);
		
		verify(customerDaoMock).delete(id);
	}

	@Test
	public void testUpdateCustomerWithNullCustomer() throws Exception {
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(CustomerServiceImpl.CUSTOMER_IS_NULL));
		
		customerService.updateCustomer(null);
	}
	
	@Test
	public void testUpdateCustomerWithNullCustomerIdentifier() throws Exception {
		CustomerDTO custDto = new CustomerDTO();
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(CustomerServiceImpl.CUSTOMER_INVALID_ID));
		
		customerService.updateCustomer(custDto);
	}	
	
	@Test
	public void testUpdateCustomer() throws Exception {
		Long id = Long.valueOf(1);
		CustomerDTO customerDto = getCustomerDTO(id);

		customerService.updateCustomer(customerDto);
		
		//verify(customerDaoMock).saveOrUpdate(customer);;
	}

	@Test
	public void testFindCustomers() {
		List<Customer> returnedLst = new ArrayList<Customer>();
		
		returnedLst.add(getCustomer(Long.valueOf(1)));
		returnedLst.add(getCustomer(Long.valueOf(2)));
		
		when(customerDaoMock.findAll()).thenReturn(returnedLst);
		
		Collection<CustomerDTO> customers = customerService.findCustomers();
		
		assertNotNull(customers);
		
		assertEquals(2, customers.size());
	}

	
	
	private Customer getCustomer(Long id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setFirstname(CUSTOMER_FIRST_NAME);
		customer.setLastname(CUSTOMER_LAST_NAME);
		customer.setTelephone(CUSTOMER_TELEPHONE);
		customer.setEmail(CUSTOMER_EMAIL);
		customer.setPassword(null);
		
		return customer;
	}
	
	private CustomerDTO getCustomerDTO(Long id) {
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setId(id);
		customerDto.setFirstname(CUSTOMER_FIRST_NAME);
		customerDto.setLastname(CUSTOMER_LAST_NAME);
		customerDto.setTelephone(CUSTOMER_TELEPHONE);
		customerDto.setEmail(CUSTOMER_EMAIL);
		customerDto.setPassword(null);
		
		return customerDto;
	}	

	
	
}
