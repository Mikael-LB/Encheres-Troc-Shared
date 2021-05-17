/**
 * 
 */
package fr.encheresnobyl.encherestroc.dal;

import java.util.List;

import fr.encheresnobyl.encherestroc.bo.ArticleVendu;

/**
 * Interface en charge de définir les méthodes du DAO ArticleVendu
 * @author Pierre
 * @version Encheres-Troc - v1.0
 * @date 11 mai 2021 - 10:23:59
 */
public interface ArticleVenduDao {

	/**
	 * Méthode en charge de
	 * @param motCle
	 * @param noCategorie
	 * @param noUtilisateur
	 * @param parametres
	 * @return
	 */
	List<ArticleVendu> selectEncheres(String motCle, int noCategorie, int noUtilisateur, List<String> parametres);

	/**
	 * Méthode en charge de
	 * @param string
	 * @param i
	 * @return
	 */
	List<ArticleVendu> selectEncheres(String motCle, int noCategorie);
	
	List<ArticleVendu> selectVentes(String motCle, int noCategorie, int noUtilisateur, List<String> parametres);
	
	ArticleVendu selectArticleById(int id);


	
}



