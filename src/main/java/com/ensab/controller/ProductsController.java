
package com.ensab.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.ensab.dao.CategorieRepository;
import com.ensab.dao.ProduitRepository;
import com.ensab.entities.Produit;
import com.ensab.entities.Categorie;


import jakarta.validation.Valid;

@Controller
public class ProductsController {

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private CategorieRepository categorieRepository;

	@GetMapping(value = "/products")
	public String index(Model m ,@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="size", defaultValue = "5") int size,
			@RequestParam(name="key", defaultValue = "") String key) {
		Page <Produit> pageProduit=produitRepository.findByDescriptionContains(key,PageRequest.of(page, size));
		m.addAttribute("pages", new int[pageProduit.getTotalPages()]);
		m.addAttribute("key", key);
		m.addAttribute("currentPage", page);
		m.addAttribute("listProduits",pageProduit.getContent());
				
		return "products"; 
	}

	@GetMapping("/addProduct")
	public String showAddProductForm(Model model) {
		   model.addAttribute("produit", new Produit());
	       model.addAttribute("categories", categorieRepository.findAll());
	       return "addProduct";
	}

	@PostMapping("/addProduct")
	public String saveProduct(@ModelAttribute("produit") Produit produit, BindingResult bindingResult,
	                          Model model, @RequestParam("file") MultipartFile image) {
	    if (bindingResult.hasErrors()) {
	        List<Categorie> categories = categorieRepository.findAll();
	        model.addAttribute("categories", categories);
	        return "addProduct";
	    }
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			produit.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    produitRepository.save(produit);

	    return "redirect:/products";
	}



	 
	@RequestMapping(value="/deleteProduct", method=RequestMethod.GET)
	public String delete(@RequestParam Long id, @RequestParam String page, @RequestParam String size, @RequestParam String key) {
	    produitRepository.deleteById(id);
	    return "redirect:/products?page=" + page + "&size=" + size + "&key=" + key;
	}
	 @RequestMapping(value="/editProduct", method=RequestMethod.GET)
	    public String edit(Model m, @RequestParam Long idProduit) {
	        Optional<Produit> optionalProduit = produitRepository.findById(idProduit);

	        if (optionalProduit.isPresent()) {
	        	Produit produit = optionalProduit.get();
	        	m.addAttribute("categories", categorieRepository.findAll());
	            m.addAttribute("produit", produit);
	            return "editProduct";
	        } else {
	            return "ProduitNonTrouve";
	        }
	    }
	    @PostMapping("/updateProduct")
	    public String updateProduct(Model m, @ModelAttribute("produit") @Valid Produit produit, BindingResult br) {
	        if (br.hasErrors()) {
	            return "editProduct";
	        }
	        produitRepository.save(produit);

	        return "redirect:/products";
	    }
	    
	

}
