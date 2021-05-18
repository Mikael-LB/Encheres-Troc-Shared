package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.encheresnobyl.encherestroc.bll.BusinessException;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;

/**
 * Servlet implementation class InscriptionServlet
 * @author mlebris2021
 * Servlet which control the get and post request for the inscription page
 */
@WebServlet({ "/InscriptionServlet", "/inscription" })
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String INSCRIPTION_PAGE = "/WEB-INF/front-office-user/inscription.jsp";

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
		Map<String, String[]> parameterMap = request.getParameterMap();
		

		for (Map.Entry<String, String[]> entry: parameterMap.entrySet()) {
			System.out.println(entry.getKey()+ " "+ Arrays.toString(entry.getValue()));
		}
	}

}
