package dao.entities;

import java.sql.Blob;
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
import javax.persistence.OneToMany;


@Entity
public class Livre {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLivre;
	
	private String titre,ISBN;
	private String image;
	
	@ManyToOne
	private Auteur auteur;
	
	@ManyToOne
	private Editeur editeur;
	@ManyToMany
	@JoinTable(name="T_Livre_Genre",
				joinColumns = @JoinColumn(name="idLivre"),
				inverseJoinColumns = @JoinColumn(name="idGenre"))
	private List<Genre> genres;
	
	@OneToMany(mappedBy = "livre")
	private List<Emprunt> emprunts;
	
	
	public int getIdLivre() {
		return idLivre;
	}

	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	

	

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	

	

	public Livre(String titre, String iSBN, String image, Auteur auteur, Editeur editeur, List<Genre> genres,
			List<Emprunt> emprunts) {
		super();
		this.titre = titre;
		ISBN = iSBN;
		this.image = image;
		this.auteur = auteur;
		this.editeur = editeur;
		this.genres = genres;
		this.emprunts = emprunts;
	}

	public Livre(int idLivre, String titre, String iSBN, String image, Auteur auteur, Editeur editeur,
			List<Genre> genres, List<Emprunt> emprunts) {
		super();
		this.idLivre = idLivre;
		this.titre = titre;
		ISBN = iSBN;
		this.image = image;
		this.auteur = auteur;
		this.editeur = editeur;
		this.genres = genres;
		this.emprunts = emprunts;
	}

	public Livre(String titre, String iSBN, Auteur auteur, Editeur editeur, List<Genre> genres,
			List<Emprunt> emprunts) {
		super();
		this.titre = titre;
		ISBN = iSBN;
		this.auteur = auteur;
		this.editeur = editeur;
		this.genres = genres;
		this.emprunts = emprunts;
	}

	public Livre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
