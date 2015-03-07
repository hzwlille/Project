package com.yaps.petstore.service.catalog;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.CategoryDTO;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.ProductDTO;




import java.util.List;
/**
 * This interface gives a remote view of the CatalogServiceBean. Any distant manualyLoadedClient that wants to call
 * a method on the CatalogServiceBean has to use this interface. Because it extends the EJBObject interface
 * (which extends Remote), every method must throw RemoteException.
 */
public interface CatalogService {

	
    // ======================================
    // =      CategoryDTO Business methods     =
    // ======================================


	/**
     * Given a CategoryDTO object, this method creates a CategoryDTO. It first transforms
     * a CategoryDTO into a CategoryDTO domain object, uses the CategoryDTO object to apply the
     * business rules for creation. It then transforms back the CategoryDTO object into a
     * CategoryDTO.
	 * 
	 * @param categoryDTO
	 * @return
	 * @throws CheckException
	 */
	CategoryDTO createCategory(CategoryDTO categoryDTO) throws  CheckException;

    /**
     * Given an id this method uses the CategoryDTO domain object to load all the data of this
     * object. It then transforms this object into a CategoryDTO and retrun it.
     *
     * @param categoryDTOId identifier
     * @return CategoryDTO
     * @throws ObjectNotFoundException is thrown if no object with this given id is found
     * @throws CheckException          is thrown if a invalid data is found
     *
     */
    CategoryDTO findCategory(Long categoryId) throws  CheckException;

    /**
     * Given an id, this method finds a CategoryDTO domain object and then calls its deletion
     * method.
     *
     * @param categoryDTOId identifier
     * @throws CheckException  is thrown if a invalid data is found
     * @throws RemoteException is thrown if a remote call fails
     */
    void deleteCategory(Long categoryId) throws  CheckException;

    /**
     * Given a CategoryDTO object, this method updates a CategoryDTO. It first transforms
     * a CategoryDTO into a CategoryDTO domain object and uses the CategoryDTO object to apply the
     * business rules for modification.
     *
     * @param categoryDTO cannot be null.
     * @throws CheckException  is thrown if a invalid data is found
     * @throws RemoteException is thrown if a remote call fails
     */
    void updateCategory(CategoryDTO categoryDTO) throws CheckException;

    /**
     * This method return all the categories from the system. It uses the CategoryDTO domain object
     * to get the data. It then transforms this collection of CategoryDTO object into a
     * collection of CategoryDTO and returns it.
     *
     * @return a collection of CategoryDTO
     *
     */
    List<CategoryDTO> findCategories() ; 

    // ======================================
    // =      ProductDTO Business methods     =
    // ======================================

    /**
     * Given a ProductDTO object, this method creates a ProductDTO. It first transforms
     * a ProductDTO into a ProductDTO domain object, uses the ProductDTO object to apply the
     * business rules for creation. It then transforms back the ProductDTO object into a
     * ProductDTO.
     *
     * @param productDTO cannot be null.
     * @return the created ProductDTO
     * @throws DuplicateKeyException is thrown if an object with the same id
     *                               already exists in the system
     * @throws CheckException        is thrown if a invalid data is found
     *
     */
    ProductDTO createProduct(ProductDTO productDTO) throws  CheckException;

    /**
     * Given an id this method uses the ProductDTO domain object to load all the data of this
     * object. It then transforms this object into a ProductDTO and returns it.
     *
     * @param productDTOId identifier
     * @return ProductDTO
     * @throws CheckException          is thrown if a invalid data is found
     *
     */
    ProductDTO findProduct(Long productId) throws  CheckException;

    /**
     * Given an id, this method finds a ProductDTO domain object and then calls its deletion
     * method.
     *
     * @param productDTOId identifier
     * @throws CheckException  is thrown if a invalid data is found
     */
    void deleteProduct(Long productId) throws  CheckException;

    /**
     * Given a ProductDTO object, this method updates a ProductDTO. It first transforms
     * a ProductDTO into a ProductDTO domain object and uses the ProductDTO object to apply the
     * business rules for modification.
     *
     * @param productDTO cannot be null.
     * @throws CheckException  is thrown if a invalid data is found
     * @throws RemoteException is thrown if a remote call fails
     */
    void updateProduct(ProductDTO productDTO) throws CheckException;

    /**
     * This method return all the productDTOs from the system. It uses the ProductDTO domain object
     * to get the data. It then transforms this collection of ProductDTO object into a
     * collection of ProductDTO and returns it.
     *
     * @return a collection of ProductDTO
     * @throws ObjectNotFoundException is thrown if the collection is empty
     *
     */
    List<ProductDTO> findProducts() ;

    /**
     * This method return all the productDTOs for a given categoryDTO. It uses the ProductDTO domain object
     * to get the data. It then transforms this collection of ProductDTO object into a
     * collection of ProductDTO and returns it.
     *
     * @param categoryDTOId categoryDTO identifier cannot be null.
     * @return a collection of ProductDTO
     * @throws CheckException         is thrown if the id is invalid
     *
     */
    List<ProductDTO> findProducts(Long categoryId) throws  CheckException;
    
    
    /**
     * This method return all the productDTOs for a given categoryDTO. It uses the ProductDTO domain object
     * to get the data. It then transforms this collection of ProductDTO object into a
     * collection of ProductDTO and returns it.
     *
     * @param categoryDTOId categoryDTO identifier cannot be null.
     * @return a collection of ProductDTO
     * @throws CheckException         is thrown if the id is invalid
     *
     */
    //List findProductDTOs(String categoryDTOName) throws  CheckException;    

    // ======================================
    // =        ItemDTO Business methods       =
    // ======================================

    /**
     * Given a ItemDTO object, this method creates a ItemDTO. It first transforms
     * a ItemDTO into a ItemDTO domain object, uses the ItemDTO object to apply the
     * business rules for creation. It then transforms back the ItemDTO object into a
     * ItemDTO.
     *
     * @param itemDTO cannot be null.
     * @return the created ItemDTO
     * @throws DuplicateKeyException is thrown if an object with the same id
     *                               already exists in the system
     * @throws CheckException        is thrown if a invalid data is found
     *
     */
    ItemDTO createItem(ItemDTO itemDTO) throws  CheckException;

    /**
     * Given an id this method uses the ItemDTO domain object to load all the data of this
     * object. It then transforms this object into a ItemDTO and returns it.
     *
     * @param itemDTOId identifier
     * @return ItemDTO
     * @throws ObjectNotFoundException is thrown if no object with this given id is found
     * @throws CheckException          is thrown if a invalid data is found
     *
     */
    ItemDTO findItem(Long itemId) throws  CheckException;

    /**
     * Given an id, this method finds a ItemDTO domain object and then calls its deletion
     * method.
     *
     * @param itemDTOId identifier
     * @throws CheckException  is thrown if a invalid data is found
     */
    void deleteItem(Long itemId) throws  CheckException;

    /**
     * Given a ItemDTO object, this method updates an ItemDTO. It first transforms
     * a ItemDTO into an ItemDTO domain object and uses the ItemDTO object to apply the
     * business rules for modification.
     *
     * @param itemDTO cannot be null.
     * @throws CheckException  is thrown if a invalid data is found
     * @throws RemoteException is thrown if a remote call fails
     */
    void updateItem(ItemDTO itemDTO) throws CheckException;

    /**
     * This method return all the itemDTOs from the system. It uses the ItemDTO domain object
     * to get the data. It then transforms this collection of ItemDTO object into a
     * collection of ItemDTO and returns it.
     *
     * @return a collection of ItemDTO
     * @throws ObjectNotFoundException is thrown if the collection is empty
     *
     */
    List<ItemDTO> findItems() ;

    /**
     * This method return all the itemDTOs for a given productDTO. It uses the ItemDTO domain object
     * to get the data. It then transforms this collection of ItemDTO object into a
     * collection of ItemDTO and returns it.
     *
     * @param productDTOId categoryDTO identifier cannot be null.
     * @return a collection of ItemDTO
     * @throws CheckException         is thrown if the id is invalid
     *
     */
    List<ItemDTO> findItems(Long productId) throws  CheckException;

    /**
     * This method return all the itemDTOs that match a given keyword. It uses the ItemDTO domain object
     * to get the data. It then transforms this collection of ItemDTO object into a
     * collection of ItemDTO and returns it.
     * 
     * @param keyword
     * @return
     * @throws CheckException
     */
	public List<ItemDTO> searchItems(String keyword) throws CheckException;
}
