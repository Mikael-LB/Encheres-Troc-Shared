/**
 * 
 */
package fr.encheresnobyl.encherestroc.bll;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;

/**
 * @author mlebris2021
 * Interface which define the method available for ArticleVenduManager
 */
public interface ArticleVenduManagerInt {
	
	/**
	 * Method to ask the DAO to get a List of ArticleVendu
	 * which name is like the parameter String nom
	 * @param nom:String
	 * @return List<ArticleVendu>
	 */
	List<ArticleVendu> getByNom(String nom);
	
	/**
	 * Method to ask the DAO to get a List of ArticleVendu
	 * whose match with the 2 parameters
	 * @param nom:String
	 * @param noCategorie:int
	 * @return List<ArticleVendu>
	 */
	List<ArticleVendu> getByNomAndCategorie(String nom, String noCategorie);
}
