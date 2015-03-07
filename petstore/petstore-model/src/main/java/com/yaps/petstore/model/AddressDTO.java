package com.yaps.petstore.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a manualyLoadedClient view of a Category. This class only
 * transfers data from a distant service to a manualyLoadedClient.
 */
public final class AddressDTO implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4972447447950642673L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
	private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    // ======================================
    // =         Getters and Setters        =
    // ======================================
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public String getStreet1() {
        return street1;
    }

    public void setStreet1(final String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(final String street2) {
    	this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
    	this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
    	this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(final String zipcode) {
    	this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
    	this.country = country;
    }

    /**
     * This method returns true if at least one attribute is set
     *
     * @return
     */
    public boolean isDirty() {
        boolean dirty = false;
        if ((getStreet1() != null && !"".equals(getStreet1())) ||
                (getStreet2() != null && !"".equals(getStreet2())) ||
                (getCity() != null && !"".equals(getCity())) ||
                (getState() != null && !"".equals(getState())) ||
                (getZipcode() != null && !"".equals(getZipcode())) ||
                (getCountry() != null && !"".equals(getCountry())))
            dirty = true;
        return dirty;
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
				.append(street1)
				.append(street2)
				.append(state)
				.append(city)
				.append(country)
				.append(zipcode)
				.hashCode();
	}    
    

}
