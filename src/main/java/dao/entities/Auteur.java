package dao.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Auteur {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom,prenom;
	@OneToMany(mappedBy="auteur", cascade = CascadeType.ALL)
	private List<Livre> livres;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public List<Livre> getLivres() {
		return livres;
	}
	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}
	
	public Auteur(int id, String nom, String prenom, List<Livre> livres) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.livres = livres;
	}
	
	public Auteur(String nom, String prenom, List<Livre> livres) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.livres = livres;
	}
	public Auteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
