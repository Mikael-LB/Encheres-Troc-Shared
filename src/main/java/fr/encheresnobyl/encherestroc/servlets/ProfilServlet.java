package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.Utf8Decoder;

import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet(urlPatterns = {"/ProfilServlet","/Profil","/Mon-Profil"})
public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		Utilisateur utilisateur =new Utilisateur(1,"Rk","renan","kerhir","kerenan@hotmail.fr","0662856060"
				,"rue du champ dolent", "35131","chartres","caVaPas",1000000,true);
		// TODO recuperation utilisateur
		request.setAttribute("utilisateur", utilisateur);
		//TODO verifier l'accet servlet
		if(request.getRequestURI().contains("Mon-Profil")) {
			request.setAttribute("modifier", "modifier");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/profil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
