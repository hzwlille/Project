package com.yaps.petstore.service.catalog.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.dao.CategoryDao;
import com.yaps.petstore.dao.ItemDao;
import com.yaps.petstore.dao.ProductDao;
import com.yaps.petstore.model.Category;
import com.yaps.petstore.model.CategoryDTO;
import com.yaps.petstore.model.Item;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.Product;
import com.yaps.petstore.model.ProductDTO;
import com.yaps.petstore.service.catalog.CatalogService;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService{

	Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class.getName());

	protected CategoryDao categoryDao ;
	
	protected ProductDao productDao ;	
	
	protected ItemDao itemDao ;
	
	@Autowired
	protected Mapper mapper;
	
	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}


	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
//add
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	
	

	public CatalogServiceImpl(){
	}
		

	
    // ======================================
	// = Product Business methoods =
	// ======================================
	public ProductDTO createProduct(ProductDTO productDto) throws CheckException {
		if (logger.isDebugEnabled()){
			logger.debug("start");			
		}

		
		if (productDto == null)
            throw new CheckException("Product object is null");

        Product product = mapper.map(productDto, Product.class);		
		
		productDao.saveOrUpdate(product);

        ProductDTO productworkDto = mapper.map(product, ProductDTO.class);

		if (logger.isDebugEnabled()){
	        logger.debug("stop");			
		}

		return productworkDto;
	}

	@Transactional(readOnly=true)
	public List<ProductDTO> findProducts() {
		List<Product> lst = productDao.findAll();
		List<ProductDTO> lstDto = new ArrayList<ProductDTO>();
		
		for (Product product:lst){
	        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
	        lstDto.add(productDTO);
		}
		
		return lstDto;	
	}

	@Transactional(readOnly=true)
	public List<ProductDTO>  findProducts(Long categoryId) throws CheckException {

		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid category id");
        
		List<Product> lst = productDao.findByCategoryId(categoryId);
		List<ProductDTO> lstDto = new ArrayList<ProductDTO>();
		
		for (Product product:lst){
	        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
	        lstDto.add(productDTO);
		}
		
		return lstDto;
	}

	@Transactional(readOnly=true)
	public ProductDTO findProduct(Long productId) throws CheckException {

		if (productId == null || "".equals(productId))
            throw new CheckException("Invalid id");
		
		Product product = productDao.get(productId);
		ProductDTO productDto = mapper.map(product, ProductDTO.class);
		return productDto;
	}	
	

	@Transactional(readOnly=true)
 	public List<ProductDTO> findProducts(String categoryName) throws CheckException {
		if (categoryName == null || "".equals(categoryName))
            throw new CheckException("Invalid category name");
		
		List<Product> lst = productDao.findByCategoryName(categoryName);
		List<ProductDTO> lstDto = new ArrayList<ProductDTO>();
		
		for (Product product:lst){
	        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
	        lstDto.add(productDTO);
		}
		
		return lstDto;		
	}	
	
	@Transactional
	public void updateProduct(ProductDTO productDto) throws CheckException {
		if (productDto == null)
            throw new CheckException("Product object is null");
		
		if (productDto.getId() == null)
            throw new CheckException("Invalid id");
        
		Product product = mapper.map(productDto, Product.class);		
		
		productDao.saveOrUpdate(product);
	}

	@Transactional
	public void deleteProduct(Long productId) throws CheckException {
		
		if (productId == null || "".equals(productId))
            throw new CheckException("Invalid id");
		
		productDao.delete(productId);
	}
	
    // ======================================
    // =        Item Business methods       =
    // ======================================	
	
	@Transactional
	public ItemDTO createItem(ItemDTO itemDto) throws CheckException {
		if (itemDto == null)
            throw new CheckException("Item object is null");
		
		Item item = mapper.map(itemDto, Item.class);
		itemDao.saveOrUpdate(item);
		
		ItemDTO itemworkDto = mapper.map(item, ItemDTO.class);
		return itemworkDto;
		
	}

	@Transactional
	public void updateItem(ItemDTO itemDto) throws CheckException {
		if (itemDto == null)
            throw new CheckException("Item object is null");
        
		
		if (itemDto.getId() == null)
            throw new CheckException("Invalid id");

		Item item = mapper.map(itemDto, Item.class);		
		
		itemDao.saveOrUpdate(item);		
	}


	@Transactional
	public void deleteItem(Long itemId) throws CheckException {
		if (itemId == null || "".equals(itemId))
            throw new CheckException("Invalid id");
		
		itemDao.delete(itemId);
	}

	@Transactional(readOnly=true)
	public List<ItemDTO> findItems() {
		List<Item> lst = itemDao.findAll();
		List<ItemDTO> lstDto = new ArrayList<ItemDTO>();
		for (Item item:lst){
	        ItemDTO itemDto = mapper.map(item, ItemDTO.class);
	        lstDto.add(itemDto);
		}
		
		return lstDto;		
	}

	@Transactional(readOnly=true)
	public List<ItemDTO> findItems(Long productId) throws CheckException {
		if (productId == null)
            throw new CheckException("Invalid id");

		List<Item> lst = itemDao.findByProductId(productId);
		List<ItemDTO> lstDto = new ArrayList<ItemDTO>();
		for (Item item:lst){
	        ItemDTO itemDto = mapper.map(item, ItemDTO.class);
	        lstDto.add(itemDto);
		}
		
		return lstDto;		
	}

	
	@Transactional(readOnly=true)
	public List<ItemDTO> searchItems(String keyword) throws CheckException{
		if (keyword == null || "".equals(keyword))
            throw new CheckException("Invalid id");

		
		List<Item> lst = itemDao.findByItemName(keyword);
		List<ItemDTO> lstDto = new ArrayList<ItemDTO>();
		for (Item item:lst){
	        ItemDTO itemDto = mapper.map(item, ItemDTO.class);
	        lstDto.add(itemDto);
		}
		
		return lstDto;
		
	}

	@Transactional(readOnly=true)
	public ItemDTO findItem(Long itemId) throws CheckException {
		if (itemId == null || "".equals(itemId))
            throw new CheckException("Invalid id");
		
		Item item = itemDao.get(itemId);    
		ItemDTO itemDto = mapper.map(item, ItemDTO.class);
		ProductDTO productDto = mapper.map(item.getProduct(), ProductDTO.class);
		
		itemDto.setProduct(productDto);
		return itemDto;
	}

	
    // ======================================
    // =        Category Business methods       =
    // ======================================	
	
	
	@Transactional
	public CategoryDTO createCategory(CategoryDTO categoryDto) throws CheckException {
		if (categoryDto == null)
            throw new CheckException("Category object is null");
        
		logger.info("start");
		Category category = mapper.map(categoryDto, Category.class);        
		
		categoryDao.saveOrUpdate(category);
		
		CategoryDTO categoryworkDto = mapper.map(category, CategoryDTO.class);
		
		logger.info("end");
		return categoryworkDto;
	}

	@Transactional
	public void deleteCategory(Long categoryId) throws CheckException {
		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");
        
		categoryDao.delete(categoryId);		
	}

	@Transactional
	public void updateCategory(CategoryDTO categoryDto) throws CheckException {
		if (categoryDto == null)
            throw new CheckException("Category object is null");
        
		if (categoryDto.getId() == null)
            throw new CheckException("Invalid id");
		
		Category category = mapper.map(categoryDto, Category.class);		
		categoryDao.saveOrUpdate(category);		
	}


	@Transactional(readOnly=true)
	public CategoryDTO findCategory(Long categoryId) throws CheckException {
		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");

		
		Category category = categoryDao.get(categoryId);
		
		CategoryDTO categoryDto = mapper.map(category,CategoryDTO.class);
		return categoryDto;
	}
	
	@Transactional(readOnly=true)
	public List<CategoryDTO> findCategories() {
		List<Category> lst =  categoryDao.findAll();
		List<CategoryDTO> lstDto = new ArrayList<CategoryDTO>();
		
		for (Category obj:lst){
	        CategoryDTO categoryDto = mapper.map(obj, CategoryDTO.class);
	        lstDto.add(categoryDto);
		}
		
		return lstDto;
	}


}
