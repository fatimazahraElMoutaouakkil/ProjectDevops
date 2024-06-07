package com.ensab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensab.dao.PaymentRepository;
import com.ensab.entities.Cart;
import com.ensab.entities.Payment;

@Service
public class PaymentService {
     
	@Autowired
	private PaymentRepository paymentRepository;
	public void addPayment(Cart cart, Payment payment) {
		payment.setCart(cart);
		cart.setPayment(payment);
		paymentRepository.save(payment);
		
	}
	  public List<Payment> getAllPayments() {
	        return paymentRepository.findAll();
	    }

}
