package com.yaps.petstore.service.supervision.impl;

import static org.junit.Assert.assertTrue;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SupervisionServiceImplTest {

	Long id = Long.valueOf(1);
	


	@Rule
	public ExpectedException exception = ExpectedException.none();
	

	private SupervisionServiceImpl supervisionService;
	
	
	@Before
	public void setUp() throws Exception {
		
		supervisionService = new SupervisionServiceImpl();
		
		Mapper mapper = new DozerBeanMapper();
		supervisionService.setMapper(mapper);
		
	}	

	@After
	public void tearDown() throws Exception {
		supervisionService = null;
	}
	
	@Test
	   public void testSupervision() throws Exception {
	}   
	
	

	
}
