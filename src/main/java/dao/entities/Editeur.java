package dao.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editeur {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEditeur;
	private String nom;
	@OneToMany(mappedBy="editeur", cascade = CascadeType.ALL)
	private List<Livre> livres;
	
	public int getIdEditeur() {
		return idEditeur;
	}
	public void setIdEditeur(int idEditeur) {
		this.idEditeur = idEditeur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Livre> getLivres() {
		return livres;
	}
	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}
	public Editeur(int idEditeur, String nom, List<Livre> livres) {
		super();
		this.idEditeur = idEditeur;
		this.nom = nom;
		this.livres = livres;
	}
	public Editeur(String nom, List<Livre> livres) {
		super();
		this.nom = nom;
		this.livres = livres;
	}
	public Editeur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
