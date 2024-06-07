package com.ensab.entities;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Produit {
	
	@Id @GeneratedValue
	public long idProduit;
	public String designation,description,nom;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	public double prix;
	public int  quantite;
	public boolean selectionne;
	@ManyToOne
	@JoinColumn(name="ID_CAT")
	private Categorie categorie;


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNom() {
		return nom;
	}

	public Produit(String designation, String description, String nom,String image, double prix, int quantite,
			boolean selectionne, Categorie categorie) {
		super();
		this.designation = designation;
		this.description = description;
		this.nom = nom;
		this.image = image;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.categorie = categorie;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public Produit() {
		super();
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Produit(long idProduit, String designation, String description,double prix, int quantite,
			boolean selectionne) {
		super();
		this.idProduit = idProduit;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
	}
	public Produit(String designation, String description, double prix, int quantite) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
	}
	
	public long getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public boolean isSelectionne() {
		return selectionne;
	}
	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	
	

}
