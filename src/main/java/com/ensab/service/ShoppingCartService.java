package com.ensab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensab.dao.CartRepository;
import com.ensab.dao.ProduitRepository;
import com.ensab.entities.Cart;
import com.ensab.entities.Produit;
import com.ensab.entities.User;

import jakarta.servlet.http.HttpSession;

//ShoppingCartService.java
@Service
public class ShoppingCartService {

 @Autowired
 private ProduitRepository produitRepository;

 @Autowired
 private CartRepository cartRepository;

	/*
	 * public void addToCart(HttpSession session,Long idProduit, int quantite) {
	 * Produit produit = produitRepository.findById(idProduit).orElse(null);
	 * 
	 * if (produit != null) { User user = (User) session.getAttribute("user"); //
	 * Obtenez l'utilisateur actuel Commande commande =
	 * getOrCreateCommandeForUser(user);
	 * 
	 * CommandLine ligneCommande = new CommandLine();
	 * ligneCommande.setProduit(produit); ligneCommande.setCommand(commande);
	 * ligneCommande.setQuantite(quantite);
	 * 
	 * commandLineRepository.save(ligneCommande); } }
	 * 
	 * private Commande getOrCreateCommandeForUser(User user) { Commande commande =
	 * commandeRepository.findByUser(user);
	 * 
	 * if (commande == null) { // Créer une nouvelle commande si l'utilisateur n'en
	 * a pas encore commande = new Commande(); commande.setUser(user);
	 * commandeRepository.save(commande); }
	 * 
	 * return commande; }
	 */
 public void addToCart(Long idProduit, Cart cart, String username){

	 Produit produit = produitRepository.findById(idProduit).orElse(null);
	     cart.setProduit(produit);

	         cart.setSubTotal(produit.getPrix());
	         cart.setUsername(username);
	         cartRepository.save(cart);
	 }
//Dans votre service ShoppingCartService
 public List<Cart> myCart(String userName){

	    List<Cart> cartItems = new ArrayList<>();
	    cartRepository.findByUsername(userName).forEach(cartItems::add);

	    return cartItems;
	}
 public double calculateTotal(String username) {
     List<Cart> cartItems = cartRepository.findByUsername(username);

     // Calculer le total en additionnant les prix de chaque élément du panier
     double total = cartItems.stream()
             .mapToDouble(cartItem -> cartItem.getProduit().getPrix())
             .sum();

     // Vous pouvez effectuer d'autres calculs ou ajustements si nécessaire.

     return total;
 }
public List<Cart> getAllCarts() {
	
	return  cartRepository.findAll();
}

public void clearCart(String username) {
    List<Cart> userCart = cartRepository.findByUsername(username);

    // Supprimer chaque élément du panier de l'utilisateur
    for (Cart cartItem : userCart) {
        cartRepository.delete(cartItem);
    }
}

 
}

