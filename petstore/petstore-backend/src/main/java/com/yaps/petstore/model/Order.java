package com.yaps.petstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public final class Order implements AbstractBean {

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
    private Address address = new Address();
    private CreditCard creditCard = new CreditCard();
    private List<OrderLine> orderLines;
    private Customer customer = new Customer();

    // ======================================
    // =            Constructors            =
    // ======================================
    public Order() {
    }

    public Order(final String firstname, final String lastname, final String street1, final String city, final String zipcode, final String country) {
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

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
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

    public CreditCard getCreditCard() {
        return creditCard;
    }
        
    public void setCreditCard(CreditCard creditCard) {
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

    public List<OrderLine> getOrderLines() {
        if (orderLines == null){
        	orderLines = new ArrayList<OrderLine>();
        }
    	
    	return this.orderLines;
    }

    public void setOrderLines(final List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

//    public String toString() {
//        final StringBuffer buf = new StringBuffer();
//        buf.append("OrderDTO{");
//        buf.append("id=").append(getId());
//        buf.append(",orderDate=").append(getOrderDate());
//        buf.append(",firstname=").append(getFirstname());
//        buf.append(",lastname=").append(getLastname());
//        buf.append(",street1=").append(getStreet1());
//        buf.append(",street2=").append(getStreet2());
//        buf.append(",city=").append(getCity());
//        buf.append(",state=").append(getState());
//        buf.append(",zipcode=").append(getZipcode());
//        buf.append(",country=").append(getCountry());
//        buf.append(",creditCardNumber=").append(getCreditCardNumber());
//        buf.append(",creditCardType=").append(getCreditCardType());
//        buf.append(",creditCardExpiry Date=").append(getCreditCardExpiryDate());
//        buf.append(",customerId=").append(getCustomer());
//        buf.append(",[orderLines=").append(getOrderLines()).append(']');
//        buf.append('}');
//        return buf.toString();
//    }
//    
//    @Override 
//    public boolean equals(Object other) {
//        boolean result = false;
//        if (other instanceof Order) {
//        	Order that = (Order) other;
//        	
//        	if (this.getId() == null || that.getId() == null) {
//        		return result;
//        	}
//        	
//            result = (
//            			this.getId().equals(that.getId())
//            			&& this.getCity().equals(that.getCity())
//            			&& this.getCountry().equals(that.getCountry())
//            			&& this.getFirstname().equals(that.getFirstname())
//            			&& this.getLastname().equals(that.getLastname())
//            		);
//        }
//        return result;
//    }
    
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
