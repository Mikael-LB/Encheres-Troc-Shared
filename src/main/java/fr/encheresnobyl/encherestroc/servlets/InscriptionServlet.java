package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
