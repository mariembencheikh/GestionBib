package dao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Genre {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGenre;
	
	private String nom;
	
	@ManyToMany(mappedBy="genres")
	private List<Livre> livres;

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
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

	public Genre(int idGenre, String nom, List<Livre> livres) {
		super();
		this.idGenre = idGenre;
		this.nom = nom;
		this.livres = livres;
	}

	public Genre(String nom, List<Livre> livres) {
		super();
		this.nom = nom;
		this.livres = livres;
	}

	public Genre(String nom) {
		super();
		this.nom = nom;
	}

	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
