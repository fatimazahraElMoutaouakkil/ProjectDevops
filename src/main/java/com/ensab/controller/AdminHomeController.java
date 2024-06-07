package com.ensab.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ensab.dao.CartRepository;
import com.ensab.dao.CategorieRepository;
import com.ensab.dao.PaymentRepository;
import com.ensab.entities.Cart;
import com.ensab.entities.Categorie;
import com.ensab.entities.Payment;
import com.ensab.service.PaymentService;
import com.ensab.service.ShoppingCartService;
@Controller
public class AdminHomeController {
	
	@Autowired
	private PaymentService paymentService;
	 @Autowired
	 private ShoppingCartService shoppingCartService;
	 @Autowired
	private CartRepository cartRepository;
    @GetMapping("/acceuil")
    public String showAcceuil() {
  
        return "acceuil";
    }
    @GetMapping("/adminHome")
    public String showRegistrationForm() {
  
        return "adminHome";
    }
    @GetMapping("/customers")
    public String showPaymentHistory(Model model) {
    	
        List<Payment> paymentList = paymentService.getAllPayments();
   

        // Ajouter la liste des paiements au modèle
        model.addAttribute("paymentList", paymentList);

        // Renvoyer la vue paymentHistory.html
        return "customers";
    }


}
