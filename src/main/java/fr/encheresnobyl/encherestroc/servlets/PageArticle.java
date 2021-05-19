package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerImpl;
import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerInt;
import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Servlet implementation class PageArticle
 */
@WebServlet(urlPatterns = {"/Page-Article","/Encherir","/Aquisition","/Ma-Vente"})
public class PageArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur sessionUtilisateur = new Utilisateur();
		if(session!=null) {
			sessionUtilisateur =  (Utilisateur) session.getAttribute("utilisateur");
			request.setAttribute("sessionUtilisateur", sessionUtilisateur);
		}
		//TODO  si connecté
		String message = "";
		String titre ="";
		String from ="";
		LocalDate now =LocalDate.now();
		int noArticle = Integer.parseInt(request.getParameter( "article" ));
		ArticleVenduManagerInt articleVenduManager = new ArticleVenduManagerImpl();
		ArticleVendu article = articleVenduManager.getArticleById(noArticle);
		if(request.getRequestURI().contains("Page-Article")) {
			if(article.getDateDebutEncheres().isBefore(now) && article.getDateFinEncheres().isAfter(now)) {
				message = "Vous pouvez encherir sur l'article ";
				titre ="Enchère";
				from ="enCour";
			}else {
				message = "Detail de l'article";
				titre ="detail de l'article";
				from ="detail";
			}
			
		}else if (request.getRequestURI().contains("Encherir")) {
			message = "Vous pouvez encherir sur l'article ";
			titre ="Enchère";
			from ="enCour";
			
		}else if (request.getRequestURI().contains("Aquisition")) {
			message = "Vous avez remporté l'article ";
			titre ="Aquisition";
			from ="aquisition";
			
		}else if (request.getRequestURI().contains("Ma-Vente")) {
			//message = article.getEnchereMax().getUtilisteur().getPseudo()+" a remporté l'enchere";
			titre ="Votre article remporté ";;
			from ="maVente";
		}

		request.setAttribute("article", article);
		request.setAttribute("titre", titre);
		request.setAttribute("message", message);
		request.setAttribute("from", from);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/article.jsp");
		rd.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur sessionUtilisateur = new Utilisateur();
		if(session!=null) {
			sessionUtilisateur =  (Utilisateur) session.getAttribute("utilisateur");
			request.setAttribute("sessionUtilisateur", sessionUtilisateur);
		}
		ArticleVenduManagerInt articleVenduManager = new ArticleVenduManagerImpl();
		ArticleVendu article = articleVenduManager.getArticleById(Integer.parseInt(request.getParameter("noArticle")));
		LocalDate now =LocalDate.now();
		int mise = Integer.parseInt(request.getParameter("mise"));
			
			//TODO renvoi vers la dao
			//TODO si ok
			//TODO recharger l'article
			//TODO titre , message, from 
			//TODO set attribute
			//TODO renvoi vers la page article 
	}

}
