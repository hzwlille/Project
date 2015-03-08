package com.yaps.petstore.service.catalog.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yaps.petstore.cache.ServiceCache;
import com.yaps.petstore.dao.CategoryDao;
import com.yaps.petstore.dao.ItemDao;
import com.yaps.petstore.dao.ProductDao;
import com.yaps.petstore.model.Category;
import com.yaps.petstore.model.CategoryDTO;
import com.yaps.petstore.model.Item;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.Product;
import com.yaps.petstore.model.ProductDTO;

@RunWith(MockitoJUnitRunner.class)
public class CatalogServiceImplTest {

	@Mock
	private CategoryDao categoryDaoMock;	
	
	@Mock
	private ProductDao productDaoMock;	
	
	@Mock
	private ItemDao itemDaoMock;
	
	private CategoryDTO categoryDto;
	
	private ProductDTO productDto;
	
	private ItemDTO itemDto;
	
	
	private CatalogServiceImpl catalogService;
	
	private Long id;
	
	@Before
	public void setUp() throws Exception {
		
		catalogService = new CatalogServiceImpl();
		catalogService.setProductDao(productDaoMock);
		
		catalogService.setItemDao(itemDaoMock);
		
		catalogService.setCategoryDao(categoryDaoMock);

		Mapper mapper = new DozerBeanMapper();
		catalogService.setMapper(mapper);
		
		itemDto = getItemDto();

		categoryDto = getCategoryDto();
				
		productDto = getProductDto();
		
		id = Long.valueOf(1L);
	}

	@After
	public void tearDown() throws Exception {
		catalogService = null;
		itemDto = null;
	}

	
	
	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */
	
	@Test 
	public void saveCategoryTest() throws Exception {
		CategoryDTO aCategoryDto = catalogService.createCategory(categoryDto);
		
		assertNotNull(aCategoryDto);
		assertEquals(categoryDto, aCategoryDto);
	}	
	
	@Test 
	public void udapteCategoryTest() throws Exception {
		catalogService.updateCategory(categoryDto);
		
		//verify(categoryDaoMock).saveOrUpdate();
	}
	
	
	@Test 
	public void deleteCategoryTest() throws Exception {
		
		catalogService.deleteCategory(id);
		
		verify(categoryDaoMock).delete(id);
	}
	

	@Test 
	public void findCategoriesTest() throws Exception {
		List<Category> returnedLst = new ArrayList<Category>();
		
		returnedLst.add(getCategory());
		returnedLst.add(getCategory2());
		
		when(categoryDaoMock.findAll()).thenReturn(returnedLst);
		
		Collection<CategoryDTO> categoriesDto = catalogService.findCategories();
		
		assertNotNull(categoriesDto);
		
		assertEquals(2, categoriesDto.size());
	}	

	@Test 
	public void findCategoryTest() throws Exception {
		when(categoryDaoMock.get(id)).thenReturn(getCategory());
		
		CategoryDTO myCategoryDto = catalogService.findCategory(id);
		
		assertNotNull(myCategoryDto);
		
		assertEquals(Long.valueOf(1L), myCategoryDto.getId());
		assertEquals(getCategoryDto(), myCategoryDto);		
	}	
	

	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */
	@Test 
	public void saveProductTest() throws Exception {
		ProductDTO aProductDto = catalogService.createProduct(productDto);
		assertNotNull(aProductDto);
		assertEquals(productDto,aProductDto);
	}	
	
	@Test 
	public void udapteProductTest() throws Exception {
		catalogService.updateProduct(productDto);
		
		//verify(productDaoMock).saveOrUpdate(productDto);
	}
	
	
	@Test 
	public void deleteProductTest() throws Exception {
		
		catalogService.deleteProduct(id);
		
		//verify(productDaoMock).delete(id);
	}
	

	@Test 
	public void findProductsTest() throws Exception {
		List<Product> returnedLst = new ArrayList<Product>();
		
		returnedLst.add(getProduct());
		returnedLst.add(getProduct2());
		
		when(productDaoMock.findAll()).thenReturn(returnedLst);
		
		Collection<ProductDTO> productDtos = catalogService.findProducts();
		
		assertNotNull(productDtos);
		
		assertEquals(2, productDtos.size());
	}	

	@Test 
	public void findProductTest() throws Exception {
		when(productDaoMock.get(id)).thenReturn(getProduct());
		
		ProductDTO prdDto = catalogService.findProduct(id);
		
		assertNotNull(prdDto);
		
		assertEquals(Long.valueOf(1L), prdDto.getId());
		assertEquals(getProductDto(), prdDto);
	}	
	

	/* ------------------------------------------ */
	/* --------TEST CRUD ITEM-------------------- */
	/* ------------------------------------------ */
	//TODO
	@Test 
	public void saveItemTest() throws Exception {
		ItemDTO aItemDto = catalogService.createItem(itemDto);
		assertNotNull(aItemDto);
		assertEquals(itemDto,aItemDto);
	}	
	
	@Test 
	public void udapteItemTest() throws Exception {
		catalogService.updateItem(itemDto);
		
		//verify(productDaoMock).saveOrUpdate(productDto);
	}
	
	
	@Test 
	public void deleteItemTest() throws Exception {
		
		catalogService.deleteItem(id);;
		
		//verify(productDaoMock).delete(id);
	}
	

	@Test 
	public void findItemsTest() throws Exception {
		List<Item> returnedLst = new ArrayList<Item>();
		
		returnedLst.add(getItem());
		returnedLst.add(getItem2());
		
		when(itemDaoMock.findAll()).thenReturn(returnedLst);
		
		Collection<ItemDTO> itemDtos = catalogService.findItems();
		
		assertNotNull(itemDtos);
		
		assertEquals(2, itemDtos.size());
	}	

	@Test 
	public void findItemTest() throws Exception {
		when(itemDaoMock.get(id)).thenReturn(getItem());
		
		ItemDTO itmDto = catalogService.findItem(id);
		
		assertNotNull(itmDto);
		
		assertEquals(Long.valueOf(1L), itmDto.getId());
		assertEquals(getItemDto(), itmDto);
	}	
	
	/* ---------------------------------- */
	/* --------- Private methods -------- */
	/* ---------------------------------- */
	
	private CategoryDTO getCategoryDto(){
		CategoryDTO category = new CategoryDTO(Long.valueOf(1L), "categoryName", "categoryDesc");
		return category;
	}

	private Category getCategory(){
		Category category = new Category(Long.valueOf(1L), "categoryName", "categoryDesc");
		return category;
	}
	

	private Category getCategory2(){
		Category category = new Category(Long.valueOf(2L), "categoryName2", "categoryDesc2");
		return category;
	}	

	
	private ProductDTO getProductDto(){
		ProductDTO product = new ProductDTO(Long.valueOf(1L), "productName", "productDesc");
		return product;
	}	
	
	

	private Product getProduct(){
		Product product = new Product(Long.valueOf(1L), "productName", "productDesc");
		return product;
	}	
	
	private Product getProduct2(){
		Product product = new Product(Long.valueOf(2L), "productName2", "productDesc2");
		return product;
	}
	
	
	
	private ItemDTO getItemDto(){
		ItemDTO itemDto = new ItemDTO(Long.valueOf(1L),"articleID",20.0);
		return itemDto;
	}

	private ItemDTO getItemDto(Item item){
		Mapper mapper = new DozerBeanMapper();
		ItemDTO itemDto = mapper.map(item, ItemDTO.class);
		ProductDTO productDto = mapper.map(item.getProduct(), ProductDTO.class);
		itemDto.setProduct(productDto);
		return itemDto;
	}
	

	private Item getItem(){
		Item item = new Item(Long.valueOf(1L),"articleID",20.0);
		item.setProduct(getProduct());
		return item;
	}
	
	private Item getItem2(){
		Item item = new Item(Long.valueOf(2L),"articleID2",30.0);
		
		return item;
	}	
	
}
