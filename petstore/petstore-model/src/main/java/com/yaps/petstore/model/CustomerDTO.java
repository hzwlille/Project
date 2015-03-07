package com.yaps.petstore.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;




/**
 * 
 * @author zou
 *
 */
public final class CustomerDTO implements AbstractBean {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4770450084273556493L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
    private String firstname;
    private String password;
    private String lastname;
    private String telephone;
    private String email;
    private AddressDTO address ;
    private CreditCardDTO creditCard;

    // ======================================
    // =            Constructors            =
    // ======================================
    public CustomerDTO() {
    }

    public CustomerDTO(final Long id, final String firstname, final String lastname) {
        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
    	this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
    	this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
    	this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
    	this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
    	this.email = email;
    }


    public AddressDTO getAddress() {
    	if (this.address == null) {
    		this.address = new AddressDTO();
    	}
    	return address;
    }
    
    public void setAddress(AddressDTO address) {
        this.address = address;
    }    

    public String getStreet1() {
        return address.getStreet1();
    }


    public String getStreet2() {
        return address.getStreet2();
    }


    public String getCity() {
        return address.getCity();
    }


    public String getState() {
        return address.getState();
    }


    public String getZipcode() {
        return address.getZipcode();
    }


    public String getCountry() {
        return address.getCountry();
    }

    public CreditCardDTO getCreditCard() {
        return creditCard;
    }
        
    public void setCreditCard(CreditCardDTO creditCard) {
		this.creditCard = creditCard;
	}

	public String getCreditCardNumber() {
		if (creditCard != null) {
	        return creditCard.getCreditCardNumber();			
		} else {
			return null;
		}

    }


    public String getCreditCardType() {
		if (creditCard != null) {
	    	return creditCard.getCreditCardType();			
		} else {
			return null;
		}

    }


    public String getCreditCardExpiryDate() {
		if (creditCard != null) {
	    	return creditCard.getCreditCardExpiryDate();			
		} else {
			return null;
		}

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
			.append(firstname)
			.append(lastname)
			.append(password)
			.append(telephone)
			.append(email)
			.hashCode();
	}
}
