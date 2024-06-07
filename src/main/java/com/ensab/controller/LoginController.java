package com.ensab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ensab.dao.UserRepository;
import com.ensab.entities.User;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/login")
    public String login(Model model, String username, String password, HttpServletRequest request) {
        // Vérifiez les informations d'identification en consultant la base de données
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            if ("admin".equals(user.getUsername())) {
                // L'administrateur obtient le rôle "ADMIN"
                user.setRole("ADMIN");
            } else {
                // Tous les autres utilisateurs obtiennent automatiquement le rôle "USER"
                user.setRole("USER");
            }

            userRepository.save(user);
            request.getSession().setAttribute("user", user);
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/adminHome";
            } else if ("USER".equals(user.getRole())) {
                return "redirect:/customerHome";
            }
        }

        model.addAttribute("error", true);
        return "login";
    }


	    @GetMapping("/logout")
	    public String logout() {
	        return "redirect:/login";
	    }

}
