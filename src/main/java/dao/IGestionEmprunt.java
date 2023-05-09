package dao;

import java.util.List;

import dao.entities.Emprunt;
import dao.entities.Livre;
import dao.entities.User;

public interface IGestionEmprunt {
	public void empruntLivre(Emprunt emprunt); 
	public List<Emprunt> getAllEmpruntsById(int id);
}
