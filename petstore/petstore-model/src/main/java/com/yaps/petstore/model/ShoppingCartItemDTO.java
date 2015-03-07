package com.yaps.petstore.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an item of the Shopping Cart.
 * A shopping cart is made of several items.
 * This class only transfers data from a distant service to a client.
 */
public class ShoppingCartItemDTO implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8510500425206147996L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private Long itemId;
    private String itemName;
    private String productDescription;
    private int quantity;
    private double unitCost;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ShoppingCartItemDTO() {
    }
    
    public ShoppingCartItemDTO(final Long itemId, final String itemName, final String productDescription, final int quantity, final double unitCost) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public Long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public double getTotalCost() {
        return unitCost * quantity;
    }    
    
    
    
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

    
    public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public int hashCode(Object obj) {
		return new HashCodeBuilder()
			.append(itemId)
			.append(itemName)
			.append(productDescription)
			.append(quantity)
			.append(unitCost)			
			.hashCode();
	}    
}
