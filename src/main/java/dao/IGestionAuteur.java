package dao;

import java.util.List;

import dao.entities.Auteur;
import dao.entities.Genre;

public interface IGestionAuteur {
	public void AddAuteur(Auteur a);
	public void deleteAuteur(int idAuteur);
	public void updateAuteur(Auteur a);
	public Auteur getAuteur(int idAuteur);
	public List<Auteur>getAllAuteurs();


}
