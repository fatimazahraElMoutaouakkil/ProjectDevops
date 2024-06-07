package com.ensab.dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensab.entities.Categorie;
import com.ensab.entities.Produit;


public interface ProduitRepository extends JpaRepository<Produit, Long> {

    public Page<Produit> findByDescriptionContains( String key, Pageable pageable);

	public List<Produit> findByCategorie(Categorie categorie);


}
