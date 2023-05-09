package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Genre;
import dao.entities.Livre;
import dao.entities.User;

public class GestionLivreImplJPA implements IGestionLivre{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionbib"); 
	EntityManager em = emf.createEntityManager();
	
	@Override
	public void AddLivre(Livre l) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(l);
		et.commit();
		
	}

	@Override
	public void deleteLivre(int idLivre) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(getLivre(idLivre));
		et.commit();	
		
	}

	@Override
	public void updateLivre(Livre l) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(l);
		et.commit();
		
	}

	@Override
	public Livre getLivre(int idLivre) {
		return em.find(Livre.class, idLivre);
		
	}

	@Override
	public List<Livre> getAllLivre() {
		Query q = em.createQuery("select l from Livre l");
		return q.getResultList();
	}

	@Override
	public List<Livre> getAllLivres(String mc) {
		Query q = em.createQuery("select l from Livre l where l.titre like :x");
		q.setParameter("x", "%"+mc+"%");

		return q.getResultList();
	}

	@Override
	public Livre vérifierLivre(String titre,String isbn) {
		
			Query q = em.createQuery("select l from Livre l  where l.titre like :x and l.ISBN like :y")
			.setParameter("x", titre)
			.setParameter("y", isbn);
			Livre livre= (Livre) q.getSingleResult();
			return livre;
		    
	}

	/*@Override
	public List<Livre> getAllLivresPag(int pageNumber, int rowsPerPage) {
		int firstResult = (pageNumber - 1) * rowsPerPage;
	    Query q = em.createQuery("select l from Livre l order by l.idLivre ASC");
	    q.setFirstResult(firstResult);
	    q.setMaxResults(rowsPerPage);
	    return q.getResultList();
	}*/
	
	
	public List<Livre> getAllLivresPag(int currentPage, int recordsPerPage) {
	    int firstResult = (currentPage - 1) * recordsPerPage;
	    
	    Query query = em.createQuery("SELECT l FROM Livre l ORDER BY l.idLivre ASC");
	    query.setFirstResult(firstResult);
	    query.setMaxResults(recordsPerPage);

	    List<Livre> livres = query.getResultList();
	    return livres;
	}

	
	
	
	
	

}
