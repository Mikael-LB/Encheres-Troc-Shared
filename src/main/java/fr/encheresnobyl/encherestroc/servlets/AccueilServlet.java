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

import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerImpl;
import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerInt;
import fr.encheresnobyl.encherestroc.bll.CategorieManagerImpl;
import fr.encheresnobyl.encherestroc.bll.CategorieManagerInt;
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
		
		ArticleVenduManagerInt articleManager= new ArticleVenduManagerImpl();
		listeArticles=articleManager.getEncheres("", 0);
		CategorieManagerInt categorieManager =new CategorieManagerImpl() ;
		List<Categorie> categories = categorieManager.getAllCategorie();
		categories.add(new Categorie(0,"Toutes"));
		//TODO  recuperation sessionId
			request.setAttribute("sessionId", "bla");
		request.setAttribute("categories", categories);
		request.setAttribute("articles", listeArticles);
		
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
		ArticleVenduManagerInt articleManager= new ArticleVenduManagerImpl();
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
				//TODO listeArticles = articleManager.getEncheres(recherche, categorie, sessionId, coche)
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
			//TODO listeArticles = articleManager.getEncheres(recherche, categorie, sessionId, coche)
		}else {
			//TODO listeArticles = articleManager.getEncheresEnCours(recherche, categorie)
		}
		CategorieManagerInt categorieManager =new CategorieManagerImpl() ;
		List<Categorie> categories = categorieManager.getAllCategorie();
		categories.add(new Categorie(0,"Toutes"));
			
			
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("categories", categories);
		request.setAttribute("articles", listeArticles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/accueil.jsp");
		rd.forward(request, response);
		
	}

	
}
