package com.ensab.entities;



import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String adresse;
    private String téléphone;
    @OneToMany(mappedBy="user")
	private Set<Cart> listCartes;
    @OneToMany(mappedBy="user")
    private Set<Payment> listpayments;


	public Set<Payment> getListpayments() {
		return listpayments;
	}
	public void setListpayments(Set<Payment> listpayments) {
		this.listpayments = listpayments;
	}
	public Set<Cart> getListCartes() {
		return listCartes;
	}
	public void setListCartes(Set<Cart> listCartes) {
		this.listCartes = listCartes;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public User(Long id, String username, String password, String role, String firstName, String lastName, String email,
			String adresse, String téléphone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adresse = adresse;
		this.téléphone = téléphone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTéléphone() {
		return téléphone;
	}
	public void setTéléphone(String téléphone) {
		this.téléphone = téléphone;
	}
	public User() {
		super();
	}
	public User(Long id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    


}
