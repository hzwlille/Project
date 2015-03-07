package com.yaps.petstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaps.petstore.model.Address;
import com.yaps.petstore.model.Category;
import com.yaps.petstore.model.CreditCard;
import com.yaps.petstore.model.Customer;
import com.yaps.petstore.model.Item;
import com.yaps.petstore.model.Order;
import com.yaps.petstore.model.OrderLine;
import com.yaps.petstore.model.Product;

import static com.yaps.petstore.utils.ConstantUtils.*;

public class OrderDaoTest extends AbstractBaseDaoTestCase {

	
	
	
	@Autowired
    private OrderDao orderDao;
    
	@Autowired
    private CustomerDao customerDao;

    @Autowired
    private ItemDao itemDao ;
    
    @Autowired
    private ProductDao productDao ;

    @Autowired
    private CategoryDao categoryDao ;    
	
	
    private Order order = null;
	
	
	@Before
	public void setUp() throws Exception {
		loadOrder();
	}


	@After
	public void tearDown() throws Exception {
		order = null;
	}

	
    @Test
    public void testCreateOrder() throws Exception {
    	orderDao.saveOrUpdate(order);
    	assertTrue("primary key assigned", order.getId() != null);
    }	

    
    @Test
    public void testUpdateOrder() throws Exception {

    	orderDao.saveOrUpdate(order);
    	
    	order.setFirstname(CUSTOMER_FIRST_NAME + "MDF");
    	order.setLastname(CUSTOMER_LAST_NAME+ "MDF");
        
    	orderDao.saveOrUpdate(order);
        
        Order orderMdf = orderDao.get(order.getId());
        assertEquals(order,orderMdf);
    }  
    
    
    @Test
    public void testGetOrder() throws Exception {

    	orderDao.saveOrUpdate(order);
        
        Order ord = orderDao.get(order.getId());

        assertNotNull(order);
        assertEquals(order,ord);
    }   
    
    @Test
    public void testRemoveOrder() throws Exception {

    	orderDao.saveOrUpdate(order);
        
    	Order ord = orderDao.get(order.getId());

        assertNotNull(ord);
        assertEquals(order,ord);
        
        orderDao.delete(order.getId());

        assertTrue(orderDao.findAll().size() == 0);
    }   

    
    @Test
    public void testGetOrders() throws Exception {

    	//create and save first order with customer1
    	orderDao.saveOrUpdate(order);    	
    	
    	Customer customer2 = getCustomer2();

    	//create and save 2 other orders with customer2
    	Order order2 = getAnotherOrder();
    	order2.setCustomer(customer2);
    	
    	Order order3 = getAnotherOrder();    	
    	order3.setCustomer(customer2);
        
    	orderDao.saveOrUpdate(order2);
    	orderDao.saveOrUpdate(order3);
    	
    	assertTrue(orderDao.findAll().size() == 3);
        

    	//Verify that the list contains 2 elements 
        assertTrue(orderDao.findByCustomerId(customer2.getId())  .size() == 2);

    }
    
    
    @Test
    public void testGetOrdersByCustomerId() throws Exception {

    	orderDao.saveOrUpdate(order);
    	Order order2 = getAnotherOrder();
    	Order order3 = getAnotherOrder();
     
    	
    	assertTrue(orderDao.findAll().size() == 1);
        
     
    	orderDao.saveOrUpdate(order2);
    	
    	//Verify that the list contains 2 elements 
        assertTrue(orderDao.findAll().size() == 2);

    }    
   

    @Test
    public void testCreateOrderWithOrderLine() throws Exception {
    	List<OrderLine> orderlines = new ArrayList<OrderLine>();
    	orderlines.add(getOrderLine());
    	order.setOrderLines(orderlines);
    	
    	orderDao.saveOrUpdate(order);
    	assertTrue("primary key assigned", order.getId() != null);
    }	
   
    
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */   
	private void loadOrder() {
		order = new Order();
		order.setOrderDate(new Date());
		order.setFirstname(ORDER_FIRST_NAME);
		order.setLastname(ORDER_LAST_NAME);		
		order.setAddress(getAddress());
		order.setCreditCard(getCreditCard());
		order.setCustomer(getCustomer());
	}
	
	
	private Order getAnotherOrder() {
		Order myOrder = new Order();
		myOrder.setOrderDate(new Date());
		myOrder.setFirstname(ORDER_FIRST_NAME);
		myOrder.setLastname(ORDER_LAST_NAME);		
		myOrder.setAddress(getAddress());
		myOrder.setCreditCard(getCreditCard());
		myOrder.setCustomer(getCustomer());
		return myOrder;
	}	


	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setFirstname(CUSTOMER_FIRST_NAME);
		customer.setLastname(CUSTOMER_LAST_NAME);
		customer.setTelephone(CUSTOMER_TELEPHONE);
		customer.setEmail(CUSTOMER_EMAIL);
		customer.setCreditCard(getCreditCard());
		customer.setAddress(getAddress());
		customerDao.saveOrUpdate(customer);
		
		return customer;
	}
	
	
	private Customer getCustomer2() {
		Customer customer = new Customer();
		customer.setFirstname(CUSTOMER_FIRST_NAME + 2);
		customer.setLastname(CUSTOMER_LAST_NAME + 2);
		customer.setTelephone(CUSTOMER_TELEPHONE  + 2);
		customer.setEmail(CUSTOMER_EMAIL);
		customer.setCreditCard(getCreditCard());
		customer.setAddress(getAddress());
		customerDao.saveOrUpdate(customer);
		
		return customer;
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
	
	private OrderLine getOrderLine() {
		OrderLine orderline = new OrderLine();
		orderline.setQuantity(ORDER_LINE_QUANTITY);
		orderline.setUnitCost(ORDER_LINE_UNIT_COST);
		orderline.setOrder(order);
		orderline.setItem(getItem());
		
		return orderline;
	}
	
	private Item getItem() {
	   	Item item = new Item();
        item.setName(ITEM_NAME);
        item.setImagePath(ITEM_IMAGE_PATH);
        item.setUnitCost(ITEM_PRICE);
        item.setProduct(getProduct());

        itemDao.saveOrUpdate(item);
        return item;
	}    
	
	private Product getProduct() {
	   	Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);

        Category category = getCategory();
        product.setCategory(category);

        productDao.saveOrUpdate(product);        
        
        return product;
	}    

	private Category getCategory() {
		Category category = new Category();
        category.setName(CATEGOY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryDao.saveOrUpdate(category);
		return category;
	}	
	
}
