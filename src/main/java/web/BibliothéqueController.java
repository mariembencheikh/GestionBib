package web;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.util.UriComponentsBuilder;

import dao.GestionAuteurImpJPA;
import dao.GestionEditeurImplJPA;
import dao.GestionEmpruntImplJPA;
import dao.GestionGenreImplJPA;
import dao.GestionLivreImplJPA;
import dao.GestionUser;
import dao.IGestionAuteur;
import dao.IGestionEditeur;
import dao.IGestionEmprunt;
import dao.IGestionGenre;
import dao.IGestionLivre;
import dao.IgestionUser;
import dao.entities.Auteur;
import dao.entities.Editeur;
import dao.entities.Emprunt;
import dao.entities.Genre;
import dao.entities.Livre;
import dao.entities.User;

/**
 * Servlet implementation class BibliothéqueController
 */
@WebServlet("/BibliothéqueController")
// upload file's size up to 16MB
@MultipartConfig
public class BibliothéqueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BibliothéqueController() {
		super();
		// TODO Auto-generated constructor stub
	}

	IGestionLivre gestionLivre;
	IGestionAuteur gestionAuteur;
	IGestionEditeur gestionEditeur;
	IGestionGenre gestionGenre;
	IgestionUser gestionUser;
	IGestionEmprunt gestionEmprunt;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		gestionLivre = new GestionLivreImplJPA();
		gestionAuteur = new GestionAuteurImpJPA();
		gestionEditeur = new GestionEditeurImplJPA();
		gestionGenre = new GestionGenreImplJPA();
		gestionUser = new GestionUser();
		gestionEmprunt = new GestionEmpruntImplJPA();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Livre> livreOnPage = null;
		int totalPages=0;
		int currentPage = 0; 
		if (session != null) {
			String action = request.getParameter("action");
			//Liste des livres
			if (action == null) {
				 
		        int totalRecords = gestionLivre.getAllLivre().size();
				 int pageSize = 3;
				 if(totalRecords<3) {
					  livreOnPage = gestionLivre.getAllLivre(); // livre pour la page actuelle

				 }
				 else {
					  totalPages = (int) Math.ceil((double) totalRecords / pageSize); // nombre total de pages
					     currentPage = 1; // page actuelle
				        if (request.getParameter("page") != null) {
				            currentPage = Integer.parseInt(request.getParameter("page"));
				        }
				        int startIndex = (currentPage - 1) * pageSize; // index de départ de la page actuelle
					    int endIndex = Math.min(startIndex + pageSize, totalRecords); // index de fin de la page actuelle
					     livreOnPage = gestionLivre.getAllLivre().subList(startIndex, endIndex); // livre pour la page actuelle

				 }
		        
		        for(Livre l:gestionLivre.getAllLivre()) {
					List<Genre> genres = l.getGenres();
					request.setAttribute("genres", genres);
					
				}
		       
		        request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage); 
			    request.setAttribute("livres", livreOnPage);
				request.getRequestDispatcher("listeLivre.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("ajout")) {
				//form ajout livre
				request.setAttribute("auteurs", gestionAuteur.getAllAuteurs());
				request.setAttribute("editeurs", gestionEditeur.getAllEditeurs());
				request.setAttribute("allGenres",gestionGenre.getAllGenres());
				request.getRequestDispatcher("ajouterLivre.jsp").forward(request, response);

			} else if (action.equalsIgnoreCase("save") && request.getMethod().equalsIgnoreCase("post")) {

				String isbn = request.getParameter("isbn");
				String titre = request.getParameter("titre");
				System.out.println(titre);
				int idAuteur = Integer.parseInt(request.getParameter("auteur"));
				int idEditeur = Integer.parseInt(request.getParameter("editeur"));
				String[] genreIds = request.getParameterValues("genre");
				
				Part filePart = request.getPart("image");
				String fileName = filePart.getSubmittedFileName();
				String uploadDir = "C:/Users/User/workspacemeriem/GestionBib/src/main/webapp/images/" + fileName;
				FileOutputStream fos = new FileOutputStream(uploadDir);
				InputStream is = filePart.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();

				String id = request.getParameter("id");
				if (id.equals("")) {

					Livre livre = new Livre(titre, 
											isbn, 
											gestionAuteur.getAuteur(idAuteur),
											gestionEditeur.getEditeur(idEditeur), 
											null, null);
					
					livre.setImage(fileName);
				    List<Genre> genres = new ArrayList<>();
					for(String genreId:genreIds) {
						Genre genre =gestionGenre.getGenre(Integer.parseInt(genreId));
						genres.add(genre);
						
					}
					livre.setGenres(genres);
					gestionLivre.AddLivre(livre);
					System.out.println("livre saved");
				} else {
					Livre l = new Livre();
					l.setIdLivre(Integer.parseInt(id));
					l.setTitre(titre);
					l.setAuteur(gestionAuteur.getAuteur(idAuteur));
					l.setEditeur(gestionEditeur.getEditeur(idEditeur));
					l.setImage(fileName);
					List<Genre> genres = new ArrayList<>();
					for(String genreId:genreIds) {
						Genre genre =gestionGenre.getGenre(Integer.parseInt(genreId));
						genres.add(genre);
						
					}
					l.setGenres(genres);
					gestionLivre.updateLivre(l);

					
				}
				int totalRecords = gestionLivre.getAllLivre().size();
				 int pageSize = 3;
		         totalPages = (int) Math.ceil((double) totalRecords / pageSize); // nombre total de pages
			     currentPage = 1; // page actuelle
		        if (request.getParameter("page") != null) {
		            currentPage = Integer.parseInt(request.getParameter("page"));
		        }
		        int startIndex = (currentPage - 1) * pageSize; // index de départ de la page actuelle
			    int endIndex = Math.min(startIndex + pageSize, totalRecords); // index de fin de la page actuelle
			     livreOnPage = gestionLivre.getAllLivre().subList(startIndex, endIndex); // livre pour la page actuelle

		        for(Livre l:gestionLivre.getAllLivre()) {
					List<Genre> genres = l.getGenres();
					request.setAttribute("genres", genres);
					
				}
		       
		        request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage); 
			    request.setAttribute("livres", livreOnPage);
				request.setAttribute("auteurs", gestionAuteur.getAllAuteurs());
				request.setAttribute("editeurs", gestionEditeur.getAllEditeurs());
				request.getRequestDispatcher("listeLivre.jsp").forward(request, response);

			} else if (action.equalsIgnoreCase("modifier")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("livre", gestionLivre.getLivre(id));
				request.setAttribute("auteurs", gestionAuteur.getAllAuteurs());
				request.setAttribute("editeurs", gestionEditeur.getAllEditeurs());
				request.setAttribute("allGenres",gestionGenre.getAllGenres());

				request.getRequestDispatcher("ajouterLivre.jsp").forward(request, response);
			} 
			else if (action.equalsIgnoreCase("supprimer")) {
				int id = Integer.parseInt(request.getParameter("id"));		
				gestionLivre.deleteLivre(id);
				request.setAttribute("livres", gestionLivre.getAllLivre());
				request.setAttribute("auteurs", gestionAuteur.getAllAuteurs());
				request.setAttribute("editeurs", gestionEditeur.getAllEditeurs());
				int totalRecords = gestionLivre.getAllLivre().size();
				 int pageSize = 3;
		         totalPages = (int) Math.ceil((double) totalRecords / pageSize); // nombre total de pages
			     currentPage = 1; // page actuelle
		        if (request.getParameter("page") != null) {
		            currentPage = Integer.parseInt(request.getParameter("page"));
		        }
		        int startIndex = (currentPage - 1) * pageSize; // index de départ de la page actuelle
			    int endIndex = Math.min(startIndex + pageSize, totalRecords); // index de fin de la page actuelle
			     livreOnPage = gestionLivre.getAllLivre().subList(startIndex, endIndex); // livre pour la page actuelle

		        for(Livre l:gestionLivre.getAllLivre()) {
					List<Genre> genres = l.getGenres();
					request.setAttribute("genres", genres);
					
				}
		       
		        request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage); 
			    request.setAttribute("livres", livreOnPage);
				request.getRequestDispatcher("listeLivre.jsp").forward(request, response);
			}else if (action.equalsIgnoreCase("rechercher")) {
				String mc = request.getParameter("mc");		
				request.setAttribute("livres", gestionLivre.getAllLivres(mc));
				
				request.getRequestDispatcher("listeLivre.jsp").forward(request, response);
				

			}else if (action.equalsIgnoreCase("listeAuteurs")) {

				request.setAttribute("auteurs", gestionAuteur.getAllAuteurs());
				request.getRequestDispatcher("listeAuteur.jsp").forward(request, response);

			} else if (action.equalsIgnoreCase("listeEditeurs")) {

				request.setAttribute("editeurs", gestionEditeur.getAllEditeurs());
				request.getRequestDispatcher("listeEditeur.jsp").forward(request, response);

			}
			else if (action.equalsIgnoreCase("modifierAut")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("auteur", gestionAuteur.getAuteur(id));
				request.getRequestDispatcher("listeAuteur.jsp").forward(request, response);

				
			} 
			else if (action.equalsIgnoreCase("saveAut") && request.getMethod().equalsIgnoreCase("post")) {
				String id = request.getParameter("id");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				if (id.equals("")) {
					Auteur auteur = new Auteur(nom, prenom, null);
					gestionAuteur.AddAuteur(auteur);
				}
				else {
					Auteur auteur = new Auteur();
					auteur.setId(Integer.parseInt(id));
					auteur.setNom(nom);
					auteur.setPrenom(prenom);
					gestionAuteur.updateAuteur(auteur);
				}
				
				request.setAttribute("auteurs", gestionAuteur.getAllAuteurs());
				request.getRequestDispatcher("listeAuteur.jsp").forward(request, response);

			}
			
			else if (action.equalsIgnoreCase("supprimerAut")) {
				int id = Integer.parseInt(request.getParameter("id"));		
				gestionAuteur.deleteAuteur(id);
				request.setAttribute("auteurs", gestionAuteur.getAllAuteurs());
				request.getRequestDispatcher("listeAuteur.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("saveEdit") && request.getMethod().equalsIgnoreCase("post")) {
				String id = request.getParameter("id");

				String nom = request.getParameter("nom");
				if (id.equals("")) {
				Editeur editeur = new Editeur(nom, null);
				gestionEditeur.AddEditeur(editeur);
				}
				else {
					Editeur editeur = new Editeur();
					editeur.setIdEditeur(Integer.parseInt(id));
					editeur.setNom(nom);
					gestionEditeur.updateEditeur(editeur);
				}
				request.setAttribute("editeurs", gestionEditeur.getAllEditeurs());
				request.getRequestDispatcher("listeEditeur.jsp").forward(request, response);

			}
			else if (action.equalsIgnoreCase("modifierEdit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("editeur",gestionEditeur.getEditeur(id));
				request.getRequestDispatcher("listeEditeur.jsp").forward(request, response);

				
			} 
			else if (action.equalsIgnoreCase("supprimerEdit")) {
				int id = Integer.parseInt(request.getParameter("id"));		
				gestionEditeur.deleteEditeur(id);
				request.setAttribute("editeurs", gestionEditeur.getAllEditeurs());
				request.getRequestDispatcher("listeEditeur.jsp").forward(request, response);
			}
			
			else if (action.equalsIgnoreCase("listeGenres")) {
				
				request.setAttribute("genres", gestionGenre.getAllGenres());

				request.getRequestDispatcher("listeGenre.jsp").forward(request, response);

			} 
			else if (action.equalsIgnoreCase("modifierGenre")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("genre",gestionGenre.getGenre(id));
				request.getRequestDispatcher("listeGenre.jsp").forward(request, response);

				
			} 
			else if (action.equalsIgnoreCase("saveGenre") && request.getMethod().equalsIgnoreCase("post")) {
				String id = request.getParameter("id");
				String nom = request.getParameter("nom");
				if (id.equals("")) {
					Genre genre = new Genre(nom);
					gestionGenre.AddGenre(genre);
				}
				else {
					Genre genre = new Genre();
					genre.setIdGenre(Integer.parseInt(id));
					genre.setNom(nom);
					gestionGenre.updateGenre(genre);
				}
				request.setAttribute("genres", gestionGenre.getAllGenres());
				request.getRequestDispatcher("listeGenre.jsp").forward(request, response);

			}
			else if (action.equalsIgnoreCase("supprimerGenre")) {
				int id = Integer.parseInt(request.getParameter("id"));		
				gestionGenre.deleteGenre(id);
				request.setAttribute("genres", gestionGenre.getAllGenres());
				request.getRequestDispatcher("listeGenre.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("ajouterEmprunt")) {
				int idLivre = Integer.parseInt(request.getParameter("id"));
				int idUSer = Integer.parseInt(request.getParameter("idUser"));
				Livre l = gestionLivre.getLivre(idLivre);
				User u = gestionUser.getUser(idUSer);
				
				request.setAttribute("livre", l);
				request.setAttribute("user", u);
				
				request.getRequestDispatcher("emprunterLivre.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("saveEmprunt") && request.getMethod().equalsIgnoreCase("post")) {
				int idLivre = Integer.parseInt(request.getParameter("idLivre"));
				int idUser = Integer.parseInt(request.getParameter("idUser"));
				String dateEmpruntString =  request.getParameter("dateE");
				String  dateRetourString =  request.getParameter("dateR");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date dateEmprunt = new Date();
				try {
					dateEmprunt = dateFormat.parse(dateEmpruntString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Utilisez parse() pour obtenir un objet Date
				Date dateRetour = new Date();
				try {
					dateRetour = dateFormat.parse(dateRetourString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Emprunt emprunt = new Emprunt(gestionLivre.getLivre(idLivre),gestionUser.getUser(idUser),dateEmprunt,dateRetour);
				gestionEmprunt.empruntLivre(emprunt);
				int totalRecords = gestionLivre.getAllLivre().size();
				 int pageSize = 3;
		       totalPages = (int) Math.ceil((double) totalRecords / pageSize); // nombre total de pages
			    currentPage = 1; // page actuelle
		        if (request.getParameter("page") != null) {
		            currentPage = Integer.parseInt(request.getParameter("page"));
		        }
		        int startIndex = (currentPage - 1) * pageSize; // index de départ de la page actuelle
			    int endIndex = Math.min(startIndex + pageSize, totalRecords); // index de fin de la page actuelle
			     livreOnPage = gestionLivre.getAllLivre().subList(startIndex, endIndex); // livre pour la page actuelle

		        for(Livre l:gestionLivre.getAllLivre()) {
					List<Genre> genres = l.getGenres();
					request.setAttribute("genres", genres);
					
				}
		       
		        request.setAttribute("totalPages", totalPages);
			    request.setAttribute("currentPage", currentPage); 
			    request.setAttribute("livres", livreOnPage);
				
				request.setAttribute("livres", gestionLivre.getAllLivre());

				
				request.getRequestDispatcher("listeLivre.jsp").forward(request, response);

			}
			else if (action.equalsIgnoreCase("mesEmprunts")) {
				int userId = Integer.parseInt(request.getParameter("userId"));

			    // Récupérer les emprunts de l'utilisateur
			    List<Emprunt> emprunts = gestionEmprunt.getAllEmpruntsById(userId);

			    // Définir les emprunts comme attribut de la requête
			    request.setAttribute("emprunts", emprunts);
				request.getRequestDispatcher("mesEmprunts.jsp").forward(request, response);

			}
			

		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	
	



}
