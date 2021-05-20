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
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Servlet implementation class AccueilServlet
 */
/**
 * @author rkerhir2021
 *
 */
@WebServlet(urlPatterns = {"/AccueilServlet"})
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
		
		
		request.setAttribute("categories", categories);
		request.setAttribute("articles", listeArticles);
		request.setAttribute("ancienCat", 0);
		
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
		String achatVente = request.getParameter("achatVente");
		String enchereOuverte = request.getParameter("encheresOuvertes");
		String enchereUtilisateur = request.getParameter("encheresUtilisateur");
		String enchereRemporte = request.getParameter("encheresRemportees");
		String ventesEnCours = request.getParameter("ventesEnCours");
		String ventesNonDebute = request.getParameter("ventesNonDebutees");
		String ventesTermine = request.getParameter("ventesTerminees");
		List<ArticleVendu> listeArticles =new ArrayList<ArticleVendu>();
		List<String> coche =new ArrayList<String>();
		ArticleVenduManagerInt articleManager= new ArticleVenduManagerImpl();
		
		
		if(achatVente!=null) {
			if(achatVente.equals("achats")) {
				if(enchereOuverte!=null) {
					coche.add(enchereOuverte);
				}
				if(enchereUtilisateur!=null) {
					coche.add(enchereUtilisateur);
				}
				if(enchereRemporte!=null) {
					coche.add(enchereRemporte);
				}
				listeArticles = articleManager.getEncheres(recherche, idCategorie, ((Utilisateur) request.getSession().getAttribute("utilisateur")).getNumeroUtilisateur(), coche);
			
			}else if(achatVente.equals("ventes")) {
				if(ventesEnCours!=null) {
					coche.add(ventesEnCours);
				}
				if(ventesNonDebute!=null) {
					coche.add(ventesNonDebute);
				}
				if(ventesTermine!=null) {
					coche.add(ventesTermine);
				}
				listeArticles = articleManager.getVentes(recherche, idCategorie, ((Utilisateur) request.getSession().getAttribute("utilisateur")).getNumeroUtilisateur(), coche);
			}
		}else {
			listeArticles = articleManager.getEncheres(recherche, idCategorie);
		}
		CategorieManagerInt categorieManager =new CategorieManagerImpl() ;
		List<Categorie> categories = categorieManager.getAllCategorie();
		categories.add(new Categorie(0,"Toutes"));
			
			
		
		request.setAttribute("categories", categories);
		request.setAttribute("articles", listeArticles);
		request.setAttribute("ancienCat", idCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/accueil.jsp");
		rd.forward(request, response);
		//TEST
		
	}

	
}
