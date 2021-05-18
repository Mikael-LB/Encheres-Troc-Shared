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
		
		
		//TODO recuperer l'utilisateur en session
		UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
		Utilisateur utilisateur = utilisateurManager.selectByIdentifiant("Rico");
		CategorieManagerInt categorieManager =new CategorieManagerImpl() ;
		List<Categorie> categories = categorieManager.getAllCategorie();

		request.setAttribute("categories", categories);
		request.setAttribute("utilisateur", utilisateur);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/vendreArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO recuperation des parametres
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");
		int idCategorie = Integer.parseInt(categorie);
		String miseAPrix = request.getParameter("miseAPrix");
		int miseAPrixInt = Integer.parseInt(miseAPrix);		
		LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
		LocalDate dateFinDate = LocalDate.parse(request.getParameter("dateFin"));
		String retraitRue = request.getParameter("retraitRue");
		String retraitCP = request.getParameter("retraitCP");
		String retraitVille = request.getParameter("retraitVille");
		
		//TODO Récupérer un utilisateur à partir de la session
		UtilisateurManagerInt utilisateurManager = new UtilisateurManagerImpl();
		Utilisateur utilisateur = utilisateurManager.selectByIdentifiant("Rico");
		//


		
		Retrait retrait =new Retrait(retraitRue, retraitCP, retraitVille);
		ArticleVendu articleVendu =new ArticleVendu(article, description, dateDebut, dateFinDate, miseAPrixInt, retrait, idCategorie);
		
		ArticleVenduManagerInt articleVenduManager = new ArticleVenduManagerImpl();
		try {
			ArticleVendu artVendu = articleVenduManager.insertNewArticle(articleVendu, utilisateur.getNumeroUtilisateur(), retrait);
		} catch (BusinessException be) {
			request.setAttribute("errorList", be.getLstErrorCodes());
			request.setAttribute("messageReader", new LecteurMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/vendreArticle.jsp");
			rd.forward(request, response);
		}
		
		//TODO upload
		/*
		for ( Part part : request.getParts() ) {
            String fileName = getFileName( part );
            String fullPath = uploadPath + File.separator + fileName;
            part.write( fullPath );
        }*/
		
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
