package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerImpl;
import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerInt;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
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
		
//		UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
//		
//		List<Utilisateur> listeUtilisateurs = utilisateurManager.selectAll();
//		
//		System.out.println("Liste Utilisateurs :");
//		for (Utilisateur utilisateur : listeUtilisateurs) {
//			System.out.println(utilisateur);
//		}
//		
//		Utilisateur utilisateurByPseuso = utilisateurManager.selectByPseudo("Pasfute35");
//		System.out.println("\nUtilisateur dont le pseudo est Pasfute35 : " + utilisateurByPseuso);
//		
//		Boolean existsUtilisateurMailTrue = utilisateurManager.selectByMail("lucie.nuzyte@random.com");
//		Boolean existsUtilisateurMailFalse = utilisateurManager.selectByMail("lucie.boulette@random.com");
//		
//		System.out.println("\nmail lucie.nuzyte@random.com : "+existsUtilisateurMailTrue+"\nmail lucie.boulette@random.com : "+existsUtilisateurMailFalse);
//		
//		Utilisateur utilisateurByIdentifiant = utilisateurManager.selectByIdentifiant("ghislain.becile@random.com");
//		System.out.println("\nUtilisateur par identifiant : " + utilisateurByIdentifiant);
		
		
		
		ArticleVenduManagerInt articleVenduManager = new ArticleVenduManagerImpl();
		
//		List<ArticleVendu> listeArticleVendusAll = articleVenduManager.getEnCours();
//		System.out.println("Tous les articles dispo : ");
//		for (ArticleVendu articleVendu : listeArticleVendusAll) {
//			System.out.println(articleVendu);
//		}
//		
//		List<ArticleVendu> listeArticleVendusNom = articleVenduManager.selectDispoByNom("C");
//		System.out.println("Tous les articles dispo avec un 'C' dans le nom :");
//		for (ArticleVendu articleVendu : listeArticleVendusNom) {
//			System.out.println(articleVendu);
//		}
//		
//		List<ArticleVendu> listeArticleVendusNomCategorie = articleVenduManager.selectDispoByNomAndCategorie("C",3);
//		System.out.println("Tous les articles dispo avec un 'C' dans le nom  et de catégorie 3 (ameubleuement):");
//		for (ArticleVendu articleVendu : listeArticleVendusNomCategorie) {
//			System.out.println(articleVendu);
//		}
		
		List<String> parametres = new ArrayList<String>();
//		parametres.add("enchereOuverte");
//		parametres.add("enchereUtilisateur");
		parametres.add("enchereRemporte");
		
		String motCle = "";
		int noCategorie = 0;
		int noUtilisateur = 2;
		
		
		List<ArticleVendu> listArticleVendus = articleVenduManager.getEncheres(motCle, noCategorie, noUtilisateur, parametres);
		for (ArticleVendu articleVendu : listArticleVendus) {
		System.out.println(articleVendu);
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
