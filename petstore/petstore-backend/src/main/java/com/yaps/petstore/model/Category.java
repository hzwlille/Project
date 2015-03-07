package com.yaps.petstore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * This class encapsulates all the data for a category.
 *
 */

@Entity
@Table(name="T_CATEGORY")
public  class Category implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4648729478877163459L;
	// ======================================
    // =             Attributes             =
    // ======================================

	@Id
	@GeneratedValue
	private Long id;
    
	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;

    @JsonManagedReference
    private List<Product> products;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Category() {
    }

    public Category(final Long id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
    	this.description = description;
    }

    @Id @GeneratedValue
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
    	this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
    	this.name = name;
    }

    @OneToMany
    @JoinColumn(name="category_fk")
    @LazyCollection(LazyCollectionOption.TRUE)
    public List<Product> getProducts() {
        return products;
    }
    
  
    public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String toString() {
//        final StringBuffer buf = new StringBuffer();
//        buf.append("Category{");
//        buf.append("id=").append(getId());
//        buf.append(",name=").append(getName());
//        buf.append(",description=").append(getDescription());
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
