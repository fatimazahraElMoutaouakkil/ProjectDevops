package com.ensab.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ensab.dao.CartRepository;
import com.ensab.entities.Cart;
import com.ensab.entities.CreditCardPayment;
import com.ensab.entities.Payment;
import com.ensab.entities.PaypalPayment;
import com.ensab.entities.User;
import com.ensab.service.PaymentService;
import com.ensab.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaimentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CartRepository cartRepository;

	@GetMapping("/payNow")
	public String showPaymentForm(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");

		// Vérifier si l'utilisateur est connecté
		if (user == null) {
			// Rediriger vers la page de connexion s'il n'est pas connecté
			return "redirect:/login";
		}

		// Envoyer l'utilisateur au modèle
		model.addAttribute("user", user);
		List<Cart> cartItems = shoppingCartService.myCart(user.getUsername());
		double total = shoppingCartService.calculateTotal(user.getUsername());
		model.addAttribute("total", total);
		model.addAttribute("cartCount", cartItems.size());
		model.addAttribute("payment", new Payment());

		return "paymentForm";
	}

	@PostMapping("/payNow")
	public String payNow(@ModelAttribute("cart") Cart cart,
			@ModelAttribute("type")   String type,HttpSession session,
	        @ModelAttribute("payment") Payment payment,@ModelAttribute("cardNumber")  String cardNumber,@DateTimeFormat(pattern = "yyyy-MM-dd") @ModelAttribute("expirationdate") Date expirationdate,@ModelAttribute("accountNumber") String accountNumber) {
		User user = (User) session.getAttribute("user");

		// Vérifier si l'utilisateur est connecté
		if (user == null) {
			// Rediriger vers la page de connexion s'il n'est pas connecté
			return "redirect:/login";
		}
	    if (type.equals("creditcard")) {
	        CreditCardPayment paiement = new CreditCardPayment();
	        paiement.setAmount(payment.getAmount());
	        paiement.setUser(user);
	        paiement.setCardNumber(cardNumber);
	        paiement.setExpirationDate(expirationdate);
	        paiement.setPaymentDate(payment.getPaymentDate());
	        
	        paymentService.addPayment(cart, paiement);
	    } else {
	        PaypalPayment paiement = new PaypalPayment() ;
	        paiement.setAmount(payment.getAmount());
	        paiement.setUser(user);
	        paiement.setPaymentDate(payment.getPaymentDate());
	        paiement.setAccountNumber(accountNumber);
	        paymentService.addPayment(cart, paiement);
	    }
	    
	    shoppingCartService.clearCart(user.getUsername());
	    return "paymentSuccess"; // Rediriger vers la page de succès
	}


}
