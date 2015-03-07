package com.yaps.petstore.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * This class encapsulates all the data for an Item.
 *
 */
public class Item implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3683094094531524261L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
    private String name;
    private double unitCost;
    private String imagePath;

    @JsonBackReference
    private Product product;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Item() {
    }

    public Item(final Long id, final String name, final double unitCost) {
        setId(id);
        setName(name);
        setUnitCost(unitCost);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
    	this.name = name;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(final double unitCost) {
    	this.unitCost = unitCost;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String imagePath) {
    	this.imagePath = imagePath;
    }

 
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String toString() {
//        final StringBuffer buf = new StringBuffer();
//        buf.append("ItemDTO{");
//        buf.append("id=").append(getId());
//        buf.append(",name=").append(getName());
//        buf.append(",unitCost=").append(getUnitCost());
//        buf.append(",imagePath=").append(getImagePath());
//        if (getProduct() != null){
//            buf.append(",productId=").append(getProduct().getId());
//            buf.append(",productName=").append(getProduct().getName());
//            buf.append(",productDescription=").append(getProduct().getDescription());
//        }
//        buf.append('}');
//        return buf.toString();
		return ToStringBuilder.reflectionToString(this);
    }
	
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	
	public int hashCode(Object obj) {
		return new HashCodeBuilder().append(id) .append(name).append(imagePath).hashCode();
	}
}
