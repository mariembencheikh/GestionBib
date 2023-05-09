package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Genre;
import dao.entities.Livre;

public class GestionGenreImplJPA implements IGestionGenre{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionbib"); 
	EntityManager em = emf.createEntityManager();
	@Override
	public void AddGenre(Genre g) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(g);
		et.commit();
		
	}

	@Override
	public void deleteGenre(int idGenre) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(getGenre(idGenre));
		et.commit();	
		
	}

	@Override
	public void updateGenre(Genre g) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(g);
		et.commit();
		
	}

	@Override
	public Genre getGenre(int idGenre) {
		return em.find(Genre.class, idGenre);
		
	}

	@Override
	public List<Genre> getAllGenres() {
		Query q = em.createQuery("select g from Genre g");
		return q.getResultList();
	}

	@Override
	public List<Livre> getAllLivres() {
		// TODO Auto-generated method stub
		return null;
	}

}
