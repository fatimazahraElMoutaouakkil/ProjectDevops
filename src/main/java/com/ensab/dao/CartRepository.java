package com.ensab.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensab.entities.Cart;
import com.ensab.entities.Categorie;

public interface CartRepository  extends JpaRepository<Cart, Long> {

	List<Cart> findByUsername(String username);
	  

}
