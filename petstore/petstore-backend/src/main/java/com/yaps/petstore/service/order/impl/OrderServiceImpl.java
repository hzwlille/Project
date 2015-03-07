package com.yaps.petstore.service.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.barkbank.client.CreditCardFacade;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.dao.OrderDao;
import com.yaps.petstore.dao.OrderLineDao;
import com.yaps.petstore.model.Address;
import com.yaps.petstore.model.CreditCardDTO;
import com.yaps.petstore.model.Customer;
import com.yaps.petstore.model.CustomerDTO;
import com.yaps.petstore.model.Item;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.Order;
import com.yaps.petstore.model.OrderDTO;
import com.yaps.petstore.model.OrderLine;
import com.yaps.petstore.service.catalog.CatalogService;
import com.yaps.petstore.service.customer.CustomerService;
import com.yaps.petstore.service.order.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	public static final String ORDER_LINE_ITEM_MUST_EXIST = "Item must exist to create an order line";
	public static final String ORDER_CUSTOMER_MUST_EXIST = "Customer must exist to create an order";
	public static final String ORDER_NO_ORDER_LINES = "There are no order lines";
	public static final String ORDER_INVALID_ID = "Invalid id";
	public static final String ORDER_ID_IS_NULL = "order Id is null";
	public static final String ORDER_IS_NULL = "order is null";
	public static final String ORDER_INVALID_INPUT = "Invalid input";
	public static final String ORDER_CUSTOMER_CANNOT_BE_NULL = "Customer cannot be null";
	public static final String ORDER_CREDIT_CARD_CANNOT_BE_NULL = "Credit card cannot be null";

	@Autowired
	protected OrderDao orderDao;

	@Autowired
	protected OrderLineDao orderLineDao;

	@Autowired
	protected CustomerService customerService;

	@Autowired
	protected CatalogService catalogService;

	@Autowired
	protected CreditCardFacade creditCardFacade;

	@Autowired
	protected Mapper mapper;

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public OrderLineDao getOrderLineDao() {
		return orderLineDao;
	}

	public void setOrderLineDao(OrderLineDao orderLineDao) {
		this.orderLineDao = orderLineDao;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CreditCardFacade getCreditCardFacade() {
		return creditCardFacade;
	}

	public void setCreditCardFacade(CreditCardFacade creditCardFacade) {
		this.creditCardFacade = creditCardFacade;
	}

	public CatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@Transactional
	public Long createOrder(Long customerId, Map<Long, Integer> shoppingCart)
			throws CheckException {

		if (customerId == null || "".equals(customerId))
			throw new CheckException("Invalid id");
		if (shoppingCart == null || shoppingCart.size() < 0)
			throw new CheckException("Shopping Cart is empty");

		// Finds the customer
		final CustomerDTO customerDto;
		try {
			customerDto = customerService.findCustomer(customerId);
		} catch (DataAccessException e) {
			throw new CheckException(ORDER_CUSTOMER_MUST_EXIST);
		}
		Customer customer = mapper.map(customerDto, Customer.class);

		// Creates the order
		final Order order = new Order();

		order.setFirstname(customer.getFirstname());
		order.setLastname(customer.getLastname());
		order.setOrderDate(new Date());

		// set the customer of the order
		order.setCustomer(customer);

		// Sets the address and credit card of the order
		order.setAddress(customer.getAddress());
		order.setCreditCard(customer.getCreditCard());

		// Checks if the credit card is valid if it hasn't been paid by check
		if (order.getCreditCard() != null) {
			CreditCardDTO creditcardDto = mapper.map(order.getCreditCard(),
					CreditCardDTO.class);
			creditCardFacade.verifyCreditCard(creditcardDto);
		}

		// Creates all the orderLines linked with the order
		final List<OrderLine> orderLines = new ArrayList<OrderLine>();
		final Iterator<Long> iterator = shoppingCart.keySet().iterator();
		while (iterator.hasNext()) {
			// Get the NEXT item id in the list
			final Long itemId = (Long) iterator.next();
			// Get the Value for the returned key
			final Integer quantity = shoppingCart.get(itemId);
			// Finds the item
			final ItemDTO itemDto;
			try {
				itemDto = catalogService.findItem(itemId);
			} catch (DataAccessException e) {
				throw new CheckException(ORDER_LINE_ITEM_MUST_EXIST);
			}

			Item item = mapper.map(itemDto, Item.class);
			// Creates the order line
			final OrderLine orderLine = new OrderLine();
			orderLine.setQuantity(quantity.intValue());
			orderLine.setItem(item);

			// Adds order line to a collection
			orderLines.add(orderLine);
		}

		// Sets orderLines into the order
		order.setOrderLines(orderLines);

		orderDao.saveOrUpdate(order);

		return order.getId();
	}

	
	@Transactional
	public OrderDTO createOrder(OrderDTO orderDto) throws CheckException {

		if (orderDto == null)
			throw new CheckException(ORDER_IS_NULL);

		if (orderDto.getCustomer() == null
				|| orderDto.getCustomer().getId() == null)
			throw new CheckException(ORDER_CUSTOMER_CANNOT_BE_NULL);

		if (orderDto.getCreditCard() == null)
			throw new CheckException(ORDER_CREDIT_CARD_CANNOT_BE_NULL);

		if (orderDto.getOrderLines() == null
				|| orderDto.getOrderLines().size() < 0)
			throw new CheckException(ORDER_NO_ORDER_LINES);

		// Finds the customer
		final CustomerDTO customerDto;
		try {
			customerDto = customerService.findCustomer(orderDto.getCustomer()
					.getId());
		} catch (DataAccessException e) {
			throw new CheckException(ORDER_CUSTOMER_MUST_EXIST);
		}

		Order order = mapper.map(orderDto, Order.class);

		Customer customer = mapper.map(customerDto, Customer.class);
		order.setCustomer(customer);

		// If the address has been set, we create the address
		if (orderDto.getAddress().isDirty()) {
			final Address address = new Address();
			address.setCity(orderDto.getCity());
			address.setCountry(orderDto.getCountry());
			address.setState(orderDto.getState());
			address.setStreet1(orderDto.getStreet1());
			address.setStreet2(orderDto.getStreet2());
			address.setZipcode(orderDto.getZipcode());
			order.setAddress(address);
		}

		// If the creditcard has been set, we create the credit card
		if (orderDto.getCreditCard().isDirty()) {

			// Checks if the credit card is valid
			creditCardFacade.verifyCreditCard(orderDto.getCreditCard());
		}

		// Creates all the orderLines linked with the order
		final List<OrderLine> orderLines = new ArrayList<OrderLine>();
		for (Iterator<OrderLine> iterator = order.getOrderLines().iterator(); iterator
				.hasNext();) {
			final OrderLine orderLine =  iterator.next();
			final ItemDTO itemDto;
			// Finds the item
			try {
				itemDto = getCatalogService().findItem(
						orderLine.getItem().getId());
			} catch (DataAccessException e) {
				throw new CheckException(ORDER_LINE_ITEM_MUST_EXIST);
			}

			// Adds order line to a collection
			orderLines.add(orderLine);
		}
		// Sets orderLines into the order
		order.setOrderLines(orderLines);

		orderDao.saveOrUpdate(order);

		orderDao.get(order.getId());

		OrderDTO result = mapper.map(order, OrderDTO.class);
		return result;

	}

	@Transactional
	public void deleteOrder(Long orderId) throws CheckException {
		if (orderId == null)
			throw new CheckException(ORDER_ID_IS_NULL);

		orderDao.delete(orderId);
	}

	
	@Transactional(readOnly=true)
	public OrderDTO findOrder(Long orderId) throws CheckException {

		if (orderId == null)
			throw new CheckException(ORDER_ID_IS_NULL);

		Order order = orderDao.get(orderId);

		OrderDTO orderDto = mapper.map(order, OrderDTO.class);

		return orderDto;
	}

	
	@Transactional(readOnly=true)
	public List<OrderDTO> findOrders(Long customerId) throws CheckException {

		if (customerId == null)
			throw new CheckException("Invalid customerId ");

		List<Order> lst = orderDao.findByCustomerId(customerId);
		List<OrderDTO> lstDto = new ArrayList<OrderDTO>();

		for (Order order : lst) {
			OrderDTO orderDTO = mapper.map(order, OrderDTO.class);
			lstDto.add(orderDTO);
		}

		return lstDto;
	}

}
