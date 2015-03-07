package com.yaps.petstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an OrderDTO. This class only
 * transfers data from a distant service to a client.
 */
public final class OrderDTO implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7227466083472030515L;
	// ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
    private Date orderDate;
    private String firstname;
    private String lastname;
    private AddressDTO address = new AddressDTO();
    private CreditCardDTO creditCard = new CreditCardDTO();
    private List<OrderLineDTO> orderLines;
    private CustomerDTO customer = new CustomerDTO();

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderDTO() {
    }

    public OrderDTO(final String firstname, final String lastname, final String street1, final String city, final String zipcode, final String country) {
        setFirstname(firstname);
        setLastname(lastname);
        setStreet1(street1);
        setCity(city);
        setZipcode(zipcode);
        setCountry(country);
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(final Date orderDate) {
    	this.orderDate = orderDate;
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

    
    public String getStreet1() {
        return this.address.getStreet1();
    }

    public AddressDTO getAddress() {
        return this.address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }     
    
    public void setStreet1(final String street1) {
    	this.address.setStreet1(street1);
    }

    public String getStreet2() {
        return this.address.getStreet2();
    }

    public void setStreet2(final String street2) {
    	this.address.setStreet2(street2);
    }

    public String getCity() {
        return this.address.getCity();
    }

    public void setCity(final String city) {
    	this.address.setCity(city);
    }

    public String getState() {
        return this.address.getState();
    }

    public void setState(final String state) {
    	this.address.setState(state);
    }

    public String getZipcode() {
        return this.address.getZipcode();
    }

    public void setZipcode(final String zipcode) {
    	this.address.setZipcode(zipcode);
    }

    public String getCountry() {
        return this.address.getCountry();
    }

    public void setCountry(final String country) {
    	this.address.setCountry(country);
    }

    public CreditCardDTO getCreditCard() {
        return creditCard;
    }
        
    public void setCreditCard(CreditCardDTO creditCard) {
		this.creditCard = creditCard;
	}

    public String getCreditCardNumber() {
        return this.creditCard.getCreditCardNumber();
    }

    public void setCreditCardNumber(final String creditCardNumber) {
        this.creditCard.setCreditCardNumber(creditCardNumber);
    }

    public String getCreditCardType() {
        return this.creditCard.getCreditCardType();
    }

    public void setCreditCardType(final String creditCardType) {
        this.creditCard.setCreditCardType(creditCardType);
    }

    public String getCreditCardExpiryDate() {
        return this.creditCard.getCreditCardExpiryDate();
    }

    public void setCreditCardExpiryDate(final String creditCardExpiryDate) {
        this.creditCard.setCreditCardExpiryDate(creditCardExpiryDate);
    }

    public List<OrderLineDTO> getOrderLines() {
        if (orderLines == null){
        	orderLines = new ArrayList<OrderLineDTO>();
        }
    	
    	return this.orderLines;
    }

    public void setOrderLines(final List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerDTO customer) {
        this.customer = customer;
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
			.append(orderDate)
			.append(firstname)
			.append(lastname)
			.hashCode();
	}    
}
