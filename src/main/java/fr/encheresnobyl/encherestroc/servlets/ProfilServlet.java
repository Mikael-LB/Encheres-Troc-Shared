package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.Utf8Decoder;

import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet( urlPatterns = {"/ProfilServlet","/Profil","/Mon-Profil"})
public class ProfilServlet extends HttpServlet {
	private static final long  serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String noUser;
		
		if(request.getRequestURI().contains("Mon-Profil")) {
			request.setAttribute("modifier", "modifier");
			Utilisateur sessionUtilisateur =  (Utilisateur) session.getAttribute("utilisateur");
			noUser = String.valueOf(sessionUtilisateur.getNumeroUtilisateur());
		}else {
			System.out.println("t'es la !!!!!");
			noUser = request.getParameter( "user" );
		}
		UtilisateurManagerInt utilisateurManager= new UtilisateurManagerImpl();
		Utilisateur utilisateur = utilisateurManager.selectByIdentifiant(noUser);
		
		request.setAttribute("sessionUtilisateur", session.getAttribute("utilisateur"));
		request.setAttribute("user", utilisateur);
		request.setAttribute("no", noUser);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/profil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		request.setAttribute("sessionUtilisateur", session.getAttribute("utilisateur"));
		request.setAttribute("user", utilisateur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/profilUser.jsp");
		rd.forward(request, response);
	}

}
