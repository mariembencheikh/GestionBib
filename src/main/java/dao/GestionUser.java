package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.entities.Livre;
import dao.entities.User;

public class GestionUser implements IgestionUser {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionbib"); 
	EntityManager em = emf.createEntityManager();
	@Override
	public void saveUser(User u) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(u);
		et.commit();		
	}

	@Override
	public User verifier(String email, String password) {
		try {
		Query q = em.createQuery("select u from User u  where u.email like :x and u.password like :y")
		.setParameter("x", email)
		.setParameter("y", password);
		User user=  (User) q.getSingleResult();
		return user;
	    } catch (NoResultException e) {			
	    	// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public User getUser(int id) {
		return em.find(User.class, id);
	}
}
