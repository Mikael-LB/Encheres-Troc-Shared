/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

/**
 * @author mlebris2021
 * class witch provide the error code for the Data Access Layer
 */
public abstract class CodesErrorDAL {

	public static final int OBJECT_NULL = 10_000;
	public static final int INSERT_OBJECT_ERROR = 10_001;
	public static final int BDD_ERROR = 10_002;
	public static final int DELETE_ERROR = 10_003;
	
	
}
