package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GestionUser;

import dao.IgestionUser;
import dao.entities.User;

/**
 * Servlet implementation class Authentification
 */
@WebServlet("/AuthController")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        // TODO Auto-generated constructor stub
    }
    IgestionUser userImpl;
    /**
	 * @see Servlet#init(ServletConfig)
	 */
   
	public void init(ServletConfig config) throws ServletException {
		userImpl = new GestionUser();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String action = request.getParameter("action");
		if(action == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}
		else {
			if(session != null) {
				session.invalidate();
				response.sendRedirect(request.getContextPath()+"/");
				//contextPath = chemin eli fel url: http://localhost:8080/GestionProduit

			}
			
		}
		
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userImpl.verifier(email, password);
		System.out.println("=================="+email + "===="+password);

		HttpSession session = request.getSession();
		if(user == null) {
			System.out.println("user nullllllllllll");
			request.setAttribute("erreur", "Email ou mot de passe est incorrect");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
			
		}
		else {
			System.out.println("user not nuuuuuuuuuuuullllllllll");
			session.setAttribute("username", user.getUsername());
			session.setAttribute("idUser", user.getId());
			session.setAttribute("role", user.getRole());
			System.out.println("usernot null");
			request.getRequestDispatcher("BibliothéqueController").forward(request, response);

		}
	}

}
