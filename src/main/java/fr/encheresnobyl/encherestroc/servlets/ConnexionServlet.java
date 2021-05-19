package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ConnexionServlet
 * 
 * @author mlebris2021 Servlet which control the get and post requests
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONNECTION_PAGE = "/WEB-INF/front-office-user/connexion.jsp";
	private static final String ACCUEIL_PAGE = "/";

	private static final String LOGIN = "login";
	private static final String PASSWD = "password";
	private static final String REMEMBER_ME = "remember";

	private static final String ATT_UTILISATEUR = "utilisateur";
	private static final String ATT_ERRORS_LIST = "errorList";
	private static final String ATT_SESSION_ID = "sessionId";
	private static final String ATT_MESSAGE_READER = "messageReader";
	private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365;

	private UtilisateurManagerInt userManager = new UtilisateurManagerImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(CONNECTION_PAGE).forward(request, response);
	}

	/**
	 * Method witch get the parameters from the request then check if user exist
	 * then allow the session to start and move user to the accueil page
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessException be = new BusinessException();
		Utilisateur user = null;
		String url = request.getContextPath() + ACCUEIL_PAGE;
		RequestDispatcher rd = request.getRequestDispatcher(ACCUEIL_PAGE);
		Cookie cookie;

		String login = request.getParameter(LOGIN);
		String passwd = request.getParameter(PASSWD);
		String rememberMe = request.getParameter(REMEMBER_ME);

		// check errors in form
		if ("".equals(login)) {
			be.addError(CodesErreursServlets.LOGIN_EMPTY);
		}
		if ("".equals(passwd)) {
			be.addError(CodesErreursServlets.PASSWORD_EMPTY);
		}
		try {
			user = userManager.selectByIdentifiant(login);
			if (null == user) {
				be.addError(CodesErreursServlets.NO_USER);
			}
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		if (null != user && !passwd.equals(user.getMotDePasse())) {
			be.addError(CodesErreursServlets.PASSWORD_WRONG);
		}
		if (be.hasError()) {
			request.setAttribute(ATT_UTILISATEUR, null);
			request.setAttribute(ATT_ERRORS_LIST, be.getLstErrorCodes());
			request.setAttribute(ATT_MESSAGE_READER, new LecteurMessage());
			request.getRequestDispatcher(CONNECTION_PAGE).forward(request, response);
			
		} else {
			// no error and user exist and password is ok
			HttpSession session = request.getSession();
			session.setAttribute(ATT_UTILISATEUR, user);
			session.setAttribute(ATT_SESSION_ID, session.getId());
			// remember me
			if (rememberMe == "on") {
				cookie = new Cookie(user.getPseudo(), user.getMotDePasse());
				cookie.setHttpOnly(true);
				cookie.setMaxAge(COOKIE_MAX_AGE);
				response.addCookie(cookie);
			}
			// without cookies need to rewrite url
			String urlEncode = response.encodeURL(url);

			// next page
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect(urlEncode);
		}
	}

}
