package com.ensab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensab.entities.Payment;



public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
