package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.IGestionEditeur;
import dao.entities.Auteur;
import dao.entities.Editeur;

public class GestionEditeurImplJPA implements IGestionEditeur{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionbib"); 
	EntityManager em = emf.createEntityManager();
	@Override
	public void AddEditeur(Editeur e) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(e);
		et.commit();
		
	}

	@Override
	public void deleteEditeur(int idEditeur) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(getEditeur(idEditeur));
		et.commit();	
		
	}

	@Override
	public void updateEditeur(Editeur e) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(e);
		et.commit();
		
	}

	@Override
	public Editeur getEditeur(int idEditeur) {
		return em.find(Editeur.class, idEditeur);
		
	}

	@Override
	public List<Editeur> getAllEditeurs() {
		Query q = em.createQuery("select e from Editeur e");
		return q.getResultList();
	}

}
