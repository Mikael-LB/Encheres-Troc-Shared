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


}
