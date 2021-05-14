package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
		
		List<Utilisateur> listeUtilisateurs = utilisateurManager.selectAll();
		
		for (Utilisateur utilisateur : listeUtilisateurs) {
			System.out.println("Liste Utilisateurs :");
			System.out.println(utilisateur);
		}
		
		Utilisateur utilisateurByPseuso = utilisateurManager.selectByPseudo("Pasfute35");
		System.out.println("\nUtilisateur dont le pseudo est Pasfute35 : " + utilisateurByPseuso);
		
		Boolean existsUtilisateurMailTrue = utilisateurManager.selectByMail("lucie.nuzyte@random.com");
		Boolean existsUtilisateurMailFalse = utilisateurManager.selectByMail("lucie.boulette@random.com");
		
		System.out.println("\nmail lucie.nuzyte@random.com : "+existsUtilisateurMailTrue+"\nmail lucie.boulette@random.com : "+existsUtilisateurMailFalse);
		
		Utilisateur utilisateurByIdentifiant = utilisateurManager.selectByIdentifiant("ghislain.becile@random.com");
		System.out.println("\nUtilisateur par identifiant : " + utilisateurByIdentifiant);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
