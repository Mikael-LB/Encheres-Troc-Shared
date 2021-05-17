package fr.encheresnobyl.encherestroc.servlets;

import java.io.File;
import java.io.IOException;
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

import fr.encheresnobyl.encherestroc.bll.CategorieManagerImpl;
import fr.encheresnobyl.encherestroc.bll.CategorieManagerInt;
import fr.encheresnobyl.encherestroc.bo.Categorie;
import fr.encheresnobyl.encherestroc.bo.Utilisateur;

/**
 * Servlet implementation class VendreArticle
 */
@WebServlet("/VendreArticle")
@MultipartConfig( fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5,
 maxRequestSize = 1024 * 1024 * 5 * 5 )

public class VendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public static final String IMAGES_FOLDER = "/";
	 public String uploadPath;
       
	 

    /*
     * Si le dossier de sauvegarde de l'image n'existe pas, on demande sa création.
     */ 
    @Override
    public void init() throws ServletException {
        uploadPath = getServletContext().getRealPath( IMAGES_FOLDER );
        File uploadDir = new File( uploadPath );
        if ( ! uploadDir.exists() ) uploadDir.mkdir();
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
		
		
		//TODO recuperer l'utilisateurs
			Utilisateur utilisateur= new Utilisateur(1,"Rk","renan","kerhir","kerenan@hotmail.fr","0662856060"
				,"rue du champ dolent", "35131","chartres","caVaPas",1000000,true);
		//CategorieManagerInt categorieManager =new CategorieManagerImpl() ;
		//List<Categorie> categories = categorieManager.getAllCategorie();
			List<Categorie> categories = new ArrayList<Categorie>();
			Categorie categorie1 = new Categorie(1, "immobilier");
			Categorie categorie2 = new Categorie(2, "voiture");
			categories.add(categorie1);
			categories.add(categorie2);
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
		//TODO creation d'un article / retrait 
		//TODO mise en base de donnée 
		//TODO renvoi vers page article avec numero article
		
		
		for ( Part part : request.getParts() ) {
            String fileName = getFileName( part );
            String fullPath = uploadPath + File.separator + fileName;
            part.write( fullPath );
        }
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/front-office-user/vendreArticle.jsp");
		rd.forward(request, response);
	}
	
	/*
     * Récupération du nom du fichier dans la requête.
     */
    private String getFileName( Part part ) {
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( content.trim().startsWith( "filename" ) )
                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
        }
        return "Default.file";
    }


}
