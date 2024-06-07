package com.ensab.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensab.dao.CartRepository;
import com.ensab.dao.CategorieRepository;
import com.ensab.dao.ProduitRepository;
import com.ensab.dao.UserRepository;
import com.ensab.entities.Cart;
import com.ensab.entities.Categorie;
import com.ensab.entities.Produit;
import com.ensab.entities.User;
import com.ensab.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;


@Controller
public class customerHomeController {
    
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieRepository categorieRepository;
    
    @Autowired
    private ShoppingCartService shoppingCartService;
    
    @Autowired
    private CartRepository cartRepository;
    
    @GetMapping(value = "/customerHome")
    public String acceuil(Model m ,HttpSession session) {
        List<Categorie> listCategories = categorieRepository.findAll();
        List<Produit> listProduits = produitRepository.findAll();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Rediriger vers la page de connexion ou gérer l'absence d'utilisateur de manière appropriée
            return "redirect:/login";
        }
        List<Cart> cartItems =  shoppingCartService.myCart(user.getUsername());
        m.addAttribute("categories", listCategories);
        m.addAttribute("products", listProduits);
        m.addAttribute("cartCount", cartItems.size());
        return "customerHome";
    }
    @GetMapping("/shop/category/{categoryId}")
    public String afficherProduitsParCategorie(@PathVariable Long categoryId, Model model) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(categoryId);
        
        if (categorieOptional.isPresent()) {
            Categorie categorie = categorieOptional.get();
            List<Produit> listProduits = produitRepository.findByCategorie(categorie);
            List<Categorie> listCategories = categorieRepository.findAll();
            model.addAttribute("categories", listCategories);
            model.addAttribute("products", listProduits);
            return "customerHome";
        } else {
            // Gérer le cas où la catégorie n'est pas trouvée
            return "redirect:/shop";
        }
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String afficherDetailsProduit(@PathVariable Long id, Model model) {
        // Récupérer le produit en fonction de l'ID
        Optional<Produit> optionalProduit = produitRepository.findById(id);

        // Vérifier si le produit existe
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();

            // Ajouter le produit au modèle
            model.addAttribute("product", produit);

            // Retourner la vue correspondante (par exemple, "productDetails")
            return "viewProduct";
        } else {
            // Gérer le cas où le produit n'est pas trouvé
            return "redirect:/customerHome"; // Rediriger vers la page principale du magasin
        }

}

    
}

    




