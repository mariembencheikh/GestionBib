package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Emprunt;
import dao.entities.Livre;
import dao.entities.User;

public class GestionEmpruntImplJPA implements IGestionEmprunt {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionbib"); 
	EntityManager em = emf.createEntityManager();
	
	@Override
	public void empruntLivre(Emprunt emprunt) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(emprunt);
		et.commit();
		
	}

	@Override
	public List<Emprunt> getAllEmpruntsById(int idUser) {
		String query = "SELECT e FROM Emprunt e WHERE e.user.id = :userId";
        TypedQuery<Emprunt> typedQuery = em.createQuery(query, Emprunt.class);
        typedQuery.setParameter("userId", idUser);

        List<Emprunt> emprunts = typedQuery.getResultList();
        return emprunts;
	}

}
