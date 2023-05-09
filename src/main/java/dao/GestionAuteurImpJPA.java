package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Auteur;

public class GestionAuteurImpJPA implements IGestionAuteur{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionbib"); 
	EntityManager em = emf.createEntityManager();
	@Override
	public void AddAuteur(Auteur a) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(a);
		et.commit();
		
	}

	@Override
	public void deleteAuteur(int idAuteur) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(getAuteur(idAuteur));
		et.commit();	
		
	}

	@Override
	public void updateAuteur(Auteur a) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(a);
		et.commit();
		
	}

	@Override
	public Auteur getAuteur(int idAuteur) {
		return em.find(Auteur.class, idAuteur);
		
	}

	@Override
	public List<Auteur> getAllAuteurs() {
		Query q = em.createQuery("select a from Auteur a");
		return q.getResultList();
	}

	
}
