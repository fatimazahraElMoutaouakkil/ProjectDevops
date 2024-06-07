package com.ensab.entities;





import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Payments")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn (name="type")
public  class Payment {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Integer idPayment;
private Float amount;
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date paymentDate;
@OneToOne(cascade = CascadeType.ALL)
private Cart cart;
@Column(name = "type", insertable = false, updatable = false)
private String type;
@ManyToOne
private User user;
public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}
public Payment() {
	super();
}
public Integer getIdPayment() {
	return idPayment;
}
public void setIdPayment(Integer idPayment) {
	this.idPayment = idPayment;
}
public Float getAmount() {
	return amount;
}
public void setAmount(Float amount) {
	this.amount = amount;
}
public Date getPaymentDate() {
	return paymentDate;
}
public void setPaymentDate(Date paymentDate) {
	this.paymentDate = paymentDate;
}
public Cart getCart() {
	return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
}

}
