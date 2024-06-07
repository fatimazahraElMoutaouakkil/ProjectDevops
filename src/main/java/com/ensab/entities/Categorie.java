package com.ensab.entities;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Categorie {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategory;
	private String nomCategorie;
	private String photo;
	@OneToMany(mappedBy="categorie")
	private Collection<Produit> produits =new ArrayList<Produit>();
	public Collection<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}
	private String description;
	
	public Categorie() {
		super();
	}
	public Categorie(long idCategory, String nomCategorie, String photo, String description) {
		super();
		this.idCategory = idCategory;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}
	public long getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
