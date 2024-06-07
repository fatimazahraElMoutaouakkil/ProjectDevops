package com.ensab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ensab.dao.UserRepository;
import com.ensab.entities.User;



@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Ajouter un nouvel utilisateur au modèle
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute("user") User user) {
        // Vérifier si l'utilisateur existe déjà dans la base de données
        if (userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
            model.addAttribute("error", "User already exists. Please choose another username.");
            return "register";
        }

        // Autres validations et traitement...
        user.setRole("USER");
        // Enregistrer l'utilisateur dans la base de données
        userRepository.save(user);

        // Rediriger vers la page de connexion après la création du compte
        return "redirect:/login?registrationSuccess";
    }

}
