package com.yaps.petstore.model;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yaps.petstore.common.exception.CheckException;




/**
 * This class encapsulates all the data for a Customer.
 *
 */
@Entity
public final class Customer implements AbstractBean {

	public static final String INVALID_PASSWORD = "Invalid password"; 
	public static final String PASSWORD_DONT_MATCH = "Invalid password";
	
	
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
    private Address address ;
    private CreditCard creditCard;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Customer() {
    }

    public Customer(final Long id, final String firstname, final String lastname) {
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

    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
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

    public CreditCard getCreditCard() {
        return creditCard;
    }
        
    public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public String getCreditCardNumber() {
        return creditCard.getCreditCardNumber();
    }


    public String getCreditCardType() {
        return creditCard.getCreditCardType();
    }


    public String getCreditCardExpiryDate() {
        return creditCard.getCreditCardExpiryDate();
    }

 
    // ======================================
    // =           Business methods         =
    // ======================================    
    /**
     * Given a password, this method then checks if it matches the user
     *
     * @param password1
     * @throws CheckException thrown if the password is empty or different than the one
     *                        store in database
     */
    public void matchPassword(final String password1) throws CheckException {
        if (password1 == null || "".equals(password1))
            throw new CheckException(INVALID_PASSWORD);

        // The password entered by the customer is not the same stored in database
        if (!password1.equals(getPassword()))
            throw new CheckException(PASSWORD_DONT_MATCH);
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
