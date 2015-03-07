package com.yaps.petstore.service.order;

import java.util.List;
import java.util.Map;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.model.OrderDTO;

/**
 * This interface gives a remote view of the OrderServiceBean. Any distant manualyLoadedClient that wants to call
 * a method on the OrderServiceBean has to use this interface. Because it extends the EJBObject interface
 * (which extends Remote), every method must throw RemoteException.
 */
public interface OrderService  {

    // ======================================
    // =           Business methods         =
    // ======================================

    /**
     * Given a customer id and a shopping card, this method creates a Order. It first gets the
     * customer information, creates an Order domain object, uses the Order object to apply the
     * business rules for creation. It then creates OrderLines using the shopping cart
     * and sends back the OrderId.
     *
     * @param customerId   cannot be null.
     * @param shoppingCart cannot be null.
     * @return the order id
     * @throws CheckException  is thrown if a invalid data is found
     */
    Long createOrder(final Long customerId, Map<Long,Integer> shoppingCart) throws  CheckException;

    
    /**
     * Given a Order object, this method creates a Order. It first transforms
     * a Order into a Order domain object, uses the Order object to apply the
     * business rules for creation. It then transforms back the Order object into a
     * Order.
     *
     * @param order cannot be null.
     * @return the created Order
     * @throws CheckException  is thrown if a invalid data is found
     */    
    OrderDTO createOrder(OrderDTO orderDto) throws  CheckException;

    /**
     * Given an id this method uses the Order domain object to load all the data of this
     * object. It then transforms this object into a Order and returns it.
     *
     * @param orderId identifier
     * @return Order
     * @throws CheckException          is thrown if a invalid data is found
     */
    OrderDTO findOrder(Long orderId) throws CheckException;

    /**
     * Given an id, this method finds an Order domain object and then calls its deletion
     * method.
     *
     * @param orderId identifier
     * @throws CheckException  is thrown if a invalid data is found
     */
    void deleteOrder(Long orderId) throws  CheckException;
    
    
	public List<OrderDTO> findOrders(Long customerId) throws CheckException;
}
