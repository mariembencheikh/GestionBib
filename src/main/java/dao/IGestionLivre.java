package dao;

import java.util.List;

import dao.entities.Genre;
import dao.entities.Livre;

public interface IGestionLivre {
	public void AddLivre(Livre l);
	public void deleteLivre(int idLivre);
	public void updateLivre(Livre l);
	public Livre getLivre(int idLivre);
	public Livre vérifierLivre(String titre,String isbn);
	public List<Livre> getAllLivre();
	public List<Livre> getAllLivres(String mc); 
	public List<Livre> getAllLivresPag(int pageNumber, int rowPerPage);

}
