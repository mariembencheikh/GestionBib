package dao.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Emprunt {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Livre livre;
	
	@ManyToOne
	private User user;
	
	private Date dateEmprunt;
	private Date dateRetour;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	public Emprunt(int id, Livre livre, User user, Date dateEmprunt, Date dateRetour) {
		super();
		this.id = id;
		this.livre = livre;
		this.user = user;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	public Emprunt(Livre livre, User user, Date dateEmprunt, Date dateRetour) {
		super();
		this.livre = livre;
		this.user = user;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	public Emprunt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
