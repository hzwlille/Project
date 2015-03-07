package com.yaps.petstore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * This class encapsulates all the data for a ShoppingCartItem.
 *
 */
public class ShoppingCartItem implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8510500425206147996L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private String _itemId;
    private String _itemName;
    private String _productDescription;
    private int _quantity;
    private double _unitCost;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ShoppingCartItem(final String itemId, final String itemName, final String productDescription, final int quantity, final double unitCost) {
        _itemId = itemId;
        _itemName = itemName;
        _productDescription = productDescription;
        _quantity = quantity;
        _unitCost = unitCost;
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getItemId() {
        return _itemId;
    }

    public String getItemName() {
        return _itemName;
    }

    public String getProductDescription() {
        return _productDescription;
    }

    public int getQuantity() {
        return _quantity;
    }

    public double getUnitCost() {
        return _unitCost;
    }

    public double getTotalCost() {
        return _quantity * _unitCost;
    }

//    public String toString() {
//        final StringBuffer buf = new StringBuffer();
//        buf.append("ItemDTO{");
//        buf.append("itemId=").append(getItemId());
//        buf.append(",itemName=").append(getItemName());
//        buf.append(",productDescription=").append(getProductDescription());
//        buf.append(",quantity=").append(getQuantity());
//        buf.append(",unitCost=").append(getUnitCost());
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
			.append(_itemId)
			.append(_itemName)
			.append(_productDescription)
			.append(_quantity)
			.append(_unitCost)			
			.hashCode();
	}    
}
