package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.encheresnobyl.encherestroc.bll.BusinessException;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Servlet implementation class ConnexionServlet
 * @author mlebris2021
 * Servlet which control the get and post requests
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONNECTION_PAGE = "/WEB-INF/front-office-user/connexion.jsp";

	private static final String LOGIN = "login";
	private static final String PASSWD = "password";
	private static final String REMEMBER_ME = "remember";
	
	private UtilisateurManagerInt userManager = new UtilisateurManagerImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(CONNECTION_PAGE).forward(request, response);
	}

	/**
	 * Method witch get the parameters from the request
	 * then check if user exist then allow the sessoin to start
	 * and move user to the accueil page
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = null;
		String login = request.getParameter(LOGIN);
		String passwd = request.getParameter(PASSWD);
		String rememberMe = request.getParameter(REMEMBER_ME);
		
		try {
			user = this.userManager.selectByIdentifiant(login);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
