package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Categorie;

/**
 * Servlet implementation class AccueilServlet
 */
/**
 * @author rkerhir2021
 *
 */
@WebServlet(urlPatterns = {"/AccueilServlet","/"})
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleVendu> listeArticles =new ArrayList<ArticleVendu>();
		//TODO listeArticle = methode(recherche, categorie)
		
		//TODO recuperation categorie
			List<Categorie> categories = new ArrayList<Categorie>();
			Categorie categorie1 = new Categorie(1, "immobilier");
			Categorie categorie2 = new Categorie(2, "voiture");
			categories.add(categorie1);
			categories.add(categorie2);
		//TODO  recuperation sessionId
			request.setAttribute("sessionId", "bla");
		request.setAttribute("categories", categories);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/accueil.jsp");
		rd.forward(request, response);
	}

	
	/**
	 * param : *"recherche" , "categorie" ,	"achatvente" , "enchereOuverte" , 
	 * "enchereUtilisateur" , "enchereRemporte" , "ventesEnCours" , "ventesNonDebute" , "ventesTermine"
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//recuperation des donné parametre
		String recherche = request.getParameter("recherche");
		String categorie = request.getParameter("categorie");
		int idCategorie = Integer.parseInt(categorie);
		String achatVente = request.getParameter("achatvente");
		String enchereOuverte = request.getParameter("enchereOuverte");
		String enchereUtilisateur = request.getParameter("enchereUtilisateur");
		String enchereRemporte = request.getParameter("enchereRemporte");
		String ventesEnCours = request.getParameter("ventesEnCours");
		String ventesNonDebute = request.getParameter("ventesNonDebute");
		String ventesTermine = request.getParameter("ventesTermine");
		List<ArticleVendu> listeArticles =new ArrayList<ArticleVendu>();
		// TODO recuperation sessionId
			int sessionId = 1;
		
		if(achatVente!=null) {
			List<String> coche =new ArrayList<String>();
			if(achatVente.equals("achats")) {
				if(!enchereOuverte.isEmpty()) {
					coche.add(enchereOuverte);
				}
				if(!enchereUtilisateur.isEmpty()) {
					coche.add(enchereUtilisateur);
				}
				if(!enchereRemporte.isEmpty()) {
					coche.add(enchereRemporte);
				}
			}
			if(achatVente.equals("ventes")) {
				if(!ventesEnCours.isEmpty()) {
					coche.add(ventesEnCours);
				}
				if(!ventesNonDebute.isEmpty()) {
					coche.add(ventesNonDebute);
				}
				if(!ventesTermine.isEmpty()) {
					coche.add(ventesTermine);
				}
			}
			//TODO listeArticle = methode(recherche, categorie, sessionId, coche)
		}else {
			//TODO listeArticle = methode(recherche, categorie)
		}
		//TODO recuperation des donnée categorie 
			List<Categorie> categories = new ArrayList<Categorie>();
			Categorie categorie1 = new Categorie(1, "immobilier");
			Categorie categorie2 = new Categorie(2, "voiture");
			categories.add(categorie1);
			categories.add(categorie2);
			
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("categories", categories);
		request.setAttribute("articles", listeArticles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/accueil.jsp");
		rd.forward(request, response);
		
	}

	
}
