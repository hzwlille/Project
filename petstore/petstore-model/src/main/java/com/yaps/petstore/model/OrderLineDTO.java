package com.yaps.petstore.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an OrderDTO Line. This class only
 * transfers data from a distant service to a client.
 */
public final class OrderLineDTO implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3646513194713497453L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
	private int quantity;
    private double unitCost;
    private OrderDTO order;
    private ItemDTO item;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderLineDTO() {
    }

    public OrderLineDTO(final int quantity, final double unitCost) {
        setQuantity(quantity);
        setUnitCost(unitCost);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(final double unitCost) {
        this.unitCost = unitCost;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(final ItemDTO item) {
        this.item = item;
    }

    public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public int hashCode(Object obj) {
		return new HashCodeBuilder()
			.append(id)
			.append(quantity)
			.append(unitCost)
			.hashCode();
	    
	}	


}
