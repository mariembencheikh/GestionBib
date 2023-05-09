package dao;

import java.util.List;

import dao.entities.Genre;
import dao.entities.Livre;

public interface IGestionGenre {
	public void AddGenre(Genre g);
	public void deleteGenre(int idGenre);
	public void updateGenre(Genre g);
	public Genre getGenre(int idGenre);
	public List<Genre>getAllGenres();
	public List<Livre> getAllLivres();


}
