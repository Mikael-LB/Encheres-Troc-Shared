package fr.encheresnobyl.encherestroc.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerImpl;
import fr.encheresnobyl.encherestroc.bll.ArticleVenduManagerInt;
import fr.encheresnobyl.encherestroc.bll.BusinessException;
import fr.encheresnobyl.encherestroc.bll.CategorieManagerImpl;
import fr.encheresnobyl.encherestroc.bll.CategorieManagerInt;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerImpl;
import fr.encheresnobyl.encherestroc.bll.UtilisateurManagerInt;
import fr.encheresnobyl.encherestroc.bo.ArticleVendu;
import fr.encheresnobyl.encherestroc.bo.Categorie;
import fr.encheresnobyl.encherestroc.bo.Retrait;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;
import fr.encheresnobyl.encherestroc.messages.LecteurMessage;
import fr.encheresnobyl.encherestroc.servlets.utils.ValidateurParse;

/**
 * Servlet implementation class VendreArticle
 */
@WebServlet("/VendreArticle")
@MultipartConfig( fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5,
 maxRequestSize = 1024 * 1024 * 5 * 5 )

public class VendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//TODO upload
	 //public static final String IMAGES_FOLDER = "/";
	 //public String uploadPath; 

    /*
     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
     */ 
    @Override
    public void init() throws ServletException {
    	//TODO upload
        //uploadPath = getServletContext().getRealPath( IMAGES_FOLDER );
        //File uploadDir = new File( uploadPath );
        //if ( ! uploadDir.exists() ) uploadDir.mkdir();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreArticle() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		CategorieManagerInt categorieManager =new CategorieManagerImpl() ;
		List<Categorie> categories = categorieManager.getAllCategorie();

		request.setAttribute("sessionUtilisateur",utilisateur );
		request.setAttribute("categories", categories);
		request.setAttribute("utilisateur", utilisateur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/vendreArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
		ValidateurParse vp = new ValidateurParse();
		String categorie = request.getParameter("categorie"); 
		vp.validerInteger(categorie, CodesErreursServlets.PARSE_CATEGORIE);
		String miseAPrix = request.getParameter("miseAPrix");
		vp.validerInteger(miseAPrix, CodesErreursServlets.PARSE_PRIX);
		String dateDebutString = request.getParameter("dateDebut");
		vp.validerDate(dateDebutString, CodesErreursServlets.PARSE_DATE_DEBUT);
		String dateFinString = request.getParameter("dateFin");
		vp.validerDate(dateFinString, CodesErreursServlets.PARSE_DATE_FIN);
		
		if (vp.getBe().hasError()) {
			throw vp.getBe();
		}
		
		int idCategorie = Integer.parseInt(categorie);
		int miseAPrixInt = Integer.parseInt(miseAPrix);		
		LocalDate dateDebut = LocalDate.parse(dateDebutString);
		LocalDate dateFinDate = LocalDate.parse(dateFinString); 
		
		
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String retraitRue = request.getParameter("retraitRue");
		String retraitCP = request.getParameter("retraitCP");
		String retraitVille = request.getParameter("retraitVille");
		
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		Retrait retrait =new Retrait(retraitRue, retraitCP, retraitVille);
		ArticleVendu articleVendu =new ArticleVendu(article, description, dateDebut, dateFinDate, miseAPrixInt, retrait, idCategorie);
		
		ArticleVenduManagerInt articleVenduManager = new ArticleVenduManagerImpl();
		
			ArticleVendu artVendu = articleVenduManager.insertNewArticle(articleVendu, utilisateur.getNumeroUtilisateur(), retrait);
			
		} catch (BusinessException be) {
			request.setAttribute("errorList", be.getLstErrorCodes());
			request.setAttribute("messageReader", new LecteurMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/vendreArticle.jsp");
			rd.forward(request, response);
			return;
		}
		
		//TODO upload
		/*
		for ( Part part : request.getParts() ) {
            String fileName = getFileName( part );
            String fullPath = uploadPath + File.separator + fileName;
            part.write( fullPath );
        }*/
		request.setAttribute("sessionUtilisateur", session.getAttribute("utilisateur"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/affichageParam.jsp");
		rd.forward(request, response);
	}
	
	/*
     * Récupération du nom du fichier dans la requête.
     */
	//TODO upload
	/*
    private String getFileName( Part part ) {
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( content.trim().startsWith( "filename" ) )
                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
        }
        return "Default.file";
    }*/


}
