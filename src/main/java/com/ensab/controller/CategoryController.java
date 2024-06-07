package com.ensab.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensab.dao.CategorieRepository;
import com.ensab.dao.UserRepository;
import com.ensab.entities.Categorie;
import com.ensab.entities.User;

import jakarta.validation.Valid;



@Controller
public class CategoryController {
    @Autowired
    private CategorieRepository categorieRepository;
    @GetMapping("/categories")
    public String showCategoriesForm(Model model ,@RequestParam(name="page",defaultValue="0")int p ,
    		@RequestParam(name="size",defaultValue="5")int s,
    		@RequestParam(name="mc",defaultValue="")String mc) {
        Page<Categorie> pageCategorie = categorieRepository.chercher("%"+mc+"%",PageRequest.of(p, s));
        model.addAttribute("listCategories", pageCategorie.getContent());
        int[] pages=new int[pageCategorie.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("size", s);
        model.addAttribute("pageCourante", p);
        model.addAttribute("mc",mc);
        return "categories";
    }
    @GetMapping("/addCategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Categorie());
        return "addCategory";
    }

    @RequestMapping(value="/saveAddCategory", method=RequestMethod.POST)
    public String save(Model m, @Valid Categorie category, BindingResult br) {
        if (categorieRepository.existsByNomCategorie(category.getNomCategorie())) {
            m.addAttribute("error", "Le nom de la catégorie existe déjà.");
            m.addAttribute("category", category); // Ajouter l'objet category au modèle
            return "addCategory";
        }

        categorieRepository.save(category);
        return "redirect:/categories";
    }
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String delete ( @RequestParam Long id ,String motCle,int page, int size) {
    	categorieRepository.deleteById(id);
    	return "redirect:/categories?page=" + page + "&size=" + size + "&motCle=" + motCle;

    	}
    
    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public String edit(Model m, @RequestParam Long idCategory) {
        Optional<Categorie> optionalCategorie = categorieRepository.findById(idCategory);

        if (optionalCategorie.isPresent()) {
        	Categorie categorie = optionalCategorie.get();
            m.addAttribute("categorie", categorie);
            return "editCategorie";
        } else {
            return "categorieNonTrouve";
        }
    }
    @PostMapping("/updateCategory")
    public String updateCategory(Model m, @ModelAttribute("categorie") @Valid Categorie category, BindingResult br) {
        if (br.hasErrors()) {
            return "editCategorie";
        }

        categorieRepository.save(category);

        return "redirect:/categories";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}