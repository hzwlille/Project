package com.yaps.petstore.service.catalog.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.model.CategoryDTO;
import com.yaps.petstore.service.catalog.CatalogService;


@ContextConfiguration({ "classpath:/applicationContext-custom-test.xml" })
@Transactional  
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
public class CatalogServiceImplIntegrationTest {

	
	private CategoryDTO categoryDto;
	
	
	@Autowired
	private CatalogService catalogService;
	
	
	@Before
	public void setUp() throws Exception {
		categoryDto = getCategoryDto();
	}

	@After
	public void tearDown() throws Exception {
		catalogService = null;
	}

	
	
	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */
	
	@Test 
	public void saveCategoryTest() throws Exception {
		CategoryDTO aCategoryDto = catalogService.createCategory(categoryDto);
		
		assertNotNull(aCategoryDto);
		assertEquals(categoryDto, aCategoryDto);
		
		CategoryDTO foundDto = catalogService.findCategory(aCategoryDto.getId());

		assertNotNull(foundDto);
		assertEquals(categoryDto, foundDto);
		
		
	}	
	
	
	
	
	/* ---------------------------------- */
	/* --------- Private methods -------- */
	/* ---------------------------------- */
	
	private CategoryDTO getCategoryDto(){
		CategoryDTO category = new CategoryDTO(Long.valueOf(1L), "categoryName", "categoryDesc");
		return category;
	}

	
	
}
