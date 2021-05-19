package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * Servlet implementation class InscriptionServlet
 * @author mlebris2021
 * Servlet which control the get and post request for the inscription page
 */
@WebServlet({ "/InscriptionServlet", "/inscription" })
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INSCRIPTION_PAGE = "/WEB-INF/front-office-user/inscription.jsp";
	private static final String ACCUEIL_PAGE = "/";
	
	private static final String PSEUDO = "pseudo";
	private static final String USERNAME = "userName";
	private static final String FIRSTNAME = "firstname";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String STREET = "street";
	private static final String POSTAL_CODE = "postalCode";
	private static final String CITY = "city";
	private static final String PASSWORD = "passwd";
	private static final String PASSWORD_CONFIRM = "passwdConfirm";

	private static final String ATT_UTILISATEUR = "utilisateur";
	private static final String ATT_ERRORS_LIST = "errorList";
	private static final String ATT_SESSION_ID = "sessionId";

	/**
	 * Method which allow to get the inscription page by the user
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(INSCRIPTION_PAGE).forward(request, response);
	}

	/**
	 * Method which get the form parameters from the request
	 * then check them
	 * if everything is ok, the method ask to create a new user to the manager
	 * else the method return a list of errors to the user form so he can change it
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
		Utilisateur user;

		String pseudo = request.getParameter(PSEUDO);
		String userName = request.getParameter(USERNAME);
		String firstname = request.getParameter(FIRSTNAME);
		String email = request.getParameter(EMAIL);
		String phone = request.getParameter(PHONE);
		String street = request.getParameter(STREET);
		String postalCode = request.getParameter(POSTAL_CODE);
		String city = request.getParameter(CITY);
		String passwd = request.getParameter(PASSWORD);
		String passwdConfirm = request.getParameter(PASSWORD_CONFIRM);
		

		//TODO to replace the method with long parameter list
		//Map<String, String[]> parameterMap = request.getParameterMap();

		try {
			utilisateurManager.checkInscriptionParam(
					pseudo,
					userName,
					firstname,
					email,
					phone,
					street,
					postalCode,
					city,
					passwd,
					passwdConfirm);
			user = new Utilisateur(pseudo, firstname, street, email, phone, city, postalCode, passwdConfirm, passwd);

			user = utilisateurManager.insertUtilisateur(user);
			//utilisateur ok so session start
			HttpSession session = request.getSession();
			session.setAttribute(ATT_UTILISATEUR, user);
			session.setAttribute(ATT_SESSION_ID, session.getId());
			// without cookies need to rewrite url
			String url = request.getContextPath() + ACCUEIL_PAGE;
			String urlEncode = response.encodeURL(url);

			// next page
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(urlEncode);


		} catch (BusinessException be) {
			request.setAttribute(ATT_ERRORS_LIST, be.getLstErrorCodes());
			request.setAttribute("messageReader", new LecteurMessage());
			request.getRequestDispatcher(INSCRIPTION_PAGE).forward(request, response);
		}
	}

}
