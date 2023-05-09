package dao;

import java.util.List;

import dao.entities.Auteur;
import dao.entities.Editeur;

public interface IGestionEditeur {
	public void AddEditeur(Editeur e);
	public void deleteEditeur(int idEditeur);
	public void updateEditeur(Editeur e);
	public Editeur getEditeur(int idEditeur);
	public List<Editeur>getAllEditeurs();
}
