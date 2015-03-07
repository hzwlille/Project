package com.yaps.petstore.service.order.impl;

import static com.yaps.petstore.utils.ConstantUtils.ADDRESS_CITY;
import static com.yaps.petstore.utils.ConstantUtils.ADDRESS_COUNTRY;
import static com.yaps.petstore.utils.ConstantUtils.ADDRESS_STATE;
import static com.yaps.petstore.utils.ConstantUtils.ADDRESS_STREET_1;
import static com.yaps.petstore.utils.ConstantUtils.ADDRESS_STREET_2;
import static com.yaps.petstore.utils.ConstantUtils.ADDRESS_ZIP_CODE;
import static com.yaps.petstore.utils.ConstantUtils.CREDIT_CARD_DATE;
import static com.yaps.petstore.utils.ConstantUtils.CREDIT_CARD_NUMBER;
import static com.yaps.petstore.utils.ConstantUtils.CREDIT_CARD_TYPE;
import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_EMAIL;
import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_FIRST_NAME;
import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_LAST_NAME;
import static com.yaps.petstore.utils.ConstantUtils.CUSTOMER_TELEPHONE;
import static com.yaps.petstore.utils.ConstantUtils.ORDER_FIRST_NAME;
import static com.yaps.petstore.utils.ConstantUtils.ORDER_LAST_NAME;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.yaps.petstore.barkbank.client.CreditCardFacade;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.dao.OrderDao;
import com.yaps.petstore.model.Address;
import com.yaps.petstore.model.AddressDTO;
import com.yaps.petstore.model.CreditCard;
import com.yaps.petstore.model.CreditCardDTO;
import com.yaps.petstore.model.Customer;
import com.yaps.petstore.model.CustomerDTO;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.Order;
import com.yaps.petstore.model.OrderDTO;
import com.yaps.petstore.service.catalog.CatalogService;
import com.yaps.petstore.service.customer.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	private OrderServiceImpl orderService;
	
	@Mock
	protected OrderDao orderDaoMock;
	
	
	@Mock
	protected CustomerService customerServiceMock;
	
	@Mock
	protected CatalogService catalogServiceMock;
	
	@Mock
	protected CreditCardFacade creditcardFacadeMock;	
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	private Mapper mapper;
	
	@Before
	public void setUp() throws Exception {
		orderService = new OrderServiceImpl();
		orderService.setOrderDao(orderDaoMock);
		orderService.setCreditCardFacade(creditcardFacadeMock);
		orderService.setCustomerService(customerServiceMock);
		orderService.setCatalogService(catalogServiceMock);
		
		mapper = new DozerBeanMapper();
		orderService.setMapper(mapper);

	}

	@After
	public void tearDown() throws Exception {
		orderService = null;
		creditcardFacadeMock = null;
		customerServiceMock = null;
		catalogServiceMock = null;
	}

	
	@Test
	public void testDeleteOrderWithNullId() throws Exception {
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(OrderServiceImpl.ORDER_ID_IS_NULL));
		
		orderService.deleteOrder(null);
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		Long orderId = Long.valueOf(1);
		
		orderService.deleteOrder(orderId);
		
		verify(orderDaoMock).delete(orderId);
	}
	
	
	@Test
	public void testfindOrderWithNullId() throws Exception {
		
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(OrderServiceImpl.ORDER_ID_IS_NULL));
		
		orderService.findOrder(null);
	}
	

	@Ignore
	public void testFindOrder() throws Exception {
	}	


	@Ignore
	public void testFindOrdersByCustomerId() throws Exception {
	}	
	
	
	@Test
	public void testCreateOrderWithNullOrder() throws Exception {
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(OrderServiceImpl.ORDER_IS_NULL));
		
		orderService.createOrder(null);	
	}
	
	@Test
	public void testCreateOrderWithNullCustomerObject() throws Exception {
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(OrderServiceImpl.ORDER_IS_NULL));
		
		orderService.createOrder(null);	
	}
	
	
	@Test
	public void testCreateOrderWithNullCustomerIdAndhashMap() throws Exception {
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(OrderServiceImpl.ORDER_CUSTOMER_CANNOT_BE_NULL));
		
		Long id = Long.valueOf(1);
		when(customerServiceMock.findCustomer(id)).thenThrow(new CheckException(OrderServiceImpl.ORDER_CUSTOMER_CANNOT_BE_NULL));
		
		orderService.createOrder(id,new HashMap<Long, Integer>());	
	}	
	
	
	@Test
	public void testCreateOrderWithNullCustomer() throws Exception {
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(OrderServiceImpl.ORDER_CUSTOMER_CANNOT_BE_NULL));
		
		orderService.createOrder(getOrderDtoWithNullCustomer(Long.valueOf(1)));	
	}

	
	@Test
	public void testCreateOrderWithNullCustomerId() throws Exception {
		exception.expect(CheckException.class);
		exception.expectMessage(containsString(OrderServiceImpl.ORDER_CUSTOMER_CANNOT_BE_NULL));
		
		orderService.createOrder(getOrderDto(Long.valueOf(1),null));	
	}
	

	@Test
	public void testCreateOrder() throws Exception {
		Long orderId = Long.valueOf(1);
		Long customerId = Long.valueOf(2);
		
		CustomerDTO customerDto = getCustomerDto(customerId);
		CreditCardDTO creditcardDto = getCreditcardDto();
		AddressDTO addressDto = getAddressDto();
		CreditCard creditcard = getCreditcard(creditcardDto);		
		
		OrderDTO orderDto =  getOrderDto(orderId);
		orderDto.setCustomer(customerDto);
		orderDto.setCreditCard(creditcardDto);
		orderDto.setAddress(addressDto);		
		
		when(customerServiceMock.findCustomer(customerId)).thenReturn(getCustomerDto(customerId));
		when(creditcardFacadeMock.verifyCreditCard(creditcardDto) ).thenReturn("OK");		
		

		OrderDTO returnedOrderDto = orderService.createOrder(orderDto);

		verify(creditcardFacadeMock).verifyCreditCard(creditcardDto);

		assertNotNull(returnedOrderDto);
		assertEquals(orderDto,returnedOrderDto);

	}
	
	
	@Test
	public void testCreateOrderFromCart() throws Exception {
		//create shopping map
		Long itemId1 = Long.valueOf(1);
		Long itemId2 = Long.valueOf(2);
		Long itemId3 = Long.valueOf(3);
		
		Map<Long, Integer> itemsMap = new HashMap<Long, Integer>();
		itemsMap.put(itemId1, Integer.valueOf(1));
		itemsMap.put(itemId2, Integer.valueOf(2));
		itemsMap.put(itemId3, Integer.valueOf(1));		
		
		Long customerId = Long.valueOf(2);
		
		CustomerDTO customerDto = getCustomerDto(customerId);
		CreditCardDTO creditcardDto = getCreditcardDto();
		AddressDTO addressDto = getAddressDto();
		
		customerDto.setCreditCard(creditcardDto);
		customerDto.setAddress(addressDto);		
		
		when(customerServiceMock.findCustomer(customerId)).thenReturn(customerDto);
		
		when(catalogServiceMock.findItem(itemId1)).thenReturn(getItemDto(itemId1));
		when(catalogServiceMock.findItem(itemId2)).thenReturn(getItemDto(itemId2));
		when(catalogServiceMock.findItem(itemId3)).thenReturn(getItemDto(itemId3));

		doAnswer(new Answer<Object>(){
	        @Override
	        public Object answer(InvocationOnMock invocation){
	           Object[] arguments = invocation.getArguments();
	           Order argument = (Order) arguments[0];
	           argument.setId(Long.valueOf(1));

	           return null;
	        }
	     }).when(orderDaoMock).saveOrUpdate(any(Order.class));
		
		//call the service to be tested
		Long returnedOrderId = orderService.createOrder(customerId,itemsMap);

		verify(creditcardFacadeMock).verifyCreditCard(creditcardDto);

		verify(catalogServiceMock,times(1)).findItem(itemId1);
		verify(catalogServiceMock,times(1)).findItem(itemId2);
		verify(catalogServiceMock,times(1)).findItem(itemId3);		
		
		verify(catalogServiceMock).findItem(itemId1);
		verify(catalogServiceMock).findItem(itemId2);
		verify(catalogServiceMock).findItem(itemId3);	
		
		assertNotNull(returnedOrderId);

	}	
	
	
	private Order getOrder(Long orderId, Long customerId) {
		Order myOrder = new Order();
		myOrder.setId(orderId);
		myOrder.setOrderDate(new Date());
		myOrder.setFirstname(ORDER_FIRST_NAME);
		myOrder.setLastname(ORDER_LAST_NAME);		
		myOrder.setCustomer(getCustomer(customerId));
		return myOrder;
	}
	
	private OrderDTO getOrderDto(Long orderId, Long customerId) {
		OrderDTO myOrderDTO = new OrderDTO();
		myOrderDTO.setId(orderId);
		myOrderDTO.setOrderDate(new Date());
		myOrderDTO.setFirstname(ORDER_FIRST_NAME);
		myOrderDTO.setLastname(ORDER_LAST_NAME);		
		myOrderDTO.setCustomer(getCustomerDto(customerId));
		myOrderDTO.setCreditCard(getCreditcardDto());
		return myOrderDTO;
	}
	
	private OrderDTO getOrderDto(Long orderId) {
		OrderDTO myOrderDTO = new OrderDTO();
		myOrderDTO.setId(orderId);
		myOrderDTO.setOrderDate(new Date());
		myOrderDTO.setFirstname(ORDER_FIRST_NAME);
		myOrderDTO.setLastname(ORDER_LAST_NAME);		
		return myOrderDTO;
	}	
	

	private OrderDTO getOrderDtoWithNullCustomer(Long id) {
		OrderDTO myOrdeDto = new OrderDTO();
		myOrdeDto.setId(id);
		myOrdeDto.setOrderDate(new Date());
		myOrdeDto.setFirstname(ORDER_FIRST_NAME);
		myOrdeDto.setLastname(ORDER_LAST_NAME);		
		return myOrdeDto;
	}	

	

	private Customer getCustomer(Long id) {
		Customer myCustomer = new Customer();
		myCustomer.setId(id);
		myCustomer.setFirstname(CUSTOMER_FIRST_NAME);
		myCustomer.setLastname(CUSTOMER_LAST_NAME);
		myCustomer.setTelephone(CUSTOMER_TELEPHONE);
		myCustomer.setEmail(CUSTOMER_EMAIL);
		return myCustomer;
	}
	
	private CustomerDTO getCustomerDto(Long id) {
		CustomerDTO myCustomerDto = new CustomerDTO();
		myCustomerDto.setId(id);
		myCustomerDto.setFirstname(CUSTOMER_FIRST_NAME);
		myCustomerDto.setLastname(CUSTOMER_LAST_NAME);
		myCustomerDto.setTelephone(CUSTOMER_TELEPHONE);
		myCustomerDto.setEmail(CUSTOMER_EMAIL);
		return myCustomerDto;
	}
	
	
	private CreditCardDTO getCreditcardDto() {
		CreditCardDTO myCreditcardDto = new CreditCardDTO();
		myCreditcardDto.setCreditCardNumber(CREDIT_CARD_NUMBER);
		myCreditcardDto.setCreditCardType(CREDIT_CARD_TYPE);
		myCreditcardDto.setCreditCardExpiryDate(CREDIT_CARD_DATE);
		return myCreditcardDto;
	}	

	private CreditCard getCreditcard(CreditCardDTO creditcardDto) {
		CreditCard myCreditcard = new CreditCard();
		myCreditcard.setCreditCardNumber(creditcardDto.getCreditCardNumber());
		myCreditcard.setCreditCardType(creditcardDto.getCreditCardType());
		myCreditcard.setCreditCardExpiryDate(creditcardDto.getCreditCardExpiryDate());
		return myCreditcard;
	}
	
	private CreditCard getCreditcard() {
		CreditCard myCreditcard = new CreditCard();
		myCreditcard.setCreditCardNumber(CREDIT_CARD_NUMBER);
		myCreditcard.setCreditCardType(CREDIT_CARD_TYPE);
		myCreditcard.setCreditCardExpiryDate(CREDIT_CARD_DATE);
		return myCreditcard;
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
	
	private AddressDTO getAddressDto(){
		AddressDTO addressDto = new AddressDTO();
		addressDto.setCity(ADDRESS_CITY);
		addressDto.setCountry(ADDRESS_COUNTRY);
		addressDto.setState(ADDRESS_STATE);
		addressDto.setStreet1(ADDRESS_STREET_1);
		addressDto.setStreet2(ADDRESS_STREET_2);
		addressDto.setZipcode(ADDRESS_ZIP_CODE);
		return addressDto;
	}
	
	private ItemDTO getItemDto(Long id){
		ItemDTO itemDto = new ItemDTO(id,"articleID",20.0);
		return itemDto;
	}	
}
