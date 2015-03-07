package com.yaps.petstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaps.petstore.model.Category;
import com.yaps.petstore.model.Product;

import static com.yaps.petstore.utils.ConstantUtils.*;


public class ProductDaoTest extends AbstractBaseDaoTestCase {

    
    @Autowired
    private ProductDao productDao ;
    
    @Autowired
    private CategoryDao categoryDao ;    
    
    private Product product = null;
    
    
    @Before
    public void setUp(){
    	loadProduct();
    }
    
    @After
    public void tearDown(){
    	categoryDao = null;
    	productDao = null;
    }

    @Test
    public void testCreateProduct() throws Exception {
        productDao.saveOrUpdate(product);    	
        assertTrue("primary key assigned", product.getId() != null);
    }    
   
    @Test
    public void testUpdateProduct() throws Exception {
        
    	productDao.saveOrUpdate(product);
    	
    	product.setName(PRODUCT_NAME + "MDF");
        product.setDescription(PRODUCT_DESCRIPTION + "MDF");
        
        productDao.saveOrUpdate(product);
        
        Product prdMdf = productDao.get(product.getId());
        assertEquals(product,prdMdf);
    }    
    
    
    @Test
    public void testGetProduct() throws Exception {

    	productDao.saveOrUpdate(product);
    	
    	Product prd = productDao.get(product.getId());

        assertNotNull(prd);
        assertEquals(product,prd);
    }   

    
    @Test
    public void testRemoveProduct() throws Exception {
    	productDao.saveOrUpdate(product);
    	
    	Product prd = productDao.get(product.getId());

        assertNotNull(prd.getId());
        assertEquals(product,prd);
        
        productDao.delete(product.getId());

        assertTrue(productDao.findAll().size() == 0);
    }

    
    
    @Test
    public void testGetProducts() throws Exception {

    	productDao.saveOrUpdate(product);
    	
        assertTrue(productDao.findAll().size() == 1);
        
        //Add another element
        Product prd2 = new Product();
    	prd2.setName(PRODUCT_NAME + "2");
    	prd2.setDescription(PRODUCT_DESCRIPTION + "2");
    	prd2.setCategory(getCategory());
    	
    	productDao.saveOrUpdate(prd2);
    	
    	//Verify that the list contains 2 elements 
        assertTrue(productDao.findAll().size() == 2);
    }    
    

    @Test
    public void testGetProductsWithCategoryId() {
    	
    	Product product1 = new Product();
        product1.setName("name1");
        product1.setDescription("description1");

        Product product2 = new Product();        
        product2.setName("name2");
        product2.setDescription("description2");
        
        Product product3 = new Product();        
        product3.setName("name3");
        product3.setDescription("description3");        
        
        
        Category category1 = getCategory();
        Long cat1Id = category1.getId();
        product1.setCategory(category1);
        product2.setCategory(category1);

        Category category2 = getCategory2(); 
        Long cat2Id = category2.getId();
        product3.setCategory(category2);
        productDao.saveOrUpdate(product1);
        productDao.saveOrUpdate(product2);
        productDao.saveOrUpdate(product3);

        assertTrue(productDao.findByCategoryId(cat1Id).size() == 2);
        assertTrue(productDao.findByCategoryId(cat2Id).size() == 1);
    }    

    
    @Test
    public void testGetProductsByCategoryName() {
    	
    	Product product1 = new Product();
        product1.setName("name1");
        product1.setDescription("description1");

        Product product2 = new Product();        
        product2.setName("name2");
        product2.setDescription("description2");
        
        Product product3 = new Product();        
        product3.setName("name3");
        product3.setDescription("description3");        
        
        
        Category category1 = getCategory();
        Long cat1Id = category1.getId();
        product1.setCategory(category1);
        product2.setCategory(category1);

        Category category2 = getCategory2(); 
        Long cat2Id = category2.getId();
        product3.setCategory(category2);
        productDao.saveOrUpdate(product1);
        productDao.saveOrUpdate(product2);
        productDao.saveOrUpdate(product3);

        assertTrue(productDao.findByCategoryId(cat1Id).size() == 2);
        assertTrue(productDao.findByCategoryId(cat2Id).size() == 1);
        
        Collection<Product> productListTwoElements = productDao.findByCategoryName(category1.getName());
        assertNotNull(productListTwoElements);
        assertTrue(productListTwoElements.size() == 2);
        
        Collection<Product> productListOneElements = productDao.findByCategoryName(category2.getName());
        assertNotNull(productListOneElements);
        assertTrue(productListOneElements.size() == 1);        
    }        
	
	
	private Category getCategory() {
		Category category = new Category();
        category.setName(CATEGOY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryDao.saveOrUpdate(category);
		return category;
	}    
	
	private Category getCategory2() {
		Category category = new Category();
        category.setName("catName2");
        category.setDescription("description2");

        categoryDao.saveOrUpdate(category);
		return category;
	}    
    
	
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */	
	private void loadProduct() {
    	product = new Product();
    	product.setName(PRODUCT_NAME);
    	product.setDescription(PRODUCT_DESCRIPTION);
    	product.setCategory(getCategory());
	}
	
}
