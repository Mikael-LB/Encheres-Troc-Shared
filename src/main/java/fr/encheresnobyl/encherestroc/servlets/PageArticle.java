package fr.encheresnobyl.encherestroc.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerImpl;
import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerInt;
import fr.encheresnobyl.encherestroc.bll.BusinessException;
import fr.encheresnobyl.encherestroc.bll.EnchereManagerImpl;
import fr.encheresnobyl.encherestroc.bll.EnchereManagerInt;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Enchere;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.messages.LecteurMessage;
import fr.encheresnobyl.encherestroc.servlets.utils.ValidateurParse;


/**
 * Servlet implementation class PageArticle
 */
@WebServlet(urlPatterns = {"/Page-Article","/Encherir","/Aquisition","/Ma-Vente","/EnchereEffectue"})
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
		HttpSession session =request.getSession();
		
		String message = "";
		String titre ="";
		String from ="";
		LocalDate now =LocalDate.now();
		int noArticle = Integer.parseInt(request.getParameter("article"));
		ArticleVenduManagerInt articleVenduManager = new ArticleVenduManagerImpl();
		ArticleVendu article = articleVenduManager.getArticleById(noArticle);
		if(request.getRequestURI().contains("Page-Article")) {
			
			if(article.getDateDebutEncheres().isBefore(now) && article.getDateFinEncheres().isAfter(now)&& !session.equals(null) ) {
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
		}else if (request.getRequestURI().contains("EnchereEffectue")) {
			message = "Vous avez encherir sur l'article ";
			titre ="Enchère";
			from ="enCour";
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

		try {
			ValidateurParse vp = new ValidateurParse();
			vp.validerInteger(request.getParameter("mise"), CodesErreursServlets.PARSE_ENCHERE);
			
			if (vp.getBe().hasError()) {
				throw vp.getBe();
			}
			
			ArticleVenduManagerInt articleVenduManager = new ArticleVenduManagerImpl();
			ArticleVendu article = articleVenduManager.getArticleById(Integer.parseInt(request.getParameter("noArticle")));
			int mise = Integer.parseInt(request.getParameter("mise"));
			UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
			Utilisateur sessionUtilisateur = ((Utilisateur) request.getSession().getAttribute("utilisateur"));
			//Récupération des données réelles de l'utilisateur en session.
			Utilisateur utilisateurBDD=utilisateurManager.selectById(sessionUtilisateur.getNumeroUtilisateur());
					
			
			Enchere enchere = new Enchere(LocalDateTime.now(), mise, utilisateurBDD, article);
			
			EnchereManagerInt enchereManager = new EnchereManagerImpl();		
			article=enchereManager.nouvelleEnchere(enchere);
			
			//MAJ de l'utilisateur en session après l'enchère.
			utilisateurBDD=utilisateurManager.selectById(utilisateurBDD.getNumeroUtilisateur());
			request.getSession().setAttribute("utilisateur", utilisateurBDD);
			
			response.sendRedirect(request.getContextPath()+"/EnchereEffectue?article="+article.getNoArticle());
			
			
		} catch (BusinessException be) {
			request.setAttribute("errorList", be.getLstErrorCodes());
			request.setAttribute("messageReader", new LecteurMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/article.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
