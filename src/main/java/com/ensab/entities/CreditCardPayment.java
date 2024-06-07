package com.ensab.entities;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CreditCard")

public class CreditCardPayment extends Payment {
	private String cardNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expirationDate;
	
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
	
	
	
	
}
