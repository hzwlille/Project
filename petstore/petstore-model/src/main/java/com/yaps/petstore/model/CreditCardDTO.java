package com.yaps.petstore.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class encapsulates all the data for a credit card.
 *
 * @see CustomerDTO
 * @see OrderDTO
 */
public final class CreditCardDTO implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 603582400595284610L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private String creditCardNumber;
    private String creditCardType;
    private String creditCardExpiryDate;

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(final String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardType() {
    	return creditCardType;
    }

    public void setCreditCardType(final String creditCardType) {
    	this.creditCardType = creditCardType;
    }

    public String getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(final String creditCardExpiryDate) {
    	this.creditCardExpiryDate = creditCardExpiryDate;
    }

    /**
     * This method returns true if at least one attribute is set
     *
     * @return
     */
    public boolean isDirty() {
        boolean dirty = false;
        if (
        		(getCreditCardNumber() != null && !"".equals(getCreditCardNumber())) ||
                (getCreditCardType() != null && !"".equals(getCreditCardType())) ||
                (getCreditCardExpiryDate() != null && !"".equals(getCreditCardExpiryDate()))
           )
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
				.append(creditCardNumber) 
				.append(creditCardNumber)
				.append(creditCardNumber)
				.hashCode();
	}    
    
}
