package com.yaps.petstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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

public class OrderLineDaoTest extends AbstractBaseDaoTestCase {

	
	@Autowired
    private OrderLineDao orderLineDao;

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
	
	
    private OrderLine orderline = null;
	
	
	@Before
	public void setUp() throws Exception {
		loadOrderLine();
	}


	@After
	public void tearDown() throws Exception {
		orderline = null;
		orderLineDao = null;
		customerDao = null;
	}

	
    @Test
    public void testCreateOrderLine() throws Exception {
    	orderLineDao.saveOrUpdate(orderline);
    	assertTrue("primary key assigned", orderline.getId() != null);
    }	

    @Test
    public void testUpdateCustomer() throws Exception {

    	orderLineDao.saveOrUpdate(orderline);
    	
    	orderline.setQuantity(ORDER_LINE_QUANTITY + 3);
    	orderline.setUnitCost(ORDER_LINE_UNIT_COST + 10d);
        
    	orderLineDao.saveOrUpdate(orderline);
        
        OrderLine orderLineMdf = orderLineDao.get(orderline.getId());
        assertEquals(orderline,orderLineMdf);
    }  
    
    
    @Test
    public void testGetCustomer() throws Exception {

    	orderLineDao.saveOrUpdate(orderline);
        
        OrderLine myOrderLine = orderLineDao.get(orderline.getId());
        
        assertNotNull(orderline);
        assertEquals(orderline,myOrderLine);
    }   
    
    @Test
    public void testRemoveCustomer() throws Exception {

    	orderLineDao.saveOrUpdate(orderline);
        
    	OrderLine myOrderLine = orderLineDao.get(orderline.getId());

        assertNotNull(myOrderLine.getId());
        assertEquals(orderline,myOrderLine);
        
        orderLineDao.delete(orderline.getId());

        assertTrue(orderLineDao.findAll().size() == 0);
    }   

    
    @Test
    public void testGetOrderLines() throws Exception {

    	orderLineDao.saveOrUpdate(orderline);
        
    	assertTrue(orderLineDao.findAll().size() == 1);
        
        //Add another element
    	OrderLine orderline2 = getAnotherOrderLine();

    	orderLineDao.saveOrUpdate(orderline2);
    	
    	//Verify that the list contains 2 elements 
        assertTrue(orderLineDao.findAll().size() == 2);

    }
   
    
    
	private void loadOrderLine() {
		orderline = new OrderLine();
		orderline.setQuantity(ORDER_LINE_QUANTITY);
		orderline.setUnitCost(ORDER_LINE_UNIT_COST);
		orderline.setOrder(getOrder());
		orderline.setItem(getItem());
	}
	
	private OrderLine getAnotherOrderLine() {
		OrderLine myOrderline = new OrderLine();
		
		myOrderline.setQuantity(2);
		myOrderline.setUnitCost(20.0d);
		
		myOrderline.setOrder(getOrder());
		myOrderline.setItem(getItem());

		return myOrderline;
	}	


	private Order getOrder() {
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setFirstname(ORDER_FIRST_NAME);
		order.setLastname(ORDER_LAST_NAME);		
		order.setAddress(getAddress());
		order.setCreditCard(getCreditCard());
		order.setCustomer(getCustomer());
		
		orderDao.saveOrUpdate(order);
		
		return order;
	}	
	
    /**
     * 
     * @return an instanciated and created customer . 
     */
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
