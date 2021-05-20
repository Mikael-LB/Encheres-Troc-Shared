/**
 * 
 */
package fr.encheresnobyl.encherestroc.servlets;

/**
 * @author mlebris2021
 * Class which list the errors codes for the servlets
 * These codes are usefull in jsp pages to give
 * error messages to the user
 */
public abstract class CodesErreursServlets {
	public static final int LOGIN_EMPTY = 30_000;
	public static final int PASSWORD_EMPTY = 30_001;
	public static final int PASSWORD_WRONG = 30_002;
	public static final int NO_USER = 30_003;
	
	
	//Verif form Ajout Article
	public static final int PARSE_PRIX=30004;
	public static final int PARSE_CATEGORIE=30005;
	public static final int PARSE_DATE_FIN=30006;
	public static final int PARSE_DATE_DEBUT=30007;

	//Verif form Nouvelle Enchere
	public static final int PARSE_ENCHERE=30008;

}
