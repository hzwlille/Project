package com.yaps.petstore.service.cart.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.FinalCartDTO;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.Product;
import com.yaps.petstore.model.ProductDTO;
import com.yaps.petstore.service.catalog.CatalogService;
import com.yaps.petstore.service.catalog.impl.CatalogServiceImpl;

import static org.mockito.Mockito.*;

 @RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceImplTest {
	
	@Mock
	CatalogService catalogServiceMock;
	
//	Lorsque la map passée en argument est null,
//la méthode renvoie une exception de type CartIsNullException.
	@Test(expected=CheckException.class)
	public void testCartNull() throws Exception{
		ShoppingCartServiceImpl shopping1=new ShoppingCartServiceImpl();
		shopping1.getCartItems(null);
	}
	
	
	
//	Lorsque la map passé en argument ne contient aucun élément, 
//la méthode renvoie une exception de type CartIsEmptyException.
	@Test(expected=CheckException.class)
	public void testCartEmpty() throws Exception{
		ShoppingCartServiceImpl shopping1=new ShoppingCartServiceImpl();
		Map<Long, Integer> map1= new HashMap();
		shopping1.getCartItems(map1);
		
	}

//	Lorsque la map contient un élément, vérifier que la méthode findItem du catalogService est appelée.
//	Vous devez mocker le service catalogService.
	@Test
	public void testCartUnElement() throws Exception{

		long id = 1l;
		
		ShoppingCartServiceImpl shopping1=new ShoppingCartServiceImpl();
		Map<Long, Integer> map1 = new HashMap<Long, Integer>();
		map1.put(id, 1);
		
		shopping1.setCatalogService(catalogServiceMock);
		
		ItemDTO itemDto = new ItemDTO(id,"name",1.0);
		ProductDTO product1=new ProductDTO();
		itemDto.setProduct(product1);
		when(catalogServiceMock.findItem(Long.valueOf(id))).thenReturn(itemDto );
		shopping1.getCartItems(map1);
		
		verify(catalogServiceMock).findItem(id);
		
	}
	
//	Lorsque la map contient 1 élément, la méthode renvoie un FinalCartDTO.
//	Vérifier que ce FinalCartDTO est non null.
//	Vous devez mocker le service catalogService et simuler sa méthode findItem.
	@Test
	public void testCartFinalCartDTONonNull() throws Exception{

		long id = 1l;
		
		ShoppingCartServiceImpl shopping1=new ShoppingCartServiceImpl();
		Map<Long, Integer> map1 = new HashMap<Long, Integer>();
		map1.put(id, 1);
		
		shopping1.setCatalogService(catalogServiceMock);
		
		ItemDTO itemDto = new ItemDTO(id,"name",1.0);
		ProductDTO product1=new ProductDTO();
		itemDto.setProduct(product1);
		when(catalogServiceMock.findItem(Long.valueOf(id))).thenReturn(itemDto );
		FinalCartDTO finalCart1=shopping1.getCartItems(map1);
		assertNotNull(finalCart1);	
	
	}
	
//	Lorsque la map contient 3 éléments, la méthode renvoie un FinalCartDTO.
//	Récupérer les cartItems contenu dans le FinalCartDTO et vérifier que le nombre d’élément de cette liste est égale à 3.
//	Vous devez mocker le service catalogService et simuler sa méthode findItem.
	@Test
	public void testCartFinalCartDTO3Elements() throws Exception{

		long id = 1l;
		long id1=id+1;
		long id2=id+2;
		
		ShoppingCartServiceImpl shopping1=new ShoppingCartServiceImpl();
		Map<Long, Integer> map1 = new HashMap<Long, Integer>();
		map1.put(id , 3);
		map1.put(id1, 3);
		map1.put(id2, 3);
		shopping1.setCatalogService(catalogServiceMock);
		
		ItemDTO itemDto  = new ItemDTO(id,"name",1.0);
		ItemDTO itemDto1 = new ItemDTO(id1,"name1",2.0);
		ItemDTO itemDto2 = new ItemDTO(id+2,"name2",3.0);
		ProductDTO product1=new ProductDTO();
		itemDto.setProduct(product1);
		ProductDTO product2=new ProductDTO();
		itemDto1.setProduct(product2);
		ProductDTO product3=new ProductDTO();
		itemDto2.setProduct(product3);
		when(catalogServiceMock.findItem(Long.valueOf(id ))).thenReturn(itemDto );
		when(catalogServiceMock.findItem(Long.valueOf(id1))).thenReturn(itemDto );
		when(catalogServiceMock.findItem(Long.valueOf(id2))).thenReturn(itemDto );
		
		FinalCartDTO finalCart1=shopping1.getCartItems(map1);
		assertEquals("Test Passed",3,finalCart1.getCartItems().size());	
	
	}
}
