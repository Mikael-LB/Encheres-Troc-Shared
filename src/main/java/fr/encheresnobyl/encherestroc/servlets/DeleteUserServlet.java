package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.encheresnobyl.encherestroc.bll.BusinessException;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.messages.LecteurMessage;

/**
 * Servlet implementation class DeleteUserServlet
 * @author mlebris2021
 * Servlet which control the get and post method for the delete user feature
 * We have decided to not delete the user in db but put some impersonal values
 */
@WebServlet({ "/DeleteUserServlet", "/supprimer" })
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACCUEIL_PAGE = "/";
	private static final String MODIFIER_PROFIL_PAGE="/WEB-INF/front-office-user/modifierProfil.jsp";
	private static final String ATT_ERRORS_LIST = "errorList";
	private static final String ATT_MESSAGE_READER = "messageReader";

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtilisateurManagerInt um = new UtilisateurManagerImpl();
		Utilisateur user;

		try {
			user = (Utilisateur) session.getAttribute("utilisateur");
			um.deleteUser(user);
			//if suppress user information is ok
			session.invalidate();
			// next page
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(request.getContextPath()+ACCUEIL_PAGE);
		} catch (BusinessException be) {
			request.setAttribute(ATT_ERRORS_LIST, be.getLstErrorCodes());
			request.setAttribute(ATT_MESSAGE_READER, new LecteurMessage());
			request.getRequestDispatcher(MODIFIER_PROFIL_PAGE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
