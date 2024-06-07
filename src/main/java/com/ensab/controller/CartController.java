package com.ensab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ensab.dao.CartRepository;
import com.ensab.entities.Cart;
import com.ensab.entities.User;
import com.ensab.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    private CartRepository cartRepository;
    @PostMapping("/addToCart/{idProduit}")
    public String addToCart(@PathVariable Long idProduit, @ModelAttribute Cart cart, HttpSession session) {
        // Enregistrer l'élément dans la table cartItem.
    	User user = (User) session.getAttribute("user");
        if (user == null) {
            // Rediriger vers la page de connexion ou gérer l'absence d'utilisateur de manière appropriée
            return "redirect:/login";
        }
        cart.setUser(user);
        shoppingCartService.addToCart(idProduit, cart, user.getUsername());
        

        // Rediriger vers la page des produits après avoir ajouté au panier.
        return "redirect:/customerHome";
    }
    
    @GetMapping("/cart")
    public  String myCart ( HttpSession session , Model model){
      User user = (User) session.getAttribute("user");
      if (user == null) {
          // Rediriger vers la page de connexion ou gérer l'absence d'utilisateur de manière appropriée
          return "redirect:/login";
      }
       List<Cart> cartItems =  shoppingCartService.myCart(user.getUsername());
       double total = shoppingCartService.calculateTotal(user.getUsername());

       // Ajouter les éléments du panier et le total au modèle
       model.addAttribute("cart", cartItems);
       model.addAttribute("total", total);
       model.addAttribute("cartCount", cartItems.size());
        return "cart";
    }
    @GetMapping("/cart/removeItem/{id}")
    public String removeItem(@PathVariable Long id) {
    	cartRepository.deleteById(id);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        // Récupérer l'utilisateur connecté depuis la session
        User user = (User) session.getAttribute("user");

        // Vérifier si l'utilisateur est connecté
        if (user == null) {
            // Rediriger vers la page de connexion s'il n'est pas connecté
            return "redirect:/login";
        }

        // Envoyer l'utilisateur au modèle
        model.addAttribute("user", user);
        List<Cart> cartItems =  shoppingCartService.myCart(user.getUsername());
        double total = shoppingCartService.calculateTotal(user.getUsername());
        model.addAttribute("total", total);
        model.addAttribute("cartCount", cartItems.size());
        // Autres opérations nécessaires pour le processus de paiement

        // Renvoyer la vue de paiement
        return "checkout";
    }
}
