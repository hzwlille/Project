package com.yaps.petstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaps.petstore.model.Category;
import com.yaps.petstore.model.Item;
import com.yaps.petstore.model.Product;
import static com.yaps.petstore.utils.ConstantUtils.*;

public class ItemDaoTest extends AbstractBaseDaoTestCase {
    


	private Item item = null;

    @Autowired
    private ItemDao itemDao ;
    
    @Autowired
    private ProductDao productDao ;
    
    @Autowired
    private CategoryDao categoryDao ;    
   
    @Before
    public void setUp(){
    	loadItem();
    }
    
    @After
    public void tearDown(){
    	categoryDao = null;
    	productDao = null;
    	itemDao  = null;
    	item = null;
    }

    
    @Test
    public void testCreateItem() throws Exception {
        itemDao.saveOrUpdate(item);    	
        assertTrue("primary key assigned", item.getId() != null);
    }   
    
    
    @Test
    public void testUpdateItem() throws Exception {
        
        itemDao.saveOrUpdate(item);
        
    	item.setName(ITEM_NAME + "MDF");
        item.setImagePath(ITEM_IMAGE_PATH + "MDF");
        item.setUnitCost(ITEM_PRICE + 10d);
        
        itemDao.saveOrUpdate(item);
        
        Item itemMdf = itemDao.get(item.getId());
        assertEquals(item,itemMdf);
    } 
    
    @Test
    public void testGetItem() throws Exception {

        itemDao.saveOrUpdate(item);
        
    	Item myItem = itemDao.get(item.getId());

        assertNotNull(item);
        assertEquals(item,myItem);
    }    
    
    
    @Test
    public void testRemoveItem() throws Exception {

        itemDao.saveOrUpdate(item);
        
    	Item myItem = itemDao.get(item.getId());

        assertNotNull(item.getId());
        assertEquals(item,myItem);
        
        itemDao.delete(item.getId());

        assertTrue(itemDao.findAll().size() == 0);
    }   
    
    
    @Test
    public void testGetItems() throws Exception {

        itemDao.saveOrUpdate(item);
        
    	assertTrue(itemDao.findAll().size() == 1);
        
        //Add another element
    	Item item2 = getAnotherItem();
    	itemDao.saveOrUpdate(item2);    	
    	
    	//Verify that the list contains 2 elements 
        assertTrue(itemDao.findAll().size() == 2);
    }   
    
    
    @Test
    public void testGetItemsWithProductId() {

        Product product1 = getProduct(PRODUCT_NAME,PRODUCT_DESCRIPTION);
        Product product2 = getProduct(PRODUCT_NAME+ "2",PRODUCT_DESCRIPTION + "2");
    	
        //Add one element
        Item item1 = new Item();
        item1.setName(ITEM_NAME);
        item1.setImagePath(ITEM_IMAGE_PATH);
        item1.setUnitCost(ITEM_PRICE);
        item1.setProduct(product1);
    	itemDao.saveOrUpdate(item1);
    	
    	
        //Add another element
        Item item2 = new Item();
        item2.setName(ITEM_NAME + "2");
        item2.setImagePath(ITEM_IMAGE_PATH + "2");
        item2.setUnitCost(ITEM_PRICE + 10d);

        item2.setProduct(product1);
    	
    	itemDao.saveOrUpdate(item2);
        
        //Add another element
        Item item3 = new Item();
        item3.setName(ITEM_NAME + "3");
        item3.setImagePath(ITEM_IMAGE_PATH + "3");
        
        item3.setProduct(product2);
    	itemDao.saveOrUpdate(item3);
    	

        assertTrue(itemDao.findByProductId(product1.getId()).size() == 2);
        assertTrue(itemDao.findByProductId(product2.getId()).size() == 1);
    }
    
 
    @Test
    public void testSearchItem() {

        Product product1 = getProduct(PRODUCT_NAME,PRODUCT_DESCRIPTION);
    	
    	itemDao.saveOrUpdate(item);

        //Add another element
        Item item2 = new Item();
        item2.setName(ITEM_NAME + "2");
        item2.setImagePath(ITEM_IMAGE_PATH + "2");
        item2.setUnitCost(ITEM_PRICE + 10d);

        item2.setProduct(product1);
    	itemDao.saveOrUpdate(item2);
        
        //Add another element
        Item item3 = new Item();
        item3.setName("findme");
        item3.setImagePath(ITEM_IMAGE_PATH + "3");
        item2.setUnitCost(ITEM_PRICE + 10d);
        
        item3.setProduct(product1);
    	itemDao.saveOrUpdate(item3);

        //Add another element
        Item item4 = new Item();
        item4.setName("toto");
        item4.setImagePath(ITEM_IMAGE_PATH + "4");
        item2.setUnitCost(ITEM_PRICE + 10d);
        
        item4.setProduct(product1);
    	itemDao.saveOrUpdate(item4);
  	
    	
        assertTrue(itemDao.findByItemName(ITEM_NAME).size() == 2);
        assertTrue(itemDao.findByItemName("fi").size() == 1);
        assertTrue(itemDao.findByItemName("tot").size() == 1);
    }
    
    
    /**
     * 
     * @return an instanciated object. The one declared as private field in the test class
     */    
	private void loadItem() {
	   	item = new Item();
        item.setName(ITEM_NAME);
        item.setImagePath(ITEM_IMAGE_PATH);
        item.setUnitCost(ITEM_PRICE);
        item.setProduct(getProduct());
	}
	
	
	private Item getAnotherItem() {
        Item item2 = new Item();
        item2.setName(ITEM_NAME + "2");
        item2.setImagePath(ITEM_IMAGE_PATH + "2");
        item2.setUnitCost(ITEM_PRICE + 10d); 
        item2.setProduct(getProduct());

        return item2;
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
	
	private Product getProduct(String name, String desc) {
	   	Product product = new Product();
        product.setName(name);
        product.setDescription(desc);

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
