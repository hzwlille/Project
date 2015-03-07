package com.yaps.petstore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This class encapsulates all the data for an OrderLine.
 *
 */
public final class OrderLine implements AbstractBean {

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
    private Order order;
    private Item item;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderLine() {
    }

    public OrderLine(final int quantity, final double unitCost) {
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

    public Item getItem() {
        return item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

    public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

//	public String toString() {
//        final StringBuffer buf = new StringBuffer();
//        buf.append("OrderLineDTO{");
//        buf.append("quantity=").append(getQuantity());
//        buf.append(",unitCost=").append(getUnitCost());
//        buf.append(",item=").append(getItem());
//        buf.append('}');
//        return buf.toString();
//    }
	
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
