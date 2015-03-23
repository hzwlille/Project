package com.yaps.petstore.web.rest;

import com.sun.jersey.api.core.InjectParam;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.CategoryDTO;
import com.yaps.petstore.model.ItemDTO;
import com.yaps.petstore.model.ProductDTO;
import com.yaps.petstore.service.catalog.CatalogService;
import com.yaps.petstore.service.customer.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/catalog")
public class CatalogController {

    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class.getName());

    @InjectParam
    CatalogService catalogService;


    public List<CategoryDTO> getCategories() {

        return Collections.EMPTY_LIST;

    }

    @Path("/categories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDTO getCategory(Long categoryId) throws CheckException {
        return null;
    }


    public CategoryDTO addCategory(final CategoryDTO categoryDto) throws CheckException {
        logger.info("start addCategory");

        logger.debug("categoryDto  = {}", categoryDto.toString());

        return null;
    }


    public CategoryDTO updateCategory(Long categoryId, final CategoryDTO categoryDto) throws CheckException {
        logger.info("start updateCategory");

        logger.debug("categoryDto  = {}", categoryDto.toString());

        return null;
    }


    public void deleteCategory(Long categoryId) throws CheckException {
        logger.info("start deleteCategory");

        logger.debug("id to delete  = {}", categoryId);
    }


    public List<ProductDTO> getProductsByCategory(Long categoryId) throws CheckException {

        return Collections.EMPTY_LIST;

    }

    @Path("/product/{productId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO getProduct(@PathParam("productId") Long productId) throws CheckException {

        return catalogService.findProduct(productId);


    }


    public ProductDTO addProduct(final ProductDTO productDto) throws CheckException {
        logger.info("start productDto");

        logger.debug("productDto  = {}", productDto.toString());
        return null;
    }


    public ProductDTO updateProduct(Long productId, final ProductDTO productDto) throws CheckException {
        logger.info("start updateCategory");

        logger.debug("productDto  = {}", productDto.toString());
        return null;
    }


    public void deleteProduct(Long productId) throws CheckException {
        logger.info("start deleteProduct");

        logger.debug("id to delete  = {}", productId);


    }

    @Path("/items/{productId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> getItemsByProductId(@PathParam("productId") Long productId) throws CheckException {

        return catalogService.findItems(productId);

    }

    @Path("/item/{productId}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemDTO getItem(@PathParam("itemId") Long itemId) throws CheckException {

        return catalogService.findItem(itemId);

    }


    public ItemDTO addItem(final ItemDTO itemDto) throws CheckException {
        logger.info("start addItem");

        logger.debug("itemDto  = {}", itemDto.toString());

        return null;
    }


    public ItemDTO updateItem(Long itemId, final ItemDTO itemDto) throws CheckException {
        logger.info("start updateItem");

        logger.debug("itemDto  = {}", itemDto.toString());

        return null;
    }


    public void deleteItem(Long itemId) throws CheckException {
        logger.info("start itemCategory");

        logger.debug("id to delete  = {}", itemId);

    }


}
