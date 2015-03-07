package com.yaps.petstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yaps.petstore.model.Category;
import static com.yaps.petstore.utils.ConstantUtils.*;

public class CategoryDaoTest  extends AbstractBaseDaoTestCase{

	
	
	@Autowired
    private CategoryDao categoryDao;
    
	
    private Category category = null;
    
    @Before
    public void setUp(){
    	loadCategory();
    }

    
    @After
    public void tearDown(){
    	category = null;
    	categoryDao = null;
    }


    @Test
    public void testCreateCategory() throws Exception {
        categoryDao.saveOrUpdate(category);
    	assertTrue("primary key assigned", category.getId() != null);
    }  
    
    @Test
    public void testUpdateCategory() throws Exception {

        categoryDao.saveOrUpdate(category);
    	
        category.setName(CATEGOY_NAME + "MDF");
        category.setDescription(CATEGORY_DESCRIPTION + "MDF");
        
        categoryDao.saveOrUpdate(category);
        
        Category catMdf = categoryDao.get(category.getId());
        assertEquals(category,catMdf);
    }  

    @Test
    public void testGetCategory() throws Exception {

        categoryDao.saveOrUpdate(category);
        
        Category cat = categoryDao.get(category.getId());

        assertNotNull(cat);
        assertEquals(category,cat);
    }   

    @Test
    public void testRemoveCategory() throws Exception {

        categoryDao.saveOrUpdate(category);
        
    	Category cat = categoryDao.get(category.getId());

        assertNotNull(cat.getId());
        assertEquals(category,cat);
        
        categoryDao.delete(category.getId());

        assertTrue(categoryDao.findAll().size() == 0);
    }   
    
    
    @Test
    public void testGetCategories() throws Exception {

        categoryDao.saveOrUpdate(category);
        
    	assertTrue(categoryDao.findAll().size() == 1);
        
        //Add another element
    	Category cat2 = new Category();
    	cat2.setName(CATEGOY_NAME + "2");
    	cat2.setDescription(CATEGORY_DESCRIPTION + "2");

    	categoryDao.saveOrUpdate(cat2);
    	
    	//Verify that the list contains 2 elements 
        assertTrue(categoryDao.findAll().size() == 2);

    }

    
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */   
	private void  loadCategory() {
		category = new Category();
		category.setName(CATEGOY_NAME);
		category.setDescription(CATEGORY_DESCRIPTION);
	}
   
    
}
