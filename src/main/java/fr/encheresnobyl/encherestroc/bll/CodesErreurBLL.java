/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

/**
 * Classe en charge
 * @author Pierre
 * @version Encheres-Troc-Shared - v1.0
 * @date 18 mai 2021 - 15:33:59
 */
public abstract class CodesErreurBLL {
	
	public static final int NOM_ARTICLE_EMPTY = 20001;
	public static final int DESCRIPTION_ARTICLE_EMPTY = 20002;
	public static final int DATES_ARTICLE_EMPTY = 20003;
	public static final int DATES_ARTICLE_INCORRECTES = 20004;
	public static final int PRIX_ARTICLE_INVALIDE = 20005;
	public static final int RUE_RETRAIT_EMPTY = 20006;
	public static final int CPO_RETRAIT_EMPTY = 20007;
	public static final int VILLE_RETRAIT_EMPTY = 20008;

	public static final int DATE_DEBUT_ARTICLE_BEFORE_TODAY = 20009;
	
	//inscription form
	public static final int EMPTY_PSEUDO = 20_010;
	public static final int EMPTY_PARAM = 20_011;
	public static final int PSEUDO_CHAR_NOT_ALLOWED = 20_012;
	public static final int PSEUDO_EXIST = 20_013;
	public static final int EMAIL_INVALID = 20_014;
	public static final int EMAIL_ALREADY_EXIST = 20_015;
	public static final int PASSWORDS_DONT_MATCH = 20_016;
	public static final int PASSWORD_TO_SHORT = 20_017;
	public static final int PHONE_ONLY_NUMBER = 20_018;
	public static final int PARAM_TOO_LONG = 20_019;
	
	//Nouvelle enchere form
	public static final int ENCHERE_INFERIEURE=20020; 
	public static final int CREDITS_INSUFFISANTS=20021; 



}
