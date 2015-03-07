package com.yaps.petstore.model;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


public final class Product implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6699161208358545698L;

	// ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
    private String name;
    private String description;

    @JsonBackReference
    private Category category;
    
    @JsonManagedReference
    private List<Item> items;
    
 

    // ======================================
    // =            Constructors            =
    // ======================================
    public Product() {
    }

    public Product(final Long id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
    	this.description = description;
    }

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

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    public List<Item> getItems() {
        return items;
    }   
    
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String toString() {
//        final StringBuffer buf = new StringBuffer();
//        buf.append("ProductDTO{");
//        buf.append("id=").append(getId());
//        buf.append(",name=").append(getName());
//        buf.append(",description=").append(getDescription());
//        if (getCategory() != null){
//            buf.append(",categoryId=").append(getCategory().getId());
//            buf.append(",categoryName=").append(getCategory().getName());
//        }
//        buf.append('}');
//        return buf.toString();
		return ToStringBuilder.reflectionToString(this);
	}
	
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public int hashCode(Object obj) {
		return new HashCodeBuilder().append(id) .append(name).append(description).hashCode();
	}	
}
